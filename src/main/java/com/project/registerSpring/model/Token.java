package com.project.registerSpring.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import java.time.LocalDateTime;

@Entity
@Table(name = "tokens",schema = "public")
@Audited
@AuditTable(value = "tokens_audit",schema = "audit")
@AllArgsConstructor
@Getter @Setter
public class Token extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    @Column(name = "tokens",nullable = false)
    private String token;

    @Column(name = "createdAt",nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "expiresAt",nullable = false)
    private LocalDateTime expiresAt;

    @Column(name = "confirmedAt",nullable = false)
    private LocalDateTime confirmedAt;


}
