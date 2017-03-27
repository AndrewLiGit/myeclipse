package com.augmentum.excel;

import static com.augmentum.constant.Constant.AUDIENCE_STRUCTURE_ID;
import static com.augmentum.constant.Constant.AUDIENCE_TYPE_BREAKINGNEWS;
import static com.augmentum.constant.Constant.AUDIENCE_TYPE_CLOSINGS;
import static com.augmentum.constant.Constant.AUDIENCE_TYPE_WEATHER;
import static com.augmentum.constant.Constant.COMPONENT_TYPE;
import static com.augmentum.constant.Constant.F_TEMPLATE_HTML;
import static com.augmentum.constant.Constant.F_TEMPLATE_WEBCONTENT;
import static com.augmentum.constant.Constant.LOCAL_OUTPUT_FOLDER;
import static com.augmentum.constant.Constant.LOCAL_TEMPLATE_FOLDER;
import static com.augmentum.constant.Constant.P_AUDIENCE_TYPE;
import static com.augmentum.constant.Constant.P_BREAKING_NEWS_AUDIENCE_ID;
import static com.augmentum.constant.Constant.P_BREAKING_NEWS_AUDIENCE_NAME;
import static com.augmentum.constant.Constant.P_BREAKING_NEWS_NEWSLETTER_URL;
import static com.augmentum.constant.Constant.P_CLOSING_AUDIENCE_ID;
import static com.augmentum.constant.Constant.P_CLOSING_AUDIENCE_NAME;
import static com.augmentum.constant.Constant.P_CLOSING_NEWSLETTER_URL;
import static com.augmentum.constant.Constant.P_CLOSING_ORGANIZATION_CODE;
import static com.augmentum.constant.Constant.P_CLOSING_ORGANIZATION_NAME;
import static com.augmentum.constant.Constant.P_CLOSING_PROVIDER;
import static com.augmentum.constant.Constant.P_WEATHER_AUDIENCE_ID;
import static com.augmentum.constant.Constant.P_WEATHER_AUDIENCE_NAME;
import static com.augmentum.constant.Constant.P_WEATHER_COUNTY_ZONE_COLLECTION;
import static com.augmentum.constant.Constant.P_WEATHER_NEWSLETTER_URL;
import static com.augmentum.constant.Constant.P_CLOSING_VIBES_ATTRIBUTE_PATH;
import static com.augmentum.constant.Constant.P_WEATHER_VIBES_ATTRIBUTE_PATH;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import net.sf.jxls.exception.ParsePropertyException;
import net.sf.jxls.transformer.XLSTransformer;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.ListUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;

import com.augmentum.constant.Constant;
import com.augmentum.county.CountyCache;
import com.augmentum.entity.Audience;
import com.augmentum.entity.Organization;
import com.augmentum.entity.WebContent;
import com.augmentum.excel.util.ExcelException;
import com.augmentum.excel.util.ExcelUtil;
import com.augmentum.excel.util.ImportUtil;
import com.augmentum.util.InstanceKeyGenerator;
import com.augmentum.util.SQLServerReaderUtil;
import com.augmentum.util.XMLReaderUtil;
import static com.augmentum.constant.Constant.sites;
import static com.augmentum.constant.Constant.weatherSites;
import static com.augmentum.constant.Constant.audienceTypes;
import static com.augmentum.constant.Constant.siteIdMap;


public class Exporter {
    
    static Logger log = Logger.getLogger(Exporter.class.getClass());  
    
    protected final static int ROW_HEADER = ExcelUtil.getSSRow(1);
    protected final static int ROW_DATASTART = ExcelUtil.getSSRow(2);
    protected final static String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    protected final static String EXCEL_ROW = "EXCEL_ROW";
    protected static SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
    
    protected static String HTMLContent = "";
    
    static {
        // Audience Component Content Template, corresponding table JOURNALARTICLE column CONTENT.
        HTMLContent = getAudienceComponentTemplateContent("template/" + F_TEMPLATE_HTML);
    }
    
