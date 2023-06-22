package Repository;

import Config.MySqlConfig;
import Dates.StringDate;
import Model.ProjectsModel;
import Model.UsersModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

public class ProjectsRepository {
    public boolean insert(ProjectsModel projectModel){
        Connection connection = null;
        try {
            connection = MySqlConfig.getConnection();
            String sql = " INSERT INTO project(projectname,datebegin,dateend,user_id) Values (?,?,?,?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,projectModel.getProjectName());
            preparedStatement.setTimestamp(2, Timestamp.valueOf(projectModel.getDateBegin()));
            preparedStatement.setTimestamp(3,Timestamp.valueOf(projectModel.getDateEnd()));
            preparedStatement.setString(4,projectModel.getUser().getId());
            System.out.println("Execute :"+sql);
            return preparedStatement.executeUpdate()>0;


        }catch (Exception e){
            System.out.println("Error at Insert ProjectModel");
            e.printStackTrace();
        } finally {
            MySqlConfig.closeConnection(connection);
        }
        return false;
    }
    public boolean update (ProjectsModel projectModel){
        Connection connection = null;
        try {
            connection = MySqlConfig.getConnection();
            String sql = " UPDATE project SET projectname = ?,datebegin = ?,dateend = ?,user_id =? WHERE id =?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,projectModel.getProjectName());
            preparedStatement.setTimestamp(2, Timestamp.valueOf(projectModel.getDateBegin()));
            preparedStatement.setTimestamp(3,Timestamp.valueOf(projectModel.getDateEnd()));
            preparedStatement.setString(4,projectModel.getUser().getId());

            preparedStatement.setInt(5,Integer.parseInt(projectModel.getId()));
            System.out.println("Execute :"+sql);
            return preparedStatement.executeUpdate()>0;

        }catch (Exception e){
            System.out.println("Error at Insert ProjectModel");
            e.printStackTrace();
        } finally {
            MySqlConfig.closeConnection(connection);
        }
        return false;
    }
    public boolean delete(ProjectsModel projectModel){
        //        DELETE FROM project WHERE id  = ?
        Connection connection = null;
        try {
            connection = MySqlConfig.getConnection();
            String sql = " DELETE FROM project WHERE id  = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,Integer.parseInt(projectModel.getId()));
            System.out.println("Execute :"+sql);
            return preparedStatement.executeUpdate()>0;

        }catch (Exception e){
            System.out.println("Error at delete ProjectModel");
            e.printStackTrace();
        } finally {
            MySqlConfig.closeConnection(connection);
        }
        return false;
    }
    public ArrayList<ProjectsModel> selectAll(){
        Connection connection = null;
        ArrayList <ProjectsModel> projectList = new ArrayList<ProjectsModel>();
        try {
            connection = MySqlConfig.getConnection();
            String sql = "SELECT p.id ,p.projectname ,p.datebegin ,p.dateend ,p.user_id ,u.fullname   from  project p join users u on u.id = p.user_id  ;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                ProjectsModel projectModel = new ProjectsModel();
                projectModel.setId(resultSet.getString("id"));
                projectModel.setProjectName((resultSet.getString("projectname")));
                projectModel.setDateBegin((resultSet.getString("datebegin")));
                projectModel.setDateEnd(resultSet.getString("dateend"));
                projectModel.setDateBegin(StringDate.stringTimeStampToStringDate(projectModel.getDateBegin()));
                projectModel.setDateEnd(StringDate.stringTimeStampToStringDate(projectModel.getDateEnd()));
                UsersModel usersModel = new UsersModel();
                usersModel.setId(resultSet.getString("user_id"));
                usersModel.setFullName(resultSet.getString("fullname"));
                projectModel.setUser(usersModel);
                projectList.add(projectModel);
            }
            System.out.println("Execute :"+sql);


        }catch (Exception e){
            System.out.println("Error at GroupWorkController");
            e.printStackTrace();
        } finally {
            MySqlConfig.closeConnection(connection);
        }
        return projectList;
    }
    public ArrayList<ProjectsModel> selectById(String id){
        Connection connection = null;
        ArrayList <ProjectsModel> projectList = new ArrayList<ProjectsModel>();
        try {
            connection = MySqlConfig.getConnection();
            String sql = "SELECT p.id ,p.projectname ,p.datebegin ,p.dateend ,p.user_id ,u.fullname   from  project p join users u on u.id = p.user_id  where p.id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,Integer.parseInt(id));
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                ProjectsModel projectModel = new ProjectsModel();
                projectModel.setId(resultSet.getString("id"));
                projectModel.setProjectName((resultSet.getString("projectname")));
                projectModel.setDateBegin((resultSet.getString("datebegin")));
                projectModel.setDateEnd(resultSet.getString("dateend"));
                projectModel.setDateBegin(StringDate.stringTimeStampToStringDate(projectModel.getDateBegin()));
                projectModel.setDateEnd(StringDate.stringTimeStampToStringDate(projectModel.getDateEnd()));
                UsersModel usersModel = new UsersModel();
                usersModel.setId(resultSet.getString("user_id"));
                usersModel.setFullName(resultSet.getString("fullname"));
                projectModel.setUser(usersModel);
                projectList.add(projectModel);
            }
            System.out.println("Execute :"+sql);


        }catch (Exception e){
            System.out.println("Error at GroupWorkController");
            e.printStackTrace();
        } finally {
            MySqlConfig.closeConnection(connection);
        }
        return projectList;
    }

    public ArrayList<ProjectsModel> selectByUserId(String userId){
        Connection connection = null;
        ArrayList <ProjectsModel> projectList = new ArrayList<ProjectsModel>();
        try {
            connection = MySqlConfig.getConnection();
            String sql = "SELECT p.id ,p.projectname ,p.datebegin ,p.dateend ,p.user_id ,u.fullname   from  project p join users u on u.id = p.user_id where user_id =?  ;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,Integer.parseInt(userId));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                ProjectsModel projectModel = new ProjectsModel();
                projectModel.setId(resultSet.getString("id"));
                projectModel.setProjectName((resultSet.getString("projectname")));
                projectModel.setDateBegin((resultSet.getString("datebegin")));
                projectModel.setDateEnd(resultSet.getString("dateend"));
                projectModel.setDateBegin(StringDate.stringTimeStampToStringDate(projectModel.getDateBegin()));
                projectModel.setDateEnd(StringDate.stringTimeStampToStringDate(projectModel.getDateEnd()));
                UsersModel usersModel = new UsersModel();
                usersModel.setId(resultSet.getString("user_id"));
                usersModel.setFullName(resultSet.getString("fullname"));
                projectModel.setUser(usersModel);
                projectList.add(projectModel);
            }
            System.out.println("Execute :"+sql);


        }catch (Exception e){
            System.out.println("Error at GroupWorkController");
            e.printStackTrace();
        } finally {
            MySqlConfig.closeConnection(connection);
        }
        return projectList;
    }

}
