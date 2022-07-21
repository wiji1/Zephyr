package dev.wiji.Zephyr.v1_8_8.PacketWrappers;

import dev.wiji.Zephyr.compatibility.Exceptions.PacketParameterException;
import dev.wiji.Zephyr.compatibility.ZPacket;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class HeldItemChangePacket extends ZPacket {
    private List<Player> clients;
    private Byte slot;

    //Slots go from 0 to 8

    public HeldItemChangePacket(Player client) {
        super(client);
        clients = new ArrayList<>();
        clients.add(client);
    }

    public HeldItemChangePacket(List<Player> clients) {
        super(clients);
        this.clients = clients;
    }

    public HeldItemChangePacket(Player client, byte slot) {
        super(client);
        clients = new ArrayList<>();
        clients.add(client);
        this.slot = slot;
    }

    public HeldItemChangePacket(List<Player> clients, byte slot) {
        super(clients);
        this.clients = clients;
        this.slot = slot;
    }

    public void setSlot(byte slot) {
        this.slot = slot;
    }

    public byte getSlot() {
        return slot;
    }

    @Override
    public Object getPacket() {
        return new PacketPlayOutHeldItemSlot(slot);
    }

    @Override
    public void sendPacket() throws PacketParameterException {
        List<Field> fields = new ArrayList<>();
        try {
            fields.add(this.getClass().getDeclaredField("slot"));
        } catch(NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
        if(slot == null) throw new PacketParameterException(fields);


        for(Player client : clients) {
            EntityPlayer nmsPlayer = ((CraftPlayer) client).getHandle();
            nmsPlayer.playerConnection.sendPacket((Packet<?>) getPacket());
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
