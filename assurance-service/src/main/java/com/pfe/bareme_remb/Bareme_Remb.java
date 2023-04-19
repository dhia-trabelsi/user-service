package com.pfe.bareme_remb;



import java.sql.Date;

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
@Table(name = "BAREME_REMB")
public class Bareme_Remb {
	
	@Id @Column(length = 4, name = "COD_SOC")
	private String Cod_Soc;
	@Column(length = 4, name = "COD_ASSUR")
	private String Cod_Assur;
	@Column(length = 4, name = "COD_FIL")
	private String Cod_Fil;
	@Column(length = 4, name = "ABRV_ACT")
	private String Abrv_Act;
	@Column(length = 1, name = "A_INDICE")
	private String A_Indice;
	@Column(name = "MTT_ACTE")
	private double Mtt_Acte;
	@Column(name = "DAT_ACTE")
	private Date Dat_Acte;
	@Column(name = "TAUX_ACT")
	private double Taux_Act;
	@Column(length = 1, name = "PLAFONNE")
	private String Plafonne;
	@Column(name = "PLAFOND")
	private double Plafond;
	@Column(length = 1, name = "VERIF_PIECE")
	private String Verif_Piece;
	@Column(length = 1, name = "VERIF_VIGN")
	private String Verif_Vign;
	@Column(length = 6, name = "DUREE_ACT")
	private String Duree_Act;
	@Column(length = 1, name = "PLAFON_PREST")
	private String Plafon_Prest;
	@Column(name = "PLAFOND_APCI")
	private double Plafond_Apci;
	@Column(length = 1, name = "PRORATAT")
	private String Proratat;
	@Column(name = "AGE_MIN")
	private int Age_Min;
	@Column(name = "AGE_MAX")
	private int Age_Max;
	@Column(length = 1, name = "REMB_FEMME_ENC")
	private String Remb_Femme_Enc;
	@Column(name = "DUREE_REMB_ENC")
	private int Duree_Remb_Enc;
	
	/*COD_SOC VARCHAR2(4) NOT NULL,
	  COD_ASSUR VARCHAR2(4) NOT NULL,
	  COD_FIL VARCHAR2(4) DEFAULT '03' NOT NULL,
	  ABRV_ACT VARCHAR2(4) NOT NULL,
	  A_INDICE VARCHAR2(1) NULL,
	  MTT_ACTE NUMBER(10, 3) NULL,
	  DAT_ACTE DATE NULL,
	  TAUX_ACT NUMBER(5, 2) NULL,
	  PLAFONNE VARCHAR2(1) NULL,
	  PLAFOND NUMBER(10, 3) NULL,
	  VERIF_PIECE VARCHAR2(1) NULL,
	  VERIF_VIGN VARCHAR2(1) NULL,
	  DUREE_ACT VARCHAR2(6) NULL,
	  PLAFON_PREST VARCHAR2(1) NULL,
	  PLAFOND_APCI NUMBER(12, 3) NULL,
	  PRORATAT VARCHAR2(1) NULL,
	  AGE_MIN NUMBER(2, 0) NULL,
	  AGE_MAX NUMBER(2, 0) NULL,
	  REMB_FEMME_ENC VARCHAR2(1) DEFAULT 'O' NOT NULL,
	  DUREE_REMB_ENC NUMBER(3, 0) NULL,*/

}
