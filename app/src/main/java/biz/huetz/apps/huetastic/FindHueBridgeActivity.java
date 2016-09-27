package biz.huetz.apps.huetastic;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.philips.lighting.hue.sdk.PHAccessPoint;
import com.philips.lighting.hue.sdk.PHBridgeSearchManager;
import com.philips.lighting.hue.sdk.PHHueSDK;
import com.philips.lighting.hue.sdk.PHSDKListener;
import com.philips.lighting.model.PHBridge;
import com.philips.lighting.model.PHHueParsingError;

import java.util.List;

public class FindHueBridgeActivity extends AppCompatActivity {
	private static final String TAG = "FindHueBridgeActivity";
	public static final int DELAY_SEARCH_START_MILLIS = 600;
	private View mContentView;
	private TextView mLookingForBridges;
	private PHHueSDK mPhilipsHueSDK;

	private final Handler mSearchForHueBridgesHandler = new Handler();
	private final Runnable mSearchForHueBridges = new Runnable() {
		@Override
		public void run() {
			PHBridgeSearchManager sm = (PHBridgeSearchManager) mPhilipsHueSDK.getSDKService( PHHueSDK.SEARCH_BRIDGE );
			sm.search( true, true );
		}
	};

	private PHSDKListener mPhilipsHueListener = new PHSDKListener() {
		@Override
		public void onCacheUpdated( List< Integer > list, PHBridge phBridge ) {

		}

		@Override
		public void onBridgeConnected( PHBridge phBridge, String s ) {

		}

		@Override
		public void onAuthenticationRequired( PHAccessPoint phAccessPoint ) {

		}

		@Override
		public void onAccessPointsFound( List< PHAccessPoint > list ) {
			Log.i( TAG, String.format( "Found %d Philips Hue bridge(s)... ", list.size() ) );

			mPhilipsHueSDK.getAccessPointsFound().clear();
			mPhilipsHueSDK.getAccessPointsFound().addAll( list );

			runOnUiThread( new Runnable() {
				@Override
				public void run() {
					int numberOfFoundAccessPoints = mPhilipsHueSDK.getAccessPointsFound().size();
					mLookingForBridges.setText( getResources().getQuantityString( R.plurals.searching_hue_bridges, numberOfFoundAccessPoints, numberOfFoundAccessPoints ) );
				}
			} );
		}

		@Override
		public void onError( int i, String s ) {

		}

		@Override
		public void onConnectionResumed( PHBridge phBridge ) {

		}

		@Override
		public void onConnectionLost( PHAccessPoint phAccessPoint ) {

		}

		@Override
		public void onParsingErrors( List< PHHueParsingError > parsingErrorsList ) {
			for( PHHueParsingError parsingError : parsingErrorsList ) {
				Log.e( TAG, "ParsingError: " + parsingError.getMessage() );
			}
		}
	};

	@Override
	protected void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );

		setContentView( R.layout.activity_find_hue_bridge );
		ActionBar actionBar = getSupportActionBar();
		if( actionBar != null ) {
			actionBar.setDisplayHomeAsUpEnabled( true );
		}

		mPhilipsHueSDK = PHHueSDK.create();
		mPhilipsHueSDK.setAppName( getResources().getString( R.string.app_name ) );
		mPhilipsHueSDK.setDeviceName( Build.MODEL );
		mPhilipsHueSDK.getNotificationManager().registerSDKListener( mPhilipsHueListener );

		mContentView = findViewById( R.id.fullscreen_content_controls );
		mContentView.setSystemUiVisibility( View.SYSTEM_UI_FLAG_LOW_PROFILE | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION );

		mLookingForBridges = (TextView) findViewById( R.id.looking_for_bridges_and_found_x_bridges );
		mLookingForBridges.setText( getResources().getQuantityString( R.plurals.searching_hue_bridges, 0, 0 ) );

		mSearchForHueBridgesHandler.postDelayed( mSearchForHueBridges, DELAY_SEARCH_START_MILLIS );
	}

	@Override
	public boolean onOptionsItemSelected( MenuItem item ) {
		int id = item.getItemId();
		if( id == android.R.id.home ) {
			NavUtils.navigateUpFromSameTask( this );
			return true;
		}
		return super.onOptionsItemSelected( item );
	}
}
