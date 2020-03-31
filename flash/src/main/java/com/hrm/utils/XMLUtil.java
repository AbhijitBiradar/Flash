package com.hrm.utils;

public class XMLUtil {
	public static NodeList getXMLNodes(final String xmlFileName, final String tagName) {
        NodeList nList = null;
        try {
            File xmlFile = new File(xmlFileName);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();
            nList = doc.getElementsByTagName(tagName);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return nList;
    }
}
