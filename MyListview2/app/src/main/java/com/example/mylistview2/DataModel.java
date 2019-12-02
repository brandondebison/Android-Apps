package com.example.mylistview2;

public class DataModel {
    String name;
    String type;
    String version_number;
    String feature;

    public DataModel(String name, String type, String version_number, String feature) {
        this.name = name;
        this.type = type;
        this.version_number = version_number;
        this.feature = feature;
    }

    public String getName() {return this.name;}

    public String getType() {return this.type;}

    public String getVersion_number() {return this.version_number;}

    public String getFeature() {return this.feature;}

}
