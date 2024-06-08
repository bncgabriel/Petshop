package com.br.bncgabriel.petshop.pet.repository;

import com.br.bncgabriel.petshop.pet.domain.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepository extends JpaRepository <Pet,Integer>{
}
