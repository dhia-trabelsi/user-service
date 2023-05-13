package com.notification.Notification;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "notification")
public class notification {
    
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String message;

    private Integer user;

    private Date date;

    @Enumerated(EnumType.STRING)
    private notifType type;

    
}
