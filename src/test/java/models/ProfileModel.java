package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProfileModel {

    @Builder.Default
    private Integer profileId = 0;

    @Builder.Default
    private String name = "Unknown artist";

    @Builder.Default
    private String email = "Unknown title";

    @Builder.Default
    private String password = "Default pass";

}
