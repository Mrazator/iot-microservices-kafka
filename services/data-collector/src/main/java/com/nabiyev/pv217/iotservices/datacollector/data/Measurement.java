package com.nabiyev.pv217.iotservices.datacollector.data;

public class Measurement extends Object {
    public String name;
    public Float value;
    public Long timestamp;

    @Override
    public String toString() {
        return "Measurement{" +
                "name='" + name + '\'' +
                ", value=" + value +
                ", timestamp=" + timestamp +
                '}';
    }
}



