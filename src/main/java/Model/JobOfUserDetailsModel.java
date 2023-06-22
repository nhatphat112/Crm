package Model;

public class JobOfUserDetailsModel {
    private StatusModel status;
    private JobsModel job;
    private String dateBegin;
    private String dateEnd;



    public StatusModel getStatus() {
        return status;
    }

    public void setStatus(StatusModel status) {
        this.status = status;
    }

    public JobsModel getJob() {
        return job;
    }

    public void setJob(JobsModel job) {
        this.job = job;
    }

    public String getDateBegin() {
        return dateBegin;
    }

    public void setDateBegin(String dateBegin) {
        this.dateBegin = dateBegin;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }
    public JobOfUserDetailsModel(StatusModel status, JobsModel job, String dateBegin, String dateEnd) {
        this.status = status;
        this.job = job;
        this.dateBegin = dateBegin;
        this.dateEnd = dateEnd;
    }

    public JobOfUserDetailsModel() {

    }
}
