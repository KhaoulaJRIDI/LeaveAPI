package com.example.leaveapi.controller;

import com.example.leaveapi.dto.AuthRequest;
import com.example.leaveapi.dto.RegisterRequest;
import com.example.leaveapi.entity.LeaveRequest;
import com.example.leaveapi.service.AuthService;
import com.example.leaveapi.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/leave")
public class LeaveController {
    @Autowired
    private LeaveService leaveService;

    @PreAuthorize("hasRole('USER')")
    @PostMapping
    public ResponseEntity<?> requestLeave(@RequestBody LeaveRequest request) {
        return ResponseEntity.ok(leaveService.createLeave(request));
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping
    public ResponseEntity<?> getMyLeaves() {
        return ResponseEntity.ok(leaveService.getLeavesForCurrentUser());
    }

    @PreAuthorize("hasRole('MANAGER')")
    @GetMapping("/pending")
    public ResponseEntity<?> getPendingLeaves() {
        return ResponseEntity.ok(leaveService.getPendingLeaves());
    }

    @PreAuthorize("hasRole('MANAGER')")
    @PutMapping("/{id}/approve")
    public ResponseEntity<?> approve(@PathVariable Long id) {
        return ResponseEntity.ok(leaveService.approveLeave(id));
    }

    @PreAuthorize("hasRole('MANAGER')")
    @PutMapping("/{id}/reject")
    public ResponseEntity<?> reject(@PathVariable Long id) {
        return ResponseEntity.ok(leaveService.rejectLeave(id));
    }

}