package com.pdcmb.covid19stats.utils;

import java.util.Objects;


/**
 * Simple class that rappresent a pair of values
 * 
 * @author Mateusz Ziomek
 * @param <T> First value (key)
 * @param <R> Second value (value)
 */
public class Pair<T, R> {

    private T key;

    private R value;


    public Pair() {
    }

    public Pair(T key, R value) {
        this.key = key;
        this.value = value;
    }

    public T getKey() {
        return this.key;
    }

    public void setKey(T key) {
        this.key = key;
    }

    public R getValue() {
        return this.value;
    }

    public void setValue(R value) {
        this.value = value;
    }

    public Pair<T, R>  key(T key) {
        this.key = key;
        return this;
    }

    public Pair<T, R> value(R value) {
        this.value = value;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Pair)) {
            return false;
        }
        Pair<T, R>  pair = (Pair<T, R>) o;
        return Objects.equals(key, pair.key) && Objects.equals(value, pair.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, value);
    }

    @Override
    public String toString() {
        return "{" +
            " key='" + getKey() + "'" +
            ", value='" + getValue() + "'" +
            "}";
    }
     
}
