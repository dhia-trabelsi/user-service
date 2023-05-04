package com.pfe.Borderau;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "BORDEAU")
public class Borderau {
    
    @Id @GeneratedValue (strategy = GenerationType.AUTO)
    private Long code;
    private String anneeBorderau;
    private Date date;
    private int nbs;
    private Date DateRem;
    private Boolean envoyer;
    private Boolean valider;
    private float mHonor;
    private float mNet;
    private long assur;

    
}
