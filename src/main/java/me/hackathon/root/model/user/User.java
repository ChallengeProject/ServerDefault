package me.hackathon.root.model.user;

import lombok.Data;

@Data
public class User implements UserPrincipal{
    private Long id;
    private String email;
    private String password;
    private Long createdTime;
    private Long updatedTime;
    private Long deletedTime;
    private UserStatus userStatus = UserStatus.NORMAL;
}
