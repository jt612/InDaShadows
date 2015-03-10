package com.jobprogrammers;

import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.entity.scene.Scene;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.debug.Debug;

/**
 * Created by jt612 on 1/22/15.
 */
public abstract class AbstractScene extends Scene {
    protected ResourceManager res = ResourceManager.getInstance();

    protected Engine engine = res.engine;
    protected startGame activity;
    protected VertexBufferObjectManager vbom = res.vbom;
    protected Camera camera = res.camera;

    protected AbstractScene() {
        activity = res.activity;
    }

    public abstract void populate();

    public void destroy() {

    }

    public void onBackKeyPressed() {
        Debug.d("Back key was pressed");
    }

    public abstract void onPause();

    public abstract void onResume();

}
