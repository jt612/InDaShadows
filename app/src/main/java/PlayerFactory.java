import com.jobprogrammers.indashadows.ResourceManager;
import com.jobprogrammers.indashadows.hero;

import org.andengine.opengl.vbo.VertexBufferObjectManager;

/**
 * Created by jt612 on 1/26/15.
 */
public class PlayerFactory {

    private static PlayerFactory INSTANCE = new PlayerFactory();
    private static VertexBufferObjectManager vbom;

    private PlayerFactory() {

    }

    public static PlayerFactory getINSTANCE() {
        return INSTANCE;
    }

    public void Create(VertexBufferObjectManager vbom) {
        this.vbom = vbom;
    }

    public hero createPlayer(float x, float y) {
        hero player = new hero(x, y, ResourceManager.getInstance().playerTextureRegion, vbom);
        player.setZIndex(2);
        return player;
    }
}
