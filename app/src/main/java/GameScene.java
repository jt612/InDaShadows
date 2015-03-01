import com.jobprogrammers.indashadows.AbstractScene;
import com.jobprogrammers.indashadows.PlayerFactory;
import com.jobprogrammers.indashadows.hero;
import com.jobprogrammers.indashadows.startGame;

import org.andengine.entity.scene.background.SpriteBackground;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;

/**
 * Created by jt612 on 1/22/15.
 */
public class GameScene extends AbstractScene {


    private hero player;
    private Text score;


    public GameScene() {
        PlayerFactory.getINSTANCE().Create(vbom);
    }

    @Override
    public void populate() {
        createBackground();
        createPlayer();
        createHUD();
        setChaseCamera();
        update();

    }

    private void createHUD() {

    }

    private void update() {
    }


    private void createPlayer() {
        player = PlayerFactory.getINSTANCE().createPlayer(res.playerTextureRegion.getWidth(),
                res.playerTextureRegion.getHeight());
        this.attachChild(player);
    }

    private void setChaseCamera() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }

    private void createBackground() {
        //create a sprite from the background texture region
        Sprite sprite = new Sprite(startGame.CAMERA_WIDTH * 0.5f, startGame.CAMERA_HEIGHT * 0.5f, res.backGroundTextureRegion,
                this.engine.getVertexBufferObjectManager());
        //create a new sprite background with the sprite that was just created
        SpriteBackground spriteBackground = new SpriteBackground(0, 0, 0, sprite);
        this.setBackground(spriteBackground);
        this.setBackgroundEnabled(true);
    }


}
