package com.example.camerasample;

import java.io.File;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.nicksapp.cameraLibrary.CustomCamera;
import com.nicksapp.cameraLibrary.OnPictureTaken;

import com.startapp.android.publish.StartAppAd;
import com.startapp.android.publish.splash.SplashConfig;
import com.startapp.android.publish.splash.SplashConfig.Theme;

public class MainActivity extends Activity implements OnClickListener, OnPictureTaken {
	 private StartAppAd startAppAd = new StartAppAd(this);
	private Button btnCustomCamera, btnDefaultCamera,btnFrontCamera;
	private TextView txtPath;
	private CustomCamera mCustomCamera;
	private ImageView imgCapture;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		   StartAppAd.init(MainActivity.this, "103205820", "203170552");
		setContentView(R.layout.activity_main);
		
		 StartAppAd.showSplash(this, savedInstanceState,
					new SplashConfig()
						.setTheme(Theme.SKY)
						.setLogo(R.drawable.ic_launcher)
						.setAppName("Camera Demo")
			);
	     

	        StartAppAd.showSlider(this);
	    	startAppAd.loadAd(); // load the next ad
	       		startAppAd.showAd(); // show the ad
	       		startAppAd.loadAd(); // load the next ad
		mCustomCamera = new CustomCamera(MainActivity.this);
		mCustomCamera.setPictureTakenListner(this);
		
		btnCustomCamera = (Button) findViewById(R.id.btnCustomCamera);
		btnCustomCamera.setOnClickListener(this);
		btnDefaultCamera = (Button) findViewById(R.id.btnDefaultCamera);
		btnDefaultCamera.setOnClickListener(this);
		btnFrontCamera = (Button) findViewById(R.id.btnFrontCamera);
		btnFrontCamera.setOnClickListener(this);
		
		imgCapture = (ImageView) findViewById(R.id.imgPic);
		txtPath = (TextView) findViewById(R.id.txtPath);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		startAppAd.showAd(); // show the ad
		startAppAd.loadAd();
		switch (v.getId()) {
		case R.id.btnCustomCamera:
			mCustomCamera.startCamera();
			break;

		case R.id.btnDefaultCamera:
			mCustomCamera.sendCameraIntent();
			break;
			
		case R.id.btnFrontCamera:
			mCustomCamera.startCameraFront();
			break;
		}
	}

	@Override
	public void pictureTaken(Bitmap bitmap, File file) {
		imgCapture.setImageBitmap(bitmap);
		txtPath.setText(file.getAbsolutePath());
		startAppAd.showAd(); // show the ad
		startAppAd.loadAd();
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		startAppAd.onBackPressed();
		super.onBackPressed();
	}

}
