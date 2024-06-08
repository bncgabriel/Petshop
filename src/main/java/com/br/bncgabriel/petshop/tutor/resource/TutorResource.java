package com.br.bncgabriel.petshop.tutor.resource;

import com.br.bncgabriel.petshop.tutor.domain.Tutor;
import com.br.bncgabriel.petshop.tutor.service.TutorService;
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
@RequestMapping(value = "/tutor")
public class TutorResource {

    @Autowired
    private TutorService tutorService;

    @Operation(summary = "Cadastrar Tutor", description = "O recurso permite cadastrar, porém não pode repetir o mesmo nome.", tags = "Tutor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cadastro com sucesso",
                    content = @Content(
                            examples = @ExampleObject(value = "{\n" +
                                    "  \"idTutor\": 5,\n" +
                                    "  \"nomeTutor\": \"Marketing\",\n" +
                                    "  \"cpf\": \"00100100100\"\n" +
                                    "  \"mensagem\": \"Cadastro Realizado com Sucesso\"n"+
                                    "}")
                    )),
            @ApiResponse(responseCode = "400", description = "Já existe produto com esse nome",
                    content = @Content(
                            examples = @ExampleObject(value = "{\"message\": \"Já existe uma Tutor com esse CPF\"}")
                    )),
            @ApiResponse(responseCode = "401", description = "Token ausente, inválido ou expirado"),
    })
    @PostMapping
    public ResponseEntity<Tutor> cadastrarTutor(@RequestBody Tutor novoTutor) {
        Tutor tutorNovo = tutorService.cadastrarTutor(novoTutor);
        return new ResponseEntity<>(tutorNovo, HttpStatus.CREATED);
    }

    @Operation(summary = "Listar Tutores", description = "O recurso listar todas os Tutores.", tags = "Tutor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "404", description = "Não encontrado")
    })
    @GetMapping
    public ResponseEntity<List<Tutor>> listarTutores() {
        List<Tutor> tutores = tutorService.listarTutores();
        return ResponseEntity.ok(tutores);
    }

    @Operation(summary = "Buscar Tutor", description = "O recurso permite buscar um Tutor pelo ID.", tags = "Tutor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "404", description = "Não encontrado")
    })
    @GetMapping("/{idTutor}")
    public ResponseEntity<Tutor> buscarTutor(@PathVariable Integer idTutor) {
        return tutorService.buscarTutor(idTutor)
                .map(tutor -> new ResponseEntity<>(tutor,HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Atualizar Tutor", description = "O recurso atualiza um Tutor pelo ID.", tags = "Tutor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "404", description = "Não encontrado")
    })
    @PutMapping("/{idTutor}")
    public ResponseEntity<Tutor> atualizarTutor(@PathVariable Integer idTutor, @RequestBody Tutor tutor){
        if (!tutorService.buscarTutor(idTutor).isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        tutor.setIdTutor(idTutor);
        Tutor novoTutor = tutorService.atualizarTutor(tutor);
        return new ResponseEntity<>(novoTutor, HttpStatus.OK);
    }

    @Operation(summary = "Deletar Tutor", description = "O recurso permite deletar um Tutor pelo ID.", tags = "Tutor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "404", description = "Não encontrado")
    })
    @DeleteMapping("/{idTutor}")
    public ResponseEntity<Void> deletarTutor(@PathVariable Integer idTutor) {
        tutorService.deletarTutor(idTutor);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
