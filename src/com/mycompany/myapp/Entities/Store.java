/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.Entities;

import java.util.Objects;

/**
 *
 * @author Jmili
 */
public class Store {
    private int id ;
    private String nameSt;
    private String location;
    private User owner;
    private String photo;
    private float rating;

    public float getRating() {
        return rating;
    }

    public Store(int id, String nameSt, String location, User owner, String photo, float rating) {
        this.id = id;
        this.nameSt = nameSt;
        this.location = location;
        this.owner = owner;
        this.photo = photo;
        this.rating = rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public Store(int id, String nameSt, String location, User owner, String photo) {
        this.id = id;
        this.nameSt = nameSt;
        this.location = location;
        this.owner = owner;
        this.photo = photo;
    }
    public Store(String nameSt, String location, User owner, String photo) {
        this.nameSt = nameSt;
        this.location = location;
        this.owner = owner;
        this.photo = photo;
    }
    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPhoto() {
        return photo;
    }
    public Store() {
    }
    
    public Store(int id,String nameSt, String location,User owner) {
        this.id=id;
        this.nameSt = nameSt;
        this.location = location;
        this.owner = owner;
    }
    public Store(String nameSt, String location,User owner) {
        this.nameSt = nameSt;
        this.location = location;
        this.owner = owner;
    }
        public Store(String nameSt, String location) {
        this.nameSt = nameSt;
        this.location = location;
    }

    public Store(int id, String nameSt) {
        this.id = id;
        this.nameSt = nameSt;
    }
    
        public Store(int id) {
        this.id=id;
    }

    public String getLocation() {
        return location;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public void setNameSt(String nameSt) {
        this.nameSt = nameSt;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public User getOwner() {
        return owner;
    }

    public String getNameSt() {
        return nameSt;
    }
    public void setId(int id) {
        this.id = id;
}
    public int getId() {
        return id;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Store other = (Store) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.nameSt, other.nameSt)) {
            return false;
        }
        if (!Objects.equals(this.location, other.location)) {
            return false;
        }
        if (!Objects.equals(this.photo, other.photo)) {
            return false;
        }
        return Objects.equals(this.owner, other.owner);
    }



    @Override
    public String toString() {
        return "Store{" + "id=" + id + ", nameSt=" + nameSt + ", location=" + location + ", owner=" + owner + '}';
    }   
}
