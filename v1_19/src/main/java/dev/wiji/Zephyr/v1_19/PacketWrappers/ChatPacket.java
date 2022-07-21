package dev.wiji.Zephyr.v1_19.PacketWrappers;

import dev.wiji.Zephyr.compatibility.Exceptions.PacketParameterException;
import dev.wiji.Zephyr.compatibility.PacketEnums.LegacyChatType;
import dev.wiji.Zephyr.compatibility.PacketEnums.ModernChatType;
import dev.wiji.Zephyr.compatibility.ZPacket;
import net.minecraft.network.chat.IChatBaseComponent;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundPlayerChatPacket;
import net.minecraft.network.protocol.game.ClientboundSystemChatPacket;
import net.minecraft.server.level.EntityPlayer;
import org.bukkit.craftbukkit.v1_19_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ChatPacket extends ZPacket {
    private List<Player> clients;
    private String message;
    private ModernChatType chatType;


    public ChatPacket(Player client) {
        super(client);
        clients = new ArrayList<>();
        clients.add(client);
    }

    public ChatPacket(List<Player> clients) {
        super(clients);
        this.clients = clients;
    }

    public ChatPacket(Player client, String message, ModernChatType chatType) {
        super(client);
        clients = new ArrayList<>();
        clients.add(client);
        this.message = message;
        this.chatType = chatType;
    }

    public ChatPacket(List<Player> clients, String message, ModernChatType chatType) {
        super(clients);
        this.clients = clients;
        this.message = message;
        this.chatType = chatType;
    }

    public void setMessage(String reason) {
        this.message = message;
    }

    public void setChatType(ModernChatType chatType) {
        this.chatType = chatType;
    }

    public String getMessage() {
        return message;
    }

    public ModernChatType getChatType() {
        return chatType;
    }

    @Override
    public Object getPacket() {
        IChatBaseComponent baseComponent = IChatBaseComponent.ChatSerializer.a(message);
        return new ClientboundSystemChatPacket(baseComponent, chatType.getPosition());
    }

    @Override
    public void sendPacket() throws PacketParameterException {
        List<Field> fields = new ArrayList<>();
        try {
            fields.add(this.getClass().getDeclaredField("message"));
            fields.add(this.getClass().getDeclaredField("chatType"));
        } catch(NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
        if(message == null || chatType == null) throw new PacketParameterException(fields);


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
