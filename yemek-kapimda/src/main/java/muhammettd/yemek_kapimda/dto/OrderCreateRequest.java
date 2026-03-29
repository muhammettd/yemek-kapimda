package muhammettd.yemek_kapimda.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class OrderCreateRequest {

    @NotNull(message = "Restoran ID gereklidir")
    private Long restaurantId;

    @NotNull(message = "Teslimat adresi seçilmelidir")
    private Long deliveryAddressId;

    @NotEmpty(message = "Sepet boş olamaz")
    private List<OrderItemRequest> items;

    public OrderCreateRequest() {
    }

    public OrderCreateRequest(Long restaurantId, Long deliveryAddressId, List<OrderItemRequest> items) {
        this.restaurantId = restaurantId;
        this.deliveryAddressId = deliveryAddressId;
        this.items = items;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Long getDeliveryAddressId() {
        return deliveryAddressId;
    }

    public void setDeliveryAddressId(Long deliveryAddressId) {
        this.deliveryAddressId = deliveryAddressId;
    }

    public List<OrderItemRequest> getItems() {
        return items;
    }

    public void setItems(List<OrderItemRequest> items) {
        this.items = items;
    }
}
