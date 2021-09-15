package de.dajae.taschenrechner.database.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="user_entity")
public class UserEntity {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="user_agent")
    private String userAgent;

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UserEntity(){}

    public UserEntity(String userAgent){
        this.userAgent = userAgent;
    }
}
