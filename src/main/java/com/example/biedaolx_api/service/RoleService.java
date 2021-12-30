package com.example.biedaolx_api.service;
import com.example.biedaolx_api.entity.Role;
import com.example.biedaolx_api.repository.RoleRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepo roleRepo;

    //Adding a role
    public Role addRole(Role role){
        if(roleRepo.findByName(role.getName()).isPresent()){
            throw new IllegalArgumentException("This role already exists");
        }else {
            return roleRepo.save(role);
        }
    }

    //Deleting a role
    public void deleteRole(String name){
        roleRepo.delete(roleRepo.findByName(name).orElseThrow());
    }

    //Editing a role
    public Role editRole(Role role){
        if(roleRepo.findById(role.getId()).isPresent()){
            return roleRepo.save(role);
        }else {
            throw new IllegalArgumentException("This role does not exist");
        }
    }

    //Get roles
    public List<Role> getRoles() {
        return roleRepo.findAll();
    }
}
