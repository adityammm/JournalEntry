package com.myFirstAPI.testAPI.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Weather {

    public Current current;

    @Getter
    @Setter
    public class Current{
        public int last_updated_epoch;
        public String last_updated;
        @JsonProperty("temp_c")
        public double tempC;
        @JsonProperty("temp_f")
        public double tempF;
        @JsonProperty("feelslike_c")
        public double feelslikeC;
    }

}
