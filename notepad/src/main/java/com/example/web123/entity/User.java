package com.example.web123.entity;
import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private String password;

    @OneToMany
    @JoinColumn(name = "userID")
    private List<Notepad> notepadSet;

    protected User(){}

    public User(String name, String password){
        this.name=name;
        this.password=password;
    }
    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public List<Notepad> getNotepadSet() {
        return notepadSet;
    }
}
