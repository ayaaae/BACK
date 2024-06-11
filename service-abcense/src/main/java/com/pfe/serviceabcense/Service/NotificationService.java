package com.pfe.serviceabcense.Service;

import com.pfe.serviceabcense.Entities.Notification;
import com.pfe.serviceabcense.Repository.NotificationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }
    public Notification addNotification(Notification notification){
        return notificationRepository.save(notification);
    }
    public Notification updateNotification(Notification notification){
        return notificationRepository.save(notification);
    }
    public List<Notification> findAllNotifications(){
        return notificationRepository.findAll();
    }
    public Notification findNotificationById(Long id){
        return notificationRepository.findById(id).get();
    }
    public void deleteNotificationById(Long id){
        notificationRepository.deleteById(id);
    }

    public List<Notification>  findNotificationByEmployeeId(long employeeId){
        return notificationRepository.findNotificationByEmployeeId(employeeId);
    }
}
