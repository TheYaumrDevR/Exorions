package de.ethasia.exorions.fileaccess;

import com.jme3.asset.AssetManager;
import com.jme3.scene.Spatial;
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import de.ethasia.exorions.javautils.Utils;
import de.ethasia.exorions.usecases.crosslayer.InformationForMapsCouldNotBeLoadedException;
import de.ethasia.exorions.usecases.crosslayer.MapDataCouldNotBeLoadedException;

public class Maps {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private static AssetManager assetManager;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Setters">
    
    public static void setAssetManager(AssetManager value) {
        assetManager = value;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Static Methods">
    
    public static Document readMapList() {  
        try {
            String mapListPath = "assets/Maps/MapList.xml";
            return loadDocument(mapListPath);
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
        try {       
            return loadDocument(path);
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
    
    public static Spatial readMapVisuals(String path) {
        Spatial result = assetManager.loadModel(path);
        return result;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Helper Methods">
    
    private static Document loadDocument(String filePath) throws SAXException, IOException, ParserConfigurationException {
        DocumentBuilderFactory xmlParserFactory = DocumentBuilderFactory.newInstance();
        
        DocumentBuilder xmlParser = xmlParserFactory.newDocumentBuilder();
        Document document = xmlParser.parse(new File(filePath));
            
        return document;        
    }
    
    //</editor-fold>
}