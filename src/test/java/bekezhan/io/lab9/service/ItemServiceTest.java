package bekezhan.io.lab9.service;

import bekezhan.io.lab9.dto.CountryDTO;
import bekezhan.io.lab9.dto.ItemDTO;
import bekezhan.io.lab9.entity.Country;
import bekezhan.io.lab9.entity.Item;
import bekezhan.io.lab9.mapper.CountryMapper;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Random;

@SpringBootTest
class ItemServiceTest {

    @Autowired
    private ItemServiceImp itemService;

    @Autowired
    private CountryServiceImp countryServiceImp;

    @Autowired
    private CountryMapper countryMapper;

    @Test
    void testFindAll() {
        List<Item> list = itemService.findAll();

        Assertions.assertNotNull(list);
        Assertions.assertFalse(list.isEmpty());

        for(Item item : list) {
            Assertions.assertNotNull(item);
            Assertions.assertNotNull(item.getId());
            Assertions.assertNotNull(item.getName());
            Assertions.assertTrue(item.getPrice() >= 0);
            Assertions.assertTrue(item.getQuantity() >= 0);
        }
    }

    @Test
    void testFindById() {
        Random random = new Random();
        List<Item> allItems = itemService.findAll();

        Assertions.assertFalse(allItems.isEmpty());

        int randomIndex = random.nextInt(allItems.size());
        Long randomId = allItems.get(randomIndex).getId();

        ItemDTO dto = itemService.findById(randomId);

        Assertions.assertNotNull(dto);
        Assertions.assertEquals(randomId, dto.getId());
        Assertions.assertNotNull(dto.getId());
        Assertions.assertNotNull(dto.getItemName());
        Assertions.assertTrue(dto.getItemPrice() >= 0);
        Assertions.assertTrue(dto.getItemQuantity() >= 0);

        Assertions.assertThrows(EntityNotFoundException.class,
                () -> itemService.findById(-1L));
    }

    @Test
    void testCreate() {
        CountryDTO country;
        List<Country> countries = countryServiceImp.findAll();

        country = countryMapper.toDTO(countries.get(0));


        ItemDTO dto = ItemDTO.builder()
                .itemName("Test Phone")
                .itemPrice(100)
                .itemQuantity(10)
                .countryId(country.getId())
                .build();

        Item saved = itemService.create(dto);

        Assertions.assertNotNull(saved.getId());
        Assertions.assertNotNull(saved.getName());
        Assertions.assertNotNull(saved.getCountry());
        Assertions.assertTrue(saved.getPrice() >= 0);
        Assertions.assertTrue(saved.getQuantity() >= 0);

        Assertions.assertEquals(dto.getItemName(), saved.getName());
        Assertions.assertEquals(dto.getItemPrice(), saved.getPrice());
        Assertions.assertEquals(dto.getItemQuantity(), saved.getQuantity());
        Assertions.assertEquals(dto.getCountryId(), saved.getCountry().getId());
    }

    @Test
    void testUpdate() {
        CountryDTO country;
        List<Country> countries = countryServiceImp.findAll();

        country = countryMapper.toDTO(countries.get(0));

        ItemDTO newItem = ItemDTO.builder()
                .itemName("Original Item")
                .itemPrice(50)
                .itemQuantity(5)
                .countryId(country.getId())
                .build();

        Item saved = itemService.create(newItem);
        Long savedId = saved.getId();

        ItemDTO updateDTO = ItemDTO.builder()
                .id(savedId)
                .itemName("Updated Item")
                .itemPrice(150)
                .itemQuantity(15)
                .countryId(country.getId())
                .build();

        Item updated = itemService.update(savedId, updateDTO);

        Assertions.assertNotNull(updated);
        Assertions.assertNotNull(updated.getId());
        Assertions.assertNotNull(updated.getName());
        Assertions.assertNotNull(updated.getCountry());
        Assertions.assertTrue(updated.getPrice() >= 0);
        Assertions.assertTrue(updated.getQuantity() >= 0);

        Assertions.assertEquals(updateDTO.getItemName(), updated.getName());
        Assertions.assertEquals(updateDTO.getItemPrice(), updated.getPrice());
        Assertions.assertEquals(updateDTO.getItemQuantity(), updated.getQuantity());
        Assertions.assertEquals(updateDTO.getCountryId(), updated.getCountry().getId());

        ItemDTO dtoFromFind = itemService.findById(updated.getId());
        Assertions.assertNotNull(dtoFromFind);
        Assertions.assertNotNull(dtoFromFind.getId());
        Assertions.assertNotNull(dtoFromFind.getItemName());
        Assertions.assertTrue(dtoFromFind.getItemPrice() >= 0);
        Assertions.assertTrue(dtoFromFind.getItemQuantity() >= 0);

        Assertions.assertEquals(updateDTO.getId(), dtoFromFind.getId());
        Assertions.assertEquals(updateDTO.getItemName(), dtoFromFind.getItemName());
        Assertions.assertEquals(updateDTO.getItemPrice(), dtoFromFind.getItemPrice());
        Assertions.assertEquals(updateDTO.getItemQuantity(), dtoFromFind.getItemQuantity());
        Assertions.assertEquals(updateDTO.getCountryId(), dtoFromFind.getCountryId());
    }

    @Test
    void testDelete() {
        CountryDTO country;
        List<Country> countries = countryServiceImp.findAll();

        country = countryMapper.toDTO(countries.get(0));


        ItemDTO newItem = ItemDTO.builder()
                .itemName("Item to Delete")
                .itemPrice(200)
                .itemQuantity(20)
                .countryId(country.getId())
                .build();

        Item saved = itemService.create(newItem);
        Long savedId = saved.getId();

        boolean deleted = itemService.deleteById(savedId);
        Assertions.assertTrue(deleted);

        Assertions.assertThrows(EntityNotFoundException.class,
                () -> itemService.findById(savedId));
    }
}