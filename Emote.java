public enum Emote {
    WALL1("\u26f0\ufe0f\u26f0\ufe0f"),
    WALL2("\u26f0\ufe0f"),
    WALL3("\ud83c\udf0b\ud83c\udf0b"),
    WALL4(" \ud83c\udf0b"),
    PLAYER(" @"),
    FLOOR(" ."),
    LAVA("\ud83d\udd25\ud83d\udd25"),
    CANDY(" \ud83c\udf6c"),
    SPIDER(" \ud83d\udd77\ufe0f"),
    MANVAMPIRE(" \ud83e\udddb\u200d\ufe0f"),
    GHOST(" \ud83d\udc7b"),
    WOMANZOMBIE(" \ud83e\udddf\u200d\ufe0f"),
    KEY(" \ud83d\udddd\ufe0f"),
    DOORS(" \ud83d\udeaa"),
    TREE("\ud83c\udf32\ud83c\udf32"),
    WALL5("\ud83c\udfd4\ufe0f\ud83c\udfd4\ufe0f"),
    TREE2("\ud83c\udf33\ud83c\udf33"),
    SWORD(" \ud83d\udde1\ufe0f"),
    POTION(" \ud83c\udf7a");

    private String emote;

    private Emote(String emote) {
        this.emote = emote;
    }

    public String getEmote() {
        return emote;
    }
}