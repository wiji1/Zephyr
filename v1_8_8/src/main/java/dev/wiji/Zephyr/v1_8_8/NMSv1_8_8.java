package dev.wiji.Zephyr.v1_8_8;

import dev.wiji.Zephyr.compatibility.NMSHelper;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class NMSv1_8_8 extends NMSHelper {

    @Override
    public int getAbsorption(Player player) {
        EntityLiving el = ((CraftPlayer)player).getHandle();
        return (int) el.getAbsorptionHearts();
    }

    @Override
    public void sendTitle(Player player, String message, int length) {
        IChatBaseComponent chatTitle = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" +
                ChatColor.translateAlternateColorCodes('&', message) + "\"}");

        PacketPlayOutTitle title = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, chatTitle);
        PacketPlayOutTitle titleLength = new PacketPlayOutTitle(5, length, 5);


        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(title);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(title);
    }



}