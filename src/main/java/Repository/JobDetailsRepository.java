package Repository;

import Config.MySqlConfig;
import Model.JobDetailsModel;
import Model.JobsModel;
import Model.UsersModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class JobDetailsRepository {
    public ArrayList<JobDetailsModel> selectByUserIdAndProjectId(UsersModel user,String projectId){
        ArrayList<JobDetailsModel> jobDetailsList = new ArrayList<>();
        Connection connection = null;
        try {
            connection = MySqlConfig.getConnection();
            String sql = "SELECT *\n" +
                    "FROM job j join workon w ON w.job_id =j.id JOIN (select * from project p where p.id = ?) p on p.id = w.project_id WHERE  w.user_id =? ;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,Integer.parseInt(projectId));
            preparedStatement.setInt(2,Integer.parseInt(user.getId()));
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("Executed :"+sql);
            while (resultSet.next()){
                JobDetailsModel jobDetails = new JobDetailsModel();
                jobDetails.setId(resultSet.getString("job_id"));
                jobDetails.setJobName(resultSet.getString("jobname"));
                jobDetails.setId(resultSet.getString("job_id"));
                jobDetails.setStatusId(resultSet.getString("status_id"));
                jobDetailsList.add(jobDetails);
            }
            System.out.println("Executed :"+sql);
            System.out.println("Check size  jobDetailsList:"+jobDetailsList.size());


        }catch (Exception e){
            System.out.println("Error select data at GroupWorkDetailsController");
            e.printStackTrace();
        } finally {
            MySqlConfig.closeConnection(connection);
        }
        return  jobDetailsList;
    }
}
