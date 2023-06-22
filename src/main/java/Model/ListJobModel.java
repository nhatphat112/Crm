package Model;

public class ListJobModel {
    private JobsModel job;
    private ProjectsModel project;
    private UsersModel users;
    private StatusModel status;
    private String dateBegin;
    private String dateEnd;

    public JobsModel getJob() {
        return job;
    }

    public void setJob(JobsModel job) {
        this.job = job;
    }

    public ProjectsModel getProject() {
        return project;
    }

    public void setProject(ProjectsModel project) {
        this.project = project;
    }

    public UsersModel getUsers() {
        return users;
    }

    public void setUsers(UsersModel users) {
        this.users = users;
    }

    public StatusModel getStatus() {
        return status;
    }

    public void setStatus(StatusModel status) {
        this.status = status;
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

    public ListJobModel() {
       this.job = new JobsModel();
       this.project = new ProjectsModel();
       this.status = new StatusModel();
       this.users = new UsersModel();

    }

    public ListJobModel(JobsModel job, ProjectsModel project, UsersModel users, StatusModel status, String dateBegin, String dateEnd) {
        this.job = job;
        this.project = project;
        this.users = users;
        this.status = status;
        this.dateBegin = dateBegin;
        this.dateEnd = dateEnd;
    }
}
