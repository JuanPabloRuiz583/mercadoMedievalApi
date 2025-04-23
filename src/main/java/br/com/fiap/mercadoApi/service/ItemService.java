package br.com.fiap.mercadoApi.service;
import br.com.fiap.mercadoApi.models.Item;
import br.com.fiap.mercadoApi.repository.ItemRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemService {
    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Item salvar(Item item) {
        return itemRepository.save(item);
    }

    public Page<Item> listarTodos(Pageable pageable) {
        return itemRepository.findAll(pageable);
    }

    public Optional<Item> buscarPorId(Long id) {
        return itemRepository.findById(id);
    }

    public void deletar(Long id) {
        itemRepository.deleteById(id);
    }

    public Page<Item> buscarPorNome(String nome, Pageable pageable) {
        return itemRepository.findByNomeContainingIgnoreCase(nome, pageable);
    }

    public Page<Item> buscarPorTipo(Item.TipoItem tipo, Pageable pageable) {
        return itemRepository.findByTipo(tipo, pageable);
    }

    public Page<Item> buscarPorPrecoRange(double min, double max, Pageable pageable) {
        return itemRepository.findByPrecoBetween(min, max, pageable);
    }

    public Page<Item> buscarPorRaridade(Item.RaridadeItem raridade, Pageable pageable) {
        return itemRepository.findByRaridade(raridade, pageable);
    }
}
