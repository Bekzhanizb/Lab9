package bekezhan.io.lab9.service;

import bekezhan.io.lab9.dto.CountryDTO;
import bekezhan.io.lab9.entity.Country;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Random;

@SpringBootTest
class CountryServiceTest {

    @Autowired
    private CountryServiceImp countryService;

    @Test
    void testFindAll() {
        List<CountryDTO> list = countryService.findAll();

        Assertions.assertNotNull(list);

        Assertions.assertFalse(list.isEmpty());

        for(CountryDTO dto : list) {
            Assertions.assertNotNull(dto);
            Assertions.assertNotNull(dto.getId());
            Assertions.assertNotNull(dto.getCountryName());
            Assertions.assertNotNull(dto.getCountryCode());
        }
    }

    @Test
    void testFindById() {
        Random random = new Random();
        Long randomIndex = random.nextLong(countryService.findAll().size());

        CountryDTO dto = countryService.findById(randomIndex);

        Assertions.assertNotNull(dto);

        Assertions.assertEquals(randomIndex, dto.getId());

        Assertions.assertNotNull(dto.getId());
        Assertions.assertNotNull(dto.getCountryName());
        Assertions.assertNotNull(dto.getCountryCode());

        Assertions.assertThrows(EntityNotFoundException.class,
                () -> countryService.findById(-1L));
    }

    @Test
    void testCreate() {
        CountryDTO dto = CountryDTO.builder()
                .countryName("Test")
                .countryCode("TSN")
                .build();

        Country saved = countryService.save(dto);

        Assertions.assertNotNull(saved.getId());
        Assertions.assertNotNull(saved.getName());
        Assertions.assertNotNull(saved.getCode());

        Assertions.assertEquals(dto.getCountryName(), saved.getName());
        Assertions.assertEquals(dto.getCountryCode(), saved.getCode());
    }

    @Test
    void testUpdate() {
        CountryDTO newCountry = CountryDTO.builder()
                .countryName("Original")
                .countryCode("ORG")
                .build();

        Country saved = countryService.save(newCountry);
        Long savedId = saved.getId();

        CountryDTO dto = CountryDTO.builder()
                .id(savedId)
                .countryName("Updated")
                .countryCode("UPD")
                .build();

        Country updated = countryService.update(savedId, dto);

        Assertions.assertNotNull(updated);
        Assertions.assertNotNull(updated.getId());
        Assertions.assertNotNull(updated.getName());
        Assertions.assertNotNull(updated.getCode());

        Assertions.assertEquals(dto.getCountryName(), updated.getName());
        Assertions.assertEquals(dto.getCountryCode(), updated.getCode());

        Assertions.assertEquals(dto.getCountryName(), updated.getName());

        CountryDTO dto2 = countryService.findById(updated.getId());
        Assertions.assertNotNull(dto2);
        Assertions.assertNotNull(dto2.getId());
        Assertions.assertNotNull(dto2.getCountryName());
        Assertions.assertNotNull(dto2.getCountryCode());

        Assertions.assertEquals(dto2.getId(), dto.getId());
        Assertions.assertEquals(dto2.getCountryName(), dto.getCountryName());
        Assertions.assertEquals(dto2.getCountryCode(), dto.getCountryCode());
    }

    @Test
    void testDelete() {
        CountryDTO newCountry = CountryDTO.builder()
                .countryName("Delete")
                .countryCode("DEL")
                .build();

        Country saved = countryService.save(newCountry);
        Long savedId = saved.getId();

        boolean deleted = countryService.deleteById(saved.getId());
        Assertions.assertTrue(deleted);


        Assertions.assertThrows(EntityNotFoundException.class,
                () -> countryService.findById(savedId));

    }
}
