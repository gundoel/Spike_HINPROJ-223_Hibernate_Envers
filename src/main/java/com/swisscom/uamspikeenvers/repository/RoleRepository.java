package com.swisscom.uamspikeenvers.repository;

import com.swisscom.uamspikeenvers.model.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {
   Role findByRolename(String rolename);
}