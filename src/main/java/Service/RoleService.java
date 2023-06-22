package Service;

import Model.RolesModel;
import Repository.RolesRepository;

import java.util.ArrayList;

public class RoleService {
    private RolesRepository rolesRepository = new RolesRepository();
    public boolean update(RolesModel role){
        return rolesRepository.updateById(role);
    }
    public boolean insert(RolesModel role){
        return rolesRepository.insert(role);
    }
    public boolean delete(RolesModel role){
        return rolesRepository.deleteById(role);
    }

    public ArrayList<RolesModel> getRoleList(){
        return rolesRepository.selectAll();
    }

}
