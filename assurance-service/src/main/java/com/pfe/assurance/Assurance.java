package com.pfe.assurance;

import java.sql.Date;
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Cod_Assur;

	@JsonIgnore
	@OneToMany
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
