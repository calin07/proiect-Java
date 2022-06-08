package com.example.web123.entity;
import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name="notepad")
public class Notepad {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(name = "title")
    private String title;
    @Column(name="content")
    private String content;
    @Column(name="date")
    private Date date;
    @Column(name="userID")
    private UUID userID;

    protected Notepad(){
    }

    public Notepad(String text, String content, Date date,UUID userID){
        this.title = text;
        this.content = content;
        this.date = date;
        this.userID=userID;
    }

    public UUID getId() {
         return id;
    }
    public String getTitle() {
        return title;
    }
    public String getContent() {
        return content;
    }
    public Date getDate() {
        return date;
    }
   public UUID getUserID() {
       return userID;
    }
}
