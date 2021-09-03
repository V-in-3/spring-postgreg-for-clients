package com.example.springpostgregforclients.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;

@Schema(
        name = "CustomerRequest",
        description = "Request for sending customer's surname and initials"
)
@Value
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerRequest {

    @Schema(description = "customer Id", required = true)
    String id;

    public CustomerRequest(String id) {
        this.id = id;
    }

    public CustomerRequest() {
        id = null;
    }
}
