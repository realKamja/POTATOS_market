package com.poteto.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import jakarta.persistence.Entity;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    private String title;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String registerDate;

    @Builder
    public Board(String title, String content, User user, LocalDateTime registerDate) {
        this.title = title;
        this.content = content;
        this.user = user;
        this.registerDate = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(registerDate);
    }

    //==���� �޼���==//
    public static Board createBoard(String title, String content, User user) {
        return Board.builder()
                .title(title).content(content).user(user)
                .registerDate(LocalDateTime.now())
                .build();
    }

    //==�ŷ� �޼���==//
    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}