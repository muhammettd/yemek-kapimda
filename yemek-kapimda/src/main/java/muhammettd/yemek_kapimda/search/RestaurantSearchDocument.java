package muhammettd.yemek_kapimda.search;

import jakarta.persistence.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;

@Document(indexName = "restaurant_v1")
public class RestaurantSearchDocument {

    @Id
    private Long id;

    @Field(type = FieldType.Text)
    private String name;

    // Filtre için keyword kullanıyoruz (tam eşleşme).
    @Field(type = FieldType.Keyword)
    private String cuisine;

    @Field(type = FieldType.Double)
    private Double ratingStars;

    @Field(type = FieldType.Boolean)
    private boolean open;

    @Field(type = FieldType.Text)
    private String description;

    @Field(type = FieldType.Keyword)
    private String logoUrl;

    @Field(type = FieldType.Double)
    private Double minOrderAmount;

    @Field(type = FieldType.Keyword)
    private String estimatedDeliveryTime;

    // Restoranın menüsündeki ürün adlarını dokümana gömüyoruz.
    @Field(type = FieldType.Text)
    private List<String> productNames;

    public RestaurantSearchDocument() {
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

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
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

    public List<String> getProductNames() {
        return productNames;
    }

    public void setProductNames(List<String> productNames) {
        this.productNames = productNames;
    }


}
