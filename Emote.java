public enum Emote {
    WALL1("##"),
    WALL2("#"),
    PLAYER(" @"),
    FLOOR(" ."),
    LAVA("\ud83d\udd25"),
    CANDY(" \ud83c\udf6c"),
    SPIDER(" \ud83d\udd77\ufe0f"),
    MANVAMPIRE("\ud83e\udddb\u200d\u2642\ufe0f"),
    GHOST(" \ud83d\udc7b"),
    WOMANZOMBIE("\ud83e\udddf\u200d\u2640\ufe0f");
    
    private String emote;

    private Emote(String emote) {
        this.emote = emote;
    }

    public String getemote() {
        return emote;
    }
}