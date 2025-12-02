package bekezhan.io.lab9.controller;

import bekezhan.io.lab9.dto.CountryDTO;
import bekezhan.io.lab9.mapper.CountryMapper;
import bekezhan.io.lab9.service.CountryService;
import bekezhan.io.lab9.service.CountryServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/countries")
@RequiredArgsConstructor
public class CountryController {

    private final CountryServiceImp  countryService;
    private final CountryMapper countryMapper;

    @GetMapping
    public List<CountryDTO> getCountries() {
        return countryMapper.toDTOs(countryService.findAll());
    }

    @GetMapping("/id")
    public CountryDTO getCountryById(@PathVariable Long id) {
        return countryService.findById(id);
    }
}
