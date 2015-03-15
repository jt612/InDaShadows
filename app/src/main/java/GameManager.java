import com.jobprogrammers.indashadows.hero;

/**
 * Created by jt612 on 1/30/15.
 */
public class GameManager {

    public hero player;
    private static GameManager INSTANCE = new GameManager();
    private int mCurrentScore;

    GameManager() {
        mCurrentScore = 0;
        //when a new game is created reset the current score
    }

    public static GameManager getInstance() {
        return INSTANCE;
    }

    public int getCurrentScore() {
        return mCurrentScore;
    }

    public void incrementScore(){
        mCurrentScore+=1;
    }


    public void decrementScore() {
        mCurrentScore -=1;
    }


}
