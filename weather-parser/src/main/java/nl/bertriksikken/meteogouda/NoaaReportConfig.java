package nl.bertriksikken.meteogouda;

import java.time.Duration;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect(getterVisibility = Visibility.NONE)
public class NoaaReportConfig {

    @JsonProperty("host")
    private String host = "https://www.meteo-gouda.nl";

    @JsonProperty("timeout")
    private int timeoutSec = 30;

    public String getHost() {
        return host;
    }

    public Duration getTimeout() {
        return Duration.ofSeconds(timeoutSec);
    }

}
