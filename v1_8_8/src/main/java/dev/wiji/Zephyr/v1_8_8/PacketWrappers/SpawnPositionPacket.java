package dev.wiji.Zephyr.v1_8_8.PacketWrappers;

import dev.wiji.Zephyr.compatibility.Exceptions.PacketParameterException;
import dev.wiji.Zephyr.compatibility.ZPacket;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class SpawnPositionPacket extends ZPacket {
    private List<Player> clients;
    private BlockPosition location;


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
        this.location = new BlockPosition(location.getX(), location.getY(), location.getZ());
    }

    public SpawnPositionPacket(List<Player> clients, Location location) {
        super(clients);
        this.clients = clients;
        this.location = new BlockPosition(location.getX(), location.getY(), location.getZ());
    }

    public void setLocation(Location location) {
        this.location = new BlockPosition(location.getX(), location.getY(), location.getZ());
    }

    public Location getLocation() {
        return new Location(Bukkit.getWorlds().get(0), location.getX(), location.getY(), location.getZ());
    }

    @Override
    public Object getPacket() {
        return new PacketPlayOutSpawnPosition(location);
    }

    @Override
    public void sendPacket() throws PacketParameterException {
        List<Field> fields = new ArrayList<>();
        try {
            fields.add(this.getClass().getDeclaredField("location"));
        } catch(NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
        if(location == null) throw new PacketParameterException(fields);


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
