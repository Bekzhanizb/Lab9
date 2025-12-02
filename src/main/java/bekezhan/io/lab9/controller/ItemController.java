package bekezhan.io.lab9.controller;

import bekezhan.io.lab9.dto.ItemDTO;
import bekezhan.io.lab9.mapper.ItemMapper;
import bekezhan.io.lab9.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
@RequiredArgsConstructor
public class ItemController {
    private final ItemMapper itemMapper;
    private final ItemService itemService;

    @GetMapping
    public List<ItemDTO> findAll() {
        return itemMapper.toDTOs(itemService.findAll());
    }

    @GetMapping("/{id}")
    public ItemDTO getById(@PathVariable Long id) {
        return itemService.findById(id);
    }
    @PostMapping
    public ItemDTO create(@RequestBody ItemDTO itemDTO) {
        return itemMapper.toDTO(itemService.create(itemDTO));
    }
    @PutMapping("/{id}")
    public ItemDTO update(@PathVariable Long id, @RequestBody ItemDTO itemDTO) {
        return  itemMapper.toDTO(itemService.update(id, itemDTO));
    }
    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id) {
        return itemService.deleteById(id);
    }
}
