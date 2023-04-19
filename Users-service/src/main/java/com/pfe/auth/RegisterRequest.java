package com.pfe.auth;






import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    

    private String cin;
    private String firstname;
    private String lastname;
    private String sexe;
    private String email;
    private String phone;
    private String address;
    private Integer age;
    private String password;
    private String coinjoint;
    private int societeId;



    
}
