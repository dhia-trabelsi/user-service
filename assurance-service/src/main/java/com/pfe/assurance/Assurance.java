package com.pfe.assurance;

import java.sql.Date;

import com.pfe.acte.Acte;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
	
	@Id @Column(length = 4, name = "COD_ASSUR")
	//@OneToMany(mappedBy = "Cod_Assur", targetEntity = Acte.class)
	private String Cod_Assur;
	@Column(length = 4, name = "COD_SOC")
	private String Cod_Soc;
	@Column(length = 100, name = "LIB_ASSUR")
	private String Lib_Assur;
	@Column(length = 100, name = "LIB_ASSUR_A")
	private String Lib_Assur_A;
	@Column(length = 20, name = "NUM_POLICE")
	private String Num_Police;
	@Column(length = 1, name = "TYP_PLAFOND")
	private String Typ_Plafond;
	@Column(length = 20, name = "TEL_ASSUR")
	private String Tel_Assur;
	@Column(length = 20, name = "FAX_ASSUR")
	private String Fax_Assur;
	@Column(length = 2, name = "PREFIXE")
	private String Prefixe;
	@Column(name = "DUREE_BULT_MUT")
	private double Duree_Bult_Mut; 
	@Column(name = "TAUX_MUT")
	private double Taux_Mut;
	@Column(name = "PLAF_MUT")
	private double Plaf_Mut;
	@Column(name = "DAT_CONTRAT")
	private Date Dat_Contrat;
	@Column(name = "DELAI_CVISITE")
	private int Delai_Cvisite;
	@Column(name = "AGE_BETWEEN_ENF")
	private int Age_Between_Enf;
	
	/*
	 COD_ASSUR VARCHAR2(4) NOT NULL,
	  COD_SOC VARCHAR2(4) NOT NULL,
	  LIB_ASSUR VARCHAR2(100) NULL,
	  LIB_ASSUR_A VARCHAR2(100) NULL,
	  NUM_POLICE VARCHAR2(20) NULL,
	  TYP_PLAFOND VARCHAR2(1) NULL,
	  TEL_ASSUR VARCHAR2(20) NULL,
	  FAX_ASSUR VARCHAR2(20) NULL,
	  PREFIXE VARCHAR2(2) NOT NULL,
	  
	  
	  DUREE_BULT_MUT BINARY_FLOAT NULL,
	  TAUX_MUT NUMBER(6, 3) NULL,
	  PLAF_MUT NUMBER(10, 3) NULL,
	  DAT_CONTRAT DATE NULL,
	  DELAI_CVISITE NUMBER(3, 0) NULL,
	  AGE_BETWEEN_ENF NUMBER(3, 0) NULL,*/

}
