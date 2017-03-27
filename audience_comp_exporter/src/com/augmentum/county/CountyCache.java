package com.augmentum.county;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CountyCache {

    public static Map<String, String> counties;
    
    static {
        counties = new HashMap<String, String>();
        File file = new File("template/wdt_county_info.txt");
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                String[] record = tempString.split(",");
                counties.put(record[0] + "-" + record[2], record[1]);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
    }
    
    public static String getValue(String key) {
        return counties.get(key);
    }
    
    public static void main(String[] args) {
        // IN,18045,Fountain
        // CO,08071,Las Animas
        // CA,06087,Santa Cruz
        System.out.println(CountyCache.getValue("IN-Fountain"));
        System.out.println(CountyCache.getValue("CO-Las Animas"));
        System.out.println(CountyCache.getValue("CA-Santa Cruz"));
    }
}