    public void export() {
        
//        List<WebContent> webContentList = processExcel(Constant.WSYR_ClOSINGLOCATION_MAPPING_FILE);//closing audience
//    	
//        if (CollectionUtils.isNotEmpty(webContentList)) {
//            doExport(webContentList, Constant.SITE_NAME, Constant.CLOSING_ALERT_AUDIENCE);
//        }
//      
    	for (int i = 0; i < audienceTypes.length; i++) {
			if (StringUtils.equals(audienceTypes[i], "closing")) {
				for (int j = 0; j < sites.length; j++) {
					System.out.println("processing " + sites[j]);
					List<WebContent> webContentList = processExcel(sites[j]);
					if (CollectionUtils.isNotEmpty(webContentList)) {
						doExport(webContentList, sites[j], Constant.CLOSING_ALERT_AUDIENCE);
					}
				}
			}
			if (StringUtils.equals(audienceTypes[i], "weather")) {
				for (int j = 0; j < weatherSites.length; j++) {
					List<WebContent> webContentList = processXML(weatherSites[j]);
					if (CollectionUtils.isNotEmpty(webContentList)) {
						doExport(webContentList, weatherSites[j], Constant.WEATHER_ALERT_AUDIENCE);
					}
				}
			}
		}
    }
    
    @SuppressWarnings("unchecked")
    private List<WebContent> processExcel(String site) {
        List<WebContent> webContentList = new ArrayList<WebContent>();
        
        try {
            FileInputStream inputStream = new FileInputStream(Constant.LOCAL_VIBES_CLOSING_AUDIENCE_FOLDER + site + "_ClosingLocation_Mapping.xls");
            if (inputStream != null) {
                Workbook wb = ExcelUtil.getWorkbook(inputStream);

                webContentList = ListUtils.sum(webContentList, processClosings(wb, site));
            }
            inputStream.close();
        } catch (FileNotFoundException e) {
            log.error("Can not find Excel file [" + site + "].", e);
            // no vibes mapping file
            webContentList = ListUtils.sum(webContentList, processClosings(null, site));
            
        } catch (ExcelException e) {
            log.error("Error when get Excel Workbook.", e);
        } catch (IOException e) {
            log.error(e);
        }
        
        return webContentList;
    }
    
	private List<WebContent> processXML(String site) {
		
		List<WebContent> webContentList = new ArrayList<WebContent>();
		webContentList = ListUtils.sum(webContentList,processWeather(site, Constant.LOCAL_WEATHER_AUDIENCE_FOLDER + site + "_Audiences_WeatherAlerts.xml"));

		return webContentList;
	}
    
    
    
    
    
    private List<WebContent> processBreakingNews(Workbook wb, String groupName) {
        List<WebContent> breakingNewsWebContent = new ArrayList<WebContent>();
        
        List<Properties> propertiesList = new ArrayList<Properties>();
        try {
            propertiesList = ImportUtil.getProperties(wb, AUDIENCE_TYPE_BREAKINGNEWS, ROW_HEADER, ROW_DATASTART, dateFormat, EXCEL_ROW);
        } catch (Exception e) {
            log.error("Error when get properties in sheet[" + AUDIENCE_TYPE_BREAKINGNEWS + "], site[" + groupName + "]", e);
        }
        for (Properties properties : propertiesList) {
            String audienceId = properties.getProperty("AUDIENCE_ID") == null ? "" : properties.getProperty("AUDIENCE_ID");
            String audienceName = properties.getProperty("AUDIENCE_NAME") == null ? "" : StringEscapeUtils.unescapeHtml4(properties.getProperty("AUDIENCE_NAME"));
            String newsLetterUrl = properties.getProperty("NEWS_LETTER_URL") == null ? "" : properties.getProperty("NEWS_LETTER_URL");
            
            String html = HTMLContent;
            html = genInstanceId(html)
                .replace(P_WEATHER_AUDIENCE_ID, "")
                .replace(P_WEATHER_AUDIENCE_NAME, "")
                .replace(P_WEATHER_NEWSLETTER_URL, "")
                .replace(P_WEATHER_COUNTY_ZONE_COLLECTION, "")
                .replace(P_CLOSING_AUDIENCE_ID, "")
                .replace(P_CLOSING_AUDIENCE_NAME, "")
                .replace(P_CLOSING_NEWSLETTER_URL, "")
                .replace(P_CLOSING_ORGANIZATION_CODE, "")
                .replace(P_CLOSING_ORGANIZATION_NAME, "")
                .replace(P_CLOSING_PROVIDER, "")
                .replace(P_BREAKING_NEWS_AUDIENCE_ID, audienceId)
                .replace(P_BREAKING_NEWS_AUDIENCE_NAME, audienceName)
                .replace(P_BREAKING_NEWS_NEWSLETTER_URL, newsLetterUrl)
                .replace(P_AUDIENCE_TYPE, AUDIENCE_TYPE_BREAKINGNEWS);
            
            breakingNewsWebContent.add(new WebContent("true", groupName, audienceName, COMPONENT_TYPE, "", "", html, AUDIENCE_STRUCTURE_ID, "", "", ""));
        }
        
        return breakingNewsWebContent;
    }
    
