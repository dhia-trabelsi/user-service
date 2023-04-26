package com.pfe.Assurance;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pfe.Societe.Societe;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "ASSURANCE")
public class Assurance {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long Cod_Assur;

    @OneToMany(mappedBy = "assurance")
    private List<Societe> societe;

    private String Lib_Assur;
    private String Lib_Assur_A;
    private String Num_Police;
    private String Typ_Plafond;
    private String Tel_Assur;
    private String Fax_Assur;
    private String Prefixe;
    private double Duree_Bult_Mut;
    private double Taux_Mut;
    private double Plaf_Mut;
    private Date Dat_Contrat;
    private int Delai_Cvisite;
    private int Age_Between_Enf;

}
