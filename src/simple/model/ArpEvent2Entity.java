package simple.model;

import com.google.appengine.api.datastore.Entity;

public class ArpEvent2Entity {

    public static final String KIND_NAME = "ArpEvent2";

    public static final String ID = "id";
    public static final String AREA_A = "area_a";
    public static final String AREA_B = "area_b";
    public static final String AREA_C = "area_c";
    public static final String AREA_D = "area_d";
    public static final String AREA_G = "area_g";

    private final Entity arpEvent2;

    public ArpEvent2Entity(long id, String areaA, String areaB, String areaC, String areaD, String areaG) {
        arpEvent2 = new Entity(KIND_NAME, id);
        arpEvent2.setProperty(ID, id);
        arpEvent2.setProperty(AREA_A, areaA);
        arpEvent2.setProperty(AREA_B, areaB);
        arpEvent2.setProperty(AREA_C, areaC);
        arpEvent2.setProperty(AREA_D, areaD);
        arpEvent2.setProperty(AREA_G, areaG);
    }

    public ArpEvent2Entity(Entity entity) {
        arpEvent2 = entity;
    }

    public Entity getEntity() {
        return arpEvent2;
    }

    public String getID() {
        return String.valueOf((Long) arpEvent2.getProperty(ID));
    }

    public String getAreaA() {
        return (String) arpEvent2.getProperty(AREA_A);
    }

    public String getAreaB() {
        return (String) arpEvent2.getProperty(AREA_B);
    }

    public String getAreaC() {
        return (String) arpEvent2.getProperty(AREA_C);
    }

    public String getAreaD() {
        return (String) arpEvent2.getProperty(AREA_D);
    }

    public String getAreaG() {
        return (String) arpEvent2.getProperty(AREA_G);
    }
}
