package muhammettd.yemek_kapimda.dto;

import java.time.LocalDateTime;

public class OrderResponse {

    private Long orderId;
    private String restaurantName;
    private String status;
    private Double totalAmount;
    private LocalDateTime createdAt;
    // Order confirmed: Pending
    private String statusLabel;

    public OrderResponse() {
    }

    public OrderResponse(Long orderId, String restaurantName, String status, Double totalAmount,
                         LocalDateTime createdAt) {
        this.orderId = orderId;
        this.restaurantName = restaurantName;
        this.status = status;
        this.totalAmount = totalAmount;
        this.createdAt = createdAt;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getStatusLabel() {
        return statusLabel;
    }

    public void setStatusLabel(String statusLabel) {
        this.statusLabel = statusLabel;
    }


}
