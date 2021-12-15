package com.arneriso.mybusinessapp.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

public class BusinessSector {

    public BusinessSector() {
    }

    private String id;
    private String name;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<BusinessSector> subSectors;

    public List<BusinessSector> getSubSectors() {
        return subSectors;
    }

    public void setSubSectors(List<BusinessSector> subSectors) {
        this.subSectors = subSectors;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
