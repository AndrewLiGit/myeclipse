package com.augmentum.constant;

import java.util.HashMap;
import java.util.Map;

public class Constant {

//    public static final String[] sites = {"LOCALSYR"};
//    public static final String[] sites = {"7NEWS", "RTV6"};
	public static final String[] audienceTypes = {"closing", "weather"};
	public static final String[] weatherSites = {"localsyr"};
//	public static final String[] sites = {"localsyr", "arkansasmatters", "illinoishomepage", "pahomepage", "rochesterfirst"};//group A
	public static final String[] sites = {"localsyr", "arkansasmatters", "illinoishomepage", "pahomepage", "rochesterfirst"};//group B
//	public static final String[] sites = {"illinoishomepage"};
	public static final Map siteIdMap = new HashMap() {{  
	    put("localsyr", "17");  
	    put("arkansasmatters", "183");
	    put("illinoishomepage", "187"); 
	    put("pahomepage", "182"); 
	    put("rochesterfirst", "185"); 
	}};
	public static final String WEATHER_ALERT_AUDIENCE = "weather_alert";
	public static final String CLOSING_ALERT_AUDIENCE = "closing_alert";
	
    public static final String LOCAL_TEMPLATE_FOLDER = "/template/";
    public static final String LOCAL_OUTPUT_FOLDER = "/output/";
    public static final String LOCAL_SSM_CLOSING_AUDIENCE_FOLDER = "ssm_closing_audience/";
    public static final String LOCAL_WEATHER_AUDIENCE_FOLDER = "weather_audience/";
    public static final String LOCAL_VIBES_CLOSING_AUDIENCE_FOLDER = "vibes_closing_audience/";
    public static final String LOCAL_ALL_ORG_FOLDER = "allorgs/";
    
    
    public static final String F_TEMPLATE_WEBCONTENT = "webcontent_template.xls";
    public static final String F_TEMPLATE_HTML = "audience_component_template.xml";
    
    public static final String AUDIENCE_STRUCTURE_ID = "AUDIENCE_COMPONENT";
    public static final String COMPONENT_TYPE = "module"; //component
    
    public static final String AUDIENCE_TYPE_WEATHER = "AUDIANCE_WEATHER";
    public static final String AUDIENCE_TYPE_CLOSINGS = "AUDIANCE_CLOSINGS";
    public static final String AUDIENCE_TYPE_BREAKINGNEWS = "AUDIANCE_BREAKING_NEWS";
    
    public static final String P_CDATA = "<![CDATA[]]>";
    public static final String P_AUDIENCE_TYPE = "@@@selected_audience_type@@@";
    
    public static final String P_WEATHER_COUNTY_ZONE_COLLECTION = "@@@weather_county_zone_collection@@@";
    public static final String P_WEATHER_AUDIENCE_NAME = "@@@weather_audience_name@@@";
    public static final String P_WEATHER_AUDIENCE_ID = "@@@weather_audience_id@@@";
    public static final String P_WEATHER_NEWSLETTER_URL = "@@@weather_newsletter_url@@@";
    public static final String P_CLOSING_PROVIDER = "@@@closing_provider@@@";
    public static final String P_CLOSING_ORGANIZATION_CODE = "@@@closing_organization_code@@@";
    public static final String P_CLOSING_ORGANIZATION_NAME = "@@@closing_organization_name@@@";
    public static final String P_CLOSING_AUDIENCE_NAME = "@@@closing_audience_name@@@";
    public static final String P_CLOSING_AUDIENCE_ID = "@@@closing_audience_id@@@";
    public static final String P_CLOSING_NEWSLETTER_URL = "@@@closing_newsletter_url@@@";
    public static final String P_CLOSING_VIBES_ATTRIBUTE_PATH = "@@@vibes_closing_attribute_path@@@";
    public static final String P_CLOSING_SSM_AUDIENCEID = "@@@closing_audience_id@@@";
    public static final String P_CLOSING_SSM_AUDIENCENAME = "@@@closing_audience_name@@@";
    public static final String P_CLOSING_SSM_NEWS_NEWSLETTER_URL = "@@@closing_newsletter_url@@@";
    public static final String P_WEATHER_VIBES_ATTRIBUTE_PATH = "@@@vibes_weather_attribute_path@@@";
    public static final String P_BREAKING_NEWS_AUDIENCE_NAME = "@@@breaking_news_audience_name@@@";
    public static final String P_BREAKING_NEWS_AUDIENCE_ID = "@@@breaking_news_audience_id@@@";
    public static final String P_BREAKING_NEWS_NEWSLETTER_URL = "@@@breaking_news_newsletter_url@@@";
    

}
