package platformmovements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class sprPlatform extends Sprite {

    String sFile;
    Texture txImg;
    private Sprite sprImg;
    Vector2 vPos, vDir;

    public sprPlatform(String _sFile, Vector2 vPlatPos, Vector2 vPlatDir) {
        sFile = _sFile;
        txImg = new Texture(sFile);
        sprImg = new Sprite(txImg);
        vPos = vPlatPos;
        vDir = vPlatDir;
    }

    void update() {
        
    }

    public Sprite getSprite() {
        return sprImg;
    }

    //@Override
    public float getX() {
        return vPos.x;
    }

    //@Override
    public float getY() {
        return vPos.y;
    }
}
