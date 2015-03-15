import com.jobprogrammers.indashadows.GameManager;

import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

/**
 * Created by jt612 on 3/14/15.
 */
public class ButtonLeft extends Sprite {


    public ButtonLeft(float x,float y, ITextureRegion pTextureRegion, VertexBufferObjectManager vb){
        super(x, y, pTextureRegion, vb);

    }


    public boolean onAreaTouched(TouchEvent touchEvent, float X, float Y)
        {
          //GameManager.getInstance().decrementScore();
            //Just in case of a memory leak
            GameManager.getInstance().player.moveLeft();

            return true;
        };



}
