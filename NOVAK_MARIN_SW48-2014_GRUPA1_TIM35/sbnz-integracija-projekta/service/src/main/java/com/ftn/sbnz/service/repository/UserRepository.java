package com.ftn.sbnz.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftn.sbnz.model.models.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	User findByUsername(String username);
}
