package com.idat.notificationsservice.repository;

import com.idat.notificationsservice.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long>{
}
