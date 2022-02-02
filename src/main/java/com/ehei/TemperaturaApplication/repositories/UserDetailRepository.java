package com.ehei.TemperaturaApplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ehei.TemperaturaApplication.entities.User;


@Repository
public interface UserDetailRepository extends JpaRepository<User,Long> {
	
	User findByUserName(String userName);

}
