package de.ethasia.exorions.technical.engine;

import com.jme3.texture.Image;
import com.jme3.texture.Texture;
import com.jme3.texture.Texture2D;
import com.jme3.texture.image.ColorSpace;
import com.jme3.util.BufferUtils;
import java.nio.ByteBuffer;

public class CharacterSpriteAtlasFromPartsBuilder {
    
    //<editor-fold defaultstate="collapsed" desc="Private Fields">
    
    private CharacterSpriteAtlas baseSprites;
    private CharacterSpriteAtlas hairSprites;
    private CharacterSpriteAtlas topSprites;
    private CharacterSpriteAtlas bottomSprites;
    private CharacterSpriteAtlas shoeSprites;
    
    private byte[] baseSpriteData;
    private byte[] hairSpriteData;
    private byte[] topSpritesData;
    private byte[] bottomSpritesData;
    private byte[] shoeSpritesData;
    
    private byte[] combinedSpriteData;
    
    private int createdSpritesWidth;
    private int createdSpritesHeight;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    public CharacterSpriteAtlasFromPartsBuilder withBaseSprites(CharacterSpriteAtlas value) {
        baseSprites = value;
        return this;
    }
    
    public CharacterSpriteAtlasFromPartsBuilder withHairSprites(CharacterSpriteAtlas value) {
        hairSprites = value;
        return this;
    }    
    
    public CharacterSpriteAtlasFromPartsBuilder withTopSprites(CharacterSpriteAtlas value) {
        topSprites = value;
        return this;
    }

    public CharacterSpriteAtlasFromPartsBuilder withBottomSprites(CharacterSpriteAtlas value) {
        bottomSprites = value;
        return this;
    }
    
    public CharacterSpriteAtlasFromPartsBuilder withShoeSprites(CharacterSpriteAtlas value) {
        shoeSprites = value;
        return this;
    }     
    
