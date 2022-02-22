package com.youtube.jwt.controller;

import com.youtube.jwt.entity.Employer;
import com.youtube.jwt.entity.Job;
import com.youtube.jwt.entity.User;
import com.youtube.jwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.persistence.PostUpdate;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostConstruct
    public void initRoleAndUser() {
        userService.initRoleAndUser();
    }

    @PostMapping({"/registerNewUser"})
    public User registerNewUser(@RequestBody User user) {
        return userService.registerNewUser(user);
    }

    @PostMapping({"/newEmployees"})
    public Employer newEmployees(@RequestBody Employer employer){ return userService.newEmployee(employer);}

    @PostMapping({"/postJob"})
    public Job postJob(@RequestBody Job job){
        return userService.postJob(job);}

    @PutMapping({"/updateJob"})
    public Job updateJob(@RequestBody Job job){
        return userService.updateJob(job);
    }

    @GetMapping({"/forAdmin"})
    @PreAuthorize("hasRole('Admin')")
    public String forAdmin(){
        return "This URL is only accessible to the admin";
    }

    @GetMapping({"/forUser"})
    @PreAuthorize("hasRole('User')")
    public String forUser(){
        return "This URL is only accessible to the user";
    }

    @GetMapping({"/getAllUsers"})
    @PreAuthorize("hasRole('Admin')")
    public List<User> getAllUsers(){
        return userService.getAllRegisteredUsers();
    }

    @GetMapping({"/getAllEmployees"})
    @PreAuthorize("hasRole('Admin')")
    public List<Employer> getAllEmployer(){
        return userService.getAllEmployer();
    }

    @DeleteMapping({"/deleteEmployee"})
    @PreAuthorize("hasRole('Admin')")
    public String deleteEmployee(@RequestParam String username){
        return userService.deleteEmployee(username);
    }

    @DeleteMapping({"/deleteUser"})
    @PreAuthorize("hasRole('Admin')")
    public String deleteUsers(@RequestParam String username){
        return userService.deleteUser(username);
    }

    @DeleteMapping({"/deleteJob"})
    @PreAuthorize("hasRole('Admin')")
    public String deleteJob(@RequestParam Long jobId){
        return userService.deleteJob(jobId);
    }

    @PostMapping("/apply")
    public String applyToJob(@RequestParam Long jobId, @RequestParam Long empId ){
        return userService.applyToJob(jobId,empId);
    }

    @GetMapping({"/getAllJobs"})
    @PreAuthorize("hasRole('Admin')")
    public List<Job> getAllJobs(){return userService.getAllJobs();}

}
