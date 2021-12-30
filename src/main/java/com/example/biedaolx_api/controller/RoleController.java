package com.example.biedaolx_api.controller;

import com.example.biedaolx_api.entity.Role;
import com.example.biedaolx_api.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin("**")
public class RoleController {

    private final RoleService roleService;

    @GetMapping("/getRoles")
    public List<Role> getRoles(){
        return roleService.getRoles();
    }

    @PostMapping("/addRole")
    public Role addRole(@RequestBody Role role){
        return roleService.addRole(role);
    }

    @DeleteMapping("/deleteRole/{roleName}")
    public void deleteRole(@PathVariable("roleName") String roleName){
        roleService.deleteRole(roleName);
    }

    @PutMapping("/editRole")
    public Role editRole(@RequestBody Role role){
        return roleService.editRole(role);
    }
}
