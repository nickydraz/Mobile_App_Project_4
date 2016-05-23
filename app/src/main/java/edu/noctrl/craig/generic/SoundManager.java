package edu.noctrl.craig.generic;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.util.Log;

import com.deitel.cannongame.R;

public class SoundManager {
	// constants and variables for managing sounds
	public static int SPIT_ID = 0;
	public static int CAMEL_HIT = 1;
	public static int SNAKE_HIT = 2;

	private boolean loaded = false;
	
	protected SoundPool soundPool; // plays sound effects
	protected Context context;
	
	public SoundManager(Context context){
        SoundPool.Builder builder = new SoundPool.Builder();
        AudioAttributes.Builder atts = new AudioAttributes.Builder();
        atts.setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION);
        atts.setUsage(AudioAttributes.USAGE_GAME);
        atts.setFlags(AudioAttributes.FLAG_AUDIBILITY_ENFORCED);
        builder.setAudioAttributes(atts.build());
        builder.setMaxStreams(3);

        // initialize SoundPool to play the app's three sound effects
		soundPool = builder.build();
		this.context = context;
		initializeSounds();
	}
	
	public void releaseResources(){
		soundPool.release(); // release all resources used by the SoundPool
		soundPool = null;
	}
	
	protected void initializeSounds(){
		soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
			@Override
			public void onLoadComplete(SoundPool soundPool, int sampleId,
									   int status) {
				loaded = true;
			}
		});
		SPIT_ID = soundPool.load(context, R.raw.spit, 1);
        CAMEL_HIT = soundPool.load(context, R.raw.camel, 1);
        //SNAKE_HIT = soundPool.load(context, R.raw.spit, 1);

	}
	
	public void playSound(int sound){

		if(loaded) {
			switch(sound){
				case 0: {
					soundPool.play(SPIT_ID, 1, 1, 1, 0, 1f);
					break;
				}
				case 1: {
					soundPool.play(CAMEL_HIT, 1, 1, 1, 0, 1f);
					break;
				}
				case 2: {
					soundPool.play(SNAKE_HIT, 1, 1, 1, 0, 1f);
					break;
				}
			}

		}
		else{
			Log.i("sound", "not loaded yet, sorry");
		}
	}
	
}
