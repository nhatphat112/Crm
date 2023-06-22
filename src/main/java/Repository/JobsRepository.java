package Repository;

import Config.MySqlConfig;
import Model.JobsModel;
import Model.UsersModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class JobsRepository {
    public boolean insert(JobsModel job){
        Connection connection = null;
        try {
            connection = MySqlConfig.getConnection();
            String sql = "INSERT INTO job (jobname)\n" +
                    "values (?);";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, job.getJobName());
            System.out.println("Executed :"+sql);
            return ps.executeUpdate()>0;
        }catch (Exception e){
            System.out.println("Error at insert"+e.getMessage());
        }finally {
            MySqlConfig.closeConnection(connection);
        }
        return false;
    }
    public ArrayList<JobsModel> selectByName(String jobName){
        ArrayList<JobsModel> jobList = new ArrayList<>();
        Connection connection = null;
        try {
            connection = MySqlConfig.getConnection();
            String sql = "SELECT *\n" +
                    "FROM job j WHERE j.jobname =?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,jobName);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("Executed :"+sql);
            while (resultSet.next()){
                JobsModel job = new JobsModel(resultSet.getString("id"),jobName);
                jobList.add(job);
            }
        }catch (Exception e){
            System.out.println("Error at selectByName"+e.getMessage());
        }finally {
            MySqlConfig.closeConnection(connection);
        }
        return jobList;
    }
    public boolean upadateById(JobsModel job){
        Connection connection = null;
        try {
            connection = MySqlConfig.getConnection();
            String sql = "UPDATE job \n" +
                    "SET jobname  = ? WHERE id  = ?;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1,job.getJobName());
            ps.setInt(2,Integer.parseInt(job.getId()));
            System.out.println("Executed :"+sql);
            return ps.executeUpdate()>0
;        }catch (Exception e){
            System.out.println("Error at JobsRepository : updateById  "+e.getMessage());
        }finally {
            MySqlConfig.closeConnection(connection);
        }
        return false;
    }
    public boolean deleteById(String id){
        Connection connection = null;
        try {
            connection = MySqlConfig.getConnection();
            String sql = "DELETE FROM \n" +
                    "job WHERE id  =?;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1,Integer.parseInt(id));
            System.out.println("Executed :"+sql);
            return ps.executeUpdate()>0;
        }catch (Exception e){
            System.out.println("Error at deleteById");
            e.printStackTrace();
        }finally {
            MySqlConfig.closeConnection(connection);
        }
        return false;
    }

}
