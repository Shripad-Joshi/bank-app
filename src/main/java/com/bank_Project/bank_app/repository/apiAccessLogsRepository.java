package com.bank_Project.bank_app.repository;

import com.bank_Project.bank_app.entity.apiaccesslogs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface apiAccessLogsRepository extends JpaRepository<apiaccesslogs,Long> {
}
