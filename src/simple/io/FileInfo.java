package simple.io;

import java.util.*;

import simple.model.FileType;

public class FileInfo {

    private final FileType fileType;
    private final String fileName;
    private final List<String> contents;

    public FileInfo(FileType fileType, String fileName, List<String> contents) {
        this.fileType = fileType;
        this.fileName = fileName;
        this.contents = contents;
    }

    public FileType getFileType() {
        return fileType;
    }

    public String getFileName() {
        return fileName;
    }

    public List<String> getContents() {
        return contents;
    }
}
