package dev.wiji.Zephyr.compatibility.PacketEnums;

public enum LegacyTeleportFlags {

    /*
     * LegacyTeleportFlags is a List of teleport modifiers. By default, the X, Y, Z, Pitch, and Yaw Values are constant
     * meaning it will teleport the Player to that location in the World. Adding the corresponding TeleportFlag will
     * instead make that variable relative. For example, with the X Flag and 2 as the Location's X value, they Player
     * will move from X=10 to X=12; as opposed to being moved to X=2.
     */


    X(0),
    Y(1),
    Z(2),
    YAW(3),
    PITCH(4);


    private final int position;

    LegacyTeleportFlags(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

}
