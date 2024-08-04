package com.bank_Project.bank_app.repository;

import com.bank_Project.bank_app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface userRepository extends JpaRepository<User,Integer> {
}
