package com.youtube.jwt.dao;

import com.youtube.jwt.entity.Job;
import org.springframework.data.repository.CrudRepository;

public interface JobDao extends CrudRepository<Job, String>{
    Job findByJobId(Long jobId);
}

