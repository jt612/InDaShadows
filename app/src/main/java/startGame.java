import android.util.DisplayMetrics;
import android.view.Display;

import com.jobprogrammers.indashadows.AbstractScene;
import com.jobprogrammers.indashadows.GameScene;
import com.jobprogrammers.indashadows.ResourceManager;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.camera.hud.HUD;
import org.andengine.engine.camera.hud.controls.AnalogOnScreenControl;
import org.andengine.engine.camera.hud.controls.BaseOnScreenControl;
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
    public int countdown = 30;
    String TAG = "startGame";
    HUD mHUD;
    Camera camera;
    AbstractScene scene;
    //Text Variables for score and timer
    private Text score;
    private Text timer;

    @Override
    public EngineOptions onCreateEngineOptions() {
        final Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics displayMetrics;

        CAMERA_WIDTH = display.getWidth();

        CAMERA_HEIGHT = display.getHeight();
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
        this.createController();
        ResourceManager.getInstance().mControl.refreshControlKnobPosition();

        pOnCreateResourcesCallback.onCreateResourcesFinished();
    }

    @Override
    public void onCreateScene(OnCreateSceneCallback pOnCreateSceneCallback) throws IOException {

        scene = new GameScene();
        ResourceManager.getInstance().music.setLooping(true);
        ResourceManager.getInstance().music.setVolume(.15f);
        ResourceManager.getInstance().music.play();


        ResourceManager.getInstance().mControl.refreshControlKnobPosition();
        scene.setChildScene(ResourceManager.getInstance().mControl);
        // play();
        pOnCreateSceneCallback.onCreateSceneFinished(scene);
    }


    @Override
    public void onPopulateScene(Scene pScene, OnPopulateSceneCallback pOnPopulateSceneCallback) throws IOException {

        scene = (AbstractScene) pScene;
        scene.populate();
        mHUD.registerUpdateHandler(new IUpdateHandler() {
            @Override
            public void onUpdate(float pSecondsElapsed) {
                //try {
                //  wait(1000.00);
                //}catch(Exception c){
                //System.exit(0);
                //}
                countdown -= pSecondsElapsed;
                if (countdown - 1 == 0) {
                    //timer.setText("Time: 0");
                    finishActivity(0);
                    //    mHUD.unregisterUpdateHandler(this);
                } else {
                    timer.setText("Time: " + countdown);
                }
            }

            @Override
            public void reset() {


            }
        });
        pOnPopulateSceneCallback.onPopulateSceneFinished();
    }


    public void createController() {

        //values for size of the images
        final float controllerX = ResourceManager.getInstance().controlBaseTextureRegion.getWidth();
        final float controllerY = ResourceManager.getInstance().controlBaseTextureRegion.getHeight();

        ResourceManager.getInstance().mControl = new AnalogOnScreenControl(controllerX, controllerY, camera, ResourceManager.getInstance().controlBaseTextureRegion, ResourceManager.getInstance().controlKnobTextureRegion, 0.1f, ResourceManager.getInstance().engine.getVertexBufferObjectManager(), new AnalogOnScreenControl.IAnalogOnScreenControlListener() {
            @Override
            public void onControlClick(AnalogOnScreenControl pAnalogOnScreenControl) {
                //for now do nothing
                //this is when they click on the control
            }

            @Override
            public void onControlChange(BaseOnScreenControl pBaseOnScreenControl, float pValueX, float pValueY) {
                camera.setCenter(camera.getCenterX() + (pValueX * 10), camera.getCenterY() + (pValueY * 10));
                //   GameManager.getInstance().incrementScore();
                //Not sure why this causes the app to crash
                //probably needs an update handler or something

            }
        });
        ResourceManager.getInstance().mControl.setAnchorCenter(0, 0);
    }

    public void createHUD() {
        mHUD = new HUD();
        ResourceManager.getInstance().camera.setHUD(mHUD);
        timer = new Text(150, 115, ResourceManager.getInstance().font, "Time: 30", new TextOptions(HorizontalAlign.RIGHT), ResourceManager.getInstance().vbom);
        score = new Text(10, 115, ResourceManager.getInstance().font, "Score: 0", new TextOptions(HorizontalAlign.LEFT), ResourceManager.getInstance().vbom);
        score.setAnchorCenter(0, -4);
        timer.setAnchorCenter(-1, -4);
        //ResourceManager.getInstance().mControl.setPosition();
        mHUD.attachChild(score);

        mHUD.attachChild(timer);
        ResourceManager.getInstance().camera.setHUD(mHUD);
    }
}
