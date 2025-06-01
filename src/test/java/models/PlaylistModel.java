package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlaylistModel {

    @Builder.Default
    @JsonProperty("id")
    private Integer playlistId = 0;

    @Builder.Default
    private String name = "Playlist";

}
