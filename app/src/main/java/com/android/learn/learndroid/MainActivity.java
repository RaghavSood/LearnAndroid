package com.android.learn.learndroid;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends Activity implements ImageInterface {

	Button surpriseButton;
	int i = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		surpriseButton = (Button) findViewById(R.id.btn_download);
		surpriseButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				new SurpriseTask(MainActivity.this, MainActivity.this).execute(new String[]{""});
			}
		});
	}

	@Override
	public void updateImages(final Bitmap[] bitmaps) {
		surpriseButton.setEnabled(false);
		final ImageView imageView = new ImageView(this);
		RelativeLayout rootLayout = (RelativeLayout) findViewById(R.id.root_layout);
		rootLayout.addView(imageView, new RelativeLayout.LayoutParams(500, 500));
		imageView.setImageBitmap(bitmaps[i++]);
		imageView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(i>=bitmaps.length) {
					i = 0;
				}
				imageView.setImageBitmap(bitmaps[i++]);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_about) {
			Intent intent = new Intent(this, AboutActivity.class);
			startActivity(intent);
			return true;
		}

		return super.onOptionsItemSelected(item);
	}
}
