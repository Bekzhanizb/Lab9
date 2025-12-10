package bekezhan.io.lab9.mapper;

import bekezhan.io.lab9.dto.ItemDTO;
import bekezhan.io.lab9.entity.Country;
import bekezhan.io.lab9.entity.Item;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class ItemMapperTest {
    @Autowired
    private ItemMapper itemMapper;
    @Test
    void testEntityToDto() {
        Country country = new Country();
        country.setId(10L);

        Item item = new Item();
        item.setId(1L);
        item.setName("IPhone");
        item.setPrice(1000);
        item.setCountry(country);
        item.setQuantity(5);

        ItemDTO dto = itemMapper.toDTO(item);

        Assertions.assertNotNull(dto);

        Assertions.assertNotNull(dto.getId());
        Assertions.assertNotNull(dto.getItemQuantity());
        Assertions.assertNotNull(dto.getItemPrice());
        Assertions.assertNotNull(dto.getCountryId());

        Assertions.assertEquals(item.getId(), dto.getId());
        Assertions.assertEquals(item.getName(), dto.getItemName());
        Assertions.assertEquals(item.getPrice(), dto.getItemPrice());
        Assertions.assertEquals(item.getQuantity(), dto.getItemQuantity());
    }

    @Test
    void testDTOToEntity() {
        Country country = new Country();
        country.setId(10L);

        ItemDTO dto = new ItemDTO();
        dto.setId(1L);
        dto.setItemName("IPhone");
        dto.setItemPrice(1000);
        dto.setCountryId(10L);
        dto.setItemQuantity(5);

        Item item = itemMapper.toEntity(dto);

        Assertions.assertNotNull(item);

        Assertions.assertNotNull(item.getId());
        Assertions.assertNotNull(item.getName());
        Assertions.assertNotNull(item.getPrice());
        Assertions.assertNotNull(item.getQuantity());

        Assertions.assertEquals(item.getId(), dto.getId());
        Assertions.assertEquals(item.getName(), dto.getItemName());
        Assertions.assertEquals(item.getPrice(), dto.getItemPrice());
        Assertions.assertEquals(item.getQuantity(), dto.getItemQuantity());
    }
}
