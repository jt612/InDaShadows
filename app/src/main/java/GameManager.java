import org.andengine.engine.camera.hud.HUD;
import org.andengine.entity.text.Text;

/**
 * Created by jt612 on 1/30/15.
 */
public class GameManager {


    private static GameManager INSTANCE;
    Text score;
    HUD mHUD;
    private int mCurrentScore;

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
        this.getInstance().mCurrentScore += 10;
        //score.setText("Score: " + this.getInstance().mCurrentScore);
    }

}
