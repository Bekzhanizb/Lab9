package bekezhan.io.lab9.service;

import bekezhan.io.lab9.dto.ItemDTO;
import bekezhan.io.lab9.entity.Country;
import bekezhan.io.lab9.entity.Item;
import bekezhan.io.lab9.mapper.ItemMapper;
import bekezhan.io.lab9.repository.CountryRepository;
import bekezhan.io.lab9.repository.ItemRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImp implements ItemService {

    private final ItemRepository itemRepository;
    private final CountryRepository countryRepository;
    private final ItemMapper itemMapper;
    @Override
    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    @Override
    public ItemDTO findById(Long id) {
        return itemMapper.toDTO(itemRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Item with id " + id + " not found!")));
    }

    @Override
    public List<Item> findByCountryId(Long id) {
        return itemRepository.findByCountryId(id);
    }

    @Override
    public Item create(ItemDTO itemDTO) {
        return itemRepository.save(itemMapper.toEntity(itemDTO));
    }

    @Override
    public Item update(Long id, ItemDTO dto) {
        Item item = itemRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Item with id " + id + " not found!"));

        if(dto.getItemName() != null) item.setName(dto.getItemName());
        if(dto.getItemPrice() != 0) item.setPrice(dto.getItemPrice());
        if(dto.getItemQuantity() != 0) item.setQuantity(dto.getItemQuantity());

        if(dto.getCountryId() != null) {
            Country country = countryRepository.findById(dto.getCountryId())
                    .orElseThrow(() -> new EntityNotFoundException("Country with id " + dto.getCountryId() + " not found!"));
            item.setCountry(country);
        }

        return itemRepository.save(item);
    }

    @Override
    public boolean deleteById(Long id) {
        if(itemRepository.existsById(id)) {
            itemRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
