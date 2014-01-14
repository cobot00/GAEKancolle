package simple.dao;

import java.util.*;

import com.google.appengine.api.datastore.*;

import simple.io.*;
import simple.model.*;

public class ArpEvent3Dao implements EntityDao {

    private static final int COLUMN_NUMBER = 7;

    private static final int COLUMN_ID = 0;
    private static final int COLUMN_AREA_A = 1;
    private static final int COLUMN_AREA_B = 2;
    private static final int COLUMN_AREA_C = 3;
    private static final int COLUMN_AREA_D = 4;
    private static final int COLUMN_AREA_F = 5;
    private static final int COLUMN_AREA_H = 6;

    private static final String COMMA = ",";
    private static final String DOUBLE_QUOTE = "\"";

    public List<ArpEvent3Entity> load() {
        final List<ArpEvent3Entity> result = new ArrayList<ArpEvent3Entity>();

        final DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
        final Query query = new Query(ArpEvent3Entity.KIND_NAME);

        PreparedQuery pq = ds.prepare(query);
        for (Entity entity : pq.asIterable()) {
            result.add(new ArpEvent3Entity(entity));
        }

        return result;
    }

    public String getFlexigridJson(SortType sortType) {
        return toFlexigridJson(load());
    }

    public String toFlexigridJson(List<ArpEvent3Entity> results) {
        final StringBuilder sb = new StringBuilder();
        final ShipDao shipDao = new ShipDao();
        final Map<String, String> ships = shipDao.loadAsMapByShipNo();

        sb.append("{ \"page\": 1, \"total\": ");
        sb.append(String.valueOf(results.size()));
        sb.append(", \"rows\":[");

        int rowIndex = 0;
        for (ArpEvent3Entity entity : results) {
            ++rowIndex;
            if (rowIndex >= 2) {
                sb.append(COMMA);
            }
            sb.append("{ \"id\":");
            //toQuote(sb, String.valueOf(rowIndex));
            sb.append(String.valueOf(rowIndex));
            sb.append(COMMA);
            sb.append("\"cell\":[");

            toQuote(sb, entity.getID());
            sb.append(COMMA);
            convertAndQuote(sb, entity.getAreaA(), ships);
            sb.append(COMMA);
            convertAndQuote(sb, entity.getAreaB(), ships);
            sb.append(COMMA);
            convertAndQuote(sb, entity.getAreaC(), ships);
            sb.append(COMMA);
            convertAndQuote(sb, entity.getAreaD(), ships);
            sb.append(COMMA);
            convertAndQuote(sb, entity.getAreaF(), ships);
            sb.append(COMMA);
            convertAndQuote(sb, entity.getAreaH(), ships);

            sb.append("]}");
        }
        sb.append("]");
        sb.append("}");

        return sb.toString();
    }

    private void toQuote(StringBuilder sb, String value) {
        sb.append(DOUBLE_QUOTE);
        sb.append(value);
        sb.append(DOUBLE_QUOTE);
    }

    private void convertAndQuote(StringBuilder sb, String value, Map<String, String> dictionary) {
        String explanation = dictionary.get(value);
        if (explanation == null) {
            explanation = value;
        }

        toQuote(sb, explanation);
    }

    public void entry(FileInfo fileInfo) {
        for (String line : fileInfo.getContents()) {
            final String[] values = line.split(",");
            if (values.length != COLUMN_NUMBER) {
                continue;
            }

            final long id = convertToLong(values[COLUMN_ID]);
            if (id <= 0) {
                continue;
            }

            final DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
            final Key key = KeyFactory.createKey(ArpEvent3Entity.KIND_NAME, id);

            try {
                ds.get(key);
                System.out.println("id = " + id + " exists!!");
                continue;
            } catch (EntityNotFoundException e) {
                // create new entity
            }

            final ShipDao shipDao = new ShipDao();
            final Map<String, String> ships = shipDao.loadAsMapByShipName();

            final String areaA = convertToShipNo(values[COLUMN_AREA_A], ships);
            final String areaB = convertToShipNo(values[COLUMN_AREA_B], ships);
            final String areaC = convertToShipNo(values[COLUMN_AREA_C], ships);
            final String areaD = convertToShipNo(values[COLUMN_AREA_D], ships);
            final String areaF = convertToShipNo(values[COLUMN_AREA_F], ships);
            final String areaH = convertToShipNo(values[COLUMN_AREA_H], ships);

            final ArpEvent3Entity arpEvent3Entity = new ArpEvent3Entity(id, areaA, areaB, areaC, areaD, areaF, areaH);
            ds.put(arpEvent3Entity.getEntity());
        }
    }

    private long convertToLong(String id) {
        try {
            return Long.parseLong(id);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private String convertToShipNo(String shipName, Map<String, String> dictionary) {
        final String shipNo = dictionary.get(shipName);
        if (shipNo != null) {
            return shipNo;
        }

        return shipName;
    }
}
