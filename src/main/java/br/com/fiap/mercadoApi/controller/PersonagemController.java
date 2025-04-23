package br.com.fiap.mercadoApi.controller;
import br.com.fiap.mercadoApi.models.Personagem;
import br.com.fiap.mercadoApi.service.PersonagemService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/personagens")
public class PersonagemController {
    private final PersonagemService personagemService;

    public PersonagemController(PersonagemService personagemService) {
        this.personagemService = personagemService;
    }

    @PostMapping
    public ResponseEntity<Personagem> criar(@RequestBody Personagem personagem) {
        return ResponseEntity.ok(personagemService.salvar(personagem));
    }

    @GetMapping
    public ResponseEntity<Page<Personagem>> listarTodos(Pageable pageable) {
        return ResponseEntity.ok(personagemService.listarTodos(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Personagem> buscarPorId(@PathVariable Long id) {
        return personagemService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Personagem> atualizar(@PathVariable Long id, @RequestBody Personagem personagem) {
        if (!personagemService.buscarPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        personagem.setId(id);
        return ResponseEntity.ok(personagemService.salvar(personagem));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (!personagemService.buscarPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        personagemService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscar")
    public ResponseEntity<Page<Personagem>> buscarPorNome(@RequestParam String nome, Pageable pageable) {
        return ResponseEntity.ok(personagemService.buscarPorNome(nome, pageable));
    }

    @GetMapping("/classe")
    public ResponseEntity<Page<Personagem>> buscarPorClasse(@RequestParam String classe, Pageable pageable) {
        return ResponseEntity.ok(personagemService.buscarPorClasse(Personagem.ClassePersonagem.valueOf(classe.toUpperCase()), pageable));
    }
}