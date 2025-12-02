package bekezhan.io.lab9.controller;

import bekezhan.io.lab9.dto.ItemDTO;
import bekezhan.io.lab9.service.CountryService;
import bekezhan.io.lab9.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;
    private final CountryService countryService;

    @GetMapping
    public String getAllItems(Model model) {
        model.addAttribute("items", itemService.findAll());
        return "items/list";
    }

    @GetMapping("/{id}")
    public String getItemById(@PathVariable Long id, Model model) {
        model.addAttribute("item", itemService.findById(id));
        return "items/view";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("item", new ItemDTO());
        model.addAttribute("countries", countryService.findAll());
        return "items/create";
    }

    @PostMapping
    public String createItem(@ModelAttribute ItemDTO itemDTO) {
        itemService.create(itemDTO);
        return "redirect:/items";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("item", itemService.findById(id));
        model.addAttribute("countries", countryService.findAll());
        return "items/edit";
    }

    @PostMapping("/update/{id}")
    public String updateItem(@PathVariable Long id, @ModelAttribute ItemDTO itemDTO) {
        itemService.update(id, itemDTO);
        return "redirect:/items";
    }

    @GetMapping("/delete/{id}")
    public String deleteItem(@PathVariable Long id) {
        itemService.deleteById(id);
        return "redirect:/items";
    }
}