package mff.study.belajar_spring_restfullapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
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

    @NotBlank
    private String country;

    private String postalCode;
}
