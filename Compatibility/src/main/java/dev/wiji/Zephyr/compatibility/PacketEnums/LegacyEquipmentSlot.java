package dev.wiji.Zephyr.compatibility.PacketEnums;

public enum LegacyEquipmentSlot {
    HELD_ITEM((byte) 0),
    BOOTS((byte) 1),
    LEGGINGS((byte) 2),
    CHESTPLATE((byte) 3),
    HELMET((byte) 4);

    private final byte position;

    LegacyEquipmentSlot(byte position) {
        this.position = position;
    }

    public byte getPosition() {
        return position;
    }

}
