package com.example.sampleproject.entity;

import java.time.LocalDateTime;

// import javax.persistence.JoinColumn;
//変更箇所
// import javax.persistence.ManyToOne;

// import javax.persistence.Entity;
// import javax.persistence.GeneratedValue;
// import javax.persistence.GenerationType;
// import javax.persistence.Id;
// import javax.persistence.Table;

// @Entity
// @Table(name="movie")
public class Movie {
    // @Id
    // @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private byte[] movie;
    private LocalDateTime created;
    // 変更箇所
    private int userId;

    // @ManyToOne
    // @JoinColumn(name = "user_id") // 付けなくてもOK
    // private Account account;
    // @ManyToOne
    // private Account account;
    // usersテーブルができたため追加した項目
    // private int userId;
    // private User user;

    //変更箇所
    // @ManyToOne
    // MemberRegistrationEntity users;

    public Movie() {

    };

    // public Movie(int id, String movie, LocalDateTime created){
    // this.id = id;
    // this.movie = movie;
    // this.created = created;

    // };

    // usersテーブルができたため追加した項目
    // public User getUser() {
    // return user;
    // }

    // public void setUser(User user) {
    // this.user = user;
    // }

    // public int getUserId() {
    // return userId;
    // }

    // public void setUserId(int userId) {
    // this.userId = userId;
    // }

    // 変更箇所
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    // ここまで

    public int getId() {
        return id;
    }

    public byte[] getMovie() {
        return movie;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMovie(byte[] movie) {
        this.movie = movie;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }
}