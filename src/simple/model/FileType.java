package simple.model;

public enum FileType {

    SHIPS("ships"), EVENT_ARP_2("event_arp_2"), EVENT_ARP_3("event_arp_3");

    private final String fileType;

    private FileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileType() {
        return fileType;
    }

    public static FileType convert(String fileType) {
        for (FileType each : values()) {
            if (each.getFileType().equals(fileType)) {
                return each;
            }
        }
        throw new IllegalArgumentException("fileType = " + fileType);
    }
}
