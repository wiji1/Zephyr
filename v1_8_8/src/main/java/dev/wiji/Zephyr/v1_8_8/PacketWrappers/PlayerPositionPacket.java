package dev.wiji.Zephyr.v1_8_8.PacketWrappers;

import dev.wiji.Zephyr.compatibility.Exceptions.PacketParameterException;
import dev.wiji.Zephyr.v1_8_8.PacketEnums.LegacyTeleportFlags;
import dev.wiji.Zephyr.compatibility.ZPacket;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PlayerPositionPacket extends ZPacket {
    private List<Player> clients;
    private Location location;
    private Set<PacketPlayOutPosition.EnumPlayerTeleportFlags> flags;

    /*
     * LegacyTeleportFlags is a List of teleport modifiers. By default, the X, Y, Z, Pitch, and Yaw Values are constant
     * meaning it will teleport the Player to that location in the World. Adding the corresponding TeleportFlag will
     * instead make that variable relative. For example, with the X Flag and 2 as the Location's X value, they Player
     * will move from X=10 to X=12; as opposed to being moved to X=2.
     */

    public PlayerPositionPacket(Player client) {
        super(client);
        clients = new ArrayList<>();
        clients.add(client);
    }

    public PlayerPositionPacket(List<Player> clients) {
        super(clients);
        this.clients = clients;
    }

    public PlayerPositionPacket(Player client, Location location, List<LegacyTeleportFlags> modifiers) {
        super(client);
        clients = new ArrayList<>();
        clients.add(client);
        this.location = location;

        Set<PacketPlayOutPosition.EnumPlayerTeleportFlags> flagSet = new HashSet<>();
        for(int i = 0; i < modifiers.size(); i++) {
            LegacyTeleportFlags flag = modifiers.get(i);

            for(int j = 0; j < PacketPlayOutPosition.EnumPlayerTeleportFlags.values().length; j++) {
                PacketPlayOutPosition.EnumPlayerTeleportFlags flagEnum = PacketPlayOutPosition.EnumPlayerTeleportFlags.values()[j];
                if(i == j) {
                    flagSet.add(flagEnum);
                    break;
                }
            }
        }

        this.flags = flagSet;
    }

    public PlayerPositionPacket(List<Player> clients, Location location, List<LegacyTeleportFlags> modifiers) {
        super(clients);
        this.clients = clients;
        this.location = location;

        Set<PacketPlayOutPosition.EnumPlayerTeleportFlags> flagSet = new HashSet<>();
        for(int i = 0; i < modifiers.size(); i++) {
            LegacyTeleportFlags flag = modifiers.get(i);

            for(int j = 0; j < PacketPlayOutPosition.EnumPlayerTeleportFlags.values().length; j++) {
                PacketPlayOutPosition.EnumPlayerTeleportFlags flagEnum = PacketPlayOutPosition.EnumPlayerTeleportFlags.values()[j];
                if(i == j) {
                    flagSet.add(flagEnum);
                    break;
                }
            }
        }

        this.flags = flagSet;
    }

    public PlayerPositionPacket(Player client, Location location) {
        super(client);
        clients = new ArrayList<>();
        clients.add(client);
        this.location = location;
        this.flags = new HashSet<>();
    }

    public PlayerPositionPacket(List<Player> clients, Location location) {
        super(clients);
        this.clients = clients;
        this.location = location;
        this.flags = new HashSet<>();
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setModifiers(List<LegacyTeleportFlags> modifiers) {
        Set<PacketPlayOutPosition.EnumPlayerTeleportFlags> flagSet = new HashSet<>();
        for(int i = 0; i < modifiers.size(); i++) {
            LegacyTeleportFlags flag = modifiers.get(i);

            for(int j = 0; j < PacketPlayOutPosition.EnumPlayerTeleportFlags.values().length; j++) {
                PacketPlayOutPosition.EnumPlayerTeleportFlags flagEnum = PacketPlayOutPosition.EnumPlayerTeleportFlags.values()[j];
                if(i == j) {
                    flagSet.add(flagEnum);
                    break;
                }
            }
        }

        this.flags = flagSet;
    }

    public Location getLocation() {
        return location;
    }

    public List<LegacyTeleportFlags> getModifiers() {
        List<LegacyTeleportFlags> modifiers = new ArrayList<>();
        for(PacketPlayOutPosition.EnumPlayerTeleportFlags flag : flags) {
            for(int i = 0; i < PacketPlayOutPosition.EnumPlayerTeleportFlags.values().length; i++) {
                PacketPlayOutPosition.EnumPlayerTeleportFlags flagEnum = PacketPlayOutPosition.EnumPlayerTeleportFlags.values()[i];
                if(flag == flagEnum) {
                    modifiers.add(LegacyTeleportFlags.values()[i]);
                    break;
                }
            }
        }
        return modifiers;
    }

    @Override
    public Object getPacket() {
        return new PacketPlayOutPosition(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch(), flags);
    }

    @Override
    public void sendPacket() throws PacketParameterException {
        List<Field> fields = new ArrayList<>();
        try {
            fields.add(this.getClass().getDeclaredField("location"));
            fields.add(this.getClass().getDeclaredField("flags"));
        } catch(NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
        if(location == null || flags == null) throw new PacketParameterException(fields);


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
