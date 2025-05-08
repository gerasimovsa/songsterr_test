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
public class SongModel {

    @Builder.Default
    private Integer songId = 0;

    @Builder.Default
    private String artist = "Unknown artist";

    @Builder.Default
    private String title = "Unknown title";

    @Builder.Default
    private Integer artistId = 0;
}
