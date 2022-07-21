package dev.wiji.Zephyr;


import dev.wiji.Zephyr.compatibility.NMSHelper;
import dev.wiji.Zephyr.compatibility.ZPacket;
import dev.wiji.Zephyr.v1_19.NMSv1_19;
import dev.wiji.Zephyr.v1_8_8.NMSv1_8_8;
import dev.wiji.Zephyr.v1_8_8.PacketWrappers.KickDisconnectPacket;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Zephyr extends JavaPlugin implements Listener {

    protected static JavaPlugin INSTANCE;
    protected static NMSHelper NMS;

    public static void init(JavaPlugin plugin) {
        INSTANCE = plugin;

        String version = Bukkit.getVersion();
        System.out.println(version);

        if(version.contains("1.8.8")) NMS = new NMSv1_8_8();
        if(version.contains("1.19")) NMS = new NMSv1_19();
    }

    public static String getVersion() {
        return Bukkit.getVersion();
    }

    public static int getAbsorption(Player player) {
        return NMS.getAbsorption(player);
    }

    public static void sendTitle(Player player, String message, int time) {
        NMS.sendTitle(player, message, time);
    }

}
