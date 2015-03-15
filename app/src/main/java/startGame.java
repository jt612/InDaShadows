import android.graphics.Point;
import android.os.Build;
import android.view.Display;
import android.view.WindowManager;

import com.jobprogrammers.indashadows.AbstractScene;
import com.jobprogrammers.indashadows.ButtonLeft;
import com.jobprogrammers.indashadows.ButtonRight;
import com.jobprogrammers.indashadows.GameManager;
import com.jobprogrammers.indashadows.GameScene;
import com.jobprogrammers.indashadows.ResourceManager;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.camera.hud.HUD;
import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.WakeLockOptions;
import org.andengine.engine.options.resolutionpolicy.FillResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.ui.activity.BaseGameActivity;
import org.andengine.util.adt.align.HorizontalAlign;


import java.io.IOException;

/**
 * Created by jt612 on 10/6/14
 */
public class startGame extends BaseGameActivity {

    public static int CAMERA_WIDTH;
    public static int CAMERA_HEIGHT;
    public int countdown = 1000;//3000 the 1000 is just a test value
    //the countdown is divided by 100 so that it runs as if the time limit is 10 seconds
    String TAG = "startGame";
    HUD mHUD;
    public static Camera camera;
    AbstractScene scene;
    //Text Variables for score and timer
    private Text score;
    private Text timer;
    public ButtonLeft leftButton;
    public ButtonRight rightButton;


    @Override
    public EngineOptions onCreateEngineOptions() {

        CAMERA_WIDTH = 0;
        CAMERA_HEIGHT = 0;
        Point size = new Point();
        WindowManager w = getWindowManager();
     //   player =
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
            w.getDefaultDisplay().getSize(size);
            CAMERA_WIDTH = size.x;
            CAMERA_HEIGHT = size.y;
        }else{
            Display d = w.getDefaultDisplay();
            CAMERA_WIDTH = d.getWidth();
            CAMERA_HEIGHT = d.getHeight();
        }

        camera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
        EngineOptions engineOptions = new EngineOptions(true, ScreenOrientation.LANDSCAPE_SENSOR, new FillResolutionPolicy(), camera);
        engineOptions.getAudioOptions().setNeedsMusic(true).setNeedsSound(true);
        engineOptions.setWakeLockOptions(WakeLockOptions.SCREEN_ON);
        engineOptions.getRenderOptions().setDithering(true);
        return engineOptions;
    }

    @Override
    public void onCreateResources(OnCreateResourcesCallback pOnCreateResourcesCallback) throws IOException {
        ResourceManager.getInstance().create(this, this.mEngine, this.camera, this.getVertexBufferObjectManager());
        ResourceManager.getInstance().loadFont();
        ResourceManager.getInstance().loadGameGraphics();

        ResourceManager.getInstance().loadGameAudio();
        this.createHUD();



        pOnCreateResourcesCallback.onCreateResourcesFinished();
    }

    @Override
    public void onCreateScene(OnCreateSceneCallback pOnCreateSceneCallback) throws IOException {

        scene = new GameScene();
        ResourceManager.getInstance().music.setLooping(true);
        ResourceManager.getInstance().music.setVolume(.15f);
        ResourceManager.getInstance().music.play();





        pOnCreateSceneCallback.onCreateSceneFinished(scene);
    }


    @Override
    public void onPopulateScene(Scene pScene, OnPopulateSceneCallback pOnPopulateSceneCallback) throws IOException {

        scene = (AbstractScene) pScene;
        scene.populate();
        mHUD.registerUpdateHandler(new IUpdateHandler() {
            @Override
            public void onUpdate(float pSecondsElapsed) {
                //Andengine doesn't have a main game loop but we can use this onUpdate method
                //as basically the same thing as a game loop
                //So in short this is the game loop.
                countdown -= pSecondsElapsed;
                if (countdown == 0) {

                    mHUD.unregisterUpdateHandler(this);
                    Text Gover = new Text(CAMERA_WIDTH / 2, CAMERA_HEIGHT / 2, ResourceManager.getInstance().GameOverFont, "GAME OVER!", new TextOptions(HorizontalAlign.CENTER), ResourceManager.getInstance().vbom);

                    Gover.setText("GAME OVER!");
                    Gover.setVisible(true);
                    ResourceManager.getInstance().music.stop();
                    ResourceManager.getInstance().music.release();
                    ResourceManager.getInstance().gameOver.play();
                    mHUD.attachChild(Gover);
                    scene.reset();
                    this.reset();

                } else {

                    timer.setText("Time: " + countdown / 100);

                    score.setText("Score: " + GameManager.getInstance().getCurrentScore());


                }
            }

            @Override
            public void reset() {


            }
        });
        pOnPopulateSceneCallback.onPopulateSceneFinished();
    }




    public void createHUD() {
        mHUD = new HUD();
        leftButton = new ButtonLeft(CAMERA_WIDTH/2-200, CAMERA_HEIGHT -  20, ResourceManager.getInstance().rightB, getVertexBufferObjectManager());
        rightButton = new ButtonRight(CAMERA_WIDTH/2+200, CAMERA_HEIGHT - 20, ResourceManager.getInstance().leftB, getVertexBufferObjectManager());
        ResourceManager.getInstance().camera.setHUD(mHUD);
        timer = new Text(CAMERA_WIDTH/2 + 100, CAMERA_HEIGHT - 15, ResourceManager.getInstance().font, "Time: 30", new TextOptions(HorizontalAlign.RIGHT), ResourceManager.getInstance().vbom);
        score = new Text(CAMERA_WIDTH/2 -70, CAMERA_HEIGHT -15, ResourceManager.getInstance().font, "Score: 0000", new TextOptions(HorizontalAlign.LEFT), ResourceManager.getInstance().vbom);
        //score.setAnchorCenter(0, -4);
        //timer.setAnchorCenter(-2, -4);




        //Registers the buttons as touchable
        mHUD.registerTouchArea(leftButton);
        mHUD.registerTouchArea(rightButton);


        //this attaches stuff to the HUD
        mHUD.attachChild(this.leftButton);
        mHUD.attachChild(this.rightButton);
        mHUD.attachChild(score);
        mHUD.attachChild(timer);


        ResourceManager.getInstance().camera.setHUD(mHUD);
    }
}
