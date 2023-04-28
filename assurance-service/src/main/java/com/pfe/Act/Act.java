package com.pfe.Act;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pfe.TypeAct.TypeAct;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "ACT")
public class Act {

    @Id @GeneratedValue (strategy = GenerationType.AUTO)
    private Integer Id;

    private String abrv;
    private String lib;
    private String libA;
    private String indice;
    private float mtt;
    private Date date;
    private float taux;
    private String plafonne;
    private float plafond;
    private String piece;
    private String vign;
    private String ctr;
    private float duree;
    private String sexe;
    private String parent;

    
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "typeAct")
    private TypeAct typeAct;


}
