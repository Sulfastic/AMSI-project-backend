package eu.needtocode.model;

import org.hibernate.validator.constraints.NotEmpty;

public class LoginUserRQ {

    @NotEmpty
    private String nickname;
    @NotEmpty
    private String password;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
