package HitDetection;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import java.util.Vector;

public class SprDino extends Sprite {

    Texture txDino, txDeadDino;
    Vector2 vPos, vDir, vGrav;
    private Sprite sprDino;
    boolean bJump;
    float fGround;

    SprDino(Texture _txDino, Texture _txDeadDino) {
        txDino = _txDino;
        txDeadDino = _txDeadDino;
        sprDino = new Sprite(txDino);
        vPos = new Vector2(0, 0);
        vDir = new Vector2(0, 0);
        vGrav = new Vector2(0, 0);
        fGround = 0;
    }
    
    void Gravity(){
        if (bJump) {
            vGrav.set(0, (float) (vGrav.y * 1.1));
        }
        if (vPos.y <= fGround) {
            vDir.set(vDir.x, 0);
            vGrav.set(0, (float) -0.5);
            vPos.set(vPos.x, fGround+1);
            bJump = false;
        }
    }

    void update() {
        vDir.add(vGrav);
        vPos.add(vDir);
        sprDino.setPosition(vPos.x, vPos.y);
        System.out.println("graound"+fGround);
        System.out.println(vPos.y);
    }

    void Animate(Texture _txDinoState) {
        sprDino.setTexture(_txDinoState);
    }

    public Sprite getSprite() {
        return sprDino;
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
