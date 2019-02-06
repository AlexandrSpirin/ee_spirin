package com.accenture.flowershop.be.business;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


public class XMLConverter {
    private Marshaller marshaller;
    private Unmarshaller unmarshaller;

    @Value("${exportPath}")
    String properyPath;

    public void convertFromObjectToXML(Object object, String fileName)
            throws IOException {
        try {
            FileOutputStream os = new FileOutputStream(properyPath + "/" + fileName);
            getMarshaller().marshal(object, new StreamResult(os));
        } catch (Exception e) {
            throw e;
        }
    }

    public Object convertFromXMLToObject(String xmlfile) throws IOException {
        try {
            FileInputStream is = new FileInputStream(xmlfile);
            return getUnmarshaller().unmarshal(new StreamSource(is));
        } catch (Exception e) {
            throw e;
        }
    }

    public Marshaller getMarshaller() {
        return marshaller;
    }

    public void setMarshaller(Marshaller marshaller) {
        this.marshaller = (Marshaller)marshaller;
    }

    public Unmarshaller getUnmarshaller() {
        return unmarshaller;
    }

    public void setUnmarshaller(Unmarshaller unmarshaller) {
        this.unmarshaller = unmarshaller;
    }
}