    private List<WebContent> processWeather(String groupName, String audienceFilePath) {
        List<WebContent> weatherWebContent = new ArrayList<WebContent>();
        List<Audience> audiences = new ArrayList<Audience>();
        audiences = XMLReaderUtil.getAllAudienceFromXML(audienceFilePath);
    	String newsLetterUrl = "http://www." + groupName + ".com/newsletters/weatheralert";
    	Map<String, String> countyMap = SQLServerReaderUtil.getCountyCodeMap();
        for (Audience audience : audiences) {
            String audienceId = audience.getAudienceGUId();
            String audienceName = audience.getAudieceName();
            String countyName = audience.getAudieceName().split(",")[0].split(" ")[0].toLowerCase();
            
            String countyCode = countyMap.get(countyName);
            
            String html = HTMLContent;
            html = genInstanceId(html)
                .replace(P_WEATHER_AUDIENCE_ID, audienceId)
                .replace(P_WEATHER_AUDIENCE_NAME, audience.getAudieceName())
                .replace(P_WEATHER_NEWSLETTER_URL, newsLetterUrl)
                .replace(P_WEATHER_COUNTY_ZONE_COLLECTION, countyCode)
                .replace(P_WEATHER_VIBES_ATTRIBUTE_PATH, "county/"+countyCode)
                .replace(P_CLOSING_AUDIENCE_ID, "")
                .replace(P_CLOSING_AUDIENCE_NAME, "")
                .replace(P_CLOSING_NEWSLETTER_URL, "")
                .replace(P_CLOSING_ORGANIZATION_CODE, "")
                .replace(P_CLOSING_ORGANIZATION_NAME, "")
                .replace(P_CLOSING_PROVIDER, "")
                .replace(P_CLOSING_VIBES_ATTRIBUTE_PATH, "")
                .replace(P_BREAKING_NEWS_AUDIENCE_ID, "")
                .replace(P_BREAKING_NEWS_AUDIENCE_NAME, "")
                .replace(P_BREAKING_NEWS_NEWSLETTER_URL, "")
                .replace(P_AUDIENCE_TYPE, AUDIENCE_TYPE_WEATHER);
            
            weatherWebContent.add(new WebContent("true", groupName.toUpperCase(), "Weather Alert For " +audienceName, COMPONENT_TYPE, "", "", html, AUDIENCE_STRUCTURE_ID, "", "", ""));
        }
        
        return weatherWebContent;
    }
    
