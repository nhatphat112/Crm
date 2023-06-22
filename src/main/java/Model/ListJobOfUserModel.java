package Model;

import java.util.ArrayList;

public class ListJobOfUserModel {
    private UsersModel user;
    private ArrayList <JobDetailsModel> listJob;
    private ArrayList<JobDetailsModel> listJobNotStarted;
    private ArrayList<JobDetailsModel> listJobProcessing;
    private ArrayList<JobDetailsModel> listJobCompleted;

    public UsersModel getUser() {
        return user;
    }

    public void setUser(UsersModel user) {
        this.user = user;
    }

    public ArrayList<JobDetailsModel> getListJob() {
        return listJob;
    }

    public void setListJob(ArrayList<JobDetailsModel> listJob) {
        this.listJob = listJob;
    }

    public ArrayList<JobDetailsModel> getListJobNotStarted() {
        return listJobNotStarted;
    }

    public void setListJobNotStarted(ArrayList<JobDetailsModel> listJobNotStarted) {
        this.listJobNotStarted = listJobNotStarted;
    }

    public ArrayList<JobDetailsModel> getListJobProcessing() {
        return listJobProcessing;
    }

    public void setListJobProcessing(ArrayList<JobDetailsModel> listJobProcessing) {
        this.listJobProcessing = listJobProcessing;
    }

    public ArrayList<JobDetailsModel> getListJobCompleted() {
        return listJobCompleted;
    }

    public void setListJobCompleted(ArrayList<JobDetailsModel> listJobCompleted) {
        this.listJobCompleted = listJobCompleted;
    }

    public ListJobOfUserModel(UsersModel user, ArrayList<JobDetailsModel> listJob, ArrayList<JobDetailsModel> listJobNotStarted, ArrayList<JobDetailsModel> listJobProcessing, ArrayList<JobDetailsModel> listJobCompleted) {
        this.user = user;
        this.listJob = listJob;
        this.listJobNotStarted = listJobNotStarted;
        this.listJobProcessing = listJobProcessing;
        this.listJobCompleted = listJobCompleted;
    }

    public ListJobOfUserModel() {
        this.user = new UsersModel();
        this.listJob = new ArrayList<>();
        this.listJobNotStarted = new ArrayList<>();
        this.listJobProcessing = new ArrayList<>();
        this.listJobCompleted = new ArrayList<>();

    }
}
