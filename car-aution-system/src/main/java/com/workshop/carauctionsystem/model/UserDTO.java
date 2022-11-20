package com.workshop.carauctionsystem.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserDTO {
    private String username;
    private String password;
    private String fullName;
    private String email;
    private String phone;
    private String avatar;
    private String addressWallet;
    private int role;
}
