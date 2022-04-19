package com.appSecure.codefellowship.Model;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;


@Entity
public class Post {

    @Setter(value= AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    private int id ;

    private String body;
    private String createdAt;

    public Post(String body, String createdAt) {
        this.body = body;
        this.createdAt = createdAt;
    }

    public Post() {
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    @ManyToOne
    User author ;

}
