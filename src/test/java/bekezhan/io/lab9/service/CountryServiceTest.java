package bekezhan.io.lab9.service;

import bekezhan.io.lab9.dto.CountryDTO;
import bekezhan.io.lab9.entity.Country;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class CountryServiceTest {

    @Autowired
    private CountryService countryService;

    @Test
    void testFindAll() {
        List<Country> list = countryService.findAll();
        Assertions.assertNotNull(list);
        Assertions.assertTrue(list.size() > 0);
    }

    @Test
    void testFindById() {
        CountryDTO dto = countryService.findById(1L);
        Assertions.assertNotNull(dto);
        Assertions.assertEquals(1L, dto.getId());
    }

    @Test
    void testFindByIdNotFound() {
        Assertions.assertThrows(EntityNotFoundException.class,
                () -> countryService.findById(-1L));
    }

    @Test
    void testCreate() {
        CountryDTO dto = CountryDTO.builder()
                .countryName("Testland")
                .countryCode("TST")
                .build();

        Country saved = countryService.save(dto);

        Assertions.assertNotNull(saved.getId());
        Assertions.assertEquals("Testland", saved.getName());
    }

    @Test
    void testUpdate() {
        CountryDTO dto = new CountryDTO(null, "UpdatedName", "UPD");

        Country updated = countryService.update(1L, dto);

        Assertions.assertEquals("UpdatedName", updated.getName());
    }

    @Test
    void testDelete() {
        boolean deleted = countryService.deleteById(1L);
        Assertions.assertTrue(deleted);
    }
}
