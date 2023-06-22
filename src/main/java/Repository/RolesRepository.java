package Repository;

import Config.MySqlConfig;
import Model.RolesModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class RolesRepository {
    public  RolesModel selectById(RolesModel object){
        Connection connection = null;
        try {
            connection = MySqlConfig.getConnection();
            String sql = "SELECT * FROM role WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,Integer.parseInt(object.getId()));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                RolesModel rolesModel = new RolesModel();
                rolesModel.setId(object.getId());
                rolesModel.setName(resultSet.getString("rolename"));
                rolesModel.setDescription(resultSet.getString("description"));
                return rolesModel;
            }
            System.out.println("Execute :"+sql);
        }catch (Exception e){
            System.out.println("Error Access Database");
            e.printStackTrace();
        }finally {
            MySqlConfig.closeConnection(connection);
        }
        return null;
    }
    public ArrayList<RolesModel> selectAll(){
        ArrayList <RolesModel> roleList = new ArrayList<>();
        Connection connection = null;
        try {
            connection = MySqlConfig.getConnection();
            String sql = "SELECT * FROM role ";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                    roleList.add( new RolesModel(resultSet.getString("id"),resultSet.getString("rolename"),resultSet.getString("description")));
            }
            System.out.println("Execute :"+sql);

        }catch (Exception e){
            System.out.println("Error at selectAll");
            e.printStackTrace();
        }finally {
            MySqlConfig.closeConnection(connection);
        }
        return roleList;
    }

    public boolean updateById(RolesModel role){
        Connection connection =null;
        try{
            connection = MySqlConfig.getConnection();
            String sql = "UPDATE `role` SET rolename =?,description =? WHERE id = ?;\n";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,role.getName());
            preparedStatement.setString(2,role.getDescription());
            preparedStatement.setString(3,role.getId());
            int resultSet =  preparedStatement.executeUpdate();
            System.out.println("Executed :"+sql);
            System.out.println("Check row executed :"+resultSet);
            return resultSet>0;

        }catch (Exception e){
            System.out.println("Error insert role at updateById");
            e.printStackTrace();
        }finally {
            MySqlConfig.closeConnection(connection);
        }
        return false;
    }
    public boolean insert(RolesModel role) {
        Connection connection =null;
        try {
            connection = MySqlConfig.getConnection();
            String sql = "INSERT INTO `role`(rolename,description) values(?,?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, role.getName());
            preparedStatement.setString(2, role.getDescription());
            int count = preparedStatement.executeUpdate();
            System.out.println("Execute :" + sql);
            return count>0;
        } catch (Exception e) {
            System.out.println("Error insert at RolesRepository");
            e.printStackTrace();
        } finally {
            MySqlConfig.closeConnection(connection);
        }
        return false;
    }
    public boolean deleteById(RolesModel role){
        Connection connection = null;
        try{
            connection = MySqlConfig.getConnection();
            String sql = "DELETE FROM `role`  WHERE id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, role.getId());
            System.out.println("Execute :"+sql);
            return  preparedStatement.executeUpdate()>0;

        }catch (Exception e){
            System.out.println("Error delete role at RoleDeleteController");
            e.printStackTrace();
        }finally {
            MySqlConfig.closeConnection(connection);
        }
        return false;
    }

}
