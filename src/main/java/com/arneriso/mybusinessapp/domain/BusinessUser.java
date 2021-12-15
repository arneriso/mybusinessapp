package com.arneriso.mybusinessapp.domain;

import java.util.List;

public class BusinessUser {

    private String id;
    private String userName;
    private List<String> sectorIds;
    private boolean agreementToTerms;

    public BusinessUser() {
    }

    public BusinessUser(String id, String userName, List<String> sectorIds, boolean agreementToTerms) {
        this.id = id;
        this.userName = userName;
        this.sectorIds = sectorIds;
        this.agreementToTerms = agreementToTerms;
    }

    public List<String> getSectorIds() {
        return sectorIds;
    }

    public void setSectorIds(List<String> sectorIds) {
        this.sectorIds = sectorIds;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean getAgreementToTerms() {
        return agreementToTerms;
    }

    public void setAgreementToTerms(boolean agreementToTerms) {
        this.agreementToTerms = agreementToTerms;
    }
}
