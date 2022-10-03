package com.cg.controller.rest;


import com.cg.model.Category;
import com.cg.model.Role;
import com.cg.model.dto.CategoryDTO;
import com.cg.model.dto.RoleDTO;
import com.cg.service.role.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/roles")
public class RoleRestController {

    @Autowired
    IRoleService roleService;

    @GetMapping
    public ResponseEntity<?> getAllRoles() {
        List<RoleDTO> roleDTOS = new ArrayList<>();

        List<Role> roles= roleService.findAll();

        for (Role role: roles){
            RoleDTO roleDTO = role.toRoleDTO();

            roleDTOS.add(roleDTO);
        }

        return new ResponseEntity<>(roleDTOS, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRoleById(@PathVariable Long id){
        Optional<Role> roleOptional = roleService.findById(id);
        Role role = roleOptional.get();
        RoleDTO roleDTO = role.toRoleDTO();

        return new ResponseEntity<>(roleDTO, HttpStatus.OK);
    }
}
