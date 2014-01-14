package simple.model;

public enum SortType {

    NONE("none"), SHIP_NO("ship_no"), SHIP_TYPE("ship_type"), RARITY("rarity");

    private final String sortType;

    private SortType(String sortType) {
        this.sortType = sortType;
    }

    public String getSortType() {
        return sortType;
    }

    public static SortType convert(String sortType) {
        for (SortType each : values()) {
            if (each.getSortType().equals(sortType)) {
                return each;
            }
        }

        return NONE;
    }
}
