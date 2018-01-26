package com.example.umaransari.myproject;

/**
 * Created by Umar Ansari on 26/01/2018.
 */

public class Donor {
    String Name, Blood, Country, Phone;

    public Donor(String name, String blood, String country, String phone) {
        Name = name;
        Blood = blood;
        Country = country;
        Phone = phone;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getBlood() {
        return Blood;
    }

    public void setBlood(String blood) {
        Blood = blood;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }
}
