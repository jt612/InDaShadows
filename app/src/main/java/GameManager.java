/**
 * Created by jt612 on 1/30/15.
 */
public class GameManager {


    private static GameManager INSTANCE;
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


}