    private List<WebContent> processClosings(Workbook wb, String groupName) {
        List<WebContent> closingsWebContent = new ArrayList<WebContent>();
        
        List<Properties> propertiesList = new ArrayList<Properties>();
        List<Audience> audienceList = new ArrayList<Audience>();
        List<Organization> orgList = new ArrayList<Organization>();
        Map<String, String> duplicateAudienceMap = new HashMap<String, String>();
        Map<String, String> duplicateAllOrgsMap = new HashMap<String, String>();
        try {
        	if (wb != null) {
        		propertiesList = ImportUtil.getProperties(wb, AUDIENCE_TYPE_CLOSINGS, ROW_HEADER, ROW_DATASTART, dateFormat, EXCEL_ROW);
        		duplicateAudienceMap = XMLReaderUtil.getDuplicateOrgFromClosings(groupName, propertiesList);
			}
            audienceList = XMLReaderUtil.getAllAudienceFromXML(Constant.LOCAL_SSM_CLOSING_AUDIENCE_FOLDER + groupName + "_Audiences.xml");
            orgList = XMLReaderUtil.getAllOrgsFromXML(Constant.LOCAL_ALL_ORG_FOLDER + groupName + "_AllOrgs.xml");
            duplicateAllOrgsMap = XMLReaderUtil.getDuplicateOrganizations(groupName, orgList);
        } catch (Exception e) {
            log.error("Error when get properties in sheet[" + AUDIENCE_TYPE_CLOSINGS + "], site[" + groupName + "]", e);
        }
        
        String siteId = (String) siteIdMap.get(groupName);
        for (Organization organization : orgList) {
        	String baseOrgId = organization.getOrgId();
        	String refOrgId = null;
        	String organizationName = organization.getOrgName();
        	String providerName = "Newsticker";
        	String attributePath = "";
        	String audienceId = "";
        	String audienceName = organization.getOrgName();
        	String audienceComponentName = "Closing For " + audienceName;
        	String newsLetterUrl = "http://www." + groupName + ".com/newsletters/closingalert";
        	
        	for (Properties properties : propertiesList) {
        		String activeFlag = properties.getProperty("Active");
        		refOrgId = properties.getProperty("ExternalID - Business ID");
        		
        		if (StringUtils.equals(activeFlag, "1") && StringUtils.isNumeric(refOrgId)) {
        			refOrgId = String.format("%06d", Long.parseLong(refOrgId));
        			if (StringUtils.equals(baseOrgId, refOrgId)) {
        				siteId = properties.getProperty("SiteID") == null ? "" : properties.getProperty("SiteID");
        				organizationName = properties.getProperty("Name") == null ? "" : properties.getProperty("Name");
        				attributePath = properties.getProperty("LocationID (Vibes)") == null ? "" : properties.getProperty("LocationID (Vibes)");
        				 
    					if (duplicateAudienceMap.containsValue(organizationName)) {
    						audienceComponentName = audienceComponentName + "_" + baseOrgId;
    						break;
    					}
					}
        		}
        	}
        	
        	if (CollectionUtils.isEmpty(propertiesList) && duplicateAllOrgsMap.containsValue(audienceName)) {
        		audienceComponentName = audienceComponentName + "_" + baseOrgId;
			}
        	
        	if (StringUtils.isEmpty(attributePath)) {
				//log.info(groupName+ "---not find in vibes:orgId = "+ baseOrgId);
			}
        	
        	for (Audience audience : audienceList) {
        		 
				String temp1 = audience.getAudieceName();
				String temp2 = organization.getOrgName();
				if (StringUtils.equals(StringUtils.normalizeSpace(temp1), StringUtils.normalizeSpace(temp2))) {
					if (CollectionUtils.isNotEmpty(propertiesList) && !duplicateAudienceMap.containsValue(temp1)) {
						audienceId = audience.getAudienceGUId();
					}
					if (CollectionUtils.isEmpty(propertiesList) && !duplicateAllOrgsMap.containsValue(temp1)) {
						audienceId = audience.getAudienceGUId();
					}
				}
        	}
        	
        	if (StringUtils.isEmpty(audienceId)) {
        		log.info(groupName + "---not find in ssm:orgId = "+ baseOrgId);
			}
        	
        	if (StringUtils.isNotEmpty(baseOrgId) && StringUtils.isNumeric(baseOrgId) 
        			&& (StringUtils.isNotEmpty(audienceId) || StringUtils.isNotEmpty(attributePath))) {
        		if (StringUtils.isNotBlank(attributePath)) {
					attributePath = "location/" + attributePath; 
				}
        		String html = HTMLContent;
        		html = genInstanceId(html)
        				.replace(P_WEATHER_AUDIENCE_ID, "")
        				.replace(P_WEATHER_AUDIENCE_NAME, "")
        				.replace(P_WEATHER_NEWSLETTER_URL, "")
        				.replace(P_WEATHER_COUNTY_ZONE_COLLECTION, "")
        				.replace(P_CLOSING_ORGANIZATION_CODE, siteId+"_"+baseOrgId)
        				.replace(P_CLOSING_ORGANIZATION_NAME, organizationName)
        				.replace(P_CLOSING_PROVIDER, providerName)
        				.replace(P_CLOSING_AUDIENCE_ID, audienceId)
        				.replace(P_CLOSING_AUDIENCE_NAME, audienceName)
        				.replace(P_CLOSING_NEWSLETTER_URL, newsLetterUrl)
        				.replace(P_CLOSING_VIBES_ATTRIBUTE_PATH, attributePath)
        				.replace(P_BREAKING_NEWS_AUDIENCE_ID, "")
        				.replace(P_BREAKING_NEWS_AUDIENCE_NAME, "")
        				.replace(P_BREAKING_NEWS_NEWSLETTER_URL, "")
        				.replace(P_AUDIENCE_TYPE, AUDIENCE_TYPE_CLOSINGS)
        				.replace(P_WEATHER_VIBES_ATTRIBUTE_PATH, "");
        		
        		closingsWebContent.add(new WebContent("true", groupName.toUpperCase(), audienceComponentName, COMPONENT_TYPE, "", "", html, AUDIENCE_STRUCTURE_ID, "", "", ""));
			} else {
				//log.info(groupName + "----------"+organization.toString() + "not matched both in ssm and vibes file!");
			}
        	
        }
        
        return closingsWebContent;
    }
    
