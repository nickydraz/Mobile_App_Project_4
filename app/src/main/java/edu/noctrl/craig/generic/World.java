package edu.noctrl.craig.generic;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by craig_000 on 5/9/2015.
 */
public class World implements View.OnTouchListener {
    public int ENEMIES_NEEDED;
    public static Object GUI_LOCKER = new Object();
    public static Resources resources;
    @Override
    public boolean onTouch(View v, MotionEvent event) {

        return true;
    }

    public interface StateListener{
        void onGameOver(boolean lost);
        void onNextStage(boolean next, int nextStage);
        void onWinGame(boolean won);
    }
    protected static final float TARGET_WIDTH = 540;
    protected static final float TARGET_HEIGHT = 960;
    protected static final float TARGET_PIXELS_PER_METER = 64F/30F;//ship len = 30m  ship base = 64px
    public static float PIXELS_PER_METER = 64F/30F;//ship len = 30m  ship base = 64px
    public double totalElapsedTime = 0;
    public int width;
    public int height;
    public StateListener listener;
    public SoundManager soundManager;
    public Point3F worldScale = Point3F.identity();
    public Timer timer;
    public TimerTask task;

    public int screenWidth;

    protected ArrayList<GameObject> objects = new ArrayList<>(1000);
    protected ArrayList<GameObject> newObjects = new ArrayList<>(1000);

    private boolean timeToSpawn = true; //should we spawn an enemy?
    public int spawnInterval = 4; //how often should the enemy spawn?

    //Counter variables
    public int spitCount = 0;
    public int hitCount = 0;
    public int killCount = 0;
    public int timeLeft = 20; //amount of time remaining for stage
    public int spitLimit = 0; //counter for number of spits on screen at once
    public int score = 0;     //the player's score
    public int enemies_left = 0;


    public World(StateListener listener, SoundManager sounds){
        this.listener = listener;
        this.soundManager = sounds;
        timer = new Timer();
        task = new TimerTask() {
            @Override
            public void run() {
                // task to run goes here
                timeLeft -= 1;

            }
        };
        timer.scheduleAtFixedRate(task, 0,
                1000);
    }

    public void addObject(GameObject obj){
        synchronized (newObjects) {
            newObjects.add(obj);
        }
    }

    protected void initialize(){

    }

    public void updateSize(float width, float height){
        this.width = (int)width;
        this.height = (int)height;
        initializeGame();
        worldScale = Point3F.identity();
        worldScale.X = width / TARGET_WIDTH;
        worldScale.Y = width / TARGET_WIDTH;
        PIXELS_PER_METER = worldScale.Y * TARGET_PIXELS_PER_METER;
        if(objects.size()==0){
            initialize();
        }
    }

    public void update(float elapsedTimeMS){
        float interval = elapsedTimeMS / 1000.0F; // convert to seconds
        if (((int)totalElapsedTime) % spawnInterval == 0) {
            if(timeToSpawn) {
                spawnEnemy();
            }
            timeToSpawn = false;
        }
        else
        timeToSpawn = true;

        for(GameObject obj : objects){
            obj.update(interval);
        }
        //checks if game is over
        isGameOver();
    }
    public void draw(Canvas canvas){
        if(canvas!=null){
            canvas.drawColor(Color.parseColor("#002C4C"));
            Paint textPaint = new Paint();
            textPaint.setColor(Color.WHITE);
            textPaint.setTextSize(60);
            canvas.drawText("Time Remaining: " + timeLeft + " seconds", 10, 55, textPaint);
            for(GameObject obj : objects){
                obj.draw(canvas);

            }
        }
    }
    public void cullAndAdd(){
        CollisionDetection.checkCollisions(objects);
        GameObject go;
        ArrayList<GameObject> removed = new ArrayList<>(objects.size());
        synchronized (newObjects){
            for(int i = objects.size() - 1;i >= 0; i--){
                go = objects.get(i);
                if(go.offScreen){
                    objects.remove(i);
                    removed.add(go);
                }
            }
            for(GameObject obj : newObjects) {
                if (obj.drawOrder == GameObject.DrawOrder.Foreground)
                    objects.add(obj);
                else
                    objects.add(0, obj);
            }
            newObjects.clear();
        }
        for(GameObject obj : removed) {
            obj.cull();
        }

    }

    public void initializeGame()
    {

    }

    public void spawnEnemy()
    {
        System.out.println("Bad Method");
    }

    //method to be overriden in each stage to check if the end game requirements have been handled
    //called in update mode
    public void isGameOver()
    {

    }
}
