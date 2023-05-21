package com.qzce.forchae.board;

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

@Getter
@NoArgsConstructor
@Entity
public class Board extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "B_NO")
    private Long bno;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "CONTENT")
    private String content;

    @CreatedBy
    @Column(name = "WRITE_ID", updatable = false)
    private String writeId;

    @LastModifiedBy
    @Column(name = "MODIFY_ID")
    private String modifyId;

    @CreatedDate
    @Column(name = "WRITE_DATE", updatable = false)
    private LocalDateTime writeDate;

    @LastModifiedDate
    @Column(name = "MODIFY_DATE")
    private LocalDateTime modifyDate;

    @Builder
    public Board(Long bno, String title, String content) {
        this.bno = bno;
        this.title = title;
        this.content = content;
    }

}
