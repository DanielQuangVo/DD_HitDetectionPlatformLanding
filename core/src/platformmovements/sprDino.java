package platformmovements;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import java.util.Vector;

public class sprDino {

    //String sFile, sFile2;
    Texture txDino, txDead;
    Vector2 vPos, vDir, vGrav, vPlatPos;
    private Sprite sprImg;
    boolean bJump;

    public sprDino(Texture txDinoAlive, Texture txDeadDino, Vector2 vDinoPos, Vector2 vDinoDir, Vector2 vDinoGrav, Vector2 vPlatformPos) {
        txDino = txDinoAlive;
        txDead = txDeadDino;
        sprImg = new Sprite(txDead);
        vPos = vDinoPos;
        vDir = vDinoDir;
        vGrav = vDinoGrav;
        vPlatPos = vPlatformPos;
    }

    void update() {
        if (bJump) {

            vGrav.set(0, (float) (vGrav.y * 1.1));
        }
        if (vPos.y < 0) {
            vDir.set(vDir.x, 0);
            vGrav.set(0, 0);
            vPos.set(vPos.x, 0);
            bJump = false;
        }
        vDir.add(vGrav);
        vPos.add(vDir);
        sprImg.setPosition(vPos.x, vPos.y);
    }

    void isHitPlatform(Sprite sprPlatform) {
        if (sprImg.getBoundingRectangle().overlaps(sprPlatform.getBoundingRectangle())) {
            sprImg.setTexture(txDead);
        } else {
            sprImg.setTexture(txDino);
        }
    }

    public Sprite getSprite() {
        return sprImg;
    }

    //@Override
    public float getX() {
        return (float) vPos.x;
    }

    //@Override
    public float getY() {
        return (float) vPos.y;
    }
}
