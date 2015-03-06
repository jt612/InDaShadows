import android.graphics.Typeface;

import com.jobprogrammers.indashadows.startGame;

import org.andengine.audio.music.Music;
import org.andengine.audio.music.MusicFactory;
import org.andengine.audio.sound.Sound;
import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.engine.camera.hud.HUD;
import org.andengine.engine.camera.hud.controls.AnalogOnScreenControl;
import org.andengine.engine.camera.hud.controls.BaseOnScreenControl;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.PixelFormat;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.atlas.bitmap.BuildableBitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.source.IBitmapTextureAtlasSource;
import org.andengine.opengl.texture.atlas.buildable.builder.BlackPawnTextureAtlasBuilder;
import org.andengine.opengl.texture.atlas.buildable.builder.ITextureAtlasBuilder;
import org.andengine.opengl.texture.bitmap.BitmapTextureFormat;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.align.HorizontalAlign;
import org.andengine.util.adt.color.Color;
import org.andengine.util.debug.Debug;

import java.io.IOException;

/**
 * Created by jt612 on 1/21/15.
 */
public class ResourceManager {

    private static final ResourceManager INSTANCE = new ResourceManager();
    public startGame activity;
    public Engine engine;
    public Camera camera;
    public VertexBufferObjectManager vbom;
    public ITiledTextureRegion playerTextureRegion;
    public ITiledTextureRegion enemyTextureRegion;
    public ITextureRegion obstacleTextureRegion;
    public ITextureRegion backGroundTextureRegion;
    // public ITexture mObstacleTextureRegion;
    public ITexture mBackgroundTextureRegion;
    public ITextureRegion controlBaseTextureRegion;
    public ITextureRegion controlKnobTextureRegion;
    //sound related variables
    public Sound soundNotMakeJump;
    public Sound soundJump;
    //Music variables
    public Music music;
    //font related
    public Font font;
    HUD mHUD;
    String TAG = "startGame/resourceManager";
    AnalogOnScreenControl mControl;
    private BuildableBitmapTextureAtlas gameTextureAtlas;
    private BuildableBitmapTextureAtlas otherTextureAtlas;
    private Text score;


    private ResourceManager() {

    }

    public static ResourceManager getInstance() {
        return INSTANCE;
    }

    public void create(startGame activity, Engine engine, Camera camera, VertexBufferObjectManager vbom) {
        this.activity = activity;
        this.engine = engine;
        this.camera = camera;
        this.vbom = vbom;
    }

    public void loadGameGraphics() {


        gameTextureAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 512,
                BitmapTextureFormat.fromPixelFormat(PixelFormat.RGBA_8888), TextureOptions.BILINEAR_PREMULTIPLYALPHA);

        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");

        //andEngine graphics create a player bitmap
        playerTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gameTextureAtlas, activity.getAssets(), "idle.png", 1, 1);

        obstacleTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, activity.getAssets(), "box.png");

        backGroundTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, activity.getAssets(), "background.jpg");
        //base of a new controller
        controlBaseTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, activity.getAssets(), "base.png");
        //joystick image for controller
        controlKnobTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, activity.getAssets(), "knob.png");

        this.createController();

        try {
            gameTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(4, 0, 4));
            gameTextureAtlas.load();
        } catch (final ITextureAtlasBuilder.TextureAtlasBuilderException e) {
            throw new RuntimeException("Error while loading game textures " +
                    "ResourceManager.java the atlas failed to build", e);
        }


        Debug.d("Graphics loaded successfully");
    }

    public void loadGameAudio() {
        try {
            MusicFactory.setAssetBasePath("mfx/");
            music = MusicFactory.createMusicFromAsset(activity.getMusicManager(), activity, "bgm.mp3");
            music.setLooping(true);
        } catch (IOException e) {
            throw new RuntimeException("Error while loading audio", e);
        }

    }

    public void loadFont() {
        font = FontFactory.createStroke(activity.getFontManager(), activity.getTextureManager(), 256, 256,
                Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD), 50, true, Color.WHITE_ABGR_PACKED_INT, 2,
                Color.BLACK_ABGR_PACKED_INT);
        font.load();
    }


    public void createController() {

        //values for size of the images
        final float controllerX = controlBaseTextureRegion.getWidth();
        final float controllerY = controlBaseTextureRegion.getHeight();

        mControl = new AnalogOnScreenControl(controllerX, controllerY, camera, controlBaseTextureRegion, controlKnobTextureRegion, 0.1f, this.engine.getVertexBufferObjectManager(), new AnalogOnScreenControl.IAnalogOnScreenControlListener() {
            @Override
            public void onControlClick(AnalogOnScreenControl pAnalogOnScreenControl) {
                //for now do nothing
                //this is when they click on the control
            }

            @Override
            public void onControlChange(BaseOnScreenControl pBaseOnScreenControl, float pValueX, float pValueY) {
                camera.setCenter(camera.getCenterX() + (pValueX * 10), camera.getCenterY() + (pValueY * 10));

            }
        });

    } // End of create controller function

    public void createHUD() {
        mHUD = new HUD();
        ResourceManager.getInstance().camera.setHUD(mHUD);
        score = new Text(16, 20, font, "0123456789", new TextOptions(HorizontalAlign.LEFT), vbom);
        score.setAnchorCenter(0, -4);
        mHUD.attachChild(score);
        //mHUD.attachChild(mControl);
        camera.setHUD(mHUD);
    }


//End of Resource Manager class
}
