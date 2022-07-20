package dev.wiji.Zephyr.compatibility;

import org.bukkit.entity.Player;

public abstract class NMSHelper {

    public abstract int getAbsorption(Player player);

    public abstract void sendTitle(Player player, String message, int ticks);
}
