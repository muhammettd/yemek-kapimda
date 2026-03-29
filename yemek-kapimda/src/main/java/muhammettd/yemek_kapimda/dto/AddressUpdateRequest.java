package muhammettd.yemek_kapimda.dto;

import jakarta.validation.constraints.NotBlank;


public class AddressUpdateRequest {

    @NotBlank(message = "Adres başlığı boş bırakılamaz (Örn: Ev, İş)")
    private String title;

    @NotBlank(message = "Şehir bilgisi boş bırakılamaz")
    private String city;

    @NotBlank(message = "İlçe bilgisi boş bırakılamaz")
    private String district;

    @NotBlank(message = "Açık adres boş bırakılamaz")
    private String fullAddress;

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
