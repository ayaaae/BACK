package com.pfe.serviceabcense.Web;

import com.pfe.serviceabcense.Entities.Notification;
import com.pfe.serviceabcense.Service.NotificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationController {
    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/AllNotifications")
    public ResponseEntity<List<Notification>> getAllNotifications(){
        List<Notification> notifications =notificationService.findAllNotifications();
        return new ResponseEntity<>(notifications, HttpStatus.OK);
    }

    @GetMapping("/notification/{idNotification}")
    public ResponseEntity<Notification> getNotification(@PathVariable Long idNotification){
        Notification notification =notificationService.findNotificationById(idNotification);
        if (notification != null) {
            return ResponseEntity.ok(notification);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping("/createNotification")
    public ResponseEntity<Notification> addNotification(@RequestBody Notification notification){
        Notification newNotification =notificationService.addNotification(notification);
        return new ResponseEntity<>(newNotification,HttpStatus.CREATED);
    }

    @PutMapping("/update/{idNotification}")
    public ResponseEntity<Notification> updateNotification(@PathVariable Long idNotification, @RequestBody Notification notification){
        System.out.println(notification);
        Notification existingNotification = notificationService.findNotificationById(idNotification);

        if (existingNotification !=null) {
            notification.setId(idNotification);
            Notification updatedNotification = notificationService.updateNotification(notification);
            return ResponseEntity.ok(updatedNotification);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{idNotification}")
    public ResponseEntity<Void> deleteNotification(@PathVariable Long idNotification){
        notificationService.deleteNotificationById(idNotification);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // find notifications by employee id
    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<Notification>> getNotificationsByEmployeeId(@PathVariable Long employeeId) {
        List<Notification> notifications = notificationService.findNotificationByEmployeeId(employeeId);
        if (notifications.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(notifications);
        }
    }

}
