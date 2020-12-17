package models;

//Created by Tharuka Gamage on 20/11/20.
//Copyright © 2020 Tharuka Gamage. All rights reserved.

public class Questionare {
    private String token;
    private String ageGroup;
    private String living;
    private boolean own_a_vehivle;
    private boolean willing_to_buy;
    private String type;
    private float price;
    private boolean wait_for_drop;
    private boolean happy_with_price;

    public Questionare() { }


    public Questionare(String token, String ageGroup, String living, boolean own_a_vehivle, boolean willing_to_buy, String type, float price, boolean wait_for_drop, boolean happy_with_price) {
        this.token = token;
        this.ageGroup = ageGroup;
        this.living = living;
        this.own_a_vehivle = own_a_vehivle;
        this.willing_to_buy = willing_to_buy;
        this.type = type;
        this.price = price;
        this.wait_for_drop = wait_for_drop;
        this.happy_with_price = happy_with_price;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAgeGroup() {
        return ageGroup;
    }

    public void setAgeGroup(String ageGroup) {
        this.ageGroup = ageGroup;
    }

    public String getLiving() {
        return living;
    }

    public void setLiving(String living) {
        this.living = living;
    }

    public boolean isOwn_a_vehivle() {
        return own_a_vehivle;
    }

    public void setOwn_a_vehivle(boolean own_a_vehivle) {
        this.own_a_vehivle = own_a_vehivle;
    }

    public boolean isWilling_to_buy() {
        return willing_to_buy;
    }

    public void setWilling_to_buy(boolean willing_to_buy) {
        this.willing_to_buy = willing_to_buy;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public boolean isWait_for_drop() {
        return wait_for_drop;
    }

    public void setWait_for_drop(boolean wait_for_drop) {
        this.wait_for_drop = wait_for_drop;
    }

    public boolean isHappy_with_price() {
        return happy_with_price;
    }

    public void setHappy_with_price(boolean happy_with_price) {
        this.happy_with_price = happy_with_price;
    }
}
