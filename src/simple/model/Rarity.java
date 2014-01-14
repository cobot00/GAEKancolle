package simple.model;

public enum Rarity {

    NORMAL(1, "N"), NORMAL_PLUS(2, "N+"), RARE(3, "R"), SUPER_RARE(4, "SR"), SRR(5, "SR+"),
    SRRR(6, "SR++"), SRRRR(7, "SR+++");

    private final int rarity;
    private final String symbol;

    private Rarity(int rarity, String symbol) {
        this.rarity = rarity;
        this.symbol = symbol;
    }

    public int getRarity() {
        return rarity;
    }

    public String getSymbol() {
        return symbol;
    }

    public static Rarity convert(int rarity) {
        for (Rarity each : values()) {
            if (each.getRarity() == rarity) {
                return each;
            }
        }

        throw new IllegalArgumentException("rarity = " + rarity);
    }
}
