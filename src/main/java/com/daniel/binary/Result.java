package com.daniel.binary;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


@JsonPropertyOrder({"serializer", "#_of_rows", "byte_size", "ser_elapsed_mean", "ser_elapsed_std_dev", "deser_elapsed_mean", "deser_elapsed_std_dev"})
public class Result {
    @JsonProperty("serializer")
    public String serializer;
    @JsonProperty("#_of_rows")
    public Integer rownums;
    @JsonProperty("byte_size")
    public Integer serSize;
    @JsonProperty("ser_elapsed_mean")
    public Double serTimeMean;
    @JsonProperty("ser_elapsed_std_dev")
    public Double serTimeStdDev;
    @JsonProperty("deser_elapsed_mean")
    public Double deserTimeMean;
    @JsonProperty("deser_elapsed_std_dev")
    public Double deserTimeStdDev;

    public Result(String serializer, Integer rownums, Integer serSize, Double serTimeMean, Double serTimeStdDev, Double deserTimeMean, Double deserTimeStdDev) {
        this.serializer = serializer;
        this.rownums = rownums;
        this.serSize = serSize;
        this.serTimeMean = serTimeMean;
        this.serTimeStdDev = serTimeStdDev;
        this.deserTimeMean = deserTimeMean;
        this.deserTimeStdDev = deserTimeStdDev;
    }
}
