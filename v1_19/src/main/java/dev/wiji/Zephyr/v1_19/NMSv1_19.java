package dev.wiji.Zephyr.v1_19;

import dev.wiji.Zephyr.compatibility.NMSHelper;
import org.bukkit.entity.Player;

public class NMSv1_19 extends NMSHelper {

    @Override
    public int getAbsorption(Player player) {
        return (int) player.getAbsorptionAmount();
    }

    @Override
    public void sendTitle(Player player, String message, int ticks) {
        player.sendTitle(message, message, 10, ticks, 10);
    }
}