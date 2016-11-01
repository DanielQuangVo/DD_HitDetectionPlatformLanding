package HitDetection;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class SprPlatform extends Sprite {
    String sFile;
    Texture txPlat;
    private Sprite sprPlat;
    Vector2 vPos, vDir;

    SprPlatform(Texture _txPlat) {
        txPlat = _txPlat;
        sprPlat = new Sprite(txPlat);
        vPos = new Vector2(200,200);
        vDir = new Vector2(0,0);
    }


    void update() {
        sprPlat.setPosition(vPos.x, vPos.y);
    }

    public Sprite getSprite() {
        return sprPlat;
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
