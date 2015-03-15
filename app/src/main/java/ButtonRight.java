import com.jobprogrammers.indashadows.GameManager;

import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

/**
 * Created by jt612 on 3/14/15.
 */
public class ButtonRight extends Sprite {

    public ButtonRight(float x,float y, ITextureRegion pTextureRegion, VertexBufferObjectManager vb){
        super(x, y, pTextureRegion, vb);

    }

        public boolean onAreaTouched(TouchEvent touchEvent, float X, float Y)
        {
            //GameManager.getInstance().incrementScore();
            //Might not be resetting the game correctly so
            //In order to not break my phone due to a memoryleak commenting this out for now
            GameManager.getInstance().player.moveRight();

            return true;
        };


}
