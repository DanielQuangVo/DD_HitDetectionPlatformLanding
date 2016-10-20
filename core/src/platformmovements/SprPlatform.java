package platformmovements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class SprPlatform extends Sprite {

    private float fX, fY, fVx, fVy;
    String sFile;
    Texture txImg;
    private Sprite sprImg;

    public SprPlatform(String _sFile, float _fX, float _fY) {
        sFile = _sFile;
        fX = _fX;
        fY = _fY;
        System.out.print(fY);
        txImg = new Texture(sFile);
        sprImg = new Sprite(txImg);
    }

    void update() {
        fX--;

        if (fX <= (0 - sprImg.getWidth())) {
            fX = 700;
        }
    }

    void setY(int _nDinoHei) {
        for(int i =0; i < 2; i++){
        fY = (((Gdx.graphics.getHeight() - _nDinoHei) / 3) * ((int) (Math.random() * 2 + 1)));
        }
    }

    public Sprite getSprite() {
        return sprImg;
    }

    //@Override
    public float getX() {
        return fX;
    }

    //@Override
    public float getY() {
        return fY;
    }
}
