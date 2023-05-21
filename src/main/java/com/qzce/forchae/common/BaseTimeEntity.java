package com.qzce.forchae.common;

import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity {

    @CreatedBy
    @Column(updatable = false)
    private String writeId;

    @LastModifiedBy
    private String modifyId;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime writeDate;

    @LastModifiedDate
    private LocalDateTime modifyDate;


}
