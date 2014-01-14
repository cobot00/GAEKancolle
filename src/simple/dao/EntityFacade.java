package simple.dao;

import simple.io.FileInfo;
import simple.model.FileType;

public class EntityFacade {

    public void entry(FileInfo fileInfo) {
        final EntityDao dao = createDao(fileInfo.getFileType());
        dao.entry(fileInfo);
    }

    public EntityDao createDao(FileType fileType) {
        switch (fileType) {
        case SHIPS:
            return new ShipDao();
        case EVENT_ARP_2:
            return new ArpEvent2Dao();
        case EVENT_ARP_3:
            return new ArpEvent3Dao();
        }

        throw new IllegalArgumentException();
    }
}
