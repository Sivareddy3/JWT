package com.youtube.jwt.dao;

import com.youtube.jwt.entity.Employer;
import org.springframework.data.repository.CrudRepository;

public interface EmployerDao extends CrudRepository<Employer, String>{
    Employer findByEmployerName(String username);

    Employer findById(Long empId);
}
