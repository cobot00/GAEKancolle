package simple;

public class LoadResult {

    private final String fileName;
    private final int loadCount;

    public LoadResult(String fileName, int loadCount) {
        this.fileName = fileName;
        this.loadCount = loadCount;
    }

    public String getFileName() {
        return fileName;
    }

    public int getLoadCount() {
        return loadCount;
    };
}
