package bekezhan.io.lab9.service;

import bekezhan.io.lab9.dto.CountryDTO;
import bekezhan.io.lab9.entity.Country;

import java.util.List;

public interface CountryService {
    List<CountryDTO> findAll();
    CountryDTO findById(Long id);
    Country save(CountryDTO countryDTO);
    Country update(Long id, CountryDTO dto);
    boolean deleteById(Long id);

}
