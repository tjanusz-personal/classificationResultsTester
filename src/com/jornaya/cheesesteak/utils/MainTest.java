package com.jornaya.cheesesteak.utils;

import org.junit.Test;

import java.io.Reader;
import java.io.StringReader;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MainTest {

    @Test
    public void findIndustryTotalErrorsReturnsLeadIdsWithIncorrectTotals() throws Exception {
        StringBuffer buffer = new StringBuffer();
        String header = "campaign_industry,classification_method,lead_id,record_id,econtext_response_status,industry,other,auto_sales,education," +
                "insurance,legal,financial_services,real_estate,home_services,jobs,senior_living\r";
        buffer.append(header);
        buffer.append("Education,URL,00000000-0000-0000-0000-000000000000,1549,200 OK - successful,Education,,,0.03,,,,0.04");
        Reader reader = new StringReader(buffer.toString());
        List<String> errors = Main.findIndustryTotalErrors(reader);
        assertEquals(1, errors.size());
        assertEquals("00000000-0000-0000-0000-000000000000", errors.get(0));
    }

    @Test
    public void findIndustryTotalErrorsReturnsEmptyListWithAllCorrectTotals() throws Exception {
        StringBuffer buffer = new StringBuffer();
        String header = "campaign_industry,classification_method,lead_id,record_id,econtext_response_status,industry,other,auto_sales,education," +
                "insurance,legal,financial_services,real_estate,home_services,jobs,senior_living\r";
        buffer.append(header);
        buffer.append("Education,URL,00000000-0000-0000-0000-000000000000,1549,200 OK - successful,Education,,,0.03,,,,0.02\r");
        buffer.append("Education,URL,00000000-0000-0000-0000-000000000001,1549,200 OK - successful,Education,,,0.03,,,,0.02\r");
        buffer.append("Education,URL,00000000-0000-0000-0000-000000000002,1549,200 OK - successful,Education,,,0.03,,,,0.02\r");
        Reader reader = new StringReader(buffer.toString());
        List<String> errors = Main.findIndustryTotalErrors(reader);
        assertEquals(0, errors.size());
    }

    @Test
    public void findIndustryCountErrorsReturnsLeadIdsWithIncorrectCounts() throws Exception {
        StringBuffer buffer = new StringBuffer();
        String header = "campaign_industry,classification_method,lead_id,record_id,econtext_response_status,industry,other,auto_sales,education," +
                "insurance,legal,financial_services,real_estate,home_services,jobs,senior_living\r";
        buffer.append(header);
        buffer.append("Education,URL,00000000-0000-0000-0000-000000000000,1549,200 OK - successful,Education,,,0.03,,,,0.04\r");
        buffer.append("Education,URL,00000000-0000-0000-0000-000000000000,1549,200 OK - successful,Education,,,,,,,0.04");
        Reader reader = new StringReader(buffer.toString());
        List<String> errors = Main.findIndustryCountErrors(reader);
        assertEquals(1, errors.size());
        assertEquals("00000000-0000-0000-0000-000000000000", errors.get(0));
    }

    @Test
    public void findIndustryCountErrorsReturnsEmptyListWithAllCorrectCounts() throws Exception {
        StringBuffer buffer = new StringBuffer();
        String header = "campaign_industry,classification_method,lead_id,record_id,econtext_response_status,industry,other,auto_sales,education," +
                "insurance,legal,financial_services,real_estate,home_services,jobs,senior_living\r";
        buffer.append(header);
        buffer.append("Education,URL,00000000-0000-0000-0000-000000000000,1549,200 OK - successful,Education,,,0.04,,,,0.01\r");
        buffer.append("Education,URL,00000000-0000-0000-0000-000000000001,1549,200 OK - successful,Education,,,0.04,,,,0.01\r");
        buffer.append("Education,URL,00000000-0000-0000-0000-000000000002,1549,200 OK - successful,Education,,,0.04,,,,0.01\r");
        Reader reader = new StringReader(buffer.toString());
        List<String> errors = Main.findIndustryCountErrors(reader);
        assertEquals(0, errors.size());
    }

    @Test
    public void findNonMatchingIndustryErrorsReturnsLeadIdsWithMultipleIndustries() throws Exception {
        StringBuffer buffer = new StringBuffer();
        String header = "campaign_industry,classification_method,lead_id,record_id,econtext_response_status,industry\r";
        buffer.append(header);
        buffer.append("Education,URL,00000000-0000-0000-0000-000000000000,1549,200 OK - successful,Education\r");
        buffer.append("Education,URL,00000000-0000-0000-0000-000000000000,1549,200 OK - successful,Jobs\r");
        Reader reader = new StringReader(buffer.toString());
        List<String> errors = Main.findNonMatchingIndustries(reader);
        assertEquals(1, errors.size());
        assertEquals("00000000-0000-0000-0000-000000000000", errors.get(0));
    }

    @Test
    public void findNonMatchingIndustryErrorsReturnsEmptyListWithNoMultipleIndustriesFound() throws Exception {
        StringBuffer buffer = new StringBuffer();
        String header = "campaign_industry,classification_method,lead_id,record_id,econtext_response_status,industry\r";
        buffer.append(header);
        buffer.append("Education,URL,00000000-0000-0000-0000-000000000000,1549,200 OK - successful,Education\r");
        buffer.append("Education,URL,00000000-0000-0000-0000-000000000000,1549,200 OK - successful,Education\r");
        buffer.append("Education,URL,00000000-0000-0000-0000-000000000001,1549,200 OK - successful,Jobs\r");
        buffer.append("Education,URL,00000000-0000-0000-0000-000000000002,1549,200 OK - successful,Auto Sales\r");
        Reader reader = new StringReader(buffer.toString());
        List<String> errors = Main.findNonMatchingIndustries(reader);
        assertEquals(0, errors.size());
    }


}