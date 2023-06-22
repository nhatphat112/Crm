package Repository;

import Config.MySqlConfig;
import Model.StatusModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class StatusRepository {
    public ArrayList<StatusModel> selectAll(){
        ArrayList<StatusModel> statusList = new ArrayList<>();
        Connection connection = null;
        try {
                connection  = MySqlConfig.getConnection();
                String sql = "Select * from status";
                PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()){
                StatusModel status = new StatusModel(resultSet.getString("id"),resultSet.getString("statusname"));
                statusList.add(status);
            }
        }catch (Exception e){
            System.out.println("Error at StatusRepository : selectAll"+e.getMessage());
        }finally {
            MySqlConfig.closeConnection(connection);
        }
        return statusList;
    }
}
