package dev.wiji.Zephyr.v1_19.PacketWrappers;

import dev.wiji.Zephyr.compatibility.Exceptions.PacketParameterException;
import dev.wiji.Zephyr.compatibility.PacketEnums.ModernAnimation;
import dev.wiji.Zephyr.compatibility.ZPacket;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.PacketPlayOutAnimation;
import net.minecraft.server.level.EntityPlayer;
import org.bukkit.craftbukkit.v1_19_R1.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_19_R1.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class EntityAnimationPacket extends ZPacket {
    private List<Player> clients;
    private Entity entity;
    private ModernAnimation animation;


    public EntityAnimationPacket(Player client) {
        super(client);
        clients = new ArrayList<>();
        clients.add(client);
    }

    public EntityAnimationPacket(List<Player> clients) {
        super(clients);
        this.clients = clients;
    }

    public EntityAnimationPacket(Player client, Entity entity, ModernAnimation animation) {
        super(client);
        clients = new ArrayList<>();
        clients.add(client);
        this.entity = entity;
        this.animation = animation;
    }

    public EntityAnimationPacket(List<Player> clients, Entity entity, ModernAnimation animation) {
        super(clients);
        this.clients = clients;
        this.entity = entity;
        this.animation = animation;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public void setAnimation(ModernAnimation animation) {
        this.animation = animation;
    }

    public Entity getEntity() {
        return entity;
    }

    public ModernAnimation getAnimation() {
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
