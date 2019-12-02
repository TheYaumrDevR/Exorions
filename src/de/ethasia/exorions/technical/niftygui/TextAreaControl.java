package de.ethasia.exorions.technical.niftygui;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.controls.AbstractController;
import de.lessvoid.nifty.controls.Parameters;
import de.lessvoid.nifty.controls.ScrollPanel;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.elements.render.TextRenderer;
import de.lessvoid.nifty.elements.tools.TextBreak;
import de.lessvoid.nifty.input.NiftyInputEvent;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.spi.render.RenderFont;
import de.lessvoid.nifty.tools.SizeValue;
import java.util.ArrayList;
import java.util.List;

public class TextAreaControl extends AbstractController implements TextArea {
    
    //<editor-fold defaultstate="collapsed" desc="String Constants">
    
    private static final String TEXT_PANEL_NAME = "#textPanel";    
    private static final String TEXT_NAME = "#areaText";
    private static final String SCROLL_PANEL_NAME = "#textScrollPanel";
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Private Fields">
    
    private Element textPanel;
    private Element outerPanel;
    private Element text;
    private ScrollPanel scrollPanel;
    private TextRenderer textRenderer;
    private int originalHeight;
    private Parameters params;
    private boolean autoScroll;
    private boolean isScrollable;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="AbstractController Overrides">
    
    @Override
    public void bind(Nifty nifty, Screen screen, Element element, Parameters params) {
        super.bind(element);
        
        textPanel = element.findElementById(TEXT_PANEL_NAME);
        outerPanel = element;
        text = element.findElementById(TEXT_NAME);
        scrollPanel = element.findNiftyControl(SCROLL_PANEL_NAME, ScrollPanel.class);
        textRenderer = text.getRenderer(TextRenderer.class);        
        
        textRenderer.setxOffsetHack(1);
        textRenderer.setLineWrapping(true);

        scrollPanel.setStepSizeY(12);
        scrollPanel.setPageSizeY(50);

        autoScroll = true;

        originalHeight = scrollPanel.getHeight();        
        
        isScrollable = params.getAsBoolean("scroll", true);
        this.params = params;
    }

    @Override
    public void onStartScreen() {
        if (textRenderer.getOriginalText().isEmpty()) {
            appendLine(params.getWithDefault("text", ""));
        }
    }

    @Override
    public boolean inputEvent(NiftyInputEvent nie) {
        return false;
    }    
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="TextArea Overrides">
    
    @Override
    public void appendLine(String text) {
        String[] wrappedText = wrapText(text.split("\n", -1));
        int oldHeight = textRenderer.getTextHeight();    
        
        addWrappedTextToTextArea(wrappedText);
        expandScrollHeightOrAreaHeightDependingOnSetting(oldHeight);
    }

    @Override
    public void setAutoScroll(boolean value) {
        autoScroll = value;        
    }

    @Override
    public void clearText() {
       textRenderer.setText("");

       textPanel.setConstraintHeight(new SizeValue(originalHeight + "px"));
       scrollPanel.getElement().layoutElements();

       if (!isScrollable) {
           outerPanel.setConstraintHeight(new SizeValue(originalHeight + "px"));
           outerPanel.getParent().layoutElements();
       }        
    }    
    
    //</editor-fold>    
    
    //<editor-fold defaultstate="collapsed" desc="Helper Methods">
    
    private String[] wrapText(final String[] textLines) {
        RenderFont font = textRenderer.getFont();

        List <String> lines = new ArrayList <> ();
        for (String line : textLines) {
            int lineLengthInPixel = font.getWidth(line);
            if (lineLengthInPixel > text.getWidth()) {
                lines.addAll(new TextBreak(line, text.getWidth(), font).split());
            } else {
                lines.add(line);
            }
        }
        return lines.toArray(new String[0]);
    }     
    
    private void addWrappedTextToTextArea(String[] wrappedText) {
        for (String line : wrappedText) {
            String originalText = textRenderer.getOriginalText();

            if (!originalText.isEmpty()) {
                textRenderer.setText(originalText + "\n" + line);
            } else {
                textRenderer.setText(line);
            }
        }        
    }

    private void expandScrollHeightOrAreaHeightDependingOnSetting(int oldTextRendererHeight) {
        if (textPanel.getHeight() < textRenderer.getTextHeight()) {
            if (isScrollable) {
                textPanel.setConstraintHeight(new SizeValue(textRenderer.getTextHeight() + "px"));
                scrollPanel.getElement().layoutElements();

                if (autoScroll && (scrollPanel.getVerticalPos() == oldTextRendererHeight - originalHeight || oldTextRendererHeight - originalHeight < 0)) {
                    scrollPanel.setVerticalPos(textRenderer.getTextHeight());
                }   
            } else {
                outerPanel.setConstraintHeight(new SizeValue(textRenderer.getTextHeight() + "px"));
                outerPanel.getParent().layoutElements();
            }           
        }    
        
        scrollPanel.getElement().layoutElements();        
    }
    
    //</editor-fold>
}