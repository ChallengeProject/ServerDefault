package me.hackathon.root.model.request;

import lombok.Data;

@Data
public class UserRequest {
    private String email;
    private String password;
}
