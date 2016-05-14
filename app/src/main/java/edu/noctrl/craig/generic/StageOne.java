package edu.noctrl.craig.generic;

import android.view.MotionEvent;
import android.view.View;

/**
 * Created by debra on 5/14/16.
 */
public class StageOne extends World{
    private SpaceCamel cam;
    public StageOne(StateListener listener, SoundManager sounds) {
        super(listener, sounds);
        cam = new SpaceCamel(this);
        //WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        cam.position =  new Point3F(180, 100, 0);
        System.out.println("In Stage One width is " + width);
        this.addObject(cam);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event)
    {
        return true;
    }

@Override
    public void initializeGame()
    {
        cam.setStartPt();
    }
}
