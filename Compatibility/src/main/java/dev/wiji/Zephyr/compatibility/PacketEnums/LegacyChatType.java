package dev.wiji.Zephyr.compatibility.PacketEnums;

public enum LegacyChatType {
    CHAT_BOX((byte) 0),
    SYSTEM_MESSAGE((byte) 1),
    ACTION_BAR((byte) 2);

    private final byte position;

    LegacyChatType(byte position) {
        this.position = position;
    }

    public byte getPosition() {
        return position;
    }

}
