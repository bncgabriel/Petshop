package com.br.bncgabriel.petshop.pet.domain;

import com.br.bncgabriel.petshop.tutor.domain.Tutor;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode
@Table(name = "PET")
public class Pet {
    @Id
    @GeneratedValue
    @Column(name = "ID_PET")
    private Integer idPet;

    @Column(name = "NOME_PET")
    private String nomePet;

    @Column(name = "ESPECIE_PET")
    private String especiePet;

    @Column(name = "RACA_PET")
    private String racaPet;

    @Column(name = "PESO_PET")
    Integer pesoPet;

    @Column(name = "DATA_DE_NASC_PET")
    private Integer dataNascPet;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_TUTOR")
    private Tutor tutor;
}
