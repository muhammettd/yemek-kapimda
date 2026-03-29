package muhammettd.yemek_kapimda.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class OrderItemRequest {

    @NotNull(message = "Ürün ID gereklidir")
    private Long productId;

    @NotNull(message = "Adet gereklidir")
    @Min(value = 1, message = "Adet en az 1 olmalıdır")
    private Integer quantity;

    public OrderItemRequest() {
    }

    public OrderItemRequest(Long productId, Integer quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
