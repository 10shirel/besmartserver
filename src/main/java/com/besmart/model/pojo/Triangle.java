package com.besmart.model.pojo;

import com.besmart.model.enums.State;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Triangle {
    @JsonProperty
    protected String id;

    @JsonProperty
    private String firstEdge;

    @JsonProperty
    private String secondEdge;

    @JsonProperty
    private String hypotenuse;

    @JsonProperty
    private State state;

    public Triangle() {
    }

    public Triangle(String id, String firstEdge, String secondEdge) {
        this.id = id;
        this.firstEdge = firstEdge;
        this.secondEdge = secondEdge;
        this.state = State.PRECALC;
    }

    public Triangle(String id, String firstEdge, String secondEdge, String hypotenuse, State state) {
        this(id, firstEdge, secondEdge);
        this.hypotenuse = hypotenuse;
        this.state = state;
    }

    public String getId() {
        return id;
    }

    public String getFirstEdge() {
        return firstEdge;
    }

    public String getSecondEdge() {
        return secondEdge;
    }

    public String getHypotenuse() {
        return hypotenuse;
    }

    public State getState() {
        return state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Triangle triangle = (Triangle) o;

        if (id != null ? !id.equals(triangle.id) : triangle.id != null) return false;
        if (firstEdge != null ? !firstEdge.equals(triangle.firstEdge) : triangle.firstEdge != null) return false;
        if (secondEdge != null ? !secondEdge.equals(triangle.secondEdge) : triangle.secondEdge != null) return false;
        if (hypotenuse != null ? !hypotenuse.equals(triangle.hypotenuse) : triangle.hypotenuse != null) return false;
        return state == triangle.state;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (firstEdge != null ? firstEdge.hashCode() : 0);
        result = 31 * result + (secondEdge != null ? secondEdge.hashCode() : 0);
        result = 31 * result + (hypotenuse != null ? hypotenuse.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Triangle{" +
                "id='" + id + '\'' +
                ", firstEdge='" + firstEdge + '\'' +
                ", secondEdge='" + secondEdge + '\'' +
                ", hypotenuse='" + hypotenuse + '\'' +
                ", state=" + state +
                '}';
    }
}


