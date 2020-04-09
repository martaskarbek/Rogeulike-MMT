public enum Emote {
    WALL1("##"),
    WALL2("#"),
    PLAYER(" @"),
    FLOOR(" ."),
    LAVA("\ud83d\udd25"),
    CANDY(" \ud83c\udf6c");
    
    private String emote;

    private Emote(String emote) {
        this.emote = emote;
    }

    public String getemote() {
        return emote;
    }
}