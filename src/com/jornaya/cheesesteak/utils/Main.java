package com.jornaya.cheesesteak.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws Exception {
        Reader reader = new FileReader("test2.csv");
        List<String> leadIdsWithDups = findNonMatchingIndustries(reader);
        System.out.println();
        System.out.println("Found: " + leadIdsWithDups.size() + " of Error Type: Non-Matching Industries");
        leadIdsWithDups.forEach(System.out::println);

        reader = new FileReader("test2.csv");
        List<String> leadsWithInvalidCounts = findIndustryCountErrors(reader);
        System.out.println();
        System.out.println("Found: " + leadsWithInvalidCounts.size() + " of Error Type: Total Count Errors");
        leadsWithInvalidCounts.forEach(System.out::println);

        reader = new FileReader("test2.csv");
        List<String> leadsWithInvalidTotals = findIndustryTotalErrors(reader);
        System.out.println();
        System.out.println("Found: " + leadsWithInvalidTotals.size() + " of Error Type: Invalid Totals");
        leadsWithInvalidTotals.forEach(System.out::println);
    }

    public static List<String> findNonMatchingIndustries(Reader theReader) {
        BufferedReader br = new BufferedReader(theReader);
        List<ClassificationRecord> classificationRecords = br.lines().skip(1)
                .map(ProcessingUtils.mapCSVToIndustryCountRecord).collect(Collectors.toList());
        List<String> leadIdsWithDups = ProcessingUtils.findLeadsWithMismatchedIndustries(classificationRecords);
        return leadIdsWithDups;
    }

    public static List<String> findIndustryCountErrors(Reader theReader) {
        BufferedReader br = new BufferedReader(theReader);
        List<ClassificationRecord> classificationRecords = br.lines().skip(1)
                .map(ProcessingUtils.mapCSVToIndustryTotalCountRecord).collect(Collectors.toList());

        List<String> leadsWithInvalidCounts = ProcessingUtils.findLeadsWithIncorrectIndustryCounts
                (classificationRecords);
        return leadsWithInvalidCounts;
    }

    public static List<String> findIndustryTotalErrors(Reader theReader) {
        BufferedReader br = new BufferedReader(theReader);
        List<ClassificationRecord> classificationRecords = br.lines().skip(1)
                .map(ProcessingUtils.mapCSVToIndustryTotalCountRecord).collect(Collectors.toList());

        List<String> leadsWithInvalidTotals = ProcessingUtils.findLeadsWithIncorrectIndustryTotals
                (classificationRecords);
        return leadsWithInvalidTotals;
    }


}
