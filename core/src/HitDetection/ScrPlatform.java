//https://github.com/Mrgfhci/Drop
package HitDetection;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import java.util.Iterator;

public class ScrPlatform implements Screen, InputProcessor {

    Game game;
    SpriteBatch batch;
    Texture txDeadDino, txDino, txPlat;
    SprDino sprDino;
    SprPlatform sprPlatform;
    private Array<SprPlatform> arsprPlatform;
    int nHitPlatform = 0;

    public ScrPlatform(Game _game) {
        game = _game;
        batch = new SpriteBatch();
        txDino = new Texture("Dinosaur.png");
        txDeadDino = new Texture("dead.png");
        txPlat = new Texture("Platform.png");
        Gdx.input.setInputProcessor((this));
        Gdx.graphics.setDisplayMode(600, 400, false);
        sprDino = new SprDino(txDino, txDeadDino);
        sprPlatform = new SprPlatform(txPlat);
        arsprPlatform = new Array<SprPlatform>();
        arsprPlatform.add(sprPlatform);
    }

    @Override
    public void show() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void render(float f) {
        Gdx.gl.glClearColor(1, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        for (SprPlatform sprPlatform : arsprPlatform) {
            sprPlatform.update();
        }
        HitDetection();
        sprDino.gravity();
        sprDino.update();
        batch.begin();
        batch.draw(sprDino.getSprite(), sprDino.getX(), sprDino.getY());
        for (SprPlatform sprPlatform : arsprPlatform) {
            batch.draw(sprPlatform.getSprite(), sprPlatform.getX(), sprPlatform.getY());
        }
        SpawnPlatform();
        batch.end();

    }

    void SpawnPlatform() {
        Iterator<SprPlatform> iter = arsprPlatform.iterator();
        while (iter.hasNext()) {
            SprPlatform sprPlatform = iter.next();
            if (sprPlatform.canSpawn() && (arsprPlatform.size < 2)) {
                sprPlatform = new SprPlatform(txPlat);
                arsprPlatform.add(sprPlatform);
            }
            if (sprPlatform.isOffScreen()) {
                iter.remove();
            }
        }
    }

    void HitDetection() {
        if (nHitPlatform() == 0) {
            System.out.println("NO HIT");
            sprDino.fGround = 0;
            sprDino.bGrav = true;
            if(sprDino.bJump == false){
            sprDino.vGrav.set(0, (float) -0.4);
            }
        } else if (nHitPlatform() == 1) {
            System.out.println("dead");
        } else if (nHitPlatform() == 2) {
            sprDino.fGround = sprPlatform.vPrevPos.y + sprPlatform.getSprite().getHeight() - 1;
            sprDino.vPos.y = sprDino.fGround;
            sprDino.bGrav = false;
            System.out.println("land");
        }
    }

    int nHitPlatform() {
        Iterator<SprPlatform> iter = arsprPlatform.iterator();
        while (iter.hasNext()) {
            SprPlatform sprPlatform = iter.next();
            System.out.println("prev dino" + sprDino.vPrevPos.y);
            System.out.println("current dino"+sprDino.vPos.y);
            System.out.println(sprPlatform.vPrevPos.y + sprPlatform.getSprite().getHeight());
            if (sprDino.getSprite().getBoundingRectangle().overlaps(sprPlatform.getSprite().getBoundingRectangle())) {
                System.out.println("OVERLAPS");
                if (sprDino.vPrevPos.y >= (sprPlatform.vPrevPos.y + sprPlatform.getSprite().getHeight())) {
                    return 2;
                } else if (sprDino.bGrav) {
                    return 1;
                } else if(sprDino.vPos.y == sprPlatform.vPrevPos.y + sprPlatform.getSprite().getHeight() - 1){
                    return 2;
                }
            } 
        }
        return 0;
    }

    @Override
    public void resize(int i, int i1) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void pause() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void resume() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void hide() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void dispose() {
        sprPlatform.getSprite().getTexture().dispose();
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.SPACE && sprDino.bGrav == false) {
            sprDino.vPos.add(0, 1);
            sprDino.vDir.set((float) sprDino.vDir.x, 25);
            sprDino.vGrav.set(0, (float) -0.4);
            sprDino.bGrav = true;
            sprDino.bJump = true;
        } else if (keycode == Input.Keys.A) {
            sprDino.vDir.set(-10, (float) sprDino.vDir.y);

        } else if (keycode == Input.Keys.D) {
            sprDino.vDir.set(10, (float) sprDino.vDir.y);
        } else if (keycode == Input.Keys.E) {
            System.exit(3);
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.A) {
            sprDino.vDir.set(0, (float) sprDino.vDir.y);
        } else if (keycode == Input.Keys.D) {
            sprDino.vDir.set(0, (float) sprDino.vDir.y);
        }
        return false;
    }

    @Override
    public boolean keyTyped(char c) {
        return false;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean touchDown(int i, int i1, int i2, int i3) {
        return false;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean touchUp(int i, int i1, int i2, int i3) {
        return false;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean touchDragged(int i, int i1, int i2) {
        return false;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean mouseMoved(int i, int i1) {
        return false;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean scrolled(int i) {
        return false;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
