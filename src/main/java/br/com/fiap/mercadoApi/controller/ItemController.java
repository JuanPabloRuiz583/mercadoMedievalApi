package br.com.fiap.mercadoApi.controller;

import br.com.fiap.mercadoApi.models.Item;
import br.com.fiap.mercadoApi.service.ItemService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/itens")
public class ItemController {
    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping
    public ResponseEntity<Item> criar(@RequestBody Item item) {
        return ResponseEntity.ok(itemService.salvar(item));
    }

    @GetMapping
    public ResponseEntity<Page<Item>> listarTodos(Pageable pageable) {
        return ResponseEntity.ok(itemService.listarTodos(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> buscarPorId(@PathVariable Long id) {
        return itemService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Item> atualizar(@PathVariable Long id, @RequestBody Item item) {
        if (!itemService.buscarPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        item.setId(id);
        return ResponseEntity.ok(itemService.salvar(item));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (!itemService.buscarPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        itemService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscar/nome")
    public ResponseEntity<Page<Item>> buscarPorNome(@RequestParam String nome, Pageable pageable) {
        return ResponseEntity.ok(itemService.buscarPorNome(nome, pageable));
    }

    @GetMapping("/buscar/tipo")
    public ResponseEntity<Page<Item>> buscarPorTipo(@RequestParam String tipo, Pageable pageable) {
        return ResponseEntity.ok(itemService.buscarPorTipo(Item.TipoItem.valueOf(tipo.toUpperCase()), pageable));
    }

    @GetMapping("/buscar/preco")
    public ResponseEntity<Page<Item>> buscarPorPrecoRange(
            @RequestParam double min,
            @RequestParam double max,
            Pageable pageable) {
        return ResponseEntity.ok(itemService.buscarPorPrecoRange(min, max, pageable));
    }

    @GetMapping("/buscar/raridade")
    public ResponseEntity<Page<Item>> buscarPorRaridade(@RequestParam String raridade, Pageable pageable) {
        return ResponseEntity.ok(itemService.buscarPorRaridade(Item.RaridadeItem.valueOf(raridade.toUpperCase()), pageable));
    }
}