package biz.huetz.apps.huetastic;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

public class VideoSurfaceView extends SurfaceView implements SurfaceHolder.Callback {
	private MediaPlayer mediaPlayer = null;
	private boolean hasStarted = false;

	public VideoSurfaceView( Context context ) {
		super( context );
		init();
	}

	public VideoSurfaceView( Context context, AttributeSet attrs ) {
		super( context, attrs );
		init();
	}

	public VideoSurfaceView( Context context, AttributeSet attrs, int defStyleAttr ) {
		super( context, attrs, defStyleAttr );
		init();
	}

	public VideoSurfaceView( Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes ) {
		super( context, attrs, defStyleAttr, defStyleRes );
		init();
	}

	private void init() {
		if( !this.isInEditMode() ) {
			mediaPlayer = new MediaPlayer();
			getHolder().addCallback( this );
		}
	}

	@Override
	public void surfaceCreated( SurfaceHolder holder ) {
		if( this.isInEditMode() ) {
			return;
		}
		AssetFileDescriptor afd = null; //getResources().openRawResourceFd( R.raw.bridge_search );
		try {
			if( !hasStarted ) {
				hasStarted = true;
				mediaPlayer.setDataSource( afd.getFileDescriptor(), afd.getStartOffset(), afd.getDeclaredLength() );
			}

			mediaPlayer.prepare();
			android.view.ViewGroup.LayoutParams lp = getLayoutParams();
			lp.height = getHeight();
			lp.width = getWidth();

			setLayoutParams( lp );
			mediaPlayer.setDisplay( getHolder() );
			mediaPlayer.setLooping( true );
			mediaPlayer.start();
		} catch( IOException e ) {
			e.printStackTrace();
		}
	}

	@Override
	public void surfaceChanged( SurfaceHolder holder, int format, int width, int height ) {

	}

	@Override
	public void surfaceDestroyed( SurfaceHolder holder ) {
		if( !this.isInEditMode() ) {
			mediaPlayer.stop();
		}
	}
}
