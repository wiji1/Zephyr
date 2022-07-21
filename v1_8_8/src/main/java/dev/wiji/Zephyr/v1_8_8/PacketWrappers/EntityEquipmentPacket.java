package dev.wiji.Zephyr.v1_8_8.PacketWrappers;

import dev.wiji.Zephyr.compatibility.Exceptions.PacketParameterException;
import dev.wiji.Zephyr.compatibility.PacketEnums.LegacyEquipmentSlot;
import dev.wiji.Zephyr.compatibility.ZPacket;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class EntityEquipmentPacket extends ZPacket {
    private List<Player> clients;
    private int entityID;
    private LegacyEquipmentSlot equipmentSlot;
    private ItemStack item;


    public EntityEquipmentPacket(Player client) {
        super(client);
        clients = new ArrayList<>();
        clients.add(client);
    }

    public EntityEquipmentPacket(List<Player> clients) {
        super(clients);
        this.clients = clients;
    }

    public EntityEquipmentPacket(Player client, int entityID, LegacyEquipmentSlot equipmentSlot, org.bukkit.inventory.ItemStack item) {
        super(client);
        clients = new ArrayList<>();
        clients.add(client);
        this.entityID = entityID;
        this.equipmentSlot = equipmentSlot;
        this.item = CraftItemStack.asNMSCopy(item);
    }

    public EntityEquipmentPacket(List<Player> clients, int entityID, LegacyEquipmentSlot equipmentSlot, org.bukkit.inventory.ItemStack item) {
        super(clients);
        this.clients = clients;
        this.entityID = entityID;
        this.equipmentSlot = equipmentSlot;
        this.item = CraftItemStack.asNMSCopy(item);
    }

    public void setEntityID(int entityID) {
        this.entityID = entityID;
    }

    public void setEquipmentSlot(LegacyEquipmentSlot equipmentSlot) {
        this.equipmentSlot = equipmentSlot;
    }

    public void setItem(org.bukkit.inventory.ItemStack item) {
        this.item = CraftItemStack.asNMSCopy(item);
    }

    public int getEntityID() {
        return entityID;
    }

    public LegacyEquipmentSlot getEquipmentSlot() {
        return equipmentSlot;
    }

    public org.bukkit.inventory.ItemStack getItem() {
        return CraftItemStack.asCraftMirror(item);
    }

    @Override
    public Object getPacket() {
        return new PacketPlayOutEntityEquipment(entityID, equipmentSlot.getPosition(), item);
    }

    @Override
    public void sendPacket() throws PacketParameterException {
        List<Field> fields = new ArrayList<>();
        try {
            fields.add(this.getClass().getDeclaredField("entityID"));
            fields.add(this.getClass().getDeclaredField("equipmentSlot"));
            fields.add(this.getClass().getDeclaredField("item"));
        } catch(NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
        if(entityID == 0 || equipmentSlot == null || item == null) {
            throw new PacketParameterException(fields);
        }


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
