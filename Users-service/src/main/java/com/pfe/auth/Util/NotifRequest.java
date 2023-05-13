package com.pfe.auth.Util;


import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotifRequest {
    
    private String message;
    private Integer user;
    private Date date;
    private String type;
    
}
