package com.example.sampleproject.entity;

import java.time.LocalDateTime;

// import javax.persistence.CascadeType;

//変更箇所(new)
// import javax.persistence.ManyToOne;

// import javax.persistence.NamedQuery;
// import java.io.Serializable;

// import javax.persistence.Entity;
// import javax.persistence.JoinColumn;

// import javax.persistence.JoinColumn;
//変更箇所
// import javax.persistence.ManyToOne;

// import javax.persistence.GeneratedValue;
// import javax.persistence.GenerationType;
// import javax.persistence.Id;
// import javax.persistence.Table;

// 変更１
// import javax.persistence.Entity;
// import javax.persistence.JoinColumn;
// import javax.persistence.OneToOne;

//変更箇所(new)
// @NamedQuery(name="MemberRegistrationEntity.findAll", query="SELECT * FROM MemberRegistrationEntity")
// 変更１
// @Entity(name = "movie")
public class Movie {
    /**
     *
     */
    // private static final long serialVersionUID = 1L;
    // @Id
    // @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private byte[] movie;
    private LocalDateTime created;
    // 変更箇所
    private int userId;

    // 変更〜
    private MemberRegistrationEntity user;

    private int views;

    private String title;

    private Image image;

    // 変更箇所(new)
    // @ManyToOne
    // @JoinColumn(name = "user_id")
    // private MemberRegistrationEntity memberRegistrationEntity;

    // 変更１
    // @OneToOne(mappedBy = "phone", cascade = CascadeType.ALL)
    // private Image image;

    public Movie() {

    };

    // public Movie(int id, String movie, LocalDateTime created){
    // this.id = id;
    // this.movie = movie;
    // this.created = created;

    // };

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    // usersテーブルができたため追加した項目
    //変更〜
    public MemberRegistrationEntity getUser() {
    return user;
    }
    public void setUser(MemberRegistrationEntity user) {
    this.user = user;
    }

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

    //変更箇所(new)
    // public MemberRegistrationEntity getMemberRegistrationEntity() {
    //     return memberRegistrationEntity;
    // }
    // public void setMemberRegistrationEntity(MemberRegistrationEntity memberRegistrationEntity) {
    //     this.memberRegistrationEntity = memberRegistrationEntity;
    // }
}