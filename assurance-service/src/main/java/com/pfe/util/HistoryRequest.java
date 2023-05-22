package com.pfe.util;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HistoryRequest {
    private String message;

    private Date date;
    private String type;
    private Integer user;
}
