package com.escola.crud.controller;

import com.escola.crud.model.Aluno;
import com.escola.crud.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alunos")
@CrossOrigin(origins = "http://localhost:4200") // Permite requisições do frontend Angular
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    // CREATE (Criar)
    @PostMapping
    public Aluno criarAluno(@RequestBody Aluno aluno) {
        return alunoService.salvar(aluno);
    }

    // READ (Ler todos)
    @GetMapping
    public List<Aluno> listarAlunos() {
        return alunoService.listarTodos();
    }

    // READ (Ler por ID)
    @GetMapping("/{id}")
    public ResponseEntity<Aluno> buscarAlunoPorId(@PathVariable Long id) {
        return alunoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // UPDATE (Atualizar)
    @PutMapping("/{id}")
    public ResponseEntity<Aluno> atualizarAluno(@PathVariable Long id, @RequestBody Aluno alunoDetalhes) {
        return alunoService.buscarPorId(id)
                .map(alunoExistente -> {
                    alunoExistente.setNome(alunoDetalhes.getNome());
                    alunoExistente.setEmail(alunoDetalhes.getEmail());
                    alunoExistente.setMatricula(alunoDetalhes.getMatricula());
                    Aluno atualizado = alunoService.salvar(alunoExistente);
                    return ResponseEntity.ok(atualizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE (Deletar)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAluno(@PathVariable Long id) {
        if (!alunoService.buscarPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        alunoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
