package de.ethasia.exorions.ioadapters.mocks;

import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import de.ethasia.exorions.interactors.crosslayer.InformationForMapsCouldNotBeLoadedException;
import de.ethasia.exorions.interactors.crosslayer.MapDataCouldNotBeLoadedException;
import de.ethasia.exorions.ioadapters.crosslayer.Maps;
import de.ethasia.exorions.javautils.Utils;
import java.io.IOException;
import java.io.StringReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class MapsMock implements Maps {
    
    //<editor-fold defaultstate="collapsed" desc="Static Mock Properties">
    
    private static String mapListXml;
    public static void setMapListXml(String value) {
        mapListXml = value;
    }
    
    private static String mapLogicXml;
    public static void setMapLogicXml(String value) {
        mapLogicXml = value;
    }    
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Maps Overrides">
    
    @Override
    public Document readMapList() {
        try {
            return convertStringToXmlDocument(mapListXml);
        } catch (ParserConfigurationException ex) {
            InformationForMapsCouldNotBeLoadedException internalException = new InformationForMapsCouldNotBeLoadedException(
                "Creating the XML parser failed.", 
                Utils.convertExceptionStackTraceToString(ex));
            throw internalException;
        } catch (SAXException ex) {
            InformationForMapsCouldNotBeLoadedException internalException = new InformationForMapsCouldNotBeLoadedException(
                "Parsing the XML failed. It might be corrupted.", 
                Utils.convertExceptionStackTraceToString(ex));  
            throw internalException;
        } catch (IOException ex) {
            InformationForMapsCouldNotBeLoadedException internalException = new InformationForMapsCouldNotBeLoadedException(
                "Loading the file failed. It might not exist or the game might not have access.", 
                Utils.convertExceptionStackTraceToString(ex));  
            throw internalException;            
        }
    }

    @Override
    public Document readMapLogic(String path) {
        try {
            return convertStringToXmlDocument(mapLogicXml);
        } catch (ParserConfigurationException ex) {
            MapDataCouldNotBeLoadedException internalException = new MapDataCouldNotBeLoadedException(
                "Creating the XML parser failed. Affected map: " + path, 
                Utils.convertExceptionStackTraceToString(ex));
            throw internalException;
        } catch (SAXException ex) {
            MapDataCouldNotBeLoadedException internalException = new MapDataCouldNotBeLoadedException(
                "Parsing the XML failed. It might be corrupted. Affected map: " + path, 
                Utils.convertExceptionStackTraceToString(ex));  
            throw internalException;            
        } catch (IOException ex) {
            MapDataCouldNotBeLoadedException internalException = new MapDataCouldNotBeLoadedException(
                "Loading the file failed. It might not exist or the game might not have access. Affected map: " + path, 
                Utils.convertExceptionStackTraceToString(ex));  
            throw internalException;  
        }
    }

    @Override
    public Spatial readMapVisuals(String path) {
        return new Node();
    }    
    
    //</editor-fold>    
    
    //<editor-fold defaultstate="collapsed" desc="Helper Methods">
    
    private Document convertStringToXmlDocument(String xmlString) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        
        DocumentBuilder docBuilder = documentBuilderFactory.newDocumentBuilder();
        Document result = docBuilder.parse(new InputSource(new StringReader(xmlString)));
        
        return result;
    }
    
    //</editor-fold>
}