package com.youtube.jwt.service;

import com.youtube.jwt.dao.EmployerDao;
import com.youtube.jwt.dao.JobDao;
import com.youtube.jwt.dao.RoleDao;
import com.youtube.jwt.dao.UserDao;
import com.youtube.jwt.entity.Employer;
import com.youtube.jwt.entity.Job;
import com.youtube.jwt.entity.Role;
import com.youtube.jwt.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private EmployerDao employerDao;

    @Autowired
    private JobDao jobDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserService() {
    }

    public void initRoleAndUser() {

        Role adminRole = new Role();
        adminRole.setRoleName("Admin");
        adminRole.setRoleDescription("Admin role");
        roleDao.save(adminRole);

        Role userRole = new Role();
        userRole.setRoleName("User");
        userRole.setRoleDescription("Default role for newly created record");
        roleDao.save(userRole);

        User adminUser = new User();
        adminUser.setUserName("admin123");
        adminUser.setUserPassword(getEncodedPassword("admin@pass"));
        adminUser.setUserFirstName("admin");
        adminUser.setUserLastName("admin");
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        adminUser.setRole(adminRoles);
        userDao.save(adminUser);

        User user = new User();
        user.setUserName("raj123");
        user.setUserPassword(getEncodedPassword("raj@123"));
        user.setUserFirstName("raj");
        user.setUserLastName("sharma");
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(userRole);
        user.setRole(userRoles);
        userDao.save(user);
    }

    public Employer newEmployee(Employer employer){
        return employerDao.save(employer);
    }

    public Job postJob(Job job) {
        System.out.println("Inside");
        return jobDao.save(job);}

    public String deleteUser(String username){
        User user = userDao.findByUserName(username);
        userDao.delete(user);
        return "Deleted";
    }

    public String deleteEmployee(String username){
        Employer employee = employerDao.findByEmployerName(username);
        employerDao.delete(employee);
        return "Deleted";
    }

    public String deleteJob(Long jobId){
        Job job1 = jobDao.findByJobId(jobId);
        jobDao.delete(job1);
        return "Deleted";
    }

    public Job updateJob(Job job){
        Job job1 = jobDao.findByJobId(job.getJobId());
        job1.setApplied(job.getApplied());
        job1.setDomain(job.getDomain());
        job1.setTechnology(job.getTechnology());
        job1.setJobTitle(job.getJobTitle());
        return jobDao.save(job1);
    }

    public User registerNewUser(User user) {
        Role role = roleDao.findById("User").get();
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(role);
        user.setRole(userRoles);
        user.setUserPassword(getEncodedPassword(user.getUserPassword()));

        return userDao.save(user);
    }

    public List<User> getAllRegisteredUsers() {
        return (List<User>) userDao.findAll();
    }

    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }

    public List<Employer> getAllEmployer() { return (List<Employer>) employerDao.findAll();}


    public String applyToJob(Long jobId, Long empId) {
        Job job1 = jobDao.findByJobId(jobId);
        Employer employee1 = employerDao.findById(empId);
        String appliedEmployees;
        if(job1.getApplied() == null){
            appliedEmployees = employee1.getEmployerName();
        }else {
            appliedEmployees = job1.getApplied() + "," + employee1.getEmployerName();
        }
        job1.setApplied(appliedEmployees);
        jobDao.save(job1);
        return "Applied";
    }

    public List<Job> getAllJobs() {return (List<Job>) jobDao.findAll();}

}
