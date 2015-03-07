import org.andengine.engine.camera.hud.HUD;
import org.andengine.entity.text.Text;

/**
 * Created by jt612 on 1/30/15.
 */
public class GameManager {


    private static GameManager INSTANCE;
    public int mCurrentScore;
    Text score;
    HUD mHUD;

    GameManager() {
        mCurrentScore = 0;
        //when a new game is created reset the current score
    }

    public static GameManager getInstance() {
        return INSTANCE;
    }

    public int getCurrentScore() {
        return this.mCurrentScore;
    }


    public void incrementScore() {
        GameManager.getInstance().mCurrentScore += 5;
        score.setText("Score: " + GameManager.getInstance().mCurrentScore);
    }

}
