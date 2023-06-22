package Model;

public class WorkOnModel {
    private UsersModel users;
    private ProjectsModel projects;
    private JobsModel jobs;
    private StatusModel status;
    private String dateBegin;
    private String dateEnd;

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

    public UsersModel getUsers() {
        return users;
    }

    public void setUsers(UsersModel users) {
        this.users = users;
    }

    public ProjectsModel getProjects() {
        return projects;
    }

    public void setProjects(ProjectsModel projects) {
        this.projects = projects;
    }

    public JobsModel getJobs() {
        return jobs;
    }

    public void setJobs(JobsModel jobs) {
        this.jobs = jobs;
    }

    public StatusModel getStatus() {
        return status;
    }

    public void setStatus(StatusModel status) {
        this.status = status;
    }

    public WorkOnModel(UsersModel users, ProjectsModel projects, JobsModel jobs, StatusModel status) {
        this.users = users;
        this.projects = projects;
        this.jobs = jobs;
        this.status = status;
    }

    public WorkOnModel(UsersModel users, ProjectsModel projects, JobsModel jobs, StatusModel status, String dateBegin, String dateEnd) {
        this.users = users;
        this.projects = projects;
        this.jobs = jobs;
        this.status = status;
        this.dateBegin = dateBegin;
        this.dateEnd = dateEnd;
    }

    public WorkOnModel() {
            this.projects = new ProjectsModel();
            this.status = new StatusModel();
            this.users = new UsersModel();
            this.jobs = new JobsModel();
    }
}
