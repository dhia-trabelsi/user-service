package com.pfe.typesactes;


import jakarta.persistence.Column;
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
import com.pfe.acte.Acte;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TYPES_ACTES")
public class Types_actes {
	

	
	@Id @Column(name = "TYPE_ACT")
	@GeneratedValue (strategy = GenerationType.AUTO)
	//@OneToMany(mappedBy = "Type_Act", targetEntity = Acte.class)
	private Integer Type_Act;
	@Column(name = "LIB_TYPE_ACTE")
	private String Lib_Type_Acte;
	
	@ManyToOne
	@JoinColumn(name = "ACT")
	private Acte acte;
	// type_act      NUMBER(2) not null,
  	// lib_type_acte VARCHAR2(40)
	

}
