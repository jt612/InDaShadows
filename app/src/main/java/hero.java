import android.graphics.Bitmap;

import com.jobprogrammers.indashadows.startGame;

import org.andengine.entity.sprite.TiledSprite;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import java.util.ArrayList;

/**
 * Created by jt612 on 10/20/14.
 */
public class hero extends TiledSprite {
    protected int xPos, yPos;
    boolean dead = false;
    private ArrayList <Bitmap> mBitmaps;
    private int currentIndex = 0;
    private Bitmap currentFrame;
    private int animationTime;
    private long frameTicker = (long)-01;


    public hero(float pX, float pY, ITiledTextureRegion pTiledTextureRegion, VertexBufferObjectManager pVbom) {
        super(pX, pY, pTiledTextureRegion, pVbom);
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public void turnLeft() {
        setFlippedHorizontal(true);
    }

    public void turnRight() {
        setFlippedHorizontal(false);
    }

    public void jump() {

    }

    public void attack() {

    }

    public void die() {

    }

    public int getXPosition() {
        return xPos;
    }

    public void moveRight(){
        turnRight();
        this.setPosition(this.mX + 5, this.mY);
    }

    public void moveLeft(){
        turnLeft();
        this.setPosition(this.mX - 5, this.mY);
        startGame.camera.updateChaseEntity();
    }

    public int getYPosition() {
        return yPos;
    }

}
