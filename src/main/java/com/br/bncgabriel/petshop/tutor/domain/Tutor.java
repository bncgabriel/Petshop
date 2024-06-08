package com.br.bncgabriel.petshop.tutor.domain;

import com.br.bncgabriel.petshop.pet.domain.Pet;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Entity
@Data
@EqualsAndHashCode
@Table(name = "Tutor")
public class Tutor {
    @Id
    @GeneratedValue
    @Column(name = "ID_TUTOR")
    private Integer idTutor;

    @Column(name = "NOME_TUTOR")
    private String nomeTutor;

    @Column(name = "CPF")
    private String cpf;

    @OneToMany(mappedBy = "tutor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Pet> pets;
}