    private void doExport(List<WebContent> webcontent, String siteName, String type) {
        try {
            String canoicalPath = new File(".").getCanonicalPath().replace("\\", "/");
            String templateFileName =  canoicalPath + LOCAL_TEMPLATE_FOLDER + F_TEMPLATE_WEBCONTENT;
            String destFileName = canoicalPath + LOCAL_OUTPUT_FOLDER + siteName + "_"+ type+"_out.xls";
            
            Map<String, List<WebContent>> beans = new HashMap<String, List<WebContent>>();
            beans.put("webcontent", webcontent);
            
            XLSTransformer transformer = new XLSTransformer();
            transformer.transformXLS(templateFileName, beans, destFileName);
            
            log.info("Site[" + siteName + "] -- " + webcontent.size() + " records generated.");
            log.info("Success, file exported " + destFileName);
        } catch (IOException e) {
            log.error("Error when get canoicalPath.", e);
        } catch (ParsePropertyException e) {
            log.error("Error when process xls transform.", e);
        } catch (InvalidFormatException e) {
            log.error("Error when process xls transform.", e);
        }
    }
    
    private static String getAudienceComponentTemplateContent(String fileName) {
        File file = new File(fileName);
        Reader reader = null;
        StringBuilder templateContent = new StringBuilder();
        
        try {
            reader = new InputStreamReader(new FileInputStream(file));
            int tempchar;
            while ((tempchar = reader.read()) != -1) {
                if (((char) tempchar) != '\r') {
                    templateContent.append((char) tempchar);
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            log.error("Can not find audience component template file [" + fileName + "].", e);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return templateContent.toString();
    }
    
    private String genInstanceId(String content) {
        for (int i = 1; i <= 24; i++) {
            content = content.replace("@@@instance_id_" + i + "@@@", InstanceKeyGenerator.getInstaceId());
        }
        return content;
    }
}