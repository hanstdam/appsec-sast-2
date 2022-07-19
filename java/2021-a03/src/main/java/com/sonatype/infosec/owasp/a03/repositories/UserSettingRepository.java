package com.sonatype.infosec.owasp.a03.repositories;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.sonatype.infosec.owasp.a03.models.Option;
import com.sonatype.infosec.owasp.a03.models.UserSetting;

public class UserSettingRepository {
	
	final String userSettingsFilePath = "xml/UserSettings.xml";
	
	public Option<UserSetting> getUserSettings(String userId) {
		try {
			File userSettingsFile = new File(userSettingsFilePath);
	
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(userSettingsFile);
			doc.getDocumentElement().normalize();
	
	        XPathFactory xPathFactory = XPathFactory.newInstance();
	        XPath xpath = xPathFactory.newXPath();
	        
			// CWE-91 XML Injection (aka Blind XPath Injection)
	        XPathExpression expression = xpath.compile("//settings/user[@id='" + userId + "']");
	        Object result = expression.evaluate(doc, XPathConstants.NODESET);
	        
	        return getUserSettingFromNodeList(result);
	        
		} catch (Exception e) {
			// Ignore
		}
		
		return new Option<UserSetting>();
	}
	
	private Option<UserSetting> getUserSettingFromNodeList(Object nodeList) {
		NodeList nodes = (NodeList) nodeList;
		if (nodes.getLength() > 0) {
			Node node = nodes.item(0);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) node;
				String userId = node.getAttributes().getNamedItem("id").getNodeValue();
				String role = element.getElementsByTagName("role").item(0).getTextContent();
				String manager = element.getElementsByTagName("manager").item(0).getTextContent();
				boolean isMFAEnabled = element.getElementsByTagName("mfa").item(0).getTextContent().equals("enabled");
				return new Option<UserSetting>(new UserSetting(userId, role, isMFAEnabled, manager));
			}
		}
		return new Option<UserSetting>();
	}
}
