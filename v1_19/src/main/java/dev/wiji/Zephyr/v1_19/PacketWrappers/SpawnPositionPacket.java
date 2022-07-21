package dev.wiji.Zephyr.v1_19.PacketWrappers;

import dev.wiji.Zephyr.compatibility.Exceptions.PacketParameterException;
import dev.wiji.Zephyr.compatibility.ZPacket;
import net.minecraft.core.BlockPosition;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.PacketPlayOutSpawnPosition;
import net.minecraft.server.level.EntityPlayer;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_19_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class SpawnPositionPacket extends ZPacket {
    private List<Player> clients;
    private Location location;
    private Float pitch;


    public SpawnPositionPacket(Player client) {
        super(client);
        clients = new ArrayList<>();
        clients.add(client);
    }

    public SpawnPositionPacket(List<Player> clients) {
        super(clients);
        this.clients = clients;
    }

    public SpawnPositionPacket(Player client, Location location) {
        super(client);
        clients = new ArrayList<>();
        clients.add(client);
        this.location = location;
        this.pitch = location.getPitch();
    }

    public SpawnPositionPacket(List<Player> clients, Location location) {
        super(clients);
        this.clients = clients;
        this.location = location;
        this.pitch = location.getPitch();
    }

    public SpawnPositionPacket(Player client, Location location, float pitch) {
        super(client);
        clients = new ArrayList<>();
        clients.add(client);
        this.location = location;
        this.pitch = pitch;
    }

    public SpawnPositionPacket(List<Player> clients, Location location, float pitch) {
        super(clients);
        this.clients = clients;
        this.location = location;
        this.pitch = pitch;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    public Location getLocation() {
        return location;
    }

    public float getPitch() {
        return pitch;
    }

    @Override
    public Object getPacket() {
        BlockPosition blockPosition = new BlockPosition(location.getX(), location.getY(), location.getZ());
        return new PacketPlayOutSpawnPosition(blockPosition, pitch);
    }

    @Override
    public void sendPacket() throws PacketParameterException {
        List<Field> fields = new ArrayList<>();
        try {
            fields.add(this.getClass().getDeclaredField("location"));
            fields.add(this.getClass().getDeclaredField("pitch"));
        } catch(NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
        if(location == null || pitch == null) throw new PacketParameterException(fields);


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
