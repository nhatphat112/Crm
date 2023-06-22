package Service;

import Model.*;
import Repository.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.ArrayList;

public class TaskService {
    private ListJobRepository listJobRepository = new ListJobRepository();
    private ProjectsRepository projectsRepository = new ProjectsRepository();
    private UsersRepository usersRepository = new UsersRepository();
    private JobsRepository jobsRepository = new JobsRepository();
    private StatusRepository statusRepository = new StatusRepository();
    private WorkOnRepository workOnRepository = new WorkOnRepository();

    public ArrayList<ListJobModel> getjobList(String roleUserId,String userId) {
        if(roleUserId.equals("2")){
            return listJobRepository.selectProjectUserId(userId);
        }
        return listJobRepository.selectAll();
    }

    public ArrayList<ProjectsModel> getProjectListById(String id) {
        return projectsRepository.selectById(id);
    }
    public ArrayList<ProjectsModel> getProjectList(String roleUserId,String userId) {
        if(roleUserId.equals("2"))
        {
           return projectsRepository.selectByUserId(userId);
        }
        return  projectsRepository.selectAll();
    }
    public ArrayList<UsersModel> getUserList(){
        return usersRepository.sellectAll();
    }
    public ArrayList<StatusModel> getStatusList(){
        return statusRepository.selectAll();
    }
    public boolean checkInputData(HttpServletRequest req, HttpServletResponse resp, WorkOnModel workOn){

        if(!Dates.checkDate.checkDateValid(workOn.getDateBegin())){
            req.setAttribute("message","Date begin is not valid");
            return false;
        } if(!Dates.checkDate.checkDateValid(workOn.getDateEnd())){
            req.setAttribute("message","Date end is not valid");
            return false;
        }
        Timestamp dateBeginTimeStamp = Dates.toTimestamp.toTimeStamp(workOn.getDateBegin());
        Timestamp dateEndTimeStamp = Dates.toTimestamp.toTimeStamp(workOn.getDateEnd());
        System.out.println("DateBegin:"+dateBeginTimeStamp);
        System.out.println("dateEndTimeStamp:"+dateEndTimeStamp);

        System.out.println("check true date---------------------- :"+dateBeginTimeStamp.compareTo(dateEndTimeStamp));

        if(dateBeginTimeStamp.compareTo(dateEndTimeStamp)>0){
            req.setAttribute("message","Date begin bigger than date end ");
            return false;
        }
        return true;
    }
    public boolean insertWorkOn(WorkOnModel workOn){
        // insert jobModel
        boolean insertJobIsSuccess = jobsRepository.insert(workOn.getJobs());
        System.out.println("Check insertJobIsSuccess:"+insertJobIsSuccess);
        if(insertJobIsSuccess){
            ArrayList<JobsModel> jobList = new ArrayList<>();
            jobList=jobsRepository.selectByName(workOn.getJobs().getJobName());
            if (jobList.size()!=0){
                workOn.setJobs(jobList.get(jobList.size()-1));
            }
            workOn.setDateBegin(Dates.toTimestamp.toStringTimeStamp(workOn.getDateBegin()));
            workOn.setDateEnd(Dates.toTimestamp.toStringTimeStamp(workOn.getDateEnd()));
            return workOnRepository.insert(workOn);
        }
        return false;
    }
    public boolean updateWorkOn(WorkOnModel workOnCurrent,WorkOnModel workOnModify){
        // insert jobModel
        workOnModify.getJobs().setId(workOnCurrent.getJobs().getId());

        boolean updateJobIsSuccess = jobsRepository.upadateById(workOnModify.getJobs());

        if(updateJobIsSuccess){
         System.out.println("check jobModifyId :"+workOnCurrent.getJobs().getId());

            workOnModify.setDateBegin(Dates.toTimestamp.toStringTimeStamp(workOnModify.getDateBegin()));
            workOnModify.setDateEnd(Dates.toTimestamp.toStringTimeStamp(workOnModify.getDateEnd()));
            System.out.println("Check getDateBegin : "+workOnModify.getDateBegin());
            System.out.println("Check getDateEnd : "+workOnModify.getDateEnd());

            return workOnRepository.updateAllById(workOnCurrent,workOnModify);
        }
        return false;
    }
    public JobsModel getJobByName(String name){
        ArrayList <JobsModel> jobList = jobsRepository.selectByName(name);
        JobsModel job = new JobsModel();
        for (JobsModel jobs : jobList){
            job = jobs;
            break;
        }
        return job;
    }
    public boolean deleteWorkOnById(WorkOnModel workOn){
        if(  workOnRepository.deleteById(workOn)){
            return jobsRepository.deleteById(workOn.getJobs().getId());
        }
        return false;
    }


}
