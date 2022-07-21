package dev.wiji.Zephyr.v1_8_8.PacketWrappers;

import dev.wiji.Zephyr.compatibility.Exceptions.PacketParameterException;
import dev.wiji.Zephyr.compatibility.ZPacket;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class UpdateHealthPacket extends ZPacket {
    private List<Player> clients;
    private Float health;
    private Integer hunger;
    private Float saturation;

    /* Health: 0 or less = dead, 20 = full HP
     * Hunger: 0–20
     * Saturation: Seems to vary from 0.0 to 5.0 in integer increments
     */

    public UpdateHealthPacket(Player client) {
        super(client);
        clients = new ArrayList<>();
        clients.add(client);
    }

    public UpdateHealthPacket(List<Player> clients) {
        super(clients);
        this.clients = clients;
    }

    public UpdateHealthPacket(Player client, float health, int hunger, float saturation) {
        super(client);
        clients = new ArrayList<>();
        clients.add(client);
        this.health = health;
        this.hunger = hunger;
        this.saturation = saturation;
    }

    public UpdateHealthPacket(List<Player> clients, float health, int hunger, float saturation) {
        super(clients);
        this.clients = clients;
        this.health = health;
        this.hunger = hunger;
        this.saturation = saturation;
    }

    public void setHealth(float health) {
        this.health = health;
    }

    public void setHunger(int hunger) {
        this.hunger = hunger;
    }

    public void setSaturation(float saturation) {
        this.saturation = saturation;
    }

    public float getHealth() {
        return health;
    }

    public int getHunger() {
        return hunger;
    }

    public float getSaturation() {
        return saturation;
    }

    @Override
    public Object getPacket() {
        return new PacketPlayOutUpdateHealth(health, hunger, saturation);
    }

    @Override
    public void sendPacket() throws PacketParameterException {
        List<Field> fields = new ArrayList<>();
        try {
            fields.add(this.getClass().getDeclaredField("health"));
            fields.add(this.getClass().getDeclaredField("hunger"));
            fields.add(this.getClass().getDeclaredField("saturation"));
        } catch(NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
        if(health == null || hunger == null || saturation == null) throw new PacketParameterException(fields);


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
