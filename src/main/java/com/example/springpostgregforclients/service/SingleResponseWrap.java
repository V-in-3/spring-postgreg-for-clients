package com.example.springpostgregforclients.service;

import com.example.springpostgregforclients.repo.ResponseWrap;
import lombok.Data;

@Data
public class SingleResponseWrap<T> implements ResponseWrap  {
    private Object wrap;

    public SingleResponseWrap(Object wrap) {
        this.wrap = wrap;
    }
}