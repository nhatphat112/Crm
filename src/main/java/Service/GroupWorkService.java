package Service;

import Dates.checkDate;
import Model.JobDetailsModel;
import Model.ListJobOfUserModel;
import Model.ProjectsModel;
import Model.UsersModel;
import Repository.JobDetailsRepository;
import Repository.ProjectsRepository;
import Repository.UsersRepository;

import javax.servlet.ServletRequest;
import java.sql.Timestamp;
import java.text.NumberFormat;
import java.util.ArrayList;

public class GroupWorkService {
    private ProjectsRepository projectsRepository = new ProjectsRepository();
    private UsersRepository usersRepository = new UsersRepository();
    private JobDetailsRepository jobDetailsRepository = new JobDetailsRepository();

    public boolean checkInputFormValid(ProjectsModel project, ServletRequest req){
        // check date valid ?
        System.out.println("Check date begin at checkInputFormValid"+project.getDateBegin());
        boolean validDateBegin = checkDate.checkDateValid(project.getDateBegin());
        if(!validDateBegin) {
            req.setAttribute("message","Date Begin Invalid!");
            return false;
        }
        boolean validDateEnd = checkDate.checkDateValid(project.getDateEnd());
        if(!validDateEnd) {
            req.setAttribute("message","Date End Invalid!");
            return false;
        }

        return true;
    }
    public boolean checkDateValid(ProjectsModel project,ServletRequest req){
        Timestamp dateBeginTimestamp = Timestamp.valueOf(project.getDateBegin());
         Timestamp dateEndTimestamp = Timestamp.valueOf(project.getDateEnd());
         System.out.println("check date Begin :"+dateBeginTimestamp);
        System.out.println("check date End :"+dateEndTimestamp);

         System.out.println("check compare :"+((dateBeginTimestamp.compareTo(dateEndTimestamp)>0)?true:false));
        if(dateBeginTimestamp.compareTo(dateEndTimestamp)>0){
            req.setAttribute("message","Date End invalid");
            return false;
        }
        return true;
    }

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
    public boolean update(ProjectsModel project){
        return projectsRepository.update(project);
    }
    public boolean insert(ProjectsModel project){
        return projectsRepository.insert(project);
    }
    public ArrayList<ListJobOfUserModel> getListJobOfUsersArrayList(String projectId){
        ArrayList <ListJobOfUserModel> listJobOfUsersArrayList = new ArrayList<>();
        // get List User
        ArrayList<UsersModel> userList = usersRepository.sellectByProjectId(projectId);
        System.out.println("Check size userList"+userList.size());
        //
        for (UsersModel user : userList){
            ListJobOfUserModel listJobOfUser = new ListJobOfUserModel();
            listJobOfUser.setUser(user);
            ArrayList<JobDetailsModel> jobDetailsList = jobDetailsRepository.selectByUserIdAndProjectId(user,projectId);
            // distribute type-list
            for (JobDetailsModel jobDetails: jobDetailsList){
                if(jobDetails.getStatusId().equals("1")){

                    listJobOfUser.getListJobNotStarted().add(jobDetails);
                } else if(jobDetails.getStatusId().equals("2")){
                    listJobOfUser.getListJobProcessing().add(jobDetails);
                } else{
                    listJobOfUser.getListJobCompleted().add(jobDetails);
                }
            }
            listJobOfUsersArrayList.add(listJobOfUser);
        }
        return listJobOfUsersArrayList;
    }
    public int countjobNotStarted(ArrayList <ListJobOfUserModel>listJobOfUsersArrayList){
        int count = 0;
        for (ListJobOfUserModel listJobOfUser : listJobOfUsersArrayList){
            for (JobDetailsModel jobDetails: listJobOfUser.getListJobNotStarted()){
                count++;
            }
        }
        return count;
    }
    public int countJobCompleted(ArrayList <ListJobOfUserModel>listJobOfUsersArrayList){
        int count = 0;
        for (ListJobOfUserModel listJobOfUser : listJobOfUsersArrayList){
            for (JobDetailsModel jobDetails :listJobOfUser.getListJobCompleted()){
                count++;
            }
        }
        return count;
    }
    public int countJobProcessing(ArrayList <ListJobOfUserModel>listJobOfUsersArrayList){
        int count = 0;
        for (ListJobOfUserModel listJobOfUser : listJobOfUsersArrayList){
            for (JobDetailsModel jobDetails: listJobOfUser.getListJobProcessing()){
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
    public ArrayList<ProjectsModel> getProjectList(String roleUserId,String userId){
        System.out.println("Check roleUserId :"+roleUserId);
        if(roleUserId.equals("2")){
          return   projectsRepository.selectByUserId(userId);
        }
        return projectsRepository.selectAll();

    }
    public boolean deleteGroupWork(ProjectsModel project){
        return projectsRepository.delete(project);
    }
    public ArrayList<UsersModel> getUserListByRoleId(String id){
        return usersRepository.selectByRoleId(id);
    }
    public ArrayList<UsersModel> getUserById(String id){
        return usersRepository.selectById(id);
    }

}
