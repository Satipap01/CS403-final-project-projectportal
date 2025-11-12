package th.ac.tu.cs.projectportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import th.ac.tu.cs.projectportal.repository.UserRepository;
import th.ac.tu.cs.projectportal.entity.Role;

import java.time.LocalDateTime;

@Service
public class UserCleanupService {

    @Autowired
    private UserRepository userRepository;

    // ลบ Student/Staff และ Guest ที่ยังไม่อนุมัติเกินเวลา
    @Scheduled(cron = "0 0 0 * * *") // ทุกวันเที่ยงคืน
    public void removeExpiredPendingUsers() {
        LocalDateTime now = LocalDateTime.now();
        var expiredUsers = userRepository.findByApprovedFalseAndApprovalExpireAtBefore(now);
        if (!expiredUsers.isEmpty()) {
            userRepository.deleteAll(expiredUsers);
        }
    }

    // ลบ Guest ที่อนุมัติแล้วและเกิน guestExpireAt
    @Scheduled(cron = "0 0 1 * * *") // ทุกวันตี 1
    public void removeExpiredApprovedGuests() {
        LocalDateTime now = LocalDateTime.now();
        var expiredGuests = userRepository.findByRoleAndApprovedTrueAndGuestExpireAtBefore(Role.Guest, now);
        if (!expiredGuests.isEmpty()) {
            userRepository.deleteAll(expiredGuests);
        }
    }
}