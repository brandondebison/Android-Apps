package com.example.jsontest;

public class PlayersModel {

    private String name, country, city;

    public void setName (String name) {
        this.name = name;
    }

    public void setCountry (String country) { this.country = country;    }
    public void setCity (String city) {
        this.city = city;
    }


    public String getName () { return this.name; }
    public String getCountry () { return this.country; }
    public String getCity () { return this.city; }


}
