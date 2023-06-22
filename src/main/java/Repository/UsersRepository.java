package Repository;

import Config.MySqlConfig;
import Model.RolesModel;
import Model.UsersModel;
import Service.UserService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class UsersRepository {
    public ArrayList<UsersModel> selectByEmailAndPassword (String email,String password ){
        ArrayList<UsersModel> userList = new ArrayList<>();
        Connection connection = null;
        try {
            String sql = "SELECT * \n" +
                    "FROM users u join `role` r ON \n" +
                    "u.role_id  = r.id WHERE email = ? and password =?;";
            connection = MySqlConfig.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,email);
            preparedStatement.setString(2,password);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                UsersModel usersModel = new UsersModel();
                usersModel.setId(resultSet.getString("id"));
                usersModel.setFullName(resultSet.getString("fullname"));
                usersModel.setAvatar(resultSet.getString("avatar"));
                usersModel.setEmail(email);
                usersModel.setPassword(password);
                usersModel.setAvatar(resultSet.getString("avatar"));
                usersModel.setCountry(resultSet.getString("country"));
                RolesModel role = new RolesModel();
                role.setId(resultSet.getString("role_id"));
                role.setName(resultSet.getString("rolename"));
                role.setDescription(resultSet.getString("description"));
                usersModel.setRolesModel(role);
                userList.add(usersModel);
            }
            System.out.println("Executed :"+sql);

        } catch (Exception e){
            System.out.println("Error SselectByEmailAndPassword at UsersRepository ");
            e.printStackTrace();
        } finally {
            MySqlConfig.closeConnection(connection);
        }
        return userList;
    }
    public ArrayList<UsersModel> selectByEmail(String email ){
        ArrayList<UsersModel> userList = new ArrayList<>();
        Connection connection = null;
        try {
            String sql = "SELECT * \n" +
                    "FROM users u join `role` r ON \n" +
                    "u.role_id  = r.id WHERE email = ?;";
            connection = MySqlConfig.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,email);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                UsersModel usersModel = new UsersModel();
                usersModel.setId(resultSet.getString("id"));
                usersModel.setFullName(resultSet.getString("fullname"));
                usersModel.setAvatar(resultSet.getString("avatar"));
                usersModel.setEmail(email);
                usersModel.setPassword(resultSet.getString("password"));
                usersModel.setAvatar(resultSet.getString("avatar"));
                usersModel.setCountry(resultSet.getString("country"));
                RolesModel role = new RolesModel();
                role.setId(resultSet.getString("role_id"));
                role.setName(resultSet.getString("rolename"));
                role.setDescription(resultSet.getString("description"));
                usersModel.setRolesModel(role);
                userList.add(usersModel);
            }
            System.out.println("Executed :"+sql);

        } catch (Exception e){
            System.out.println("Error SselectByEmailAndPassword at UsersRepository ");
            e.printStackTrace();
        } finally {
            MySqlConfig.closeConnection(connection);
        }
        return userList;
    }
    public  ArrayList<UsersModel> selectById(String id){
        ArrayList<UsersModel> userList = new ArrayList<>();
        Connection connection = null;
        try {
            String sql = "SELECT * \n" +
                    "FROM users u join `role` r ON \n" +
                    "u.role_id  = r.id WHERE u.id = ?;";
            connection = MySqlConfig.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                UsersModel usersModel = new UsersModel();
                usersModel.setId(resultSet.getString("id"));
                usersModel.setFullName(resultSet.getString("fullname"));
                usersModel.setAvatar(resultSet.getString("avatar"));
                usersModel.setEmail(resultSet.getString("email"));
                usersModel.setPassword(resultSet.getString("password"));
                usersModel.setAvatar(resultSet.getString("avatar"));
                usersModel.setCountry(resultSet.getString("country"));
                RolesModel role = new RolesModel(resultSet.getString("role_id"),resultSet.getString("rolename"),resultSet.getString("description"));
                usersModel.setRolesModel(role);
                userList.add(usersModel);
            }
            System.out.println("Executed :"+sql);

        } catch (Exception e){
            System.out.println("Error selectById at UsersRepository ");
            e.printStackTrace();
        } finally {
            MySqlConfig.closeConnection(connection);
        }
        return userList;
    }
    public ArrayList <UsersModel> sellectAll(){
        ArrayList<UsersModel> userList = new ArrayList<>();
        Connection connection = null;
        try {
            String sql = "SELECT * \n" +
                    "FROM users u join `role` r ON \n" +
                    "u.role_id  = r.id ;";
            connection = MySqlConfig.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                UsersModel usersModel = new UsersModel();
                usersModel.setId(resultSet.getString("id"));
                usersModel.setFullName(resultSet.getString("fullname"));
                usersModel.setAvatar(resultSet.getString("avatar"));
                usersModel.setEmail(resultSet.getString("email"));
                usersModel.setPassword(resultSet.getString("password"));
                usersModel.setAvatar(resultSet.getString("avatar"));
                usersModel.setCountry(resultSet.getString("country"));
                RolesModel role = new RolesModel();
                role.setId(resultSet.getString("role_id"));
                role.setName(resultSet.getString("rolename"));
                role.setDescription(resultSet.getString("description"));
                usersModel.setRolesModel(role);
                userList.add(usersModel);
            }
            System.out.println("Executed :"+sql);

        } catch (Exception e){
            System.out.println("Error selectById at UsersRepository ");
            e.printStackTrace();
        } finally {
            MySqlConfig.closeConnection(connection);
        }
        return userList;
    }
    public ArrayList <UsersModel> sellectByProjectId(String projectId){
        ArrayList<UsersModel> userList = new ArrayList<>();
        Connection connection = null;
        try {
            String sql = "select u.id ,u.fullname ,u.role_id \n" +
                    "FROM users u join workon w on u.id  = w.user_id \n" +
                    "\t\t\tJOIN (select id from project p WHERE p.id = ?) as p on p.id  = w.project_id \n" +
                    "\t\t\tJOIN `role` r on r.id =u.role_id ;";
            connection = MySqlConfig.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,Integer.parseInt(projectId));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                UsersModel usersModel = new UsersModel();
                usersModel.setId(resultSet.getString("id"));
                usersModel.setFullName(resultSet.getString("fullname"));
                RolesModel role = new RolesModel();
                role.setId(resultSet.getString("role_id"));
                usersModel.setRolesModel(role);
                userList.add(usersModel);
            }
            System.out.println("Executed :"+sql);

        } catch (Exception e){
            System.out.println("Error selectById at UsersRepository ");
            e.printStackTrace();
        } finally {
            MySqlConfig.closeConnection(connection);
        }
        return userList;
    }
    public boolean insert (UsersModel user){
            Connection connection = null;
            try {
                connection = MySqlConfig.getConnection();
                String sql = "INSERT  INTO users (fullname,email,password,phone,country,role_id,avatar)\n" +
                        "Values (?,?,?,?,?,?,?);";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1,user.getFullName());
                preparedStatement.setString(2,user.getEmail());
                preparedStatement.setString(3,user.getPassword());
                preparedStatement.setString(4,user.getPhone());
                preparedStatement.setString(5,user.getCountry());
                preparedStatement.setString(6,user.getRolesModel().getId());
                preparedStatement.setString(7,user.getAvatar());
                int result = preparedStatement.executeUpdate();
                System.out.println("Executed :"+sql);
                return result>0;

            }catch (Exception e){
                System.out.println("Error at insert ");
                e.printStackTrace();
            } finally {
                MySqlConfig.closeConnection(connection);
            }
            return false;
    }
    public boolean update(UsersModel user){
        Connection connection = null;
        try {
            connection = MySqlConfig.getConnection();
            String sql = "UPDATE users SET fullname =?,phone =?,email =?,password =?,role_id =?,country =?,gender =?,avatar =? WHERE id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,user.getFullName());
            preparedStatement.setString(2,user.getPhone());
            preparedStatement.setString(3,user.getEmail());
            preparedStatement.setString(4,user.getPassword());
            preparedStatement.setString(5,user.getRolesModel().getId());
            preparedStatement.setString(6,user.getCountry());
            preparedStatement.setString(7,user.getGender());
            preparedStatement.setString(8,user.getAvatar());
            preparedStatement.setString(9,user.getId());

            int result = preparedStatement.executeUpdate();
            System.out.println("Executed :"+sql);
            return result>0;

        }catch (Exception e){
            System.out.println("Error at update ");
            e.printStackTrace();
        } finally {
            MySqlConfig.closeConnection(connection);
        }
        return false;
    }
    public boolean deleteById(UsersModel user){
            // DELETE FROM users WHERE id  = 10;
        Connection connection = null;
        try {
            connection = MySqlConfig.getConnection();
            String sql = "DELETE FROM users WHERE id  = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,user.getId());
            int result = preparedStatement.executeUpdate();
            System.out.println("Executed :"+sql);
            return result>0;

        }catch (Exception e){
            System.out.println("Error at deleteById ");
            e.printStackTrace();
        } finally {
            MySqlConfig.closeConnection(connection);
        }
        return false;
    }
    public ArrayList<UsersModel> selectByRoleId(String roleId){
        ArrayList<UsersModel> usersModelArrayList = new ArrayList<>();
        Connection connection = null;
        try {
            connection = MySqlConfig.getConnection();
            String sql = "SELECT * from users u join role r on r.id = u.role_id WHERE role_id =? ;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,Integer.parseInt(roleId));
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                UsersModel usersModel = new UsersModel();
                usersModel.setId(resultSet.getString("id"));
                usersModel.setFullName(resultSet.getString("fullname"));
                usersModel.setAvatar(resultSet.getString("avatar"));
                usersModel.setEmail(resultSet.getString("email"));
                usersModel.setPassword(resultSet.getString("password"));
                usersModel.setAvatar(resultSet.getString("avatar"));
                usersModel.setCountry(resultSet.getString("country"));
                RolesModel role = new RolesModel();
                role.setId(resultSet.getString("role_id"));
                role.setName(resultSet.getString("rolename"));
                role.setDescription(resultSet.getString("description"));
                usersModel.setRolesModel(role);
                usersModelArrayList.add(usersModel);
            }
        }catch (Exception e){
            System.out.println("Error at selectByRoleId");
            e.printStackTrace();
        }finally {
            MySqlConfig.closeConnection(connection);
        }
        return usersModelArrayList;
    }




}
