package com.arneriso.mybusinessapp.domain;

import java.util.List;

public class Sectors {

    public Sectors() {
    }

    private List<BusinessSector> value;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<BusinessSector> getValue() {
        return value;
    }

    public void setValue(List<BusinessSector> value) {
        this.value = value;
    }
}
