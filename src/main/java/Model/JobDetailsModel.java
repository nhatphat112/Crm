package Model;

public class JobDetailsModel extends JobsModel{
    private String statusId;

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public JobDetailsModel(String id, String jobName, String statusId) {
        super(id, jobName);
        this.statusId = statusId;
    }

    public JobDetailsModel(String jobName, String statusId) {
        super(jobName);
        this.statusId = statusId;
    }

    public JobDetailsModel(String statusId) {
        this.statusId = statusId;
    }

    public JobDetailsModel() {
    }
}
