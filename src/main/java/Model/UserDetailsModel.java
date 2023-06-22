package Model;

import java.util.ArrayList;

public class UserDetailsModel extends UsersModel{

    ArrayList<JobOfUserDetailsModel> jobListNotStarted  = new ArrayList<>() ;
    ArrayList<JobOfUserDetailsModel> jobListProcessing  = new ArrayList<>() ;
    ArrayList<JobOfUserDetailsModel> jobListCompleted  = new ArrayList<>() ;

    public UserDetailsModel() {
    }

    public ArrayList<JobOfUserDetailsModel> getJobListNotStarted() {
        return jobListNotStarted;
    }

    public void setJobListNotStarted(ArrayList<JobOfUserDetailsModel> jobListNotStarted) {
        this.jobListNotStarted = jobListNotStarted;
    }

    public ArrayList<JobOfUserDetailsModel> getJobListProcessing() {
        return jobListProcessing;
    }

    public void setJobListProcessing(ArrayList<JobOfUserDetailsModel> jobListProcessing) {
        this.jobListProcessing = jobListProcessing;
    }

    public ArrayList<JobOfUserDetailsModel> getJobListCompleted() {
        return jobListCompleted;
    }

    public void setJobListCompleted(ArrayList<JobOfUserDetailsModel> jobListCompleted) {
        this.jobListCompleted = jobListCompleted;
    }

    public UserDetailsModel(String idUser, ArrayList<JobOfUserDetailsModel> jobListNotStarted, ArrayList<JobOfUserDetailsModel> jobListProcessing, ArrayList<JobOfUserDetailsModel> jobListCompleted) {
        this.setId(idUser);
        this.jobListNotStarted = jobListNotStarted;
        this.jobListProcessing = jobListProcessing;
        this.jobListCompleted = jobListCompleted;
    }
}
