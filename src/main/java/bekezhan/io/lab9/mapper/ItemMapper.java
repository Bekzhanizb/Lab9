package bekezhan.io.lab9.mapper;

import bekezhan.io.lab9.dto.ItemDTO;
import bekezhan.io.lab9.entity.Item;
import bekezhan.io.lab9.entity.Country;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    @Mapping(source = "name", target = "itemName")
    @Mapping(source = "price", target = "itemPrice")
    @Mapping(source = "quantity", target = "itemQuantity")
    @Mapping(source = "country.id", target = "countryId")
    ItemDTO toDTO(Item item);

    @Mapping(source = "itemName", target = "name")
    @Mapping(source = "itemPrice", target = "price")
    @Mapping(source = "itemQuantity", target = "quantity")
    @Mapping(target = "country", ignore = true)
    Item toEntity(ItemDTO dto);

    List<ItemDTO> toDTOs(List<Item> items);


}