package dev.wiji.Zephyr.compatibility;

import dev.wiji.Zephyr.compatibility.Exceptions.PacketParameterException;
import org.bukkit.entity.Player;

import java.util.List;

public abstract class ZPacket {

    public ZPacket(Player client) {

    }

    public ZPacket(List<Player> clients) {

    }

    public abstract Object getPacket();

    public abstract void sendPacket() throws PacketParameterException;

    public abstract Player getPlayer();

    public abstract List<Player> getPlayers();

}
