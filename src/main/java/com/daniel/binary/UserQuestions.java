package com.daniel.binary;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.avro.reflect.AvroName;

import java.util.ArrayList;
import java.util.Objects;

public class UserQuestions {
    @JsonProperty("id")
    public String id;
    @JsonProperty("response_time")
    public ArrayList<ResponseTime> responseTime;
}

class ResponseTime {
    @JsonProperty("percentiles")
    public Percentiles percentiles;
    @JsonProperty("period")
    public Integer period;
    @JsonProperty("sample")
    public Integer sample;
    @JsonProperty("unanswered_questions")
    public Float unansweredQuestions;
}

class Percentiles {
    @JsonProperty("10")
    @AvroName("p10")
    public Float percentile10;
    @JsonProperty("25")
    @AvroName("p25")
    public Float percentile25;
    @JsonProperty("50")
    @AvroName("p50")
    public Float percentile50;
    @JsonProperty("75")
    @AvroName("p75")
    public Float percentile75;
    @JsonProperty("90")
    @AvroName("p90")
    public Float percentile90;
}