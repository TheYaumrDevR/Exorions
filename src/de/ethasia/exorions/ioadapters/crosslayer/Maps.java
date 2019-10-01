package de.ethasia.exorions.ioadapters.crosslayer;

import com.jme3.scene.Spatial;
import org.w3c.dom.Document;

public interface Maps {

    public Document readMapList();   
    public Document readMapLogic(String path);
    public Spatial readMapVisuals(String path); 
}