package edu.upc.dsa.minimo2;

public class Repos {
private String name;
private String language;

    public Repos(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Repos(String name, String lenguage) {
        this.name = name;
        this.language = lenguage;
    }

    public Repos() {
    }
}
