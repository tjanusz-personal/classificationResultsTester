package com.jornaya.cheesesteak.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ProcessingUtils {

    private static String getColumnStringValue(String[] lineAsArray, int position) {
        if (lineAsArray.length <= position) {
            return "";
        }
        String rawValues = lineAsArray[position];
        if (rawValues.isEmpty()) {
            return "";
        }
        return lineAsArray[position];
    }

    // campaign_industry, classification_method, lead_id, record_id, econtext_response_status, industry
    public static Function<String, ClassificationRecord> mapCSVToIndustryCountRecord = (line) -> {
        String[] lineArray = line.split(",");// a CSV has comma separated lines
        ClassificationRecord item = new ClassificationRecord();
        item.setCampaignClassification(lineArray[0]);
        item.setClassificationMethod(lineArray[1]);
        item.setLeadId(lineArray[2]);
        item.setRecordId(lineArray[3]);
        item.setIndustry(lineArray[5]);
        return item;
    };

    // campaign_industry, classification_method, lead_id, record_id, econtext_response_status, industry
    // other, auto_sales, education, insurance, legal, financial_services, real_estate, home_services
    // jobs, senior_living
    public static Function<String, ClassificationRecord> mapCSVToIndustryTotalCountRecord = (line) -> {
        String[] lineArray = line.split(",");// a CSV has comma separated lines
        ClassificationRecord item = new ClassificationRecord();
        item.setCampaignClassification(lineArray[0]);
        item.setClassificationMethod(lineArray[1]);
        item.setLeadId(lineArray[2]);
        item.setRecordId(lineArray[3]);
        item.setIndustry(lineArray[5]);

        item.setRawScoreOther(ProcessingUtils.getColumnStringValue(lineArray, 6));
        item.setRawScoreAutoSales(ProcessingUtils.getColumnStringValue(lineArray, 7));
        item.setRawScoreEducation(ProcessingUtils.getColumnStringValue(lineArray, 8));
        item.setRawScoreInsurance(ProcessingUtils.getColumnStringValue(lineArray, 9));
        item.setRawScoreLegal(ProcessingUtils.getColumnStringValue(lineArray, 10));
        item.setRawScoreFinancialServices(ProcessingUtils.getColumnStringValue(lineArray, 11));
        item.setRawScoreRealEstate(ProcessingUtils.getColumnStringValue(lineArray, 12));
        item.setRawScoreHomeServices(ProcessingUtils.getColumnStringValue(lineArray, 13));
        item.setRawScoreJobs(ProcessingUtils.getColumnStringValue(lineArray, 14));
        item.setRawScoreSeniorLiving(ProcessingUtils.getColumnStringValue(lineArray, 15));

        // calculate the counts and total values from the raw strings
        item.calculateCounts();
        item.calculateTotals();
        return item;
    };

    public static List<String> findLeadsWithMismatchedIndustries(List<ClassificationRecord> classificationRecords) {
        Map<String, Map<String, List<ClassificationRecord>>> leadIdsMap = classificationRecords.stream().collect
                (Collectors.groupingBy(ClassificationRecord::getLeadId,
                        Collectors.groupingBy(ClassificationRecord::getIndustry)));

        // Filter out only ones with multiple industries
        List<String> leadIdsWithDups = leadIdsMap.entrySet().stream()
                .filter(theMap -> theMap.getValue().keySet().size() > 1)
                .map(theMap -> theMap.getKey())
                .collect(Collectors.toList());
        return leadIdsWithDups;
    }

    // static reducer function takes two classification objects and will combine their totals/counts
    private static BinaryOperator<ClassificationRecord> reduceCountAndTotals = (classification1, classification2) -> {
        ClassificationRecord res = new ClassificationRecord();
        res.setScoreOtherCount(classification1.getScoreOtherCount() + classification2.getScoreOtherCount());
        res.setScoreOther(classification1.getScoreOther() + classification2.getScoreOther());
        res.setScoreRealEstateCount(classification1.getScoreRealEstateCount() + classification2.getScoreRealEstateCount());
        res.setScoreRealEstate(classification1.getScoreRealEstate() + classification2.getScoreRealEstate());
        res.setScoreLegalCount(classification1.getScoreLegalCount() + classification2.getScoreLegalCount());
        res.setScoreLegal(classification1.getScoreLegal() + classification2.getScoreLegal());
        res.setScoreJobsCount(classification1.getScoreJobsCount() + classification2.getScoreJobsCount());
        res.setScoreJobs(classification1.getScoreJobs() + classification2.getScoreJobs());
        res.setScoreInsuranceCount(classification1.getScoreInsuranceCount() + classification2.getScoreInsuranceCount());
        res.setScoreInsurance(classification1.getScoreInsurance() + classification2.getScoreInsurance());
        res.setScoreHomeServicesCount(classification1.getScoreHomeServicesCount() +
                classification2.getScoreHomeServicesCount());
        res.setScoreHomeServices(classification1.getScoreHomeServices() +
                classification2.getScoreHomeServices());
        res.setScoreFinancialServicesCount(classification1.getScoreFinancialServicesCount() +
                classification2.getScoreFinancialServicesCount());
        res.setScoreFinancialServices(classification1.getScoreFinancialServices() +
                classification2.getScoreFinancialServices());
        res.setScoreEducationCount(classification1.getScoreEducationCount() + classification2.getScoreEducationCount());
        res.setScoreEducation(classification1.getScoreEducation() + classification2.getScoreEducation());
        res.setScoreSeniorLivingCount(classification1.getScoreSeniorLivingCount() +
                classification2.getScoreSeniorLivingCount());
        res.setScoreSeniorLiving(classification1.getScoreSeniorLiving() + classification2.getScoreSeniorLiving());
        res.setScoreAutoSalesCount(classification1.getScoreAutoSalesCount() + classification2.getScoreAutoSalesCount());
        res.setScoreAutoSales(classification1.getScoreAutoSales() + classification2.getScoreAutoSales());
        return res;
    };

    public static List<String> findLeadsWithIncorrectIndustryCounts(List<ClassificationRecord> classificationRecords) {
        Map<String, List<ClassificationRecord>> classificationsByLeadId = classificationRecords.stream().collect
                (Collectors.groupingBy(ClassificationRecord::getLeadId));
        List<String> leadsWithInvalidCounts = new ArrayList<String>();

        for (String leadId : classificationsByLeadId.keySet()) {
            List<ClassificationRecord> classifications = classificationsByLeadId.get(leadId);

            ClassificationRecord summaryRecord = classifications.stream().reduce(new ClassificationRecord(),
                    reduceCountAndTotals);
            ClassificationRecord record = classifications.get(0);
            int industryCount = summaryRecord.getScoreCountForIndustry(record.getIndustry());
            int max = summaryRecord.getMaxCountAcrossAllIndustries();
            if (industryCount < max && industryCount > 0) {
                leadsWithInvalidCounts.add(record.getLeadId());
            }
        }
        return leadsWithInvalidCounts;
    }

    public static List<String> findLeadsWithIncorrectIndustryTotals(List<ClassificationRecord> classificationRecords) {
        Map<String, List<ClassificationRecord>> classificationsByLeadId = classificationRecords.stream().collect
                (Collectors.groupingBy(ClassificationRecord::getLeadId));
        List<String> leadsWithInvalidTotals = new ArrayList<String>();

        for (String leadId : classificationsByLeadId.keySet()) {
            List<ClassificationRecord> classifications = classificationsByLeadId.get(leadId);
            ClassificationRecord summaryRecord = classifications.stream().reduce(new ClassificationRecord(),
                    reduceCountAndTotals);
            ClassificationRecord record = classifications.get(0);

            int industryCount = summaryRecord.getScoreCountForIndustry(record.getIndustry());
            int maxCount = summaryRecord.getMaxCountAcrossAllIndustries();

            double industryTotal = summaryRecord.getScoreForIndustry(record.getIndustry());
            double max = summaryRecord.getMaxScoreAcrossAllIndustries();
            if (industryTotal < max && industryTotal > 0) {
                // if passing industry counts skip it..
                if (industryCount >= maxCount) {
                    continue;
                }
                leadsWithInvalidTotals.add(record.getLeadId());
            }
        }
        return leadsWithInvalidTotals;
    }


}
