package com.example.vetserveph.Others;

public class Pet {
    String pet_name;
    String species;
    String breed;
    String gender;
    String age;
    public Pet(String pet_name, String species, String breed, String gender, String age) {
        this.pet_name = pet_name;
        this.species = species;
        this.breed = breed;
        this.gender = gender;
        this.age = age;
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

    public String getAge() {
        return age;
    }
}
