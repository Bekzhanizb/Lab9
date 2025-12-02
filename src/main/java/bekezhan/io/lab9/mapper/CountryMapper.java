package bekezhan.io.lab9.mapper;

import bekezhan.io.lab9.dto.CountryDTO;
import bekezhan.io.lab9.entity.Country;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CountryMapper {
    @Mapping(source = "name", target = "countryName")
    @Mapping(source = "code", target = "countryCode")
    CountryDTO toDTO(Country country);

    @Mapping(source = "countryName", target = "name")
    @Mapping(source = "countryCode", target = "code")
    Country toEntity(CountryDTO countryDTO);

    List<CountryDTO> toDTOs(List<Country> countries);
}
