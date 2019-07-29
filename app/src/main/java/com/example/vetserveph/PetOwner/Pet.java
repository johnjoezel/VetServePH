package com.example.vetserveph.PetOwner;

public class Pet {
    String pet_name;
    String species;
    String breed;
    String gender;
    String color;
    private String dob;



    public Pet(String pet_name, String species, String breed, String gender, String dob, String color) {
        this.pet_name = pet_name;
        this.species = species;
        this.breed = breed;
        this.gender = gender;
        this.dob = dob;
        this.color = color;
    }

    public String getPet_name() {
        return pet_name;
    }

    public String getSpecies() {
        return species;
    }

    public String getBreed() {
        return breed;
    }

    public String getGender() {
        return gender;
    }

    public String getDob() {
        return dob;
    }

    public String getColor() {
        return color;
    }

}
