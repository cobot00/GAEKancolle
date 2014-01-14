package simple.dao;

import java.util.*;

import com.google.appengine.api.datastore.*;
import com.google.appengine.api.datastore.Query.SortDirection;
import com.google.gson.Gson;

import simple.io.FileInfo;
import simple.model.Rarity;
import simple.model.ShipEntity;
import simple.model.ShipPOJO;
import simple.model.SortType;

public class ShipDao implements EntityDao {

    private static final int COLUMN_NUMBER = 4;

    private static final int COLUMN_SHIP_NO = 0;
    private static final int COLUMN_SHIP_NAME = 1;
    private static final int COLUMN_SHIP_TYPE = 2;
    private static final int COLUMN_RARITY = 3;

    private static final String COMMA = ",";
    private static final String DOUBLE_QUOTE = "\"";

    public Map<String, String> loadAsMapByShipName() {
        final Map<String, String> result = new HashMap<String, String>();

        final List<ShipEntity> list = load(SortType.SHIP_NO);
        for (ShipEntity entity : list) {
            result.put(entity.getShipNm(), entity.getShipNo());
        }

        return result;
    }

    public Map<String, String> loadAsMapByShipNo() {
        final Map<String, String> result = new HashMap<String, String>();

        final List<ShipEntity> list = load(SortType.SHIP_NO);
        for (ShipEntity entity : list) {
            result.put(entity.getShipNo(), entity.getShipNm());
        }

        return result;
    }

    public List<ShipEntity> load(SortType sortType) {
        final List<ShipEntity> result = new ArrayList<ShipEntity>();

        final DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
        final Query query = new Query(ShipEntity.KIND_NAME);

        switch (sortType) {
        case SHIP_NO:
            query.addSort(ShipEntity.SHIP_NO);
            break;
        case SHIP_TYPE:
            query.addSort(ShipEntity.SHIP_TYPE);
            query.addSort(ShipEntity.SHIP_NO);
            break;
        case RARITY:
            query.addSort(ShipEntity.RARITY, SortDirection.DESCENDING);
            //query.addSort(ShipEntity.SHIP_NO);
            break;
        case NONE:
            break;
        }

        PreparedQuery pq = ds.prepare(query);
        for (Entity entity : pq.asIterable()) {
            result.add(new ShipEntity(entity));
        }

        return result;
    }

    public String getFlexigridJson(SortType sortType) {
        return toFlexigridJson(load(sortType));
    }

    public String toFlexigridJson(List<ShipEntity> ships) {
        final StringBuilder sb = new StringBuilder();

        sb.append("{ \"page\": 1, \"total\": ");
        sb.append(String.valueOf(ships.size()));
        sb.append(", \"rows\":[");

        int rowIndex = 0;
        for (ShipEntity entity : ships) {
            ++rowIndex;
            if (rowIndex >= 2) {
                sb.append(COMMA);
            }
            sb.append("{ \"id\":");
            //toQuote(sb, String.valueOf(rowIndex));
            sb.append(String.valueOf(rowIndex));
            sb.append(COMMA);
            sb.append("\"cell\":[");

            toQuote(sb, entity.getShipNo());
            sb.append(COMMA);
            toQuote(sb, entity.getShipType());
            sb.append(COMMA);
            toQuote(sb, entity.getShipNm());
            sb.append(COMMA);
            toQuote(sb, Rarity.convert(entity.getRarity()).getSymbol());

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

    public String toJson(List<ShipEntity> ships) {
        final Gson gson = new Gson();

        final List<ShipPOJO> pojos = new ArrayList<ShipPOJO>();
        for (ShipEntity entity : ships) {
            final ShipPOJO pojo = new ShipPOJO();
            pojo.setShip_no(entity.getShipNo());
            pojo.setShip_name(entity.getShipNm());
            pojo.setShip_type(entity.getShipType());
            pojo.setRarity(Rarity.convert(entity.getRarity()).getSymbol());

            pojos.add(pojo);
        }

        return gson.toJson(pojos);
    }

    public void entry(FileInfo fileInfo) {
        for (String line : fileInfo.getContents()) {
            final String[] values = line.split(",");
            if (values.length != COLUMN_NUMBER) {
                continue;
            }

            final String shipNo = formatShipNo(values[COLUMN_SHIP_NO]);
            if (shipNo == null) {
                continue;
            }

            final DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
            final Key key = KeyFactory.createKey(ShipEntity.KIND_NAME, shipNo);

            try {
                ds.get(key);
                System.out.println("shipNo = " + shipNo + " exists!!");
                continue;
            } catch (EntityNotFoundException e) {
                // create new entity
            }

            final String shipName = values[COLUMN_SHIP_NAME];
            final String shipType = values[COLUMN_SHIP_TYPE];
            final int rarity = Integer.parseInt(values[COLUMN_RARITY]);

            final ShipEntity shipTypeEntity = new ShipEntity(shipNo, shipName, shipType, rarity);
            ds.put(shipTypeEntity.getEntity());
        }
    }

    private String formatShipNo(String shipNo) {
        try {
            return String.format("%03d", Integer.parseInt(shipNo));
        } catch (NumberFormatException e) {
            //
        }

        return null;
    }
}
