package HitDetection;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class ScrPlatform implements Screen, InputProcessor {

    Game game;
    SpriteBatch batch;
    Texture txDeadDino, txDino, txPlat;
    SprDino sprDino;
    SprPlatform sprPlatform;

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
    }

    @Override
    public void show() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void render(float f) {
        Gdx.gl.glClearColor(1, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        sprPlatform.update();
        isHitPlatform();
        batch.begin();
        batch.draw(sprDino.getSprite(), sprDino.getX(), sprDino.getY());
        batch.draw(sprPlatform.getSprite(), sprPlatform.getX(), sprPlatform.getY());
        batch.end();
    }
  

    void isHitPlatform() {
        if (sprDino.getSprite().getBoundingRectangle().overlaps(sprPlatform.getSprite().getBoundingRectangle())) {
            sprDino.update(txDeadDino);
        } else {
            sprDino.update(txDino);
        }
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
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.SPACE && sprDino.bJump == false) {
            sprDino.vDir.set((float) sprDino.vDir.x, 25);
            sprDino.vGrav.set(0, (float) -0.5);
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
