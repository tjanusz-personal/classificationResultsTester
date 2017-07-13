package com.jornaya.cheesesteak.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ProcessingUtils {

    public static int getColumnCountValue(String[] lineAsArray, int position) {
        if (lineAsArray.length <= position) {
            return 0;
        }
        String rawValues = lineAsArray[position];
        if (rawValues.isEmpty()) {
            return 0;
        }
        if (Float.valueOf(lineAsArray[position]) > 0) {
            return 1;
        }
        return 0;
    }

    public static double getColumnDoubleValue(String[] lineAsArray, int position) {
        if (lineAsArray.length <= position) {
            return 0;
        }
        String rawValues = lineAsArray[position];
        if (rawValues.isEmpty()) {
            return 0;
        }
        return Double.valueOf(lineAsArray[position]);
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
        item.setScoreOther(ProcessingUtils.getColumnCountValue(lineArray, 6));
        item.setScoreAutoSales(ProcessingUtils.getColumnCountValue(lineArray, 7));
        item.setScoreEducation(ProcessingUtils.getColumnCountValue(lineArray, 8));
        item.setScoreInsurance(ProcessingUtils.getColumnCountValue(lineArray, 9));
        item.setScoreLegal(ProcessingUtils.getColumnCountValue(lineArray, 10));
        item.setScoreFinancialServices(ProcessingUtils.getColumnCountValue(lineArray, 11));
        item.setScoreRealEstate(ProcessingUtils.getColumnCountValue(lineArray, 12));
        item.setScoreHomeServices(ProcessingUtils.getColumnCountValue(lineArray, 13));
        item.setScoreJobs(ProcessingUtils.getColumnCountValue(lineArray, 14));
        item.setScoreSeniorLiving(ProcessingUtils.getColumnCountValue(lineArray, 15));
        return item;
    };

    // campaign_industry, classification_method, lead_id, record_id, econtext_response_status, industry
    // other, auto_sales, education, insurance, legal, financial_services, real_estate, home_services
    // jobs, senior_living
    public static Function<String, ClassificationRecord> mapCSVToIndustryTotalRecord = (line) -> {
        String[] lineArray = line.split(",");// a CSV has comma separated lines
        ClassificationRecord item = new ClassificationRecord();
        item.setCampaignClassification(lineArray[0]);
        item.setClassificationMethod(lineArray[1]);
        item.setLeadId(lineArray[2]);
        item.setRecordId(lineArray[3]);
        item.setIndustry(lineArray[5]);
        item.setScoreOther(ProcessingUtils.getColumnDoubleValue(lineArray, 6));
        item.setScoreAutoSales(ProcessingUtils.getColumnDoubleValue(lineArray, 7));
        item.setScoreEducation(ProcessingUtils.getColumnDoubleValue(lineArray, 8));
        item.setScoreInsurance(ProcessingUtils.getColumnDoubleValue(lineArray, 9));
        item.setScoreLegal(ProcessingUtils.getColumnDoubleValue(lineArray, 10));
        item.setScoreFinancialServices(ProcessingUtils.getColumnDoubleValue(lineArray, 11));
        item.setScoreRealEstate(ProcessingUtils.getColumnDoubleValue(lineArray, 12));
        item.setScoreHomeServices(ProcessingUtils.getColumnDoubleValue(lineArray, 13));
        item.setScoreJobs(ProcessingUtils.getColumnDoubleValue(lineArray, 14));
        item.setScoreSeniorLiving(ProcessingUtils.getColumnDoubleValue(lineArray, 15));
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

    public static List<String> findLeadsWithIncorrectIndustryCounts(List<ClassificationRecord> classificationRecords) {
        Map<String, List<ClassificationRecord>> classificationsByLeadId = classificationRecords.stream().collect
                (Collectors.groupingBy(ClassificationRecord::getLeadId));
        List<String> leadsWithInvalidCounts = new ArrayList<String>();

        for (String leadId : classificationsByLeadId.keySet()) {
            List<ClassificationRecord> classifications = classificationsByLeadId.get(leadId);
            ClassificationRecord summaryRecord = new ClassificationRecord();

            // Only count scores that have a value
            long count = classifications.stream().filter(record -> record.getScoreAutoSales() > 0).count();
            summaryRecord.setScoreAutoSales(count);
            count = classifications.stream().filter(record -> record.getScoreSeniorLiving() > 0).count();
            summaryRecord.setScoreSeniorLiving(count);
            count = classifications.stream().filter(record -> record.getScoreEducation() > 0).count();
            summaryRecord.setScoreEducation(count);
            count = classifications.stream().filter(record -> record.getScoreFinancialServices() > 0).count();
            summaryRecord.setScoreFinancialServices(count);
            count = classifications.stream().filter(record -> record.getScoreHomeServices() > 0).count();
            summaryRecord.setScoreHomeServices(count);
            count = classifications.stream().filter(record -> record.getScoreInsurance() > 0).count();
            summaryRecord.setScoreInsurance(count);
            count = classifications.stream().filter(record -> record.getScoreJobs() > 0).count();
            summaryRecord.setScoreJobs(count);
            count = classifications.stream().filter(record -> record.getScoreLegal() > 0).count();
            summaryRecord.setScoreLegal(count);
            count = classifications.stream().filter(record -> record.getScoreRealEstate() > 0).count();
            summaryRecord.setScoreRealEstate(count);
            count = classifications.stream().filter(record -> record.getScoreOther() > 0).count();
            summaryRecord.setScoreOther(count);

            ClassificationRecord record = classifications.get(0);
            double industryCount = summaryRecord.getScoreForIndustry(record.getIndustry());
            double max = summaryRecord.getMaxScoreAcrossAllIndustries();
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
                    (classification1, classification2) -> {

                ClassificationRecord res = new ClassificationRecord();
                res.setScoreOther(classification1.getScoreOther() + classification2.getScoreOther());
                res.setScoreRealEstate(classification1.getScoreRealEstate() + classification2.getScoreRealEstate());
                res.setScoreLegal(classification1.getScoreLegal() + classification2.getScoreLegal());
                res.setScoreJobs(classification1.getScoreJobs() + classification2.getScoreJobs());
                res.setScoreInsurance(classification1.getScoreInsurance() + classification2.getScoreInsurance());
                res.setScoreHomeServices(classification1.getScoreHomeServices() +
                        classification2.getScoreHomeServices());
                res.setScoreFinancialServices(classification1.getScoreFinancialServices() +
                        classification2.getScoreFinancialServices());
                res.setScoreEducation(classification1.getScoreEducation() + classification2.getScoreEducation());
                res.setScoreSeniorLiving(classification1.getScoreSeniorLiving() +
                        classification2.getScoreSeniorLiving());
                res.setScoreAutoSales(classification1.getScoreAutoSales() + classification2.getScoreAutoSales());
                return res;
            });

            ClassificationRecord record = classifications.get(0);
            double industryTotal = summaryRecord.getScoreForIndustry(record.getIndustry());
            double max = summaryRecord.getMaxScoreAcrossAllIndustries();
            if (industryTotal < max && industryTotal > 0) {
                leadsWithInvalidTotals.add(record.getLeadId());
            }
        }
        return leadsWithInvalidTotals;
    }


}
