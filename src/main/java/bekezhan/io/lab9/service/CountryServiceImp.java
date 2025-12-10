package bekezhan.io.lab9.service;

import bekezhan.io.lab9.dto.CountryDTO;
import bekezhan.io.lab9.entity.Country;
import bekezhan.io.lab9.mapper.CountryMapper;
import bekezhan.io.lab9.repository.CountryRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryServiceImp implements CountryService {

    private final CountryRepository countryRepository;
    private final CountryMapper countryMapper;

    @Override
    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    @Override
    public CountryDTO findById(Long id) {
        return countryMapper.toDTO(countryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Country with id " + id + " not found!")));
    }

    @Override
    public Country save(CountryDTO countryDTO) {
        return countryRepository.save(countryMapper.toEntity(countryDTO));
    }

    @Override
    public Country update(Long id, CountryDTO dto) {
        Country country = countryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Country not found"));
        if(dto.getCountryName() != null) country.setName(dto.getCountryName());
        if(dto.getCountryCode() != null) country.setCode(dto.getCountryCode());
        return countryRepository.save(country);
    }

    @Override
    public boolean deleteById(Long id) {
        if(countryRepository.existsById(id)){
            countryRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
