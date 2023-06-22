package Repository;

import Config.MySqlConfig;
import Dates.StringDate;
import Model.ListJobModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ListJobRepository {
    public ArrayList <ListJobModel> selectAll(){
        Connection connection = null;
        ArrayList <ListJobModel> listJobArrayList = new ArrayList<ListJobModel>();
        try {
            connection = MySqlConfig.getConnection();
            String sql = "SELECT  j.id as job_id, j.jobname , u.id  as user_id, u.fullname, p.id  as project_id, p.projectname , w.datebegin , w.dateend , s.id as status_id ,s.statusname  \n" +
                    "FROM job j inner join workon w  on j.id  = w.job_id inner JOIN users u on u.id =w.user_id inner join project p on p.id = w.project_id INNER JOIN status s on s.id  = w.status_id  ;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("Execute :"+sql);
            while (resultSet.next()){
                ListJobModel listJob = new ListJobModel();
                listJob.getJob().setId(resultSet.getString("job_id"));
                listJob.getJob().setJobName(resultSet.getString("jobname"));
                listJob.getProject().setId(resultSet.getString("project_id"));
                listJob.getProject().setProjectName(resultSet.getString("projectname"));
                listJob.getUsers().setId(resultSet.getString("user_id"));
                listJob.getUsers().setFullName(resultSet.getString("fullname"));
                listJob.getStatus().setId(resultSet.getString("status_id"));
                listJob.getStatus().setStatusName(resultSet.getString("statusname"));
                listJob.setDateBegin(StringDate.stringTimeStampToStringDate(resultSet.getString("datebegin")));
                listJob.setDateEnd(StringDate.stringTimeStampToStringDate(resultSet.getString("dateend")));
                listJobArrayList.add(listJob);

            }

        }catch (Exception e){
            System.out.println("Error select data at listJobArrayList ");
            e.printStackTrace();
        } finally {
            MySqlConfig.closeConnection(connection);
        }
        return listJobArrayList;
    }
    public ArrayList <ListJobModel> selectProjectUserId(String projectUserId){
        Connection connection = null;
        ArrayList <ListJobModel> listJobArrayList = new ArrayList<ListJobModel>();
        try {
            connection = MySqlConfig.getConnection();
            String sql = "SELECT j.id as job_id, j.jobname , u.id  as user_id, u.fullname, p.id  as project_id, p.projectname , w.datebegin , w.dateend , s.id as status_id ,s.statusname  \n" +
                    "FROM workon w join (select * from project p WHERE p.user_id =?) as p\n" +
                    "on p.id = w.project_id join users u on u.id  = w.user_id JOIN status s on w.status_id = s.id JOIN job j on w.job_id  = j.id  ;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,Integer.parseInt(projectUserId));
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("Execute :"+sql);
            while (resultSet.next()){
                ListJobModel listJob = new ListJobModel();
                listJob.getJob().setId(resultSet.getString("job_id"));
                listJob.getJob().setJobName(resultSet.getString("jobname"));
                listJob.getProject().setId(resultSet.getString("project_id"));
                listJob.getProject().setProjectName(resultSet.getString("projectname"));
                listJob.getUsers().setId(resultSet.getString("user_id"));
                listJob.getUsers().setFullName(resultSet.getString("fullname"));
                listJob.getStatus().setId(resultSet.getString("status_id"));
                listJob.getStatus().setStatusName(resultSet.getString("statusname"));
                listJob.setDateBegin(StringDate.stringTimeStampToStringDate(resultSet.getString("datebegin")));
                listJob.setDateEnd(StringDate.stringTimeStampToStringDate(resultSet.getString("dateend")));
                listJobArrayList.add(listJob);

            }

        }catch (Exception e){
            System.out.println("Error select data at listJobArrayList ");
            e.printStackTrace();
        } finally {
            MySqlConfig.closeConnection(connection);
        }
        return listJobArrayList;
    }
}
