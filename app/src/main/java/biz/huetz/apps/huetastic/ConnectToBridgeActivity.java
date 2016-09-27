package biz.huetz.apps.huetastic;

import android.app.Activity;
import android.os.Bundle;

public class ConnectToBridgeActivity extends Activity {

	@Override
	protected void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_connect_to_bridge );
		setTitle( getResources().getString( R.string.title_activity_connect_to_bridge ) );
	}
}
