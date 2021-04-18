package com.farhan.sps.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VacancyModel {

    @SerializedName("Id")
    @Expose
    private String id;
    @SerializedName("Title")
    @Expose
    private String title;
    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("NoOfVacancies")
    @Expose
    private String noOfVacancies;
    @SerializedName("VacancyCreated")
    @Expose
    private String vacancyCreated;
    @SerializedName("OwnedBy")
    @Expose
    private String ownedBy;
    @SerializedName("CompanyId")
    @Expose
    private String companyId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNoOfVacancies() {
        return noOfVacancies;
    }

    public void setNoOfVacancies(String noOfVacancies) {
        this.noOfVacancies = noOfVacancies;
    }

    public String getVacancyCreated() {
        return vacancyCreated;
    }

    public void setVacancyCreated(String vacancyCreated) {
        this.vacancyCreated = vacancyCreated;
    }

    public String getOwnedBy() {
        return ownedBy;
    }

    public void setOwnedBy(String ownedBy) {
        this.ownedBy = ownedBy;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }



}
