package com.gaurav.securelogin.repository;

import com.gaurav.securelogin.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
}
