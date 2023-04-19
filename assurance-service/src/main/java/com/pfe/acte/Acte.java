package com.pfe.acte;


import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pfe.typesactes.Types_actes;

import jakarta.persistence.Column;
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
@Table(name = "ACTE")
public class Acte {
	

	
	@Id @Column(name = "TYPE_ACTE")
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	//@ManyToOne(targetEntity = Types_actes.class)
	private Integer Type_Act;
	@Column(length = 5, name = "ABRV_ACT")
	private String Abrv_Act;
	@Column(length = 100, name = "LIB_ACT")
	private String Lib_Act;
	@Column(length = 100, name = "LIB_ACT_A")
	private String Lib_Act_A;
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
	@Column(length = 1,	name = "VERIF_PIECE")
	private String Verif_Piece;
	@Column(length = 1, name = "VERIF_VIGN")
	private String Verif_Vign;
	@Column(length = 1, name = "CTR_DUREE")
	private String Ctr_Duree;
	@Column(name = "DUREE_ACT")
	private float Duree_Act;
	@Column(length = 1, name = "SEXE")
	private String Sexe;
	@Column(length = 1, name = "PARENTE")
	private String Parent;
	@Column(name = "COD_ASSUR")
	//@ManyToOne(targetEntity = Assurance.class)
	private String Cod_Assur;

	@JsonIgnore
	@OneToMany
	private List<Types_actes> types_actes;
	
	/*TYPE_ACT varchar2(4) NOT NULL,
	  ABRV_ACT varchar2(5) NOT NULL,
	  LIB_ACT varchar2(100),
	  LIB_ACT_A varchar2(100),
	  A_INDICE varchar2(1),
	  MTT_ACTE number(10, 3),
	  DAT_ACTE date,
	  TAUX_ACT number(5, 2),
	  PLAFONNE varchar2(1),
	  PLAFOND number(10, 3),
	  VERIF_PIECE varchar2(1),
	  VERIF_VIGN varchar2(1),
	  CTR_DUREE varchar2(1),
	  DUREE_ACT float,
	  SEXE varchar2(1),
	  PARENTE varchar2(1),
	  CONSTRAINT PK_ACTE PRIMARY KEY (TYPE_ACTE,ABRV_ACT)*/

}
