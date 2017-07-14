package com.jornaya.cheesesteak.utils;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ClassificationRecordTest {

    private ClassificationRecord record;

    @Before
    public void setUp() {
        record = new ClassificationRecord();
    }

    @Test
    public void getScoreForIndustryReturnsCorrectScores() {
        record.setScoreOther(.1);
        record.setScoreRealEstate(.2);
        record.setScoreLegal(.3);
        record.setScoreJobs(.4);
        record.setScoreInsurance(.5);
        record.setScoreHomeServices(.6);
        record.setScoreFinancialServices(.7);
        record.setScoreEducation(.8);
        record.setScoreSeniorLiving(.9);
        record.setScoreAutoSales(.11);
        assertEquals(.1, record.getScoreForIndustry("Other"), .1);
        assertEquals(.2, record.getScoreForIndustry("Real Estate"), .1);
        assertEquals(.3, record.getScoreForIndustry("Legal"), .1);
        assertEquals(.4, record.getScoreForIndustry("Jobs"), .1);
        assertEquals(.5, record.getScoreForIndustry("Insurance"), .1);
        assertEquals(.6, record.getScoreForIndustry("Home Services"), .1);
        assertEquals(.7, record.getScoreForIndustry("Financial Services"), .1);
        assertEquals(.8, record.getScoreForIndustry("Education"), .1);
        assertEquals(.9, record.getScoreForIndustry("Senior Living"), .1);
        assertEquals(.11, record.getScoreForIndustry("Auto Sales"), .1);
    }

    @Test(expected = RuntimeException.class)
    public void getScoreForIndustryThrowsErrorOnMissingIndustry() {
        record.getScoreForIndustry("invalid");
    }

    @Test(expected = RuntimeException.class)
    public void getScoreForIndustryThrowsErrorOnInvalidCaseInIndustryDefinition() {
        record.getScoreForIndustry("JOBS");
    }

    @Test(expected = RuntimeException.class)
    public void getScoreForIndustryThrowsErrorNullIndustry() {
        record.getScoreForIndustry(null);
    }

    @Test
    public void getScoreForIndustryReturnsZeroIfNoScoreForValue() {
        assertEquals(0, record.getScoreForIndustry("Other"), .1);
        assertEquals(0, record.getScoreForIndustry("Real Estate"), .1);
        assertEquals(0, record.getScoreForIndustry("Legal"), .1);
        assertEquals(0, record.getScoreForIndustry("Jobs"), .1);
        assertEquals(0, record.getScoreForIndustry("Insurance"), .1);
        assertEquals(0, record.getScoreForIndustry("Home Services"), .1);
        assertEquals(0, record.getScoreForIndustry("Financial Services"), .1);
        assertEquals(0, record.getScoreForIndustry("Education"), .1);
        assertEquals(0, record.getScoreForIndustry("Senior Living"), .1);
        assertEquals(0, record.getScoreForIndustry("Auto Sales"), .1);
    }

    @Test
    public void getMaxScoreAcrossAllIndustriesReturnsMaxScoreWhenAllPopulated() {
        record.setScoreOther(.1);
        record.setScoreRealEstate(.2);
        record.setScoreLegal(.3);
        record.setScoreJobs(.4);
        record.setScoreInsurance(.5);
        record.setScoreHomeServices(.6);
        record.setScoreFinancialServices(.7);
        record.setScoreEducation(.8);
        record.setScoreSeniorLiving(.9);
        record.setScoreAutoSales(.11);
        double maxScore = record.getMaxScoreAcrossAllIndustries();
        assertEquals(.9, maxScore, .01);
    }

    @Test
    public void getMaxScoreAcrossAllIndustriesReturnsMaxScoreWhenSomePopulated() {
        record.setScoreOther(.1);
        record.setScoreHomeServices(.6);
        record.setScoreFinancialServices(.7);
        record.setScoreAutoSales(.11);
        double maxScore = record.getMaxScoreAcrossAllIndustries();
        assertEquals(.7, maxScore, .01);
    }

    @Test
    public void getMaxScoreAcrossAllIndustriesReturnsMaxScoreWithSameValues() {
        record.setScoreOther(.1);
        record.setScoreHomeServices(.6);
        record.setScoreFinancialServices(.6);
        record.setScoreAutoSales(.1);
        double maxScore = record.getMaxScoreAcrossAllIndustries();
        assertEquals(.6, maxScore, .01);
    }

    @Test
    public void getMaxScoreAcrossAllIndustriesReturnsZeroWhenNoScores() {
        double maxScore = record.getMaxScoreAcrossAllIndustries();
        assertEquals(0, maxScore, .01);
    }

    @Test
    public void calculateIndustryCountReturnsZeroWithEmptyString() {
        assertEquals(0, record.calculateIndustryCount(""));
        assertEquals(0, record.calculateIndustryCount(null));
    }

    @Test
    public void calculateIndustryCountReturnsValueWithSingleString() {
        assertEquals(1, record.calculateIndustryCount("0.32"));
    }

    @Test
    public void calculateIndustryCountReturnsValueWithDelimitedSeparatedStrings() {
        assertEquals(3, record.calculateIndustryCount("0.32;0.21;0.22"));
        assertEquals(2, record.calculateIndustryCount("0.32;0.21"));
    }

    @Test
    public void calculateIndustryTotalReturnsZeroWithEmptyString() {
        assertEquals(0, record.calculateIndustryTotal(""), 0.01);
        assertEquals(0, record.calculateIndustryTotal(null), 0.01);
    }

    @Test
    public void calculateIndustryTotalReturnsValueWithSingleString() {
        assertEquals(0.32, record.calculateIndustryTotal("0.32"), 0.01);
    }

    @Test
    public void calculateIndustryTotalReturnsValueWithDelimitedString() {
        assertEquals(0.74, record.calculateIndustryTotal("0.32;0.21;0.21"), 0.01);
        assertEquals(0.42, record.calculateIndustryTotal("0.21;0.21"), 0.01);
    }

}