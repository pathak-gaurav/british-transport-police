package com.gaurav.securelogin.repository;

import com.gaurav.securelogin.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository  extends JpaRepository<Authority,Long> {
}
