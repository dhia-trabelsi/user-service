package com.pfe.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    

    private Integer id;
  private String cin;
  private String firstname;
  private String lastname;
  private String sexe;
  private String email;
  private String phone;
  private String address;
  private Integer age;
  private String coinjoint;
    private Role role;
}
