package bekezhan.io.lab9.controller;

import bekezhan.io.lab9.dto.CountryDTO;
import bekezhan.io.lab9.mapper.CountryMapper;
import bekezhan.io.lab9.service.CountryServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/countries")
@RequiredArgsConstructor
public class CountryController {

    private final CountryServiceImp countryService;
    private final CountryMapper countryMapper;

    @GetMapping
    public List<CountryDTO> getCountries() {
        return countryMapper.toDTOs(countryService.findAll());
    }

    @GetMapping("/{id}")
    public CountryDTO getCountryById(@PathVariable Long id) {
        return countryService.findById(id);
    }

    @PostMapping
    public CountryDTO createCountry(@RequestBody CountryDTO countryDTO) {
        return countryMapper.toDTO(countryService.save(countryDTO));
    }

    @PutMapping("/{id}")
    public CountryDTO updateCountry(@PathVariable Long id, @RequestBody CountryDTO countryDTO) {
        return countryMapper.toDTO(countryService.update(id, countryDTO));
    }

    @DeleteMapping("/{id}")
    public void deleteCountryById(@PathVariable Long id) {
        countryService.deleteById(id);
    }
}
