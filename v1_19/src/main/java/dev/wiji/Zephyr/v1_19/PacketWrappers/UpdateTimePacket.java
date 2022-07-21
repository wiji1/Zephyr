package dev.wiji.Zephyr.v1_19.PacketWrappers;

import dev.wiji.Zephyr.compatibility.Exceptions.PacketParameterException;
import dev.wiji.Zephyr.compatibility.ZPacket;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.PacketPlayOutUpdateTime;
import net.minecraft.server.level.EntityPlayer;
import org.bukkit.craftbukkit.v1_19_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class UpdateTimePacket extends ZPacket {
    private List<Player> clients;
    private long worldAge;
    private long time;


    public UpdateTimePacket(Player client) {
        super(client);
        clients = new ArrayList<>();
        clients.add(client);
    }

    public UpdateTimePacket(List<Player> clients) {
        super(clients);
        this.clients = clients;
    }

    public UpdateTimePacket(Player client, long worldAge, long time) {
        super(client);
        clients = new ArrayList<>();
        clients.add(client);
        this.worldAge = worldAge;
        this.time = time;
    }

    public UpdateTimePacket(List<Player> clients, long worldAge, long time) {
        super(clients);
        this.clients = clients;
        this.worldAge = worldAge;
        this.time = time;
    }

    public void setWorldAge(long worldAge) {
        this.worldAge = worldAge;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getWorldAge() {
        return worldAge;
    }

    public long getTime() {
        return time;
    }

    @Override
    public Object getPacket() {
        return new PacketPlayOutUpdateTime(worldAge, time, true);
    }

    @Override
    public void sendPacket() throws PacketParameterException {
        List<Field> fields = new ArrayList<>();
        try {
            fields.add(this.getClass().getDeclaredField("worldAge"));
            fields.add(this.getClass().getDeclaredField("time"));
        } catch(NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
        if(worldAge == 0 || time == 0) {
            throw new PacketParameterException(fields);
        }


        for(Player client : clients) {
            EntityPlayer nmsPlayer = ((CraftPlayer) client).getHandle();
            nmsPlayer.b.a((Packet<?>) getPacket());
        }
    }

    @Override
    public Player getPlayer() {
        return clients.get(0);
    }

    @Override
    public List<Player> getPlayers() {
        return clients;
    }

    @Override
    public void setPlayer(Player player) {
        clients.clear();
        clients.add(player);
    }

    @Override
    public void setPlayers(List<Player> players) {
        clients = players;
    }
}