    public CharacterSpriteAtlas build() {
        return extractDataFromSpriteImages();
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Helper Methods">
    
    private CharacterSpriteAtlas extractDataFromSpriteImages() {
        CharacterSpriteAtlas.Builder resultBuilder = new CharacterSpriteAtlas.Builder();
        
        for (int i = 0; i < CharacterSpriteAtlas.NUMBER_OF_ANIMATION_FRAMES_PER_CHARACTER; i++) {
            extractBaseSpriteByteData(i);
            extractShoeSpriteByteData(i);
            extractBottomSpriteByteData(i);
            extractTopSpriteByteData(i);
            extractHairSpriteByteData(i);
            
            resultBuilder.setSpriteOn(combineSubImagesOnIndexIntoSprite(), i);
        }
        
        return resultBuilder.build();
    }
    
    private void extractBaseSpriteByteData(int imageIndexInAtlas) {
        combinedSpriteData = new byte[4];
        baseSpriteData = new byte[4];
        createdSpritesWidth = 1;
        createdSpritesHeight = 1;
        
        if (null != baseSprites) {
            Texture baseSprite = baseSprites.getSpriteOn(imageIndexInAtlas);
            
            boolean baseSpriteImageExists = baseSprite != null && baseSprite.getImage() != null;
            if (baseSpriteImageExists) {
                baseSprite.getImage().getData().get(0).rewind();
                
                if (baseSprite.getImage().getData().get(0).hasRemaining()) {
                    createdSpritesWidth = baseSprite.getImage().getWidth();
                    createdSpritesHeight = baseSprite.getImage().getHeight();
            
                    combinedSpriteData = new byte[createdSpritesWidth * createdSpritesHeight * 4];                
                
                    baseSpriteData = new byte[createdSpritesWidth * createdSpritesHeight * 4];
                    baseSprite.getImage().getData().get(0).get(baseSpriteData);   
                }                
            }
        }
    }

    private void extractShoeSpriteByteData(int imageIndexInAtlas) {
        if (null != shoeSprites) {
            Texture shoeSprite = shoeSprites.getSpriteOn(imageIndexInAtlas);
            shoeSpritesData = extractSpriteByteDataFrom(shoeSprite);
        }        
    }    
    
    private void extractBottomSpriteByteData(int imageIndexInAtlas) {
        if (null != bottomSprites) {
            Texture bottomSprite = bottomSprites.getSpriteOn(imageIndexInAtlas);
            bottomSpritesData = extractSpriteByteDataFrom(bottomSprite);
        }         
    }    

    private void extractTopSpriteByteData(int imageIndexInAtlas) {
        if (null != topSprites) {
            Texture topSprite = topSprites.getSpriteOn(imageIndexInAtlas);
            topSpritesData = extractSpriteByteDataFrom(topSprite);
        }        
    } 
    
    private void extractHairSpriteByteData(int imageIndexInAtlas) {
        if (null != hairSprites) {
            Texture hairSprite = hairSprites.getSpriteOn(imageIndexInAtlas);
            hairSpriteData = extractSpriteByteDataFrom(hairSprite);
        }        
    }
    
    private byte[] extractSpriteByteDataFrom(Texture sprite) {
        byte[] result = new byte[createdSpritesWidth * createdSpritesHeight * 4];
        
        boolean spriteImageExists = sprite != null && sprite.getImage() != null;
        if (spriteImageExists) {
            sprite.getImage().getData().get(0).rewind();
            
            if (sprite.getImage().getData().get(0).hasRemaining()) {
                int spriteWidth = sprite.getImage().getWidth();
                int spriteHeight = sprite.getImage().getHeight();
                
                if (spriteWidthAndHeightAreMatchingBaseSprite(spriteWidth, spriteHeight)) {
                    sprite.getImage().getData().get(0).get(result); 
                }
            }            
        }

        return result;
    }
    
    private boolean spriteWidthAndHeightAreMatchingBaseSprite(int width, int height) {
        return width == createdSpritesWidth && height == createdSpritesHeight;
    }
    
    private Texture combineSubImagesOnIndexIntoSprite() {
        for (int i = 0; i < createdSpritesHeight; i++) {
            for (int j = 0; j < createdSpritesWidth; j++) {
                int pixelIndex = (j + i * createdSpritesWidth) * 4;
                
                if (baseSpriteData[pixelIndex] != 0) {
                    combinedSpriteData[pixelIndex] = baseSpriteData[pixelIndex + 3];
                    combinedSpriteData[pixelIndex + 1] = baseSpriteData[pixelIndex + 2];
                    combinedSpriteData[pixelIndex + 2] = baseSpriteData[pixelIndex + 1];
                    combinedSpriteData[pixelIndex + 3] = baseSpriteData[pixelIndex];                    
                }                
                
                if (shoeSpritesData[pixelIndex] != 0) {
                    combinedSpriteData[pixelIndex] = shoeSpritesData[pixelIndex + 3];
                    combinedSpriteData[pixelIndex + 1] = shoeSpritesData[pixelIndex + 2];
                    combinedSpriteData[pixelIndex + 2] = shoeSpritesData[pixelIndex + 1];
                    combinedSpriteData[pixelIndex + 3] = shoeSpritesData[pixelIndex];                    
                }
                
                if (bottomSpritesData[pixelIndex] != 0) {
                    combinedSpriteData[pixelIndex] = bottomSpritesData[pixelIndex + 3];
                    combinedSpriteData[pixelIndex + 1] = bottomSpritesData[pixelIndex + 2];
                    combinedSpriteData[pixelIndex + 2] = bottomSpritesData[pixelIndex + 1];
                    combinedSpriteData[pixelIndex + 3] = bottomSpritesData[pixelIndex];                     
                }
                
                if (topSpritesData[pixelIndex] != 0) {
                    combinedSpriteData[pixelIndex] = topSpritesData[pixelIndex + 3];
                    combinedSpriteData[pixelIndex + 1] = topSpritesData[pixelIndex + 2];
                    combinedSpriteData[pixelIndex + 2] = topSpritesData[pixelIndex + 1];
                    combinedSpriteData[pixelIndex + 3] = topSpritesData[pixelIndex];                    
                }
                
                if (hairSpriteData[pixelIndex] != 0) {
                    combinedSpriteData[pixelIndex] = hairSpriteData[pixelIndex + 3];
                    combinedSpriteData[pixelIndex + 1] = hairSpriteData[pixelIndex + 2];
                    combinedSpriteData[pixelIndex + 2] = hairSpriteData[pixelIndex + 1];
                    combinedSpriteData[pixelIndex + 3] = hairSpriteData[pixelIndex];                    
                }                 
            }
        }
        
        return convertCurrentCombinedSpriteDataToImage();
    }
    
    private Texture convertCurrentCombinedSpriteDataToImage() {
        ByteBuffer buffer = BufferUtils.createByteBuffer(combinedSpriteData);
        Image spriteImage = new Image(Image.Format.RGBA8, createdSpritesWidth, createdSpritesHeight, buffer, ColorSpace.Linear);
        
        Texture combinedTexture = new Texture2D(spriteImage);
        combinedTexture.setMagFilter(Texture.MagFilter.Nearest);
        
        return combinedTexture;
    }
    
    //</editor-fold>
}