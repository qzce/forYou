package com.qzce.forchae.comment;

import com.qzce.forchae.common.BaseTimeEntity;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "BC_NO")
    private Long bcno;

    @NotNull
    @Column(name = "B_NO")
    private Long bno;

    @Column(name = "BC_CONTENT")
    private String bcContent;

    @CreatedBy
    @Column(name = "WRITE_ID")
    private String writeId;

    @LastModifiedBy
    @Column(name = "MODIFY_ID")
    private String modifyId;

    @CreatedDate
    @Column(name = "WRITE_DATE")
    private LocalDateTime writeDate;

    @LastModifiedDate
    @Column(name = "MODIFY_DATE")
    private LocalDateTime modifyDate;

    @Builder
    public Comment(Long bcno, Long bno, String bcContent) {
        this.bcno = bcno;
        this.bno = bno;
        this.bcContent = bcContent;
    }
}
