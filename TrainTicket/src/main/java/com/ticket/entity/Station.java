package com.ticket.entity;

public class Station {
    private int id;
    private String station;

    private String abbr;
    private String pinyin;
    private String spell;
    private String code;
    private double longitude;
    private double latitude;

    @Override
    public String toString() {
        return "Station{" +
                "id=" + id +
                ", station='" + station + '\'' +
                ", abbr='" + abbr + '\'' +
                ", pinyin='" + pinyin + '\'' +
                ", spell='" + spell + '\'' +
                ", code='" + code + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getAbbr() {
        return abbr;
    }

    public void setAbbr(String abbr) {
        this.abbr = abbr;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getSpell() {
        return spell;
    }

    public void setSpell(String spell) {
        this.spell = spell;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
