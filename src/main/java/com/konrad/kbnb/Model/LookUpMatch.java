package com.konrad.kbnb.Model;

public class LookUpMatch {
    private String name;
    private Long id;

    public LookUpMatch(String name, long id) {
        this.name = name;
        this.id = id;
    }

    public LookUpMatch() {}
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
