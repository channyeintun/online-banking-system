package com.code2.onlinebankingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.code2.onlinebankingsystem.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	User getUserByUsername(String username);

}
