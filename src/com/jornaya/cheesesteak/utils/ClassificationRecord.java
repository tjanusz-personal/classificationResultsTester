package com.jornaya.cheesesteak.utils;

import java.util.Arrays;

public class ClassificationRecord {

    private String campaignClassification;
    private String classificationMethod;
    private String leadId;
    private String recordId;
    private String industry;
    private double scoreOther;
    private double scoreAutoSales;
    private double scoreEducation;
    private double scoreInsurance;
    private double scoreLegal;
    private double scoreFinancialServices;
    private double scoreRealEstate;
    private double scoreHomeServices;
    private double scoreJobs;
    private double scoreSeniorLiving;

    public String getCampaignClassification() {
        return campaignClassification;
    }

    public void setCampaignClassification(String campaignClassification) {
        this.campaignClassification = campaignClassification;
    }

    public String getClassificationMethod() {
        return classificationMethod;
    }

    public void setClassificationMethod(String classificationMethod) {
        this.classificationMethod = classificationMethod;
    }

    public String getLeadId() {
        return leadId;
    }

    public void setLeadId(String leadId) {
        this.leadId = leadId;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public double getScoreOther() {
        return scoreOther;
    }

    public void setScoreOther(double scoreOther) {
        this.scoreOther = scoreOther;
    }

    public double getScoreAutoSales() {
        return scoreAutoSales;
    }

    public void setScoreAutoSales(double scoreAutoSales) {
        this.scoreAutoSales = scoreAutoSales;
    }

    public double getScoreEducation() {
        return scoreEducation;
    }

    public void setScoreEducation(double scoreEducation) {
        this.scoreEducation = scoreEducation;
    }

    public double getScoreInsurance() {
        return scoreInsurance;
    }

    public void setScoreInsurance(double scoreInsurance) {
        this.scoreInsurance = scoreInsurance;
    }

    public double getScoreLegal() {
        return scoreLegal;
    }

    public void setScoreLegal(double scoreLegal) {
        this.scoreLegal = scoreLegal;
    }

    public double getScoreFinancialServices() {
        return scoreFinancialServices;
    }

    public void setScoreFinancialServices(double scoreFinancialServices) {
        this.scoreFinancialServices = scoreFinancialServices;
    }

    public double getScoreRealEstate() {
        return scoreRealEstate;
    }

    public void setScoreRealEstate(double scoreRealEstate) {
        this.scoreRealEstate = scoreRealEstate;
    }

    public double getScoreHomeServices() {
        return scoreHomeServices;
    }

    public void setScoreHomeServices(double scoreHomeServices) {
        this.scoreHomeServices = scoreHomeServices;
    }

    public double getScoreJobs() {
        return scoreJobs;
    }

    public void setScoreJobs(double scoreJobs) {
        this.scoreJobs = scoreJobs;
    }

    public double getScoreSeniorLiving() {
        return scoreSeniorLiving;
    }

    public void setScoreSeniorLiving(double scoreSeniorLiving) {
        this.scoreSeniorLiving = scoreSeniorLiving;
    }

    public double getScoreForIndustry(String industry) {
        switch (industry) {
            case "Other":
                return this.getScoreOther();
            case "Auto Sales":
                return this.getScoreAutoSales();
            case "Education":
                return this.getScoreEducation();
            case "Insurance":
                return this.getScoreInsurance();
            case "Legal":
                return this.getScoreLegal();
            case "Financial Services":
                return this.getScoreFinancialServices();
            case "Real Estate":
                return this.getScoreRealEstate();
            case "Home Services":
                return this.getScoreHomeServices();
            case "Jobs":
                return this.getScoreJobs();
            case "Senior Living":
                return this.getScoreSeniorLiving();
            default:
                throw new RuntimeException("Invalid industry called!");
        }
    }

    public double getMaxScoreAcrossAllIndustries() {
        double[] industryTotals = {
                this.getScoreAutoSales(),
                this.getScoreSeniorLiving(),
                this.getScoreEducation(),
                this.getScoreFinancialServices(),
                this.getScoreHomeServices(),
                this.getScoreInsurance(),
                this.getScoreJobs(),
                this.getScoreLegal(),
                this.getScoreRealEstate(),
                this.getScoreOther(),
        };

        double max = Arrays.stream(industryTotals).max().getAsDouble();
        return max;
    }

}
