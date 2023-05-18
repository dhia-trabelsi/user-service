package com.notification.History;

import java.sql.Date;

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
@Table(name = "history")
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String message;

    private Date date;

    @Enumerated(EnumType.STRING)
    private HistoryType type;

    private Integer user;
}
