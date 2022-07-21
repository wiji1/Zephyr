package dev.wiji.Zephyr.v1_19.PacketWrappers;

import dev.wiji.Zephyr.compatibility.Exceptions.PacketParameterException;
import dev.wiji.Zephyr.compatibility.ZPacket;
import dev.wiji.Zephyr.v1_19.PacketEnums.ModernEntityType;
import net.minecraft.core.Position;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.PacketPlayOutSpawnEntity;
import net.minecraft.server.level.EntityPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.World;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_19_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_19_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class SpawnEntityPacket extends ZPacket {
    private List<Player> clients;
    private ModernEntityType entityType;
    private Location location;


    public SpawnEntityPacket(Player client) {
        super(client);
        clients = new ArrayList<>();
        clients.add(client);
    }

    public SpawnEntityPacket(List<Player> clients) {
        super(clients);
        this.clients = clients;
    }

    public SpawnEntityPacket(Player client, ModernEntityType entityType, Location location) {
        super(client);
        clients = new ArrayList<>();
        clients.add(client);
        this.entityType = entityType;
        this.location = location;
    }

    public SpawnEntityPacket(List<Player> clients, ModernEntityType entityType, Location location) {
        super(clients);
        this.clients = clients;
        this.entityType = entityType;
        this.location = location;
    }

    public void setEntityType(ModernEntityType entityType) {
        this.entityType = entityType;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public ModernEntityType getEntityType() {
        return entityType;
    }

    public Location getLocation() {
        return location;
    }

    @Override
    public Object getPacket() {
        Entity entity;

        try {
            Constructor[] constructors = entityType.getEntityClass().getDeclaredConstructors();
            Constructor constructor = constructors[0];
            constructor.setAccessible(true);

            World world = ((CraftWorld) location.getWorld()).getHandle();

            constructor.newInstance(world);
            entity = entityType.getEntityClass().newInstance();

        } catch(InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }

        entity.teleportTo(((CraftWorld) location.getWorld()).getHandle(), new Position(location.getX(), location.getY(), location.getZ()));
        return new PacketPlayOutSpawnEntity(entity, 0);
    }

    @Override
    public void sendPacket() throws PacketParameterException {
        List<Field> fields = new ArrayList<>();
        try {
            fields.add(this.getClass().getDeclaredField("entityType"));
            fields.add(this.getClass().getDeclaredField("location"));
        } catch(NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
        if(entityType == null || location == null) throw new PacketParameterException(fields);


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
