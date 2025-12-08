package bekezhan.io.lab9.service;

import bekezhan.io.lab9.dto.ItemDTO;
import bekezhan.io.lab9.entity.Item;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ItemServiceTest {

    @Autowired
    private ItemService itemService;

    @Test
    void testFindAll() {
        List<Item> list = itemService.findAll();
        Assertions.assertNotNull(list);
        Assertions.assertTrue(list.size() > 0);
    }

    @Test
    void testFindById() {
        ItemDTO dto = itemService.findById(1L);
        Assertions.assertNotNull(dto);
        Assertions.assertEquals(1L, dto.getId());
    }

    @Test
    void testFindByIdNotFound() {
        Assertions.assertThrows(EntityNotFoundException.class,
                () -> itemService.findById(-1L));
    }

    @Test
    void testCreate() {
        ItemDTO dto = ItemDTO.builder()
                .itemName("Test Phone")
                .itemPrice(100)
                .itemQuantity(10)
                .countryId(1L)
                .build();

        Item item = itemService.create(dto);

        Assertions.assertNotNull(item.getId());
        Assertions.assertEquals("Test Phone", item.getName());
    }

    @Test
    void testUpdate() {
        ItemDTO dto = ItemDTO.builder()
                .itemName("UpdatedItem")
                .itemPrice(999)
                .itemQuantity(15)
                .countryId(1L)
                .build();

        Item updated = itemService.update(1L, dto);

        Assertions.assertEquals("UpdatedItem", updated.getName());
    }

    @Test
    void testDelete() {
        boolean deleted = itemService.deleteById(1L);
        Assertions.assertTrue(deleted);
    }
}
