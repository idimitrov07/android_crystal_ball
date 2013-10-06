package com.sharpdevelopers.dimitry.crystallball;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import com.sharpdevelopers.dimitry.crystallball.R;
import com.sharpdevelopers.dimitry.crystallball.ShakeDetector.OnShakeListener;

public class MainActivity extends Activity {

	public static final String TAG = MainActivity.class.getSimpleName();
	
	private CrystallBall mCrystallBall = new CrystallBall();
	private TextView mAnswerLabel;
	private ImageView mCrystallBallImage;
	private SensorManager mSensorManager;
	private Sensor mAccelerometer;
	private ShakeDetector mShakeDetector;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// declare our View variables
		mAnswerLabel = (TextView) findViewById(R.id.textView1);
		mCrystallBallImage = (ImageView) findViewById(R.id.imageView1);

		mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		mAccelerometer = mSensorManager
				.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		mShakeDetector = new ShakeDetector(new OnShakeListener() {

			public void onShake() {
				// TODO Auto-generated method stub
				handleNewAnswer();
			}
		});

		// Toast.makeText(this, "Welcome to your future!",
		// Toast.LENGTH_LONG).show();

		Log.d(TAG, "We are logging");
		
	}

	@Override
	public void onResume() {
		super.onResume();
		mSensorManager.registerListener(mShakeDetector, mAccelerometer,
				SensorManager.SENSOR_DELAY_UI);

	}

	@Override
	public void onPause() {
		super.onPause();
		mSensorManager.unregisterListener(mShakeDetector);

	}

	private void animateCrystallBall() {

		mCrystallBallImage.setImageResource(R.drawable.ball_animation);
		AnimationDrawable ballAnimation = (AnimationDrawable) mCrystallBallImage
				.getDrawable();
		if (ballAnimation.isRunning()) {
			ballAnimation.stop();
		}
		ballAnimation.start();
	}

	private void animateAnswer() {
		AlphaAnimation fadeAnimation = new AlphaAnimation(0, 1);
		fadeAnimation.setDuration(1500);
		fadeAnimation.setFillAfter(true);

		mAnswerLabel.setAnimation(fadeAnimation);
	}

	private void playSound() {
		MediaPlayer player = MediaPlayer.create(this, R.raw.crystalball);
		player.start();
		player.setOnCompletionListener(new OnCompletionListener() {

			public void onCompletion(MediaPlayer mp) {
				// TODO Auto-generated method stub
				mp.release();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void handleNewAnswer() {
		String answer = mCrystallBall.getAnAnswer();

		// update the label
		mAnswerLabel.setText(answer);

		animateCrystallBall();
		animateAnswer();
		playSound();
	}

}
