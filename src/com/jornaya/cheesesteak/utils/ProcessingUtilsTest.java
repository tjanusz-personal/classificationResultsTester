package com.jornaya.cheesesteak.utils;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ProcessingUtilsTest {

    @Test
    public void mapCSVToIndustryCountRecordReturnsCorrectPopulatedObject() throws Exception {
        String lineAsString = "campaign_industry,classification_method,lead_id,record_id,econtext_response_status,industry";
        ClassificationRecord classificationRecord = ProcessingUtils.mapCSVToIndustryCountRecord.apply(lineAsString);
        assertEquals("campaign_industry", classificationRecord.getCampaignClassification());
        assertEquals("classification_method", classificationRecord.getClassificationMethod());
        assertEquals("lead_id", classificationRecord.getLeadId());
        assertEquals("record_id", classificationRecord.getRecordId());
        assertEquals("industry", classificationRecord.getIndustry());
    }

    @Test
    public void mapCSVToIndustryTotalCountRecordReturnsCorrectPopulatedObject() throws Exception {
        // campaign_industry	classification_method	lead_id	record_id	econtext_response_status	industry
        // other	auto_sales	education	insurance	legal	financial_services	real_estate	home_services
        // jobs	senior_living
        String lineAsString = "campaign_industry,classification_method,lead_id,record_id,econtext_response_status," +
                "industry,0,.1,.2,.3,.4,.5,.6,.7,.8,.9";
        ClassificationRecord classificationRecord = ProcessingUtils.mapCSVToIndustryTotalCountRecord.apply(lineAsString);
        assertEquals("campaign_industry", classificationRecord.getCampaignClassification());
        assertEquals("classification_method", classificationRecord.getClassificationMethod());
        assertEquals("lead_id", classificationRecord.getLeadId());
        assertEquals("record_id", classificationRecord.getRecordId());
        assertEquals("industry", classificationRecord.getIndustry());
        assertEquals(0, classificationRecord.getScoreOtherCount(), 0.1);
        assertEquals(1, classificationRecord.getScoreAutoSalesCount(),0.1);
        assertEquals(1,classificationRecord.getScoreEducationCount(), .1);
        assertEquals(1, classificationRecord.getScoreInsuranceCount(), .1);
        assertEquals(1, classificationRecord.getScoreLegalCount(), .1);
        assertEquals(1, classificationRecord.getScoreFinancialServicesCount(), .1);
        assertEquals(1, classificationRecord.getScoreRealEstateCount(), .1);
        assertEquals(1, classificationRecord.getScoreHomeServicesCount(), .1);
        assertEquals(1, classificationRecord.getScoreJobsCount(), .1);
        assertEquals(1, classificationRecord.getScoreSeniorLivingCount(), .1);
    }

    @Test
    public void mapCSVToIndustryTotalCountRecordReturnsCorrectPopulatedObjectWithMissingIndustries() throws Exception {
        // campaign_industry	classification_method	lead_id	record_id	econtext_response_status	industry
        // other	auto_sales	education	insurance	legal	financial_services	real_estate	home_services
        // jobs	senior_living
        String lineAsString = "campaign_industry,classification_method,lead_id,record_id,econtext_response_status," +
                "industry,0,.1";
        ClassificationRecord classificationRecord = ProcessingUtils.mapCSVToIndustryTotalCountRecord.apply(lineAsString);
        assertEquals("campaign_industry", classificationRecord.getCampaignClassification());
        assertEquals("classification_method", classificationRecord.getClassificationMethod());
        assertEquals("lead_id", classificationRecord.getLeadId());
        assertEquals("record_id", classificationRecord.getRecordId());
        assertEquals("industry", classificationRecord.getIndustry());
        assertEquals(0, classificationRecord.getScoreOtherCount(), 0.1);
        assertEquals(1, classificationRecord.getScoreAutoSalesCount(),0.1);
        assertEquals(0,classificationRecord.getScoreEducationCount(), .1);
        assertEquals(0, classificationRecord.getScoreInsuranceCount(), .1);
        assertEquals(0, classificationRecord.getScoreLegalCount(), .1);
        assertEquals(0, classificationRecord.getScoreFinancialServicesCount(), .1);
        assertEquals(0, classificationRecord.getScoreRealEstateCount(), .1);
        assertEquals(0, classificationRecord.getScoreHomeServicesCount(), .1);
        assertEquals(0, classificationRecord.getScoreJobsCount(), .1);
        assertEquals(0, classificationRecord.getScoreSeniorLivingCount(), .1);
    }

    @Test
    public void findLeadsWithMismatchedIndustriesFindsLeadWithMismatchedIndustry() throws Exception {
        List<ClassificationRecord> classifications = new ArrayList<ClassificationRecord>() {
            {
                add(dummyClassification("LeadId1", "Jobs"));
                add(dummyClassification("LeadId1", "Jobs"));
                add(dummyClassification("LeadId3", "Jobs"));
                add(dummyClassification("LeadId3", "Jobs"));
                add(dummyClassification("LeadId3", "Auto Sales"));
            }
        };
        List<String> mismatchedIndustries = ProcessingUtils.findLeadsWithMismatchedIndustries(classifications);
        assertEquals(1, mismatchedIndustries.size());
        assertEquals("LeadId3", mismatchedIndustries.get(0));
    }

    @Test
    public void findLeadsWithMismatchedIndustriesFindsMultipleLeadsWithMismatchedIndustry() throws Exception {
        List<ClassificationRecord> classifications = new ArrayList<ClassificationRecord>() {
            {
                add(dummyClassification("LeadId1", "Jobs"));
                add(dummyClassification("LeadId1", "Jobs"));
                add(dummyClassification("LeadId3", "Jobs"));
                add(dummyClassification("LeadId3", "Auto Sales"));
                add(dummyClassification("LeadId4", "Education"));
                add(dummyClassification("LeadId4", "Legal"));
            }
        };
        List<String> mismatchedIndustries = ProcessingUtils.findLeadsWithMismatchedIndustries(classifications);
        String[] expectedLeads = { "LeadId3", "LeadId4"};
        assertEquals(2, mismatchedIndustries.size());
        Assert.assertArrayEquals(expectedLeads, mismatchedIndustries.toArray());
    }

    @Test
    public void findLeadsWithMismatchedIndustriesReturnsEmptyListWithNoMismatchedIndustries() throws Exception {
        List<ClassificationRecord> classifications = new ArrayList<ClassificationRecord>() {
            {
                add(dummyClassification("LeadId1", "Jobs"));
                add(dummyClassification("LeadId1", "Jobs"));
                add(dummyClassification("LeadId3", "Jobs"));
                add(dummyClassification("LeadId4", "Legal"));
            }
        };
        List<String> mismatchedIndustries = ProcessingUtils.findLeadsWithMismatchedIndustries(classifications);
        assertEquals(0, mismatchedIndustries.size());
    }

    private ClassificationRecord dummyClassification(String leadId, String industry) {
        ClassificationRecord record = new ClassificationRecord();
        record.setIndustry(industry);
        record.setLeadId(leadId);
        return record;
    }

    @Test
    public void findLeadsWithIncorrectIndustryCountsReturnsListWithMismatchedCounts() throws Exception {
        List<ClassificationRecord> classifications = new ArrayList<ClassificationRecord>() {
            {
                // Jobs is correct
                add(dummyClassification("LeadId1", "Jobs", ".3", "0"));
                add(dummyClassification("LeadId1", "Jobs", ".3", "0"));

                // Jobs is incorrect
                add(dummyClassification("LeadId3", "Jobs", ".2", ".1"));
                add(dummyClassification("LeadId3", "Jobs", ".2", ".1;.1"));

                // Legal is incorrect
                add(dummyClassification("LeadId4", "Legal", ".3",".1"));
                add(dummyClassification("LeadId4", "Legal", ".3;.3", "1"));
            }
        };

        List<String> mismatchedIndustries = ProcessingUtils.findLeadsWithIncorrectIndustryCounts(classifications);
        String[] expectedLeads = { "LeadId3", "LeadId4"};
        assertEquals(2, mismatchedIndustries.size());
        Assert.assertArrayEquals(expectedLeads, mismatchedIndustries.toArray());
    }

    @Test
    public void findLeadsWithIncorrectIndustryCountsReturnsEmptyListWithNothingFound() throws Exception {
        List<ClassificationRecord> classifications = new ArrayList<ClassificationRecord>() {
            {
                // Jobs is correct
                add(dummyClassification("LeadId1", "Jobs", ".3", ""));
                add(dummyClassification("LeadId1", "Jobs", ".3", ""));

                // Jobs is correct
                add(dummyClassification("LeadId3", "Jobs", ".2;.3", ".1"));
                add(dummyClassification("LeadId3", "Jobs", ".2;.3", ".1"));

                // Legal is correct
                add(dummyClassification("LeadId4", "Legal", "",".1"));
                add(dummyClassification("LeadId4", "Legal", ".3;.2", ".2;.3"));
            }
        };
        List<String> mismatchedIndustries = ProcessingUtils.findLeadsWithIncorrectIndustryCounts(classifications);
        assertEquals(0, mismatchedIndustries.size());
    }

    private ClassificationRecord dummyClassification(String leadId, String industry, String scoreJobs, String scoreLegal) {
        ClassificationRecord record = new ClassificationRecord();
        record.setIndustry(industry);
        record.setLeadId(leadId);
        record.setRawScoreJobs(scoreJobs);
        record.setScoreJobsCount(record.calculateIndustryCount(scoreJobs));
        record.setScoreJobs(record.calculateIndustryTotal(scoreJobs));
        record.setRawScoreLegal(scoreLegal);
        record.setScoreLegalCount(record.calculateIndustryCount(scoreLegal));
        record.setScoreLegal(record.calculateIndustryTotal(scoreLegal));
        return record;
    }

    @Test
    public void findLeadsWithIncorrectIndustryTotalsReturnsListWithMismatchedTotals() throws Exception {
        List<ClassificationRecord> classifications = new ArrayList<ClassificationRecord>() {
            {
                // Jobs is correct
                add(dummyClassification("LeadId1", "Jobs", ".3", ".1"));
                add(dummyClassification("LeadId1", "Jobs", ".3", ".1"));

                // Jobs is incorrect
                add(dummyClassification("LeadId3", "Jobs", ".2", ".2;.1"));
                add(dummyClassification("LeadId3", "Jobs", ".1", ".1;.1"));

                // Legal is incorrect
                add(dummyClassification("LeadId4", "Legal", ".1;.1;.1",".1"));
                add(dummyClassification("LeadId4", "Legal", ".3", ".4"));
            }
        };
        List<String> mismatchedIndustries = ProcessingUtils.findLeadsWithIncorrectIndustryTotals(classifications);
        String[] expectedLeads = { "LeadId3", "LeadId4"};
        assertEquals(2, mismatchedIndustries.size());
        Assert.assertArrayEquals(expectedLeads, mismatchedIndustries.toArray());
    }

    @Test
    public void findLeadsWithIncorrectIndustryTotalsReturnsEmptyListWithNoMismatchedTotals() throws Exception {
        List<ClassificationRecord> classifications = new ArrayList<ClassificationRecord>() {
            {
                // Jobs is correct
                add(dummyClassification("LeadId1", "Jobs", ".3;.1", ".1;.1"));
                add(dummyClassification("LeadId1", "Jobs", ".3", ".1"));

                // Jobs is correct
                add(dummyClassification("LeadId3", "Jobs", ".2", ".2"));
                add(dummyClassification("LeadId3", "Jobs", ".3;.1", ".2;.1"));

                // Legal is correct
                add(dummyClassification("LeadId4", "Legal", ".3;.1",".1;.1"));
                add(dummyClassification("LeadId4", "Legal", ".3", ".7"));
            }
        };
        List<String> mismatchedIndustries = ProcessingUtils.findLeadsWithIncorrectIndustryTotals(classifications);
        assertEquals(0, mismatchedIndustries.size());
    }


}