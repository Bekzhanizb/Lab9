package bekezhan.io.lab9.mapper;

import bekezhan.io.lab9.dto.ItemDTO;
import bekezhan.io.lab9.entity.Item;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemMapper {
    @Mapping(source = "name", target = "itemName")
    @Mapping(source = "price", target = "itemPrice")
    @Mapping(source = "quantity", target = "itemQuantity")
    ItemDTO toDTO(Item item);

    @Mapping(source = "itemName", target = "name")
    @Mapping(source = "itemPrice", target = "price")
    @Mapping(source = "itemQuantity", target = "quantity")
    Item toEntity(ItemDTO dto);

    List<ItemDTO> toDTOs(List<Item> items);
}
