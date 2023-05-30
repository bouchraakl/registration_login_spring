package com.project.registerSpring.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@MappedSuperclass
public class AbstractEntity {

    @Id
    @Getter
    @SequenceGenerator(name = "log_sequence",sequenceName = "log_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "log_sequence")
    @Column(name = "id", unique = true)
    private Long id;

    @Getter
    @Setter
    @Column(name = "register")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime register;

    @Getter
    @Setter
    @Column(name = "update")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime update;

    @PrePersist
    private void updateRegisterOnPersist() {
        this.register = LocalDateTime.now();
    }

    @PreUpdate
    private void updateOnUpdate() {
        this.update = LocalDateTime.now();
    }

}