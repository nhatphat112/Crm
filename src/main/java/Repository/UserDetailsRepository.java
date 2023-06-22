package Repository;

import Config.MySqlConfig;
import Model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class UserDetailsRepository {
    private UsersRepository usersRepository = new UsersRepository();
    public ArrayList<JobOfUserDetailsModel> selectJobListByUserId(UsersModel user){
        ArrayList<JobOfUserDetailsModel> jobOfUserDetailsList = new ArrayList<>();
        Connection connection = null;
        try {
            String sql = "SELECT   s.id as status_id , s.statusname , w.datebegin ,w.dateend ,j.id  as job_id,j.jobname \n" +
                    "FROM (select u.id, u.fullname ,u.email ,u.avatar FROM users u WHERE id = ?)\n" +
                    "AS u\n" +
                    "join workon w \n" +
                    "on u.id  = w.user_id\n" +
                    "JOIN status s \n" +
                    "on s.id  = w.status_id \n" +
                    "JOIN job j \n" +
                    "on j.id  = w.job_id ;";
            connection = MySqlConfig.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,user.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                JobOfUserDetailsModel job = new JobOfUserDetailsModel();
                job.setJob(new JobsModel(resultSet.getString("job_id"),resultSet.getString("jobname")));
                job.setStatus(new StatusModel(resultSet.getString("status_id"),resultSet.getString("jobname")));
                job.setDateBegin(resultSet.getString("datebegin"));
                job.setDateEnd(resultSet.getString("dateend"));
                jobOfUserDetailsList.add(job);
            }

            System.out.println("Executed :"+sql);

        } catch (Exception e){
            System.out.println("Error selectById at UsersRepository ");
            e.printStackTrace();
        } finally {
            MySqlConfig.closeConnection(connection);
        }
        return jobOfUserDetailsList;
    }
    public ArrayList<UsersModel> selectUserById(UsersModel user){
        return usersRepository.selectById(user.getId());

    }
    //u.fullname ,u.email ,u.avatar,
}
