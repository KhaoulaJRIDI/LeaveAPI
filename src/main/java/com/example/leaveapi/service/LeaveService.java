package com.example.leaveapi.service;

import com.example.leaveapi.entity.LeaveRequest;
import com.example.leaveapi.entity.User;
import com.example.leaveapi.enums.LeaveStatus;
import com.example.leaveapi.repository.LeaveRepository;
import com.example.leaveapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveService {
    @Autowired private LeaveRepository leaveRepo;
    @Autowired private UserRepository userRepo;
    @Autowired private JwtService jwtService;

    public LeaveRequest createLeave(LeaveRequest request) {
        String username = jwtService.getCurrentUsername();
        User user = userRepo.findByUsername(username).orElseThrow();
        request.setEmployee(user);
        return leaveRepo.save(request);
    }

    public List<LeaveRequest> getLeavesForCurrentUser() {
        String username = jwtService.getCurrentUsername();
        User user = userRepo.findByUsername(username).orElseThrow();
        return leaveRepo.findByEmployee(user);
    }

    public List<LeaveRequest> getPendingLeaves() {
        return leaveRepo.findByStatus(LeaveStatus.PENDING);
    }

    public LeaveRequest approveLeave(Long id) {
        LeaveRequest leave = leaveRepo.findById(id).orElseThrow();
        leave.setStatus(LeaveStatus.APPROVED);
        return leaveRepo.save(leave);
    }

    public LeaveRequest rejectLeave(Long id) {
        LeaveRequest leave = leaveRepo.findById(id).orElseThrow();
        leave.setStatus(LeaveStatus.REJECTED);
        return leaveRepo.save(leave);
    }
}
