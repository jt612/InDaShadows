import android.graphics.Typeface;

import com.jobprogrammers.indashadows.startGame;

import org.andengine.audio.music.Music;
import org.andengine.audio.music.MusicFactory;
import org.andengine.audio.sound.Sound;
import org.andengine.audio.sound.SoundFactory;
import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
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
    public ITextureRegion rightB;
    public ITextureRegion leftB;


    //sound related variables
    public Sound soundNotMakeJump;
    public Sound soundJump;
    //Music variables
    public Music music;
    public Sound gameOver;
    //font related
    public Font font;
    public Font GameOverFont;
    String TAG = "startGame/resourceManager";

    private BuildableBitmapTextureAtlas gameTextureAtlas;



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


        rightB = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, activity.getAssets(), "left.png");
        leftB = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, activity.getAssets(), "right.png");


        try {
            gameTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(5, 0, 5));
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

            SoundFactory.setAssetBasePath("sfx/");
            gameOver = SoundFactory.createSoundFromAsset(activity.getSoundManager(), activity, "death.mp3");
            gameOver.setLooping(false);
        } catch (IOException e) {
            throw new RuntimeException("Error while loading audio", e);
        }

    }

    public void loadFont() {
        GameOverFont = FontFactory.createStroke(activity.getFontManager(), activity.getTextureManager(), 256, 256,
                Typeface.create(Typeface.SERIF, Typeface.BOLD), 45, true, Color.ARGB_PACKED_RED_CLEAR, 2, Color.ABGR_PACKED_GREEN_CLEAR);

        font = FontFactory.createStroke(activity.getFontManager(), activity.getTextureManager(), 256, 256,
                Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD), 36, true, Color.WHITE_ABGR_PACKED_INT, 2,
                Color.BLACK_ABGR_PACKED_INT);
        GameOverFont.load();
        font.load();
    }


}
//End of Resource Manager class

