package com.pfe.assur_fil;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "ASSUR_FIL")
public class Assur_fil {
    

    @Id @Column(length = 4, name = "COD_SOC")
    private String Cod_Soc;
    @Column(length = 4, name = "COD_ASSUR")
    private String Cod_Assur;
    @Column(length = 4, name = "COD_FIL")
    private String Cod_Fil;
    @Column(name = "MNT_ADHER")
    private double Mnt_Adher;
    @Column(name = "MNT_ENF")
    private double Mnt_Enf;
    @Column(name = "MNT_CONJ")
    private double Mnt_Conj;
    @Column(name = "MNT_PERE")
    private double Mnt_Pere;
    @Column(name = "MNT_MERE")
    private double Mnt_Mere;
    @Column(length = 1, name = "PRORAT_PEC")
    private String Prorat_Pec;
    @Column(name = "MNT_ADHER_APCI")
    private double Mnt_Adher_Apci;
    @Column(name = "MNT_ENF_APCI")
    private double Mnt_Enf_Apci;
    @Column(name = "MNT_CONJ_APCI")
    private double Mnt_Conj_Apci;
    


//     COD_SOC VARCHAR2(4) NOT NULL,
//   COD_ASSUR VARCHAR2(4) NOT NULL,
//   COD_FIL VARCHAR2(4) DEFAULT '03' NOT NULL,
//   MNT_ADHER NUMBER(10, 3),
//   MNT_ENF NUMBER(10, 3),
//   MNT_CONJ NUMBER(10, 3),
//   MNT_PERE NUMBER(10, 3),
//   MNT_MERE NUMBER(10, 3),
//   PRORAT_PEC VARCHAR2(1),
//   MNT_ADHER_APCI NUMBER(10, 3),
//   MNT_ENF_APCI NUMBER(10, 3),
//   MNT_CONJ_APCI NUMBER(10, 3),
}
