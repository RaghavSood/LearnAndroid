package com.android.learn.learndroid;

import android.graphics.Bitmap;
import android.os.AsyncTask;

/**
 * Created by raghavsood on 11/09/15.
 */
public class SurpriseTask extends AsyncTask<String, Integer, Bitmap[]> {
	public static String TAG = "SurpriseTask";

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
	}

	@Override
	protected Bitmap[] doInBackground(String... params) {
		return new Bitmap[10];
	}

	@Override
	protected void onProgressUpdate(Integer... values) {
		super.onProgressUpdate(values);
	}

	@Override
	protected void onPostExecute(Bitmap[] bitmaps) {
		super.onPostExecute(bitmaps);
	}
}
