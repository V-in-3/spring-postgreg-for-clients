package com.example.springpostgregforclients.dto;

import com.example.springpostgregforclients.model.Customer;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.util.JSONPObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.json.JSONObject;

import java.util.Optional;

@Schema(
        name = "CustomerResponse",
        description = "Returning customer's surname and initials when customer is exists"
)

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerResponse {
    @Schema(description = "Customer's surname and initials")
    String surnameAndInitials;

    public CustomerResponse(String surnameAndInitials) {
        this.surnameAndInitials = surnameAndInitials;
    }

    public CustomerResponse() {
    }

    public Optional<String> getSurnameAndInitials() {
        return surnameAndInitials==null ? Optional.empty() : Optional.of(surnameAndInitials);
    }

    public void setSurnameAndInitials(String surnameAndInitials) {
        this.surnameAndInitials = surnameAndInitials;
    }


    @Override
    public String toString()
    {
        return "{\"surnameAndInitials\":\"" + surnameAndInitials + "\"}";
    }
/*
    public JSONObject getFio()
    {
        JSONObject jo = new JSONObject();
        jo.put("fio", surnameAndInitials);
        return jo;
    }

 */
}
