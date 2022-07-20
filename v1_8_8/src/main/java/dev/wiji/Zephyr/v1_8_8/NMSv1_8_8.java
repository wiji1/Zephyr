package dev.wiji.Zephyr.v1_8_8;

import dev.wiji.Zephyr.compatibility.NMSHelper;
import net.minecraft.server.v1_8_R3.EntityLiving;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class NMSv1_8_8 extends NMSHelper {

    @Override
    public int getAbsorption(Player player) {
        EntityLiving el = ((CraftPlayer)player).getHandle();
        return (int) el.getAbsorptionHearts();
    }
}