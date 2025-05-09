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

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping
    public ResponseEntity<?> requestLeave(@RequestBody LeaveRequest request) {
        return ResponseEntity.ok(leaveService.createLeave(request));
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/all")
    public ResponseEntity<?> getMyLeaves() {
        return ResponseEntity.ok(leaveService.getLeavesForCurrentUser());
    }

    @PreAuthorize("hasAuthority('MANAGER')")
    @GetMapping("/pending")
    public ResponseEntity<?> getPendingLeaves() {
        return ResponseEntity.ok(leaveService.getPendingLeaves());
    }

    @PreAuthorize("hasAuthority('MANAGER')")
    @PutMapping("/{id}/approve")
    public ResponseEntity<?> approve(@PathVariable Long id) {
        return ResponseEntity.ok(leaveService.approveLeave(id));
    }

    @PreAuthorize("hasAuthority('MANAGER')")
    @PutMapping("/{id}/reject")
    public ResponseEntity<?> reject(@PathVariable Long id) {
        return ResponseEntity.ok(leaveService.rejectLeave(id));
    }

}