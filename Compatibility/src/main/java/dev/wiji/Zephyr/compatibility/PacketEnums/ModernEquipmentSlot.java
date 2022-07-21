package dev.wiji.Zephyr.compatibility.PacketEnums;

public enum ModernEquipmentSlot {
    HELD_ITEM((byte) 0, "mainhand"),
    OFF_HAND((byte) 1, "offhand"),
    BOOTS((byte) 2, "feet"),
    LEGGINFS((byte) 3, "legs"),
    CHESTPLAYE((byte) 4, "chest"),
    HELMET((byte) 5, "head");

    private final byte position;
    private final String equipmentName;

    ModernEquipmentSlot(byte position, String equipmentName) {
        this.position = position;
        this.equipmentName = equipmentName;
    }

    public byte getPosition() {
        return position;
    }

    public String getName() {
        return equipmentName;
    }

    public static ModernEquipmentSlot fromName(String name) {
        for(ModernEquipmentSlot value : values()) {
            if(value.getName().equals(name)) return value;
        }
        return null;
    }

}
