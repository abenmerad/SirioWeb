package com.sirioitalia.api.service;

import com.sirioitalia.api.exception.ResourceException;
import com.sirioitalia.api.model.Role;
import com.sirioitalia.api.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    private RoleRepository roleRepository;

    public RoleService() {
        super();
    }

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        super();

        this.roleRepository = roleRepository;
    }

    public Iterable <Role> getRoles() {
        return roleRepository.findAll();
    }

    public Role getRoleById(Long roleId) throws ResourceException {
        Role foundedRole = roleRepository.findById(role)
                .orElseThrow(() -> new ResourceException("FindRoleFailed", HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND));
    }
}
