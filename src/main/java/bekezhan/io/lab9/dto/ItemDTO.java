package bekezhan.io.lab9.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemDTO {
    private Long id;
    private String itemName;
    private int itemPrice;
    private int itemQuantity;
    private Long countryId;
}
