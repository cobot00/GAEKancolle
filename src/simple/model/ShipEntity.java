package simple.model;

import com.google.appengine.api.datastore.Entity;

public class ShipEntity {

    public static final String KIND_NAME = "Ship";

    public static final String SHIP_NO = "ship_no";
    public static final String SHIP_NAME = "ship_name";
    public static final String SHIP_TYPE = "ship_type";
    public static final String RARITY = "rarity";

    private final Entity ship;

    public ShipEntity(String shipNo, String shipName, String shipType, int rarity) {
        ship = new Entity(KIND_NAME, shipNo);
        ship.setProperty(SHIP_NO, shipNo);
        ship.setProperty(SHIP_NAME, shipName);
        ship.setProperty(SHIP_TYPE, shipType);
        ship.setProperty(RARITY, rarity);
    }

    public ShipEntity(Entity entity) {
        ship = entity;
    }

    public Entity getEntity() {
        return ship;
    }

    public String getShipNo() {
        return (String) ship.getProperty(SHIP_NO);
    }

    public String getShipNm() {
        return (String) ship.getProperty(SHIP_NAME);
    }

    public String getShipType() {
        return (String) ship.getProperty(SHIP_TYPE);
    }

    public int getRarity() {
        return ((Long) ship.getProperty(RARITY)).intValue();
    }
}
