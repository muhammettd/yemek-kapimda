package muhammettd.yemek_kapimda.dto;

public class AddressResponse {

    private Long id;
    private String title;
    private String city;
    private String district;
    private String fullAddress;

    public AddressResponse() {
    }

    public AddressResponse(Long id, String title, String city, String district, String fullAddress) {
        this.id = id;
        this.title = title;
        this.city = city;
        this.district = district;
        this.fullAddress = fullAddress;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }
}
