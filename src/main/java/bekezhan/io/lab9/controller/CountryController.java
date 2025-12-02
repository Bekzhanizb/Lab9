package bekezhan.io.lab9.controller;

import bekezhan.io.lab9.dto.CountryDTO;
import bekezhan.io.lab9.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/countries")
@RequiredArgsConstructor
public class CountryController {

    private final CountryService countryService;

    @GetMapping
    public String getAllCountries(Model model) {
        model.addAttribute("countries", countryService.findAll());
        return "countries/list";
    }

    @GetMapping("/{id}")
    public String getCountryById(@PathVariable Long id, Model model) {
        model.addAttribute("country", countryService.findById(id));
        return "countries/view";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("country", new CountryDTO());
        return "countries/create";
    }

    @PostMapping
    public String createCountry(@ModelAttribute CountryDTO countryDTO,
                                RedirectAttributes redirectAttributes) {
        countryService.save(countryDTO);
        redirectAttributes.addFlashAttribute("success", "Country created successfully!");
        return "redirect:/countries";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("country", countryService.findById(id));
        return "countries/edit";
    }

    @PostMapping("/update/{id}")
    public String updateCountry(@PathVariable Long id,
                                @ModelAttribute CountryDTO countryDTO,
                                RedirectAttributes redirectAttributes) {
        countryService.update(id, countryDTO);
        redirectAttributes.addFlashAttribute("success", "Country updated successfully!");
        return "redirect:/countries";
    }

    @GetMapping("/delete/{id}")
    public String deleteCountry(@PathVariable Long id,
                                RedirectAttributes redirectAttributes) {
        boolean deleted = countryService.deleteById(id);
        if (deleted) {
            redirectAttributes.addFlashAttribute("success", "Country deleted successfully!");
        } else {
            redirectAttributes.addFlashAttribute("error", "Country not found!");
        }
        return "redirect:/countries";
    }
}