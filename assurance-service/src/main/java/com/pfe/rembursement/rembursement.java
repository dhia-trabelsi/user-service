package com.pfe.rembursement;

import java.util.Date;

import com.pfe.Bds.Bulltin;

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
@Table(name = "REMBURSEMENT")
public class rembursement {
    

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer RembId;

    private Date date;
    private Integer actId;
    private Double mtt;
    private Double mttRemb;
    private Integer bulltinId;

    

}
