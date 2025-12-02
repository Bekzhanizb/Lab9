package bekezhan.io.lab9.service;

import bekezhan.io.lab9.dto.ItemDTO;
import bekezhan.io.lab9.entity.Item;

import java.util.List;

public interface ItemService {
    List<Item> findAll();
    ItemDTO findById(Long id);
    List<Item> findByCountryId(Long id);
    Item update(Long id, ItemDTO dto);
    boolean deleteById(Long id);
}
