package muhammettd.yemek_kapimda.dto;

import jakarta.validation.constraints.NotBlank;

public class AddressCreateRequest {

    @NotBlank
    private String title;

    @NotBlank
    private String city;

    @NotBlank
    private String district;

    @NotBlank
    private String fullAddress;

    public AddressCreateRequest() {
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
