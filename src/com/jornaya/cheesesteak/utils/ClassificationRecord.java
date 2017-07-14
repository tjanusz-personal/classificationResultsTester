package com.jornaya.cheesesteak.utils;

import java.util.Arrays;
import java.util.List;

public class ClassificationRecord {

    private String campaignClassification;
    private String classificationMethod;
    private String leadId;
    private String recordId;
    private String industry;

    private String rawScoreOther;
    private String rawScoreAutoSales;
    private String rawScoreEducation;
    private String rawScoreInsurance;
    private String rawScoreLegal;
    private String rawScoreFinancialServices;
    private String rawScoreRealEstate;
    private String rawScoreHomeServices;
    private String rawScoreJobs;
    private String rawScoreSeniorLiving;

    private int scoreOtherCount;
    private int scoreAutoSalesCount;
    private int scoreEducationCount;
    private int scoreInsuranceCount;
    private int scoreLegalCount;
    private int scoreFinancialServicesCount;
    private int scoreRealEstateCount;
    private int scoreHomeServicesCount;
    private int scoreJobsCount;
    private int scoreSeniorLivingCount;

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

    public String getRawScoreOther() {
        return rawScoreOther;
    }

    public void setRawScoreOther(String rawScoreOther) {
        this.rawScoreOther = rawScoreOther;
    }

    public String getRawScoreAutoSales() {
        return rawScoreAutoSales;
    }

    public void setRawScoreAutoSales(String rawScoreAutoSales) {
        this.rawScoreAutoSales = rawScoreAutoSales;
    }

    public String getRawScoreEducation() {
        return rawScoreEducation;
    }

    public void setRawScoreEducation(String rawScoreEducation) {
        this.rawScoreEducation = rawScoreEducation;
    }

    public String getRawScoreInsurance() {
        return rawScoreInsurance;
    }

    public void setRawScoreInsurance(String rawScoreInsurance) {
        this.rawScoreInsurance = rawScoreInsurance;
    }

    public String getRawScoreLegal() {
        return rawScoreLegal;
    }

    public void setRawScoreLegal(String rawScoreLegal) {
        this.rawScoreLegal = rawScoreLegal;
    }

    public String getRawScoreFinancialServices() {
        return rawScoreFinancialServices;
    }

    public void setRawScoreFinancialServices(String rawScoreFinancialServices) {
        this.rawScoreFinancialServices = rawScoreFinancialServices;
    }

    public String getRawScoreRealEstate() {
        return rawScoreRealEstate;
    }

    public void setRawScoreRealEstate(String rawScoreRealEstate) {
        this.rawScoreRealEstate = rawScoreRealEstate;
    }

    public String getRawScoreHomeServices() {
        return rawScoreHomeServices;
    }

    public void setRawScoreHomeServices(String rawScoreHomeServices) {
        this.rawScoreHomeServices = rawScoreHomeServices;
    }

    public String getRawScoreJobs() {
        return rawScoreJobs;
    }

    public void setRawScoreJobs(String rawScoreJobs) {
        this.rawScoreJobs = rawScoreJobs;
    }

    public String getRawScoreSeniorLiving() {
        return rawScoreSeniorLiving;
    }

    public void setRawScoreSeniorLiving(String rawScoreSeniorLiving) {
        this.rawScoreSeniorLiving = rawScoreSeniorLiving;
    }

    public int getScoreOtherCount() {
        return scoreOtherCount;
    }

    public void setScoreOtherCount(int scoreOtherCount) {
        this.scoreOtherCount = scoreOtherCount;
    }

    public int getScoreAutoSalesCount() {
        return scoreAutoSalesCount;
    }

    public void setScoreAutoSalesCount(int scoreAutoSalesCount) {
        this.scoreAutoSalesCount = scoreAutoSalesCount;
    }

    public int getScoreEducationCount() {
        return scoreEducationCount;
    }

    public void setScoreEducationCount(int scoreEducationCount) {
        this.scoreEducationCount = scoreEducationCount;
    }

    public int getScoreInsuranceCount() {
        return scoreInsuranceCount;
    }

    public void setScoreInsuranceCount(int scoreInsuranceCount) {
        this.scoreInsuranceCount = scoreInsuranceCount;
    }

    public int getScoreLegalCount() {
        return scoreLegalCount;
    }

    public void setScoreLegalCount(int scoreLegalCount) {
        this.scoreLegalCount = scoreLegalCount;
    }

    public int getScoreFinancialServicesCount() {
        return scoreFinancialServicesCount;
    }

    public void setScoreFinancialServicesCount(int scoreFinancialServicesCount) {
        this.scoreFinancialServicesCount = scoreFinancialServicesCount;
    }

    public int getScoreRealEstateCount() {
        return scoreRealEstateCount;
    }

    public void setScoreRealEstateCount(int scoreRealEstateCount) {
        this.scoreRealEstateCount = scoreRealEstateCount;
    }

