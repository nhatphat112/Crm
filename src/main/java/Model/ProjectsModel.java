package Model;

public class ProjectsModel {
    private String id;
    private String projectName;
    private String dateBegin;
    private String dateEnd;
    private UsersModel user;

    public ProjectsModel(String id, String projectName, String dateBegin, String dateEnd, UsersModel user) {
        this.id = id;
        this.projectName = projectName;
        this.dateBegin = dateBegin;
        this.dateEnd = dateEnd;
        this.user = user;
    }

    public UsersModel getUser() {
        return user;
    }

    public void setUser(UsersModel user) {
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
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

    public ProjectsModel(String id, String projectName, String dateBegin, String dateEnd) {
        this.id = id;
        this.projectName = projectName;
        this.dateBegin = dateBegin;
        this.dateEnd = dateEnd;
    }

    public ProjectsModel(String projectName, String dateBegin, String dateEnd) {
        this.projectName = projectName;
        this.dateBegin = dateBegin;
        this.dateEnd = dateEnd;
    }

    public ProjectsModel() {
    }
}
