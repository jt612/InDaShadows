import org.andengine.entity.sprite.TiledSprite;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

/**
 * Created by jt612 on 10/20/14.
 */
public class hero extends TiledSprite {
    boolean dead = false;

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
        setCurrentTileIndex(0);
    }

    public void attack() {
        setCurrentTileIndex(1);
    }

    public void die() {
        setCurrentTileIndex(2);
    }


}
