package com.example.leaveapi.repository;

import com.example.leaveapi.entity.LeaveRequest;
import com.example.leaveapi.entity.User;
import com.example.leaveapi.enums.LeaveStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;
@RepositoryRestResource
public interface LeaveRepository extends JpaRepository<LeaveRequest, Long> {
    List<LeaveRequest> findByEmployee(User employee);
    List<LeaveRequest> findByStatus(LeaveStatus status);
}