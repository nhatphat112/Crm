package Repository;

import Config.MySqlConfig;
import Model.WorkOnModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

public class WorkOnRepository {
    public boolean insert(WorkOnModel workOn) {
        Connection connection = null;
        try {
            connection = MySqlConfig.getConnection();
            String sql = "INSERT INTO workon (user_id,project_id,job_id,status_id,datebegin,dateend)\n" +
                    "Values (?,?,?,?,?,?);";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, workOn.getUsers().getId());
            ps.setString(2, workOn.getProjects().getId());
            ps.setString(3, workOn.getJobs().getId());
            ps.setString(4, workOn.getStatus().getId());
            ps.setString(5, workOn.getDateBegin());
            ps.setString(6, workOn.getDateEnd());
            System.out.println("Executed :" + sql);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Error at WorkOnRepository : insert " + e.getMessage());
        } finally {

        }
        return false;
    }

    public boolean updateAllById(WorkOnModel workOnCurrent, WorkOnModel workOnUpdate) {
        Connection connection = null;
        try {
            connection = MySqlConfig.getConnection();
            String sql = "UPDATE workon \n" +
                    "SET user_id =?,project_id =?,job_id =?,status_id =?,datebegin =?,dateend =? WHERE user_id =? and project_id =? and  job_id =? and status_id =?;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(workOnUpdate.getUsers().getId()));
            ps.setInt(2, Integer.parseInt(workOnUpdate.getProjects().getId()));
            ps.setInt(3, Integer.parseInt(workOnUpdate.getJobs().getId()));
            ps.setInt(4, Integer.parseInt(workOnUpdate.getStatus().getId()));
            ps.setTimestamp(5, Timestamp.valueOf(workOnUpdate.getDateBegin()));
            ps.setTimestamp(6, Timestamp.valueOf(workOnUpdate.getDateEnd()));
            ps.setInt(7, Integer.parseInt(workOnCurrent.getUsers().getId()));
            ps.setInt(8, Integer.parseInt(workOnCurrent.getProjects().getId()));
            ps.setInt(9, Integer.parseInt(workOnCurrent.getJobs().getId()));
            ps.setInt(10, Integer.parseInt(workOnCurrent.getStatus().getId()));
            System.out.println("Executed :"+sql);
            return ps.executeUpdate()>0;

        } catch (Exception e) {
            System.out.println("Error at updateById");
            e.printStackTrace();

        } finally {
            MySqlConfig.closeConnection(connection);
        }
        return false;
    }
    public boolean deleteById(WorkOnModel workOn){
        Connection connection = null;
        try {
            connection = MySqlConfig.getConnection();
            String sql = "DELETE FROM \n" +
                    "workon WHERE user_id  =? AND project_id = ? AND job_id = ? AND status_id  = ?;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1,Integer.parseInt(workOn.getUsers().getId()));
            ps.setInt(2,Integer.parseInt(workOn.getProjects().getId()));
            ps.setInt(3,Integer.parseInt(workOn.getJobs().getId()));
            ps.setInt(4,Integer.parseInt(workOn.getStatus().getId()));
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
    public ArrayList<WorkOnModel> selectByUserId(String userId){
        ArrayList<WorkOnModel> workOnList = new ArrayList<>();
        Connection connection = null;
        try {
            connection = MySqlConfig.getConnection();
            String sql = "SELECT project_id ,p.projectname ,job_id , j.jobname ,status_id ,s.statusname, w.datebegin ,w.dateend \n" +
                    "FROM  workon w \n" +
                    "join (select id from users u WHERE id = ?) AS u on w.user_id  = u.id \n" +
                    "join project p on p.id  = w.project_id \n" +
                    "JOIN job j ON j.id = w.job_id \n" +
                    "JOIN status s ON s.id = w.status_id ;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1,Integer.parseInt(userId));
            ResultSet resultSet = ps.executeQuery();
            while(resultSet.next()){
                WorkOnModel workOn = new WorkOnModel();
                workOn.getProjects().setId(resultSet.getString("project_id"));
                workOn.getProjects().setProjectName(resultSet.getString("projectname"));
                workOn.getJobs().setJobName(resultSet.getString("jobname"));
                workOn.getJobs().setId(resultSet.getString("job_id"));
                workOn.getStatus().setId(resultSet.getString("status_id"));
                workOn.getStatus().setStatusName(resultSet.getString("statusname"));
                workOn.setDateBegin(resultSet.getString("datebegin"));
                workOn.setDateEnd(resultSet.getString("dateEnd"));
                workOnList.add(workOn);
            }
        }catch (Exception e){
            System.out.println("Error at selectByUserId");
            e.printStackTrace();
        }finally {
            MySqlConfig.closeConnection(connection);
        }
        return workOnList;
    }
    public boolean updateStatusIdById(WorkOnModel workOnCurrent, String statusIdUpdate) {
        Connection connection = null;
        try {
            connection = MySqlConfig.getConnection();
            String sql = "UPDATE workon \n" +
                    "SET status_id =? WHERE user_id =? and project_id =? and  job_id =? and status_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(statusIdUpdate));
            ps.setInt(2, Integer.parseInt(workOnCurrent.getUsers().getId()));
            ps.setInt(3, Integer.parseInt(workOnCurrent.getProjects().getId()));
            ps.setInt(4, Integer.parseInt(workOnCurrent.getJobs().getId()));
            ps.setInt(5, Integer.parseInt(workOnCurrent.getStatus().getId()));
            System.out.println("Executed :"+sql);
            return ps.executeUpdate()>0;

        } catch (Exception e) {
            System.out.println("Error at updateById");
            e.printStackTrace();

        } finally {
            MySqlConfig.closeConnection(connection);
        }
        return false;
    }
    public ArrayList<WorkOnModel> selectById(WorkOnModel workOn){
        ArrayList<WorkOnModel> workOnList = new ArrayList<>();
        Connection connection = null;
        try {
            connection = MySqlConfig.getConnection();
            String sql = "SELECT user_id, u.fullname, project_id ,p.projectname ,job_id , j.jobname ,status_id ,s.statusname, w.datebegin ,w.dateend \n" +
                    "FROM  workon w \n" +
                    "join (select id, fullname from users  WHERE id = ?) AS u on w.user_id  = u.id \n" +
                    "join (select id, projectname from project p   WHERE id = ?) p on p.id  = w.project_id \n" +
                    "JOIN (select id, jobname from job j  WHERE id = ?) j ON j.id = w.job_id \n" +
                    "JOIN (select id, statusname from status s   WHERE id = ?) s ON s.id = w.status_id ;;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1,Integer.parseInt(workOn.getUsers().getId()));
            ps.setInt(2,Integer.parseInt(workOn.getProjects().getId()));
            ps.setInt(3,Integer.parseInt(workOn.getJobs().getId()));
            ps.setInt(4,Integer.parseInt(workOn.getStatus().getId()));
            ResultSet resultSet = ps.executeQuery();
            while(resultSet.next()){
                WorkOnModel workOnModel = new WorkOnModel();
                workOnModel.getUsers().setId(workOn.getUsers().getId());
                workOnModel.getUsers().setFullName(resultSet.getString("fullname"));
                workOnModel.getProjects().setId(workOn.getProjects().getId());
                workOnModel.getProjects().setProjectName(resultSet.getString("projectname"));
                workOnModel.getJobs().setJobName(resultSet.getString("jobname"));
                workOnModel.getJobs().setId(workOn.getJobs().getId());
                workOnModel.getStatus().setId(workOn.getStatus().getId());
                workOnModel.getStatus().setStatusName(resultSet.getString("statusname"));
                workOnModel.setDateEnd(resultSet.getString("dateend"));
                workOnModel.setDateBegin(resultSet.getString("datebegin"));
                workOnList.add(workOnModel);
            }
        }catch (Exception e){
            System.out.println("Error at selectById");
            e.printStackTrace();
        }finally {
            MySqlConfig.closeConnection(connection);
        }
        return workOnList;
    }
    public ArrayList<WorkOnModel> selectAll(){
        ArrayList<WorkOnModel> workOnList = new ArrayList<>();
        Connection connection = null;
        try {
            connection = MySqlConfig.getConnection();
            String sql = "SELECT w.user_id ,u.fullname, project_id ,p.projectname ,w.job_id , j.jobname ,status_id ,s.statusname, w.datebegin ,w.dateend \n" +
                    "FROM  workon w \n" +
                    "join users   u on w.user_id  = u.id \n" +
                    "join  project p on p.id  = w.project_id \n" +
                    "JOIN job j ON j.id = w.job_id \n" +
                    "JOIN status s ON s.id = w.status_id ;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            while(resultSet.next()){
                WorkOnModel workOnModel = new WorkOnModel();
                workOnModel.getUsers().setId(resultSet.getString("user_id"));
                workOnModel.getUsers().setFullName(resultSet.getString("fullname"));
                workOnModel.getProjects().setId(resultSet.getString("project_id"));
                workOnModel.getProjects().setProjectName(resultSet.getString("projectname"));
                workOnModel.getJobs().setJobName(resultSet.getString("jobname"));
                workOnModel.getJobs().setId(resultSet.getString("job_id"));
                workOnModel.getStatus().setId(resultSet.getString("status_id"));
                workOnModel.getStatus().setStatusName(resultSet.getString("statusname"));
                workOnModel.setDateEnd(resultSet.getString("datebegin"));
                workOnModel.setDateBegin(resultSet.getString("dateEnd"));
                workOnList.add(workOnModel);
            }
        }catch (Exception e){
            System.out.println("Error at selectById");
            e.printStackTrace();
        }finally {
            MySqlConfig.closeConnection(connection);
        }
        return workOnList;
    }

}
