package dev.wiji.Zephyr.v1_19.PacketWrappers;

import com.mojang.datafixers.util.Pair;
import dev.wiji.Zephyr.compatibility.Exceptions.PacketParameterException;
import dev.wiji.Zephyr.v1_19.PacketEnums.ModernEquipmentSlot;
import dev.wiji.Zephyr.compatibility.ZPacket;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.PacketPlayOutEntityEquipment;
import net.minecraft.server.level.EntityPlayer;
import net.minecraft.world.entity.EnumItemSlot;
import net.minecraft.world.item.ItemStack;
import org.bukkit.craftbukkit.v1_19_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_19_R1.inventory.CraftItemStack;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class EntityEquipmentPacket extends ZPacket {
    private List<Player> clients;
    private Integer entityID;
    private List<Pair<EnumItemSlot, ItemStack>> equipment;

    public EntityEquipmentPacket(Player client) {
        super(client);
        clients = new ArrayList<>();
        clients.add(client);
    }

    public EntityEquipmentPacket(List<Player> clients) {
        super(clients);
        this.clients = clients;
    }

    public EntityEquipmentPacket(Player client, int entityID, List<Pair<ModernEquipmentSlot, org.bukkit.inventory.ItemStack>> equipment) {
        super(client);
        clients = new ArrayList<>();
        clients.add(client);
        this.entityID = entityID;
        this.equipment = new ArrayList<>();

        for(Pair<ModernEquipmentSlot, org.bukkit.inventory.ItemStack> pair : equipment) {
            this.equipment.add(new Pair<>(EnumItemSlot.a(pair.getFirst().getName()), CraftItemStack.asNMSCopy(pair.getSecond())));
        }
    }

    public EntityEquipmentPacket(List<Player> clients, int entityID, List<Pair<ModernEquipmentSlot, org.bukkit.inventory.ItemStack>> equipment) {
        super(clients);
        this.clients = clients;
        this.entityID = entityID;
        this.equipment = new ArrayList<>();

        for(Pair<ModernEquipmentSlot, org.bukkit.inventory.ItemStack> pair : equipment) {
            this.equipment.add(new Pair<>(EnumItemSlot.a(pair.getFirst().getName()), CraftItemStack.asNMSCopy(pair.getSecond())));
        }
    }

    public EntityEquipmentPacket(Player client, int entityID, ModernEquipmentSlot equipmentSlot, org.bukkit.inventory.ItemStack item) {
        super(client);
        clients = new ArrayList<>();
        clients.add(client);
        this.entityID = entityID;
        this.equipment = new ArrayList<>();
        this.equipment.add(new Pair<>(EnumItemSlot.a(equipmentSlot.getName()), CraftItemStack.asNMSCopy(item)));
    }

    public EntityEquipmentPacket(List<Player> clients, int entityID, ModernEquipmentSlot equipmentSlot, org.bukkit.inventory.ItemStack item) {
        super(clients);
        this.clients = clients;
        this.entityID = entityID;
        this.equipment = new ArrayList<>();
        this.equipment.add(new Pair<>(EnumItemSlot.a(equipmentSlot.getName()), CraftItemStack.asNMSCopy(item)));
    }

    public void setEntityID(int entityID) {
        this.entityID = entityID;
    }

    public void setEquipment(List<Pair<ModernEquipmentSlot, org.bukkit.inventory.ItemStack>> equipment) {
        this.equipment = new ArrayList<>();

        for(Pair<ModernEquipmentSlot, org.bukkit.inventory.ItemStack> pair : equipment) {
            this.equipment.add(new Pair<>(EnumItemSlot.a(pair.getFirst().getName()), CraftItemStack.asNMSCopy(pair.getSecond())));
        }
    }

    public void setEquipment(ModernEquipmentSlot equipmentSlot, org.bukkit.inventory.ItemStack item) {
        this.equipment = new ArrayList<>();
        this.equipment.add(new Pair<>(EnumItemSlot.a(equipmentSlot.getName()), CraftItemStack.asNMSCopy(item)));
    }

    public int getEntityID() {
        return entityID;
    }

    public List<Pair<ModernEquipmentSlot, org.bukkit.inventory.ItemStack>> getEquipment() {
        List<Pair<ModernEquipmentSlot, org.bukkit.inventory.ItemStack>> equipment = new ArrayList<>();

        for(Pair<EnumItemSlot, ItemStack> pair : this.equipment) {
            equipment.add(new Pair<>(ModernEquipmentSlot.fromName(pair.getFirst().name()), CraftItemStack.asCraftMirror(pair.getSecond())));
        }
        return equipment;
    }

    public ModernEquipmentSlot getEquipmentSlot() {
        return ModernEquipmentSlot.fromName(this.equipment.get(0).getFirst().name());
    }

    public org.bukkit.inventory.ItemStack getItem() {
        return CraftItemStack.asCraftMirror(this.equipment.get(0).getSecond());
    }

    @Override
    public Object getPacket() {
        return new PacketPlayOutEntityEquipment(entityID, equipment);
    }

    @Override
    public void sendPacket() throws PacketParameterException {
        List<Field> fields = new ArrayList<>();
        try {
            fields.add(this.getClass().getDeclaredField("entityID"));
            fields.add(this.getClass().getDeclaredField("equipment"));
        } catch(NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
        if(entityID == null || equipment == null) {
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
