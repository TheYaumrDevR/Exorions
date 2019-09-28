package de.ethasia.exorions.fileaccess;

import com.jme3.scene.Spatial;
import de.ethasia.exorions.javautils.Utils;
import de.ethasia.exorions.usecases.crosslayer.InformationForMapsCouldNotBeLoadedException;
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class Maps {
    
    //<editor-fold defaultstate="collapsed" desc="Static Methods">
    
    public static Document readMapList() {
        DocumentBuilderFactory xmlParserFactory = DocumentBuilderFactory.newInstance();
        
        try {
            String mapListPath = "assets/Maps/MapList.xml";
            
            DocumentBuilder xmlParser = xmlParserFactory.newDocumentBuilder();
            Document mapList = xmlParser.parse(new File(mapListPath));
            
            return mapList;
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
    
    public static Document readMapLogic(String path) {
        return null;
    }
    
    public static Spatial readMapVisuals(String path) {
        return null;
    }
    
    //</editor-fold>
}