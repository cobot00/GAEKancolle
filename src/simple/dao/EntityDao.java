package simple.dao;

import simple.io.FileInfo;
import simple.model.SortType;

public interface EntityDao {

    void entry(FileInfo fileInfo);

    String getFlexigridJson(SortType sortType);
}
