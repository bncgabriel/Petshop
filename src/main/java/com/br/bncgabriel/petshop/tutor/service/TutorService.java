package com.br.bncgabriel.petshop.tutor.service;

import com.br.bncgabriel.petshop.tutor.domain.Tutor;
import com.br.bncgabriel.petshop.tutor.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TutorService {

    @Autowired
    private TutorRepository tutorRepository;

    public Tutor cadastrarTutor(Tutor novoTutor){
        return tutorRepository.save(novoTutor);
    }

    public List<Tutor> listarTutores(){
        return tutorRepository.findAll();
    }

    public Optional<Tutor> buscarTutor(Integer idTutor){
        return tutorRepository.findById(idTutor);
    }

    public Tutor atualizarTutor(Tutor tutor){
        return tutorRepository.save(tutor);
    }

    public void deletarTutor(Integer idTutor){
        tutorRepository.deleteById(idTutor);
    }
}
