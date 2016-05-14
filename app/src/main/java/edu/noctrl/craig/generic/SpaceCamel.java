package edu.noctrl.craig.generic;

import android.graphics.Rect;

/**
 * Created by debra on 5/14/16.
 */
public class SpaceCamel extends GameSprite{

    static final Rect camelRect = new Rect(0, 0, 77, 52);
    private Point3F scalePt = new Point3F(1, 1, 1);
    private World w;

    public SpaceCamel(World theWorld) {
        super(theWorld);
        w = theWorld;
    }

    @Override
    public Rect getSource() {
        return camelRect;
    }

    @Override
    public Point3F getScale() {
        return scalePt;
    }

    @Override
    public void cull() {

    }

    public void setStartPt()
    {
        position = new Point3F(180, w.height/2, 0);
    }
}
