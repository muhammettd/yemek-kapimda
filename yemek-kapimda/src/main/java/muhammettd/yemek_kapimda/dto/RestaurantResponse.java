package muhammettd.yemek_kapimda.dto;

public class RestaurantResponse {

    private Long id;
    private String name;
    private String description;
    private String logoUrl;
    private Double minOrderAmount;
    private String estimatedDeliveryTime;
    private boolean isOpen;

    private String cuisine;
    private Double ratingStars;

    public RestaurantResponse() {
    }

    public RestaurantResponse(Long id, String name, String description,
                              String logoUrl, Double minOrderAmount, String estimatedDeliveryTime,
                              boolean isOpen, String cuisine, Double ratingStars) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.logoUrl = logoUrl;
        this.minOrderAmount = minOrderAmount;
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

    public Double getMinOrderAmount() {
        return minOrderAmount;
    }

    public void setMinOrderAmount(Double minOrderAmount) {
        this.minOrderAmount = minOrderAmount;
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
