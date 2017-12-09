package com.besmart.model;

import com.besmart.model.enums.GetBy;
import com.besmart.model.enums.State;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class Filter {
    @JsonProperty
    private List<BigInteger> ids;

    @JsonProperty
    private State state;

    @JsonProperty
    private GetBy getBy;


    public List<BigInteger> getIds() {
        return ids;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public GetBy getGetBy() {
        return getBy;
    }

    public void setGetBy(GetBy getBy) {
        this.getBy = getBy;
    }
}
