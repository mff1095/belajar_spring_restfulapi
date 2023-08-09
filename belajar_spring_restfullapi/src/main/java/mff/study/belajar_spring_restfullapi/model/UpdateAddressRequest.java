package mff.study.belajar_spring_restfullapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAddressRequest {
    private String street;

    @JsonIgnore
    private String contactId;

    @JsonIgnore
    private String addressId;

    private String city;

    private String province;

    private String country;

    private String postalCode;
}
