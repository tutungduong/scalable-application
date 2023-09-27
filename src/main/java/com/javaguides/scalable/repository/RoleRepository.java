package com.javaguides.scalable.repository;

import com.javaguides.scalable.entity.Role;
import com.javaguides.scalable.entity.constants.RoleStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {

//    Role findByName(RoleStatus name);
}
