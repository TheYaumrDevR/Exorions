package de.ethasia.exorions.technical;

import de.ethasia.exorions.ioadapters.crosslayer.Maps;
import de.ethasia.exorions.ioadapters.crosslayer.TechnicalsFactory;
import de.ethasia.exorions.technical.fileaccess.MapsImpl;

public class RealTechnicalsFactory extends TechnicalsFactory {

    //<editor-fold defaultstate="collapsed" desc="TechnicalsFactory Overrides">
    
    @Override
    public Maps createMaps() {
        return new MapsImpl();
    }    
    
    //</editor-fold>    
}