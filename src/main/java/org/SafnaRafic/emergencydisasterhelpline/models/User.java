package org.SafnaRafic.emergencydisasterhelpline.models;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    private int active;

    private String roles= "";

    private String permissions = "";

    public User(String username, String password, String roles, String permissions) {
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.permissions = permissions;
        this.active= 1;
    }
    protected User(){

    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getActive() {
        return active;
    }

    public String getRoles() {
        return roles;
    }

    public String getPermissions() {
        return permissions;
    }
    public List<String> getRoleList(){
        if(this.roles.length() > 0){
            return Arrays.asList(this.roles.split(","));
        }
        return new ArrayList<>();
    }
    public List<String> getPermissionsList(){
        if(this.permissions.length() > 0){
            return Arrays.asList(this.permissions.split(","));
        }
        return new ArrayList<>();
    }
}
//public class User extends AbstractEntityAdmin {
//    @NotNull
//    private String username;
//
//    @NotNull
//    private String pwHash;
//
//    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//
//    public User() {
//    }
//
//
//    public User(String username, String password) {
//        this.username = username;
//        this.pwHash = encoder.encode(password);
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public boolean isMatchingPassword(String password) {
//        return encoder.matches(password, pwHash);
//    }
//}