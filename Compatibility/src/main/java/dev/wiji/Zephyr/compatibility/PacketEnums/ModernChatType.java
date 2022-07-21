package dev.wiji.Zephyr.compatibility.PacketEnums;

public enum ModernChatType {
    CHAT_MESSAGE((byte) 0),
    SYSTEM_MESSAGE((byte) 1),
    ACTION_BAR((byte) 2),
    SAY_COMMAND((byte) 3),
    MSG_COMMAND((byte) 4),
    TEAM_MSG_COMMAND((byte) 5),
    EMOTE_COMMAND((byte) 6),
    TELLRAW_COMMAND((byte) 7);

    private final byte position;

    ModernChatType(byte position) {
        this.position = position;
    }

    public byte getPosition() {
        return position;
    }

}
