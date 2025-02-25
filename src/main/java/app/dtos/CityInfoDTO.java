package app.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "city_info")
public class CityInfoDTO
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    private String hovedtype;

    private String undertype;

    @JsonProperty("primærtnavn")
    private String primærtNavn;

    @JsonProperty("primærnavnestatus")
    private String primærnavneStatus;

    private String ændret;

    @JsonProperty("geo_ændret")
    private String geoÆndret;

    @JsonProperty("geo_version")
    private int geoVersion;

    private String href;

    @OneToMany(cascade =  CascadeType.ALL)
    @JoinColumn(name = "city_info_id") // opretter en FK i city_info tabellen
    private List<Kommuner> kommuner;

    @Override
    public String toString() {
        return "CityInfoDTO{" +
            "id=" + id +
            ", hovedtype='" + hovedtype + '\'' +
            ", undertype='" + undertype + '\'' +
            ", primærtnavn='" + primærtNavn + '\'' +
            ", primærnavnestatus='" + primærnavneStatus + '\'' +
            ", ændret='" + ændret + '\'' +
            ", geoÆndret='" + geoÆndret + '\'' +
            ", geoVersion=" + geoVersion +
            ", href='" + href + '\'' +
            '}';
    }

    @Entity
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Kommuner
    {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        private String href;
        private Integer kode;

        private String navn;
    }

    @Entity
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Egenskaber
    {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        @JsonProperty("bebyggelseskode")
        private Integer bebyggelsesKode;

        @JsonProperty("indbyggertal")
        private String indbyggerTal;
    }

}
