package muhammettd.yemek_kapimda.model;

import jakarta.persistence.*;

@Entity
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private User owner;

    private String name;
    private String description;
    private String logoUrl;

    private Double minAmountOrder;
    private String estimatedDeliveryTime;
    private boolean isOpen;

    private String cuisine;
    private Double ratingStars;

    public Restaurant() {
    }

    public Restaurant(Long id, User owner, String name,
                      String description, String logoUrl, Double minAmountOrder,
                      String estimatedDeliveryTime, boolean isOpen, String cuisine, Double ratingStars) {
        this.id = id;
        this.owner = owner;
        this.name = name;
        this.description = description;
        this.logoUrl = logoUrl;
        this.minAmountOrder = minAmountOrder;
        this.estimatedDeliveryTime = estimatedDeliveryTime;
        this.isOpen = isOpen;
        this.cuisine = cuisine;
        this.ratingStars = ratingStars;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public Double getMinAmountOrder() {
        return minAmountOrder;
    }

    public void setMinAmountOrder(Double minAmountOrder) {
        this.minAmountOrder = minAmountOrder;
    }

    public String getEstimatedDeliveryTime() {
        return estimatedDeliveryTime;
    }

    public void setEstimatedDeliveryTime(String estimatedDeliveryTime) {
        this.estimatedDeliveryTime = estimatedDeliveryTime;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public Double getRatingStars() {
        return ratingStars;
    }

    public void setRatingStars(Double ratingStars) {
        this.ratingStars = ratingStars;
    }
}
