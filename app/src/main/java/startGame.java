import com.jobprogrammers.indashadows.GameScene;

import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.WakeLockOptions;
import org.andengine.engine.options.resolutionpolicy.FillResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.ui.activity.BaseGameActivity;
import org.andengine.util.debug.Debug;

import java.io.IOException;

/**
 * Created by jt612 on 10/6/14
 */
public class startGame extends BaseGameActivity {

    public static int CAMERA_WIDTH = 800;
    public static int CAMERA_HEIGHT = 400;
    String TAG = "startGame";
    //final DisplayMetrics dm = new DisplayMetrics();
    //this.dm.
    Camera camera;
    Engine engine;
    VertexBufferObjectManager vbom;

    @Override
    public EngineOptions onCreateEngineOptions() {
        camera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
        EngineOptions engineOptions = new EngineOptions(true, ScreenOrientation.LANDSCAPE_FIXED, new FillResolutionPolicy(), camera);
        engineOptions.getAudioOptions().setNeedsMusic(true).setNeedsSound(true);
        engineOptions.setWakeLockOptions(WakeLockOptions.SCREEN_ON);
        engineOptions.getRenderOptions().setDithering(true);
        Debug.i("Engine Configured. So far more progress than I've made in the past couple of months!");
        return engineOptions;
    }

    @Override
    public void onCreateResources(OnCreateResourcesCallback pOnCreateResourcesCallback) throws IOException {
        // ResourceManager.getInstance().create(this, engine, camera, vbom);
        //gives an error now because of the file structure change
        //going to see if I can get it to push and keep the original file structure
        ResourceManager.getInstance().loadFont();
        ResourceManager.getInstance().loadGameGraphics();

        ResourceManager.getInstance().loadGameAudio();
        ResourceManager.getInstance().createHUD();
        ResourceManager.getInstance().createController();


        pOnCreateResourcesCallback.onCreateResourcesFinished();
    }

    @Override
    public void onCreateScene(OnCreateSceneCallback pOnCreateSceneCallback) throws IOException {
        Scene scene;
        scene = new GameScene();
        ResourceManager.getInstance().music.setLooping(true);
        ResourceManager.getInstance().music.setVolume(.15f);
        ResourceManager.getInstance().music.play();


        ResourceManager.getInstance().mControl.refreshControlKnobPosition();
        //scene.setChildScene(ResourceManager.getInstance().mControl);

        pOnCreateSceneCallback.onCreateSceneFinished(scene);
    }

    @Override
    public void onPopulateScene(Scene pScene, OnPopulateSceneCallback pOnPopulateSceneCallback) throws IOException {
        AbstractScene scene = (AbstractScene) pScene;
        scene.populate();
        pOnPopulateSceneCallback.onPopulateSceneFinished();
    }

}
