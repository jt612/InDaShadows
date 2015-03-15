import com.jobprogrammers.indashadows.AbstractScene;
import com.jobprogrammers.indashadows.GameManager;
import com.jobprogrammers.indashadows.PlayerFactory;
import com.jobprogrammers.indashadows.startGame;

import org.andengine.entity.scene.background.RepeatingSpriteBackground;
import org.andengine.entity.text.Text;

/**
 * Created by jt612 on 1/22/15.
 */
public class GameScene extends AbstractScene {

   // private hero player;
    private Text score;
    private Text timer;

    public GameScene() {
        PlayerFactory.getINSTANCE().Create(vbom);
    }

    @Override
    public void populate() {
        createBackground();
        createPlayer();
       // this.camera.setChaseEntity(GameManager.getInstance().player);
        //trying to figure out how to make our camera chase the player might need to use the smooth camera
        //instead of the regular Camera class
        //if the setchaseEntity line is uncommented the player will be drawn in the middle

    }



    private void createPlayer() {
      GameManager.getInstance().player = PlayerFactory.getINSTANCE().createPlayer(10, 45);
      this.attachChild(GameManager.getInstance().player);
    }




    @Override
    public void onPause() {
      this.onPause();
    }

    @Override
    public void onResume() {
        this.onResume();
    }

    private void createBackground() {
        //create a sprite from the background texture region
       // Sprite sprite = new Sprite(startGame.CAMERA_WIDTH , startGame.CAMERA_HEIGHT , res.backGroundTextureRegion,
                //this.engine.getVertexBufferObjectManager());
        //create a new sprite background with the sprite that was just created
        RepeatingSpriteBackground spriteBackground = new RepeatingSpriteBackground(startGame.CAMERA_WIDTH, startGame.CAMERA_HEIGHT - 50, res.backGroundTextureRegion, this.engine.getVertexBufferObjectManager());
        this.setBackground(spriteBackground);
        this.setBackgroundEnabled(true);
    }


}
