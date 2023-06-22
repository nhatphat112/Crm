package Service;

import Model.*;
import Repository.JobDetailsRepository;
import Repository.ProjectsRepository;
import Repository.UsersRepository;
import Repository.WorkOnRepository;

import java.text.NumberFormat;
import java.util.ArrayList;

public class DashboardService {
    private JobDetailsRepository jobDetailsRepository = new JobDetailsRepository();
    private UsersRepository usersRepository = new UsersRepository();
    private ProjectsRepository projectsRepository = new ProjectsRepository();
    private WorkOnRepository workOnRepository = new WorkOnRepository();

    public boolean convertToFormTimeStamp(ProjectsModel project){
        try {
            project.setDateBegin(String.valueOf(Dates.toTimestamp.toTimeStamp(project.getDateBegin())));
            project.setDateEnd(String.valueOf(Dates.toTimestamp.toTimeStamp(project.getDateEnd())));
            return true;
        } catch (Exception e){
            System.out.println("Error at convertToFormTimeStamp");
            e.printStackTrace();
        }
        return false;
    }
    public int countjobByStatusId(ArrayList<WorkOnModel> workOnList,String status){
        int count = 0;
        for (WorkOnModel work :workOnList ){
            if(work.getStatus().getId().equalsIgnoreCase(status)){
                count++;
            }
        }
        return count;
    }

    public int jobTotal(int jobNotStarted,int jobProcessing,int jobCompleted ){
        return jobNotStarted+jobProcessing+jobCompleted;
    }
    public double calPercent(int number, int total){
        if(total==0) return total;
        return (double) number*100/total;
    }
    public String formaTwoDecimals(double number){
        NumberFormat nfTwoDecimals = NumberFormat.getInstance();
        nfTwoDecimals.setMinimumFractionDigits(1);
        nfTwoDecimals.setMaximumFractionDigits(2);
        return nfTwoDecimals.format(number);
    }
    public ArrayList<ProjectsModel> getProjectList(){
        return projectsRepository.selectAll();
    }
    public ArrayList<WorkOnModel> getTaskList(){
        return workOnRepository.selectAll();
    }
}
