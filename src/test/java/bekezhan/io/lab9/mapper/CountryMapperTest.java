package bekezhan.io.lab9.mapper;

import bekezhan.io.lab9.dto.CountryDTO;
import bekezhan.io.lab9.entity.Country;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class CountryMapperTest {

    @Autowired
    private CountryMapper countryMapper;

    @Test
    void testEntityToDto() {
        Country country = new Country();
        country.setId(1L);
        country.setName("Kazakhstan");
        country.setCode("KZ");

        CountryDTO dto = countryMapper.toDTO(country);

        Assertions.assertNotNull(dto);
        Assertions.assertEquals(country.getId(), dto.getId());
        Assertions.assertEquals(country.getName(), dto.getCountryName());
        Assertions.assertEquals(country.getCode(), dto.getCountryCode());
    }

    @Test
    void testDtoToEntity() {
        CountryDTO dto = CountryDTO.builder()
                .id(1L)
                .countryName("Kazakhstan")
                .countryCode("KZ")
                .build();

        Country country = countryMapper.toEntity(dto);

        Assertions.assertNotNull(country);
        Assertions.assertEquals(dto.getId(), country.getId());
        Assertions.assertEquals(dto.getCountryName(), country.getName());
        Assertions.assertEquals(dto.getCountryCode(), country.getCode());
    }

    @Test
    void testListMapping() {
        Country c = new Country();
        c.setId(1L);
        c.setName("Japan");
        c.setCode("JP");

        List<CountryDTO> dtoList = countryMapper.toDTOs(List.of(c));

        Assertions.assertNotNull(dtoList);
        Assertions.assertEquals(1, dtoList.size());
        Assertions.assertEquals("Japan", dtoList.get(0).getCountryName());
    }
}
