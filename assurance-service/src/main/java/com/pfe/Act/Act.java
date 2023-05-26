package com.pfe.Act;

import java.util.Date;

import com.pfe.Bds.Bulltin;
import com.pfe.TypeAct.TypeAct;
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

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id;

    private String abrv;
    private String lib;

    private Date date;
    private Double taux;
    private String plafonne;
    private String ctr;
    private String sexe;
    private String parent;

    private String duree;
    private String indice;
    private Double mtt;
    private Double plafond;
    private String piece;
    private String vign;

    @ManyToOne
    @JoinColumn(name = "typeAct")
    private TypeAct typeAct;

    @ManyToOne
    @JoinColumn(name = "bulltin_id")
    private Bulltin bulltin;

}
