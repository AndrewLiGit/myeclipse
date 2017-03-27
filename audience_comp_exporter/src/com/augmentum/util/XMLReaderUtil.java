package com.augmentum.util;

import com.augmentum.constant.Constant;
import com.augmentum.entity.Audience;
import com.augmentum.entity.Organization;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLReaderUtil {
	
    static Logger log = Logger.getLogger(XMLReaderUtil.class.getClass()); 
    
	public static List<Audience> getAllAudienceFromXML(String filePath) {
		List<Audience> audienceList = new ArrayList<Audience>();
		Element element = null;
		File f = new File(filePath);// your xml file path
		DocumentBuilder db = null;
		DocumentBuilderFactory dbf = null;
		try {
			dbf = DocumentBuilderFactory.newInstance();
			db = dbf.newDocumentBuilder();
			Document dt = db.parse(f);
			element = dt.getDocumentElement();
			NodeList childNodes = element.getChildNodes();
			for (int i = 0; i < childNodes.getLength(); i++) {
				Node node1 = childNodes.item(i);
				if ("audience".equals(node1.getNodeName())) {
					Audience audience = new Audience();
					NodeList nodeDetail = node1.getChildNodes();
					for (int j = 0; j < nodeDetail.getLength(); j++) {
						Node detail = nodeDetail.item(j);
						if ("audienceGUID".equals(detail.getNodeName())) {
							audience.setAudienceGUId(detail.getTextContent());
						} else if ("audienceName".equals(detail.getNodeName())) {
							audience.setAudieceName(detail.getTextContent());
						}
					}
					audienceList.add(audience);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return audienceList;
	}
	
	public static List<Organization> getAllOrgsFromXML(String filePath) {
		List<Organization> orgList = new ArrayList<Organization>();
		Element element = null;
		File f = new File(filePath);// your xml file path
		DocumentBuilder db = null;
		DocumentBuilderFactory dbf = null;
		try {
			dbf = DocumentBuilderFactory.newInstance();
			db = dbf.newDocumentBuilder();
			Document dt = db.parse(f);
			element = dt.getDocumentElement();
			NodeList childNodes = element.getChildNodes();
			for (int i = 0; i < childNodes.getLength(); i++) {
				Node node1 = childNodes.item(i);
				if ("RECORD".equals(node1.getNodeName())) {
					Organization organization = new Organization();
					NodeList nodeDetail = node1.getChildNodes();
					for (int j = 0; j < nodeDetail.getLength(); j++) {
						Node detail = nodeDetail.item(j);
						if ("REC_ID".equals(detail.getNodeName())) {
							organization.setOrgId(detail.getTextContent());
						} else if ("FORCED_ORGANIZATION_NAME".equals(detail
								.getNodeName())) {
							organization.setOrgName(detail.getTextContent());
						}
					}
					orgList.add(organization);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return orgList;
	}
	
	public static Map<String, String> getDuplicateOrganizations(String groupName, List<Organization> baseOrgList){
		Map<String, String> result = new HashMap<String, String>();
		Map<String, String> tempMap = new HashMap<String, String>();
		for (Organization organization : baseOrgList) {
			if (tempMap.containsKey(organization.getOrgName())) {
				result.put(organization.getOrgId(), organization.getOrgName());
				result.put(tempMap.get(organization.getOrgName()), organization.getOrgName());
			} else {
				tempMap.put(organization.getOrgName(), organization.getOrgId());
			}
		}
		log.info("-----------there are " + result.size() + " duplicate orgs in " +groupName+ " AllOrgs.xml-----------");
		log.info(result);
		return result;
	}
	
	public static Map<String, String> getDuplicateOrgFromClosings(String groupName, List<Properties> properties){
		Map<String, String> result = new HashMap<String, String>();
		Map<String, String> tempMap = new HashMap<String, String>();
		for (Properties property : properties) {
			String activeFlag = property.getProperty("Active");
			if (StringUtils.equals(activeFlag, "1")) {
				String orgName = property.getProperty("Name");
				String orgId = property.getProperty("ExternalID - Business ID");
				if (tempMap.containsKey(orgName)) {
					result.put(orgId, orgName);
					result.put(tempMap.get(orgName), orgName);
					
				} else {
					tempMap.put(orgName, orgId);
				}
			}
		}
		log.info("-----------there are " + result.size() + " duplicate orgs in " +groupName+ " location mapping file-----------");
		log.info(result);
		return result;
	}
	
	public static List<Organization> getUnimportOrganizations(List<Organization> baseOrgList, List<Properties> properties,
			List<Audience> audiences) {
		
		List<Organization> result = new ArrayList<Organization>();
		boolean extInMapping = true;
		boolean extInAudience = true;
		for (Properties property : properties) {
			for (Organization organization : baseOrgList) {
				String orgId = property.getProperty("ExternalID - Business ID");
				if (StringUtils.isNumeric(orgId)) {
					orgId = String.format("%06d", Long.parseLong(orgId));
					if (StringUtils.equals(orgId, organization.getOrgId())) {
						extInMapping = true;
						break;
					} else {
						extInMapping = false;
					}
				} else {
					extInMapping = false;
				}
			}
			if (!extInMapping) {
				Organization organization = new Organization();
				organization.setOrgId(property.getProperty("ExternalID - Business ID"));
				organization.setOrgName(property.getProperty("Name"));
				result.add(organization);
			}
		}
		
		for (Audience audience : audiences) {
			for (Organization organization : baseOrgList) {
				String temp1 = audience.getAudieceName();
				String temp2 = organization.getOrgName();
				if (StringUtils.equals(StringUtils.normalizeSpace(temp1), StringUtils.normalizeSpace(temp2))) {
					extInAudience = true;
					break;
				} else {
					extInAudience = false;
				}
			}
			if (!extInAudience) {
				Organization organization = new Organization();
				organization.setOrgName(audience.getAudieceName());
				result.add(organization);
			}
		}
		
		try {
			FileWriter writer = new FileWriter("unimport-org-"+new Random()+".txt",true); //target file path 
			BufferedWriter  bw = new BufferedWriter(writer);
		    for (Organization organization : result) {
				bw.write(organization.toString()+"\t\n");
		    }
		    bw.close();
		    writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	public static List<Organization> getImportOrgsWithNoneData(List<Organization> baseOrgList, List<Properties> properties,
			List<Audience> audiences) {
		
		List<Organization> result = new ArrayList<Organization>();
		boolean extInMapping = true;
		boolean extInAudience = true;
		for (Organization organization : baseOrgList) {
			for (Properties property : properties) {
				String orgId = property.getProperty("ExternalID - Business ID");
				if (StringUtils.isNumeric(orgId)) {
					orgId = String.format("%06d", Long.parseLong(orgId));
					if (StringUtils.equals(orgId, organization.getOrgId())) {
						extInMapping = true;
						break;
					} else {
						extInMapping = false;
					}
				} else {
					extInMapping = false;
				}
			}
			if (!extInMapping) {
				Organization org = new Organization();
				org.setOrgId(organization.getOrgId());
				org.setOrgName(organization.getOrgName());
				result.add(org);
			}
		}
		
		for (Organization organization : baseOrgList) {
			for (Audience audience : audiences) {
				String temp1 = audience.getAudieceName();
				String temp2 = organization.getOrgName();
				if (StringUtils.equals(StringUtils.normalizeSpace(temp1), StringUtils.normalizeSpace(temp2))) {
					extInAudience = true;
					break;
				} else {
					extInAudience = false;
				}
			}
			if (!extInAudience) {
				Organization org = new Organization();
				org.setOrgId(organization.getOrgId());
				org.setOrgName(organization.getOrgName());
				result.add(org);
			}
		}
		
	    	try {
	    		FileWriter writer = new FileWriter("import-org-none-data-"+new Random()+".txt",true); //target file path 
	    		BufferedWriter  bw = new BufferedWriter(writer);
	    		for (Organization organization : result) {
	    			bw.write(organization.toString()+"\t\n");
	    		}
				bw.close();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		return result;
	}
	
	//generate org file to put into org data import file
	public static void generateOrgFile(String[] sites){
		for (int m = 0; m < sites.length; m++) {
			Element element = null;
			File f = new File(Constant.LOCAL_ALL_ORG_FOLDER + sites[m] + "_AllOrgs.xml");// your all org xml file path
			DocumentBuilder db = null;
			DocumentBuilderFactory dbf = null;
			String orgCode = "";
			try {
				dbf = DocumentBuilderFactory.newInstance();
				db = dbf.newDocumentBuilder();
				Document dt = db.parse(f);
				element = dt.getDocumentElement();
				NodeList childNodes = element.getChildNodes();
				for (int i = 0; i < childNodes.getLength(); i++) {
					Node node1 = childNodes.item(i);
					if ("RECORD".equals(node1.getNodeName())) {
						NodeList nodeDetail = node1.getChildNodes();
						String name = "";
						for (int j = 0; j < nodeDetail.getLength(); j++) {
							Node detail = nodeDetail.item(j);
							if ("REC_ID".equals(detail.getNodeName())) {
								String siteId = (String) Constant.siteIdMap.get(sites[m]);
								orgCode = siteId + "_" + detail.getTextContent();
							} else if ("FORCED_ORGANIZATION_NAME".equals(detail
									.getNodeName())) {
								name = detail.getTextContent();
							}
						}
						if (!("".equals(orgCode))) {
							System.out.println(orgCode + ",\"" + name + "\",4");
						}
						FileWriter writer = new FileWriter("output/" + sites[m] + "_org.txt", true); 
						BufferedWriter bw = new BufferedWriter(writer);
						bw.write(orgCode + ",\"" + name + "\",4\t\n");
						bw.close();
						writer.close();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String args[]) {
		generateOrgFile(Constant.sites);
	}
}
