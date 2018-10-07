package com.memeteam.pureshkabird;

import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.AdRequest;

public class AndroidLauncher extends AndroidApplication {
	public boolean isOnline() {
		ConnectivityManager cm =
				(ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cm.getActiveNetworkInfo();
		return netInfo != null && netInfo.isConnectedOrConnecting();
	}

	@Override
	protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
	    AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();

		if(!isOnline()){
			initialize(new PureshkaBirdGame(false), config);
		}
		else {
            // Create the layout
            RelativeLayout layout = new RelativeLayout(this);

            // Do the stuff that initialize() would do for you
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);

            // Create the libgdx View
            View gameView = initializeForView(new PureshkaBirdGame(true), config);

            // Add the libgdx view
            layout.addView(gameView);

            // Create and setup the AdMob view
            AdView adView = new AdView(this);
            adView.setAdUnitId("ca-app-pub-2356801943660362/3520696132");

            adView.setAdSize(AdSize.BANNER);
            adView.loadAd(new AdRequest.Builder().build());

            // Add the AdMob view
            RelativeLayout.LayoutParams adParams =
                    new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                            RelativeLayout.LayoutParams.WRAP_CONTENT);
            //adParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
            adParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            adParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
            adView.setBackgroundColor(Color.parseColor("#f48c42"));
            layout.addView(adView, adParams);

            // Hook it all up
            setContentView(layout);
        }
	}
}
