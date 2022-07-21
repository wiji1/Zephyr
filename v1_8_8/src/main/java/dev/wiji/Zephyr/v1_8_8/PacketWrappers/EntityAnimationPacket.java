package dev.wiji.Zephyr.v1_8_8.PacketWrappers;

import dev.wiji.Zephyr.compatibility.Exceptions.PacketParameterException;
import dev.wiji.Zephyr.compatibility.PacketEnums.LegacyAnimation;
import dev.wiji.Zephyr.compatibility.ZPacket;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class EntityAnimationPacket extends ZPacket {
    private List<Player> clients;
    private org.bukkit.entity.Entity entity;
    private LegacyAnimation animation;


    public EntityAnimationPacket(Player client) {
        super(client);
        clients = new ArrayList<>();
        clients.add(client);
    }

    public EntityAnimationPacket(List<Player> clients) {
        super(clients);
        this.clients = clients;
    }

    public EntityAnimationPacket(Player client, org.bukkit.entity.Entity entity, LegacyAnimation animation) {
        super(client);
        clients = new ArrayList<>();
        clients.add(client);
        this.entity = entity;
        this.animation = animation;
    }

    public EntityAnimationPacket(List<Player> clients, org.bukkit.entity.Entity entity, LegacyAnimation animation) {
        super(clients);
        this.clients = clients;
        this.entity = entity;
        this.animation = animation;
    }

    public void setEntity(org.bukkit.entity.Entity entity) {
        this.entity = entity;
    }

    public void setAnimation(LegacyAnimation animation) {
        this.animation = animation;
    }

    public Entity getEntity() {
        return entity;
    }

    public LegacyAnimation getAnimation() {
        return animation;
    }

    @Override
    public Object getPacket() {
        return new PacketPlayOutAnimation(((CraftEntity) entity).getHandle(), animation.getPosition());
    }

    @Override
    public void sendPacket() throws PacketParameterException {
        List<Field> fields = new ArrayList<>();
        try {
            fields.add(this.getClass().getDeclaredField("entity"));
            fields.add(this.getClass().getDeclaredField("animation"));
        } catch(NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
        if(entity == null || animation == null) throw new PacketParameterException(fields);


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
