package com.example.springpostgregforclients.service;

import com.example.springpostgregforclients.repo.ResponseWrap;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseEntityBuilder {
    private HttpStatus status = HttpStatus.OK;
    private ResponseWrap wrap;

    public ResponseEntityBuilder status(HttpStatus status) {
        this.status = status;
        return this;
    }

    public ResponseEntityBuilder wrap(ResponseWrap responseWrap) {
        this.wrap = responseWrap;
        return this;
    }

    public ResponseEntity<ResponseWrap> build() {
        return ResponseEntity.status(status).body(wrap);
    }
}
