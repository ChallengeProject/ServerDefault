package me.hackathon.root.model.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    private Long id;
    private UserGrade grade;
    private String email;
    private String password;
    private String phoneNumber;
    private String name;
    private String address;
    private UserStatus status = UserStatus.NORMAL;
    private Long createdTime;
    private String birth;
    private Integer coin;
    private String profileUrl;
    private Integer vltTime;
}