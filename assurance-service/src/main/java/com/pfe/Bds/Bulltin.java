package com.pfe.Bds;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pfe.Act.Act;
import com.pfe.Borderau.Borderau;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
@Table(name = "BUllTIN")
public class Bulltin {
    
    @Id @GeneratedValue (strategy = GenerationType.AUTO)
    private Integer Id;

    
    private int userID;
    private long assur;
    private String prestataire;
    private Date dateSoin;
    private Integer mois;
    private Integer ans;
    private Date dateSaisie;
    private String valider;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "code")
    private  Borderau borderau;

    
    @OneToMany(mappedBy = "Id")
    private List<Act> acts;

}
