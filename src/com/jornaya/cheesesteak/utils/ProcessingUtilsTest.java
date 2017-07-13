package com.jornaya.cheesesteak.utils;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ProcessingUtilsTest {

    @Test
    public void getColumnDoubleValueReturns0ForPositionLongerThanArray() throws Exception {
        String[] lineAsArray = { "","","0.33", "", "0.22"};
        Assert.assertEquals(0.0, ProcessingUtils.getColumnDoubleValue(lineAsArray,8),.01);
    }

    @Test
    public void getColumnCount1ValueReturns1ForColumnHavingValue() throws Exception {
        String[] lineAsArray = { "","","0.33", "", "0.22"};
        Assert.assertEquals(1, ProcessingUtils.getColumnCountValue(lineAsArray,2));
    }

    @Test
    public void getColumnCountValueReturns0ForColumnMissingValue() throws Exception {
        String[] lineAsArray = { "","","0.33", "", "0.22"};
        Assert.assertEquals(0, ProcessingUtils.getColumnCountValue(lineAsArray,1));
    }

    @Test
    public void getColumnCountValueReturns0ForPositionLongerThanArray() throws Exception {
        String[] lineAsArray = { "","","0.33", "", "0.22"};
        Assert.assertEquals(0, ProcessingUtils.getColumnCountValue(lineAsArray,8));
    }

    @Test
    public void getColumnDoubleValueReturnsValueForColumnHavingValue() throws Exception {
        String[] lineAsArray = { "","","0.33", "", "0.22"};
        Assert.assertEquals(0.33, ProcessingUtils.getColumnDoubleValue(lineAsArray,2),.01);
    }

    @Test
    public void getColumnDoubleValueReturns0ForColumnMissingValue() throws Exception {
        String[] lineAsArray = { "","","0.33", "", "0.22"};
        Assert.assertEquals(0.0, ProcessingUtils.getColumnDoubleValue(lineAsArray,1),.01);
    }

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
        assertEquals(0, classificationRecord.getScoreOther(), 0.1);
        assertEquals(1, classificationRecord.getScoreAutoSales(),0.1);
        assertEquals(1,classificationRecord.getScoreEducation(), .1);
        assertEquals(1, classificationRecord.getScoreInsurance(), .1);
        assertEquals(1, classificationRecord.getScoreLegal(), .1);
        assertEquals(1, classificationRecord.getScoreFinancialServices(), .1);
        assertEquals(1, classificationRecord.getScoreRealEstate(), .1);
        assertEquals(1, classificationRecord.getScoreHomeServices(), .1);
        assertEquals(1, classificationRecord.getScoreJobs(), .1);
        assertEquals(1, classificationRecord.getScoreSeniorLiving(), .1);
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
        assertEquals(0, classificationRecord.getScoreOther(), 0.1);
        assertEquals(1, classificationRecord.getScoreAutoSales(),0.1);
        assertEquals(0,classificationRecord.getScoreEducation(), .1);
        assertEquals(0, classificationRecord.getScoreInsurance(), .1);
        assertEquals(0, classificationRecord.getScoreLegal(), .1);
        assertEquals(0, classificationRecord.getScoreFinancialServices(), .1);
        assertEquals(0, classificationRecord.getScoreRealEstate(), .1);
        assertEquals(0, classificationRecord.getScoreHomeServices(), .1);
        assertEquals(0, classificationRecord.getScoreJobs(), .1);
        assertEquals(0, classificationRecord.getScoreSeniorLiving(), .1);
    }

    @Test
    public void mapCSVToIndustryTotalRecordReturnsCorrectPopulatedObject() throws Exception {
        // campaign_industry	classification_method	lead_id	record_id	econtext_response_status	industry
        // other	auto_sales	education	insurance	legal	financial_services	real_estate	home_services
        // jobs	senior_living
        String lineAsString = "campaign_industry,classification_method,lead_id,record_id,econtext_response_status," +
                "industry,0,.1,.2,.3,.4,.5,.6,.7,.8,.9";
        ClassificationRecord classificationRecord = ProcessingUtils.mapCSVToIndustryTotalRecord.apply(lineAsString);
        assertEquals("campaign_industry", classificationRecord.getCampaignClassification());
        assertEquals("classification_method", classificationRecord.getClassificationMethod());
        assertEquals("lead_id", classificationRecord.getLeadId());
        assertEquals("record_id", classificationRecord.getRecordId());
        assertEquals("industry", classificationRecord.getIndustry());
        assertEquals(0, classificationRecord.getScoreOther(), 0.1);
        assertEquals(.1, classificationRecord.getScoreAutoSales(),0.1);
        assertEquals(.2,classificationRecord.getScoreEducation(), .1);
        assertEquals(.3, classificationRecord.getScoreInsurance(), .1);
        assertEquals(.4, classificationRecord.getScoreLegal(), .1);
        assertEquals(.5, classificationRecord.getScoreFinancialServices(), .1);
        assertEquals(.6, classificationRecord.getScoreRealEstate(), .1);
        assertEquals(.7, classificationRecord.getScoreHomeServices(), .1);
        assertEquals(.8, classificationRecord.getScoreJobs(), .1);
        assertEquals(.9, classificationRecord.getScoreSeniorLiving(), .1);
    }

    @Test
    public void mapCSVToIndustryTotalRecordReturnsCorrectPopulatedObjectWithMissingIndustries() throws Exception {
        // campaign_industry	classification_method	lead_id	record_id	econtext_response_status	industry
        // other	auto_sales	education	insurance	legal	financial_services	real_estate	home_services
        // jobs	senior_living
        String lineAsString = "campaign_industry,classification_method,lead_id,record_id,econtext_response_status," +
                "industry,0,.1";
        ClassificationRecord classificationRecord = ProcessingUtils.mapCSVToIndustryTotalRecord.apply(lineAsString);
        assertEquals("campaign_industry", classificationRecord.getCampaignClassification());
        assertEquals("classification_method", classificationRecord.getClassificationMethod());
        assertEquals("lead_id", classificationRecord.getLeadId());
        assertEquals("record_id", classificationRecord.getRecordId());
        assertEquals("industry", classificationRecord.getIndustry());
        assertEquals(0, classificationRecord.getScoreOther(), 0.1);
        assertEquals(.1, classificationRecord.getScoreAutoSales(),0.1);
        assertEquals(0,classificationRecord.getScoreEducation(), .1);
        assertEquals(0, classificationRecord.getScoreInsurance(), .1);
        assertEquals(0, classificationRecord.getScoreLegal(), .1);
        assertEquals(0, classificationRecord.getScoreFinancialServices(), .1);
        assertEquals(0, classificationRecord.getScoreRealEstate(), .1);
        assertEquals(0, classificationRecord.getScoreHomeServices(), .1);
        assertEquals(0, classificationRecord.getScoreJobs(), .1);
        assertEquals(0, classificationRecord.getScoreSeniorLiving(), .1);
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
                add(dummyClassification("LeadId1", "Jobs", .3, 0));
                add(dummyClassification("LeadId1", "Jobs", .3, 0));

                // Jobs is incorrect
                add(dummyClassification("LeadId3", "Jobs", .2, .1));
                add(dummyClassification("LeadId3", "Jobs", 0, .1));

                // Legal is incorrect
                add(dummyClassification("LeadId4", "Legal", .3,.1));
                add(dummyClassification("LeadId4", "Legal", .3, 0));
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
                add(dummyClassification("LeadId1", "Jobs", .3, 0));
                add(dummyClassification("LeadId1", "Jobs", .3, 0));

                // Jobs is correct
                add(dummyClassification("LeadId3", "Jobs", .2, .1));
                add(dummyClassification("LeadId3", "Jobs", .2, .1));

                // Legal is correct
                add(dummyClassification("LeadId4", "Legal", 0,.1));
                add(dummyClassification("LeadId4", "Legal", .3, .2));
            }
        };
        List<String> mismatchedIndustries = ProcessingUtils.findLeadsWithIncorrectIndustryCounts(classifications);
        assertEquals(0, mismatchedIndustries.size());
    }

    private ClassificationRecord dummyClassification(String leadId, String industry, double scoreJobs, double scoreLegal) {
        ClassificationRecord record = new ClassificationRecord();
        record.setIndustry(industry);
        record.setLeadId(leadId);
        record.setScoreJobs(scoreJobs);
        record.setScoreLegal(scoreLegal);
        return record;
    }

    @Test
    public void findLeadsWithIncorrectIndustryTotalsReturnsListWithMismatchedTotals() throws Exception {
        List<ClassificationRecord> classifications = new ArrayList<ClassificationRecord>() {
            {
                // Jobs is correct
                add(dummyClassification("LeadId1", "Jobs", .3, .1));
                add(dummyClassification("LeadId1", "Jobs", .3, .1));

                // Jobs is incorrect
                add(dummyClassification("LeadId3", "Jobs", .2, .2));
                add(dummyClassification("LeadId3", "Jobs", .1, .2));

                // Legal is incorrect
                add(dummyClassification("LeadId4", "Legal", .3,.1));
                add(dummyClassification("LeadId4", "Legal", .3, 0));
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
                add(dummyClassification("LeadId1", "Jobs", .3, .1));
                add(dummyClassification("LeadId1", "Jobs", .3, .1));

                // Jobs is correct
                add(dummyClassification("LeadId3", "Jobs", .2, .2));
                add(dummyClassification("LeadId3", "Jobs", .3, .2));

                // Legal is correct
                add(dummyClassification("LeadId4", "Legal", .3,.1));
                add(dummyClassification("LeadId4", "Legal", .3, .7));
            }
        };
        List<String> mismatchedIndustries = ProcessingUtils.findLeadsWithIncorrectIndustryTotals(classifications);
        assertEquals(0, mismatchedIndustries.size());
    }


}