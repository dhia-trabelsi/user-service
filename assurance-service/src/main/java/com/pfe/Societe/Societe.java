package com.pfe.Societe;


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
@Table(name = "SOCIETE")
public class Societe {

    
    @Id @GeneratedValue (strategy = GenerationType.AUTO)
    private int Cod;

    
    private String Lib;
    private String LibA;
    private String Adresse;
    private String AdresseA;
    private String Tel;
    private String Fax;
    private String CodeTva;
    private String CodeRetr;
    private String NumRetr;
    private String RepWeb;
    private String Regime;
    private String NumPolice;
    private String AdrElectronique;
    private String CodPay;
    private int AgeMin;
    private int AgeMax;


    


    /*
    COD_SOC VARCHAR2(4) NOT NULL,
    LIB_SOC VARCHAR2(100),
    LIB_SOC_A VARCHAR2(100),
    ADR_SOC VARCHAR2(100),
    ADR_SOC_A VARCHAR2(100),
    TEL_SOC VARCHAR2(20),
    FAX_SOC VARCHAR2(20),
    COD_TVA VARCHAR2(20),
    COD_RETR VARCHAR2(10),
    NUM_RETR VARCHAR2(10),
    REP_WEB VARCHAR2(1),
    REGIME VARCHAR2(1),
    NUM_POLICE VARCHAR2(25),
    ADR_ELECTRONIQUE VARCHAR2(100),
    COD_PAY VARCHAR2(4),
    AGE_MIN NUMBER(3),
    AGE_MAX NUMBER(3),
    */
    
}
