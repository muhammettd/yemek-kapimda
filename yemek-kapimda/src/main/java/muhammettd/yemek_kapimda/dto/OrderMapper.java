package muhammettd.yemek_kapimda.dto;

import muhammettd.yemek_kapimda.model.Order;
import muhammettd.yemek_kapimda.model.OrderStatus;

public class OrderMapper {

    public static OrderResponse toResponse(Order order) {
        if (order == null) {
            return null;
        }
        OrderResponse dto = new OrderResponse(
                order.getId(),
                order.getRestaurant() != null ? order.getRestaurant().getName() : null,
                order.getStatus() != null ? order.getStatus().name() : null,
                order.getTotalAmount(),
                order.getCreatedAt()
        );
        dto.setStatusLabel(statusLabelTr(order.getStatus()));
        return dto;
    }

    private static String statusLabelTr(OrderStatus status) {
        if (status == null) {
            return null;
        }
        return switch (status) {
            case PENDING -> "Sipariş onaylandı";
            case PREPARING -> "Hazırlanıyor";
            case ON_THE_WAY -> "Kurye yolda";
            case DELIVERED -> "Teslim edildi";
            case CANCELED -> "İptal edildi";
        };


    }

}
