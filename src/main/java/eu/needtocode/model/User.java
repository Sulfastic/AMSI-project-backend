package eu.needtocode.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {

    @Id
    @GeneratedValue
    private long id;
    private String nickname;
    private String email;
    private String password;
    private String hash;
    private boolean active;


    @SuppressWarnings("unused")
    public User() {
    }

    private User(String nickname, String email, String password, String hash, boolean active) {
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.hash = hash;
        this.active = active;
    }

    public static User createInactiveUser(String nickname, String email, String password, String hash) {
        return new User(nickname, email, password, hash, false);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public boolean isActive() {
        return active;
    }

    public boolean isNotActive() {
        return !active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
