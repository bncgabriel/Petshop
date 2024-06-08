package com.br.bncgabriel.petshop.pet.resource;


import com.br.bncgabriel.petshop.pet.domain.Pet;
import com.br.bncgabriel.petshop.pet.service.PetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/Pet")
public class PetResource {

    @Autowired
    private PetService petService;

    @Operation(summary = "Adicionar Pet", description = "O recurso permite cadastrar, porém não pode repetir o mesmo nome.", tags = "Pet")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cadastro com sucesso",
                    content = @Content(
                            examples = @ExampleObject(value = "{\n" +
                                    "  \"idPet\": 5,\n" +
                                    "  \"nomePet\": \"Marketing\",\n" +
                                    "  \"cpf\": \"00100100100\"\n" +
                                    "  \"mensagem\": \"Cadastro Realizado com Sucesso\"n"+
                                    "}")
                    )),
            @ApiResponse(responseCode = "400", description = "Já existe produto com esse nome",
                    content = @Content(
                            examples = @ExampleObject(value = "{\"message\": \"Já existe uma Pet com esse CPF\"}")
                    )),
            @ApiResponse(responseCode = "401", description = "Token ausente, inválido ou expirado"),
    })
    @PostMapping
    public ResponseEntity<Pet> adicionarPet(@RequestBody Pet novoPet) {
        Pet PetNovo = petService.adicionarPet(novoPet);
        return new ResponseEntity<>(PetNovo, HttpStatus.CREATED);
    }

    @Operation(summary = "Listar Pets", description = "O recurso listar todas os Pets.", tags = "Pet")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "404", description = "Não encontrado")
    })
    @GetMapping
    public ResponseEntity<List<Pet>> listarPets() {
        List<Pet> pets = petService.listarPets();
        return ResponseEntity.ok(pets);
    }

    @Operation(summary = "Buscar Pet", description = "O recurso permite buscar um Pet pelo ID.", tags = "Pet")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "404", description = "Não encontrado")
    })
    @GetMapping("/{idPet}")
    public ResponseEntity<Pet> buscarPet(@PathVariable Integer idPet) {
        return petService.buscarPet(idPet)
                .map(Pet -> new ResponseEntity<>(Pet,HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Atualizar Pet", description = "O recurso atualiza um Pet pelo ID.", tags = "Pet")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "404", description = "Não encontrado")
    })
    @PutMapping("/{idPet}")
    public ResponseEntity<Pet> atualizarPet(@PathVariable Integer idPet, @RequestBody Pet Pet){
        if (!petService.buscarPet(idPet).isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Pet.setIdPet(idPet);
        Pet novoPet = petService.atualizarPet(Pet);
        return new ResponseEntity<>(novoPet, HttpStatus.OK);
    }

    @Operation(summary = "Deletar Pet", description = "O recurso permite deletar um Pet pelo ID.", tags = "Pet")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "404", description = "Não encontrado")
    })
    @DeleteMapping("/{idPet}")
    public ResponseEntity<Void> deletarPet(@PathVariable Integer idPet) {
        petService.deletarPet(idPet);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
