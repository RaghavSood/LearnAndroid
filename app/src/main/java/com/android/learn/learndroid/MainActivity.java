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

		//Inflate the layout
		setContentView(R.layout.activity_main);

		// Get a reference to the surprise me button
		surpriseButton = (Button) findViewById(R.id.btn_download);
		//Set a listener on the button to detect clicks
		surpriseButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// Start the asynctask to download the images
				new SurpriseTask(MainActivity.this, MainActivity.this).execute(new String[]{""});
			}
		});
	}

	@Override
	public void updateImages(final Bitmap[] bitmaps) {
		surpriseButton.setEnabled(false); // Disable the button, since we already have the images
		final ImageView imageView = new ImageView(this);// Create a new ImageView to add to the screen
		// Get a reference to the root layout
		RelativeLayout rootLayout = (RelativeLayout) findViewById(R.id.root_layout);
		//Attach the image view to our relative layout
		rootLayout.addView(imageView, new RelativeLayout.LayoutParams(500, 500));
		imageView.setImageBitmap(bitmaps[i++]); // Display the first image
		// Set the on click listener for the image view
		imageView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(i>=bitmaps.length) { // Wrap around once we reach the end
					i = 0;
				}
				imageView.setImageBitmap(bitmaps[i++]); // Display the next image
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
			// Create an Intent to go to the About activity
			Intent intent = new Intent(this, AboutActivity.class);
			startActivity(intent); // Launch the About activity
			return true;
		}

		return super.onOptionsItemSelected(item);
	}
}
