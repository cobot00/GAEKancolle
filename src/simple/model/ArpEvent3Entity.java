package simple.model;

import com.google.appengine.api.datastore.Entity;

public class ArpEvent3Entity {

    public static final String KIND_NAME = "ArpEvent3";

    public static final String ID = "id";
    public static final String AREA_A = "area_a";
    public static final String AREA_B = "area_b";
    public static final String AREA_C = "area_c";
    public static final String AREA_D = "area_d";
    public static final String AREA_F = "area_f";
    public static final String AREA_H = "area_h";

    private final Entity arpEvent3;

    public ArpEvent3Entity(long id, String areaA, String areaB, String areaC, String areaD, String areaF, String areaH) {
        arpEvent3 = new Entity(KIND_NAME, id);
        arpEvent3.setProperty(ID, id);
        arpEvent3.setProperty(AREA_A, areaA);
        arpEvent3.setProperty(AREA_B, areaB);
        arpEvent3.setProperty(AREA_C, areaC);
        arpEvent3.setProperty(AREA_D, areaD);
        arpEvent3.setProperty(AREA_F, areaF);
        arpEvent3.setProperty(AREA_H, areaH);
    }

    public ArpEvent3Entity(Entity entity) {
        arpEvent3 = entity;
    }

    public Entity getEntity() {
        return arpEvent3;
    }

    public String getID() {
        return String.valueOf((Long) arpEvent3.getProperty(ID));
    }

    public String getAreaA() {
        return (String) arpEvent3.getProperty(AREA_A);
    }

    public String getAreaB() {
        return (String) arpEvent3.getProperty(AREA_B);
    }

    public String getAreaC() {
        return (String) arpEvent3.getProperty(AREA_C);
    }

    public String getAreaD() {
        return (String) arpEvent3.getProperty(AREA_D);
    }

    public String getAreaF() {
        return (String) arpEvent3.getProperty(AREA_F);
    }

    public String getAreaH() {
        return (String) arpEvent3.getProperty(AREA_H);
    }
}
