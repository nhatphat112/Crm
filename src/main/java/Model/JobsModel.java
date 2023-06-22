package Model;

public class JobsModel {
    private String id;
    private  String jobName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public JobsModel(String id, String jobName) {
        this.id = id;
        this.jobName = jobName;
    }

    public JobsModel(String jobName) {
        this.jobName = jobName;
    }

    public JobsModel() {
    }
}
