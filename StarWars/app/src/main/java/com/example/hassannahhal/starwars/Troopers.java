package com.example.hassannahhal.starwars;

/**
 * Created by hassannahhal on 2015-11-28.
 */
public class Troopers {

    private String plantName;
    private int id;

    public Troopers(int id) {
        //this.plantName = name;
        this.id = id;
    }

    public Troopers() {
    }

    public String getPlantName() {
        return plantName;
    }

    public void setPlantName(String name) {
        this.plantName = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int lastId) {
        this.id = lastId + 1;
    }


}
