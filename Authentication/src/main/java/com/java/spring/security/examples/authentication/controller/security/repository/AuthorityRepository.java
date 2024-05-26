package com.java.spring.security.examples.authentication.controller.security.repository;

import com.java.spring.security.examples.authentication.controller.security.repository.model.Authority;
import com.java.spring.security.examples.authentication.controller.security.repository.model.AuthorityId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("authority_repository")
public interface AuthorityRepository extends JpaRepository<Authority, AuthorityId> {

}
