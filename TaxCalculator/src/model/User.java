package model;

import java.io.Serializable;

public class User implements Serializable {
    // 序列化版本UID(Serialization version UID)
    private static final long serialVersionUID = 1L;
    // 用户名(Username)
    private String username;
    // 密码(Password)
    private String password;


    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getters and setters
    // 获取用户名(Get username)
    public String getUsername() {
        return username;
    }

    // 获取密码(Get password)
    public String getPassword() {
        return password;
    }

    // 设置用户名(Set username)
    public void setUsername(String username) {
        this.username = username;
    }

    // 设置密码(Set password)
    public void setPassword(String password) {
        this.password = password;
    }
}