    public int getScoreHomeServicesCount() {
        return scoreHomeServicesCount;
    }

    public void setScoreHomeServicesCount(int scoreHomeServicesCount) {
        this.scoreHomeServicesCount = scoreHomeServicesCount;
    }

    public int getScoreJobsCount() {
        return scoreJobsCount;
    }

    public void setScoreJobsCount(int scoreJobsCount) {
        this.scoreJobsCount = scoreJobsCount;
    }

    public int getScoreSeniorLivingCount() {
        return scoreSeniorLivingCount;
    }

    public void setScoreSeniorLivingCount(int scoreSeniorLivingCount) {
        this.scoreSeniorLivingCount = scoreSeniorLivingCount;
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

    public int getScoreCountForIndustry(String industry) {
        switch (industry) {
            case "Other":
                return this.getScoreOtherCount();
            case "Auto Sales":
                return this.getScoreAutoSalesCount();
            case "Education":
                return this.getScoreEducationCount();
            case "Insurance":
                return this.getScoreInsuranceCount();
            case "Legal":
                return this.getScoreLegalCount();
            case "Financial Services":
                return this.getScoreFinancialServicesCount();
            case "Real Estate":
                return this.getScoreRealEstateCount();
            case "Home Services":
                return this.getScoreHomeServicesCount();
            case "Jobs":
                return this.getScoreJobsCount();
            case "Senior Living":
                return this.getScoreSeniorLivingCount();
            default:
                throw new RuntimeException("Invalid industry called!");
        }
    }

    public int getMaxCountAcrossAllIndustries() {
        int[] industryTotals = {
                this.getScoreAutoSalesCount(),
                this.getScoreSeniorLivingCount(),
                this.getScoreEducationCount(),
                this.getScoreFinancialServicesCount(),
                this.getScoreHomeServicesCount(),
                this.getScoreInsuranceCount(),
                this.getScoreJobsCount(),
                this.getScoreLegalCount(),
                this.getScoreRealEstateCount(),
                this.getScoreOtherCount(),
        };

        int max = Arrays.stream(industryTotals).max().getAsInt();
        return max;
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

    public int calculateIndustryCount(String industry) {
        if (industry == null || industry.isEmpty() || industry.equalsIgnoreCase("0")) {
            return 0;
        }
        String[] strings = industry.split("\\;");
        return strings.length;
    }

    public double calculateIndustryTotal(String industry) {
        if (industry == null || industry.isEmpty()) {
            return 0;
        }
        // string should be ; separate list at some point in future
        String[] strings = industry.split("\\;");
        List<String> stringList = Arrays.asList(strings);
        double total = stringList.stream().mapToDouble(Double::new).sum();
        return total;
    }

    public void calculateCounts() {
        this.setScoreAutoSalesCount(this.calculateIndustryCount(this.getRawScoreAutoSales()));
        this.setScoreSeniorLivingCount(this.calculateIndustryCount(this.getRawScoreSeniorLiving()));
        this.setScoreEducationCount(this.calculateIndustryCount(this.getRawScoreEducation()));
        this.setScoreFinancialServicesCount(this.calculateIndustryCount(this.getRawScoreFinancialServices()));
        this.setScoreHomeServicesCount(this.calculateIndustryCount(this.getRawScoreHomeServices()));
        this.setScoreInsuranceCount(this.calculateIndustryCount(this.getRawScoreInsurance()));
        this.setScoreJobsCount(this.calculateIndustryCount(this.getRawScoreJobs()));
        this.setScoreLegalCount(this.calculateIndustryCount(this.getRawScoreLegal()));
        this.setScoreRealEstateCount(this.calculateIndustryCount(this.getRawScoreRealEstate()));
        this.setScoreOtherCount(this.calculateIndustryCount(this.getRawScoreOther()));
    }

    public void calculateTotals() {
        this.setScoreAutoSales(this.calculateIndustryTotal(this.getRawScoreAutoSales()));
        this.setScoreSeniorLiving(this.calculateIndustryTotal(this.getRawScoreSeniorLiving()));
        this.setScoreEducation(this.calculateIndustryTotal(this.getRawScoreEducation()));
        this.setScoreFinancialServices(this.calculateIndustryTotal(this.getRawScoreFinancialServices()));
        this.setScoreHomeServices(this.calculateIndustryTotal(this.getRawScoreHomeServices()));
        this.setScoreInsurance(this.calculateIndustryTotal(this.getRawScoreInsurance()));
        this.setScoreJobs(this.calculateIndustryTotal(this.getRawScoreJobs()));
        this.setScoreLegal(this.calculateIndustryTotal(this.getRawScoreLegal()));
        this.setScoreRealEstate(this.calculateIndustryTotal(this.getRawScoreRealEstate()));
        this.setScoreOther(this.calculateIndustryTotal(this.getRawScoreOther()));
    }

}
