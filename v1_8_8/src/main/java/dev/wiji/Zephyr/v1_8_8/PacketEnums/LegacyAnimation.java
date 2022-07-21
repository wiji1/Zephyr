package dev.wiji.Zephyr.v1_8_8.PacketEnums;

public enum LegacyAnimation {
    SWING_ARM((byte) 0),
    TAKE_DAMAGE((byte) 1),
    LEAVE_BED((byte) 2),
    EAT_FOOD((byte) 3),
    CRITICAL_EFFECT((byte) 4),
    MAGIC_CRITICAL_EFFECT((byte) 5);


    private final byte position;

    LegacyAnimation(byte position) {
        this.position = position;
    }

    public byte getPosition() {
        return position;
    }

}
