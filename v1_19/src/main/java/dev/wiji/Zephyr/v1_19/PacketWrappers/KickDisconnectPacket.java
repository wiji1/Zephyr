package dev.wiji.Zephyr.v1_19.PacketWrappers;

import dev.wiji.Zephyr.compatibility.Exceptions.PacketParameterException;
import dev.wiji.Zephyr.compatibility.ZPacket;
import net.minecraft.network.chat.IChatBaseComponent;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.PacketPlayOutKickDisconnect;
import net.minecraft.server.level.EntityPlayer;
import org.bukkit.craftbukkit.v1_19_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_19_R1.util.CraftChatMessage;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class KickDisconnectPacket extends ZPacket {
    private List<Player> clients;
    private String reason;


    public KickDisconnectPacket(Player client) {
        super(client);
        clients = new ArrayList<>();
        clients.add(client);
    }

    public KickDisconnectPacket(List<Player> clients) {
        super(clients);
        this.clients = clients;
    }

    public KickDisconnectPacket(Player client, String reason) {
        super(client);
        clients = new ArrayList<>();
        clients.add(client);
        this.reason = reason;
    }

    public KickDisconnectPacket(List<Player> clients, String reason) {
        super(clients);
        this.clients = clients;
        this.reason = reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }

    @Override
    public Object getPacket() {
        IChatBaseComponent[] baseComponent = CraftChatMessage.fromString(reason);

        return new PacketPlayOutKickDisconnect(baseComponent[0]);
    }

    @Override
    public void sendPacket() throws PacketParameterException {
        List<Field> fields = new ArrayList<>();
        try {
            fields.add(this.getClass().getDeclaredField("reason"));
        } catch(NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
        if(reason == null) {
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
}
