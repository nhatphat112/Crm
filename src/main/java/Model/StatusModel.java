package Model;

public class StatusModel {
    private String id;
    private String statusName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public StatusModel() {
    }

    public StatusModel(String id, String statusName) {
        this.id = id;
        this.statusName = statusName;
    }

    public StatusModel(String statusName) {
        this.statusName = statusName;
    }
}
