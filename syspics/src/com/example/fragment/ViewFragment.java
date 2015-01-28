package com.example.fragment;

import com.example.syspics.DemoActivity;
import com.example.syspics.R;
import com.example.tools.Album;
import com.example.tools.BitmapRelease;
import com.example.tools.BitmapScale;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

public class ViewFragment extends Fragment {
	private final static int RESULT = 200;
	private View rootView;
	private ImageView imageView1;
	private Button photoBu;
	private Button cameraBu;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle saveInstanceState) {
		if (null == rootView) {
			rootView = inflater
					.inflate(R.layout.viewfragment, container, false);
		}
		findViews(rootView);
		init();
		addListener();
		return rootView;
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == RESULT && resultCode == Activity.RESULT_OK
				&& data != null) {
			Uri selectedImage = data.getData();
			String[] filePathColumn = { MediaStore.Images.Media.DATA };

			Cursor cursor = getActivity().getContentResolver().query(
					selectedImage, filePathColumn, null, null, null);
			cursor.moveToFirst();

			int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
			String picturePath = cursor.getString(columnIndex);
			cursor.close();
			BitmapRelease.recycleBitmap(imageView1);
			Bitmap bitmap = Album.getAlbumBitmap(picturePath);
			Bitmap showBitmap = BitmapScale.scale(bitmap);
			BitmapRelease.recycleBitmap(bitmap);
			imageView1.setImageBitmap(showBitmap);
		}
	}

	private void init() {
		Bitmap bitmap = ((DemoActivity) getActivity()).getBitmap();
		Bitmap showBitmap = BitmapScale.scale(bitmap);
		imageView1.setImageBitmap(showBitmap);
	}

	private void findViews(View view) {
		imageView1 = (ImageView) view.findViewById(R.id.imageView1);
		photoBu = (Button) view.findViewById(R.id.photoBu);
		cameraBu = (Button) view.findViewById(R.id.cameraBu);
	}

	private void addListener() {
		photoBu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(
						Intent.ACTION_PICK,
						android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
				startActivityForResult(intent, RESULT);
			}
		});

		cameraBu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

			}
		});
	}
}
