package com.example.fragment;

import java.util.List;

import com.example.global.ViewPicSize;
import com.example.syspics.R;
import com.example.tools.Album;
import com.example.tools.BitmapRelease;
import com.example.tools.CameraPreview;
import com.example.tools.RotateBitmap;
import com.example.tools.UsefulImgSize;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.Size;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

public class TakeFragment extends Fragment {
	public static final int RESULT = 100;
	private Button cameraBu;
	private Button pictureBu;
	private View rootView;
	private LinearLayout surfaceContainer;
	private CameraPreview preview;
	private Jump2View jump;
	private Camera camera;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		if (!(activity instanceof Jump2View)) {
			throw new IllegalStateException("error");
		}
		jump = (Jump2View) activity;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle saveInstanceState) {
		if (null == rootView) {
			rootView = inflater
					.inflate(R.layout.takefragment, container, false);
		}
		findViews(rootView);
		addSurfaceView();
		addListener();
		return rootView;
	}

	@Override
	public void onPause() {
		// 被交换到后台时执行
		super.onPause();
	}

	@Override
	public void onStop() {
		if (camera != null) {
			camera.stopPreview();
			camera.release();
		}
		super.onStop();
	}

	@Override
	public void onResume() {
		super.onResume();
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
			Bitmap bitmap = Album.getAlbumBitmap(picturePath);
			jump.getPic(bitmap);
		}
	}

	// ***************common init***************

	@SuppressWarnings("deprecation")
	private void addSurfaceView() {
		camera = Camera.open();
		camera.setDisplayOrientation(90);
		if (camera != null) {
			Camera.Parameters parameters = camera.getParameters();
			parameters.setPictureFormat(PixelFormat.JPEG);
			List<Size> sizes = parameters.getSupportedPictureSizes();
			Size optimalSize = UsefulImgSize.getSmallestSize(sizes);
			parameters.setPictureSize(optimalSize.width, optimalSize.height);
			camera.setParameters(parameters);
		}
		preview = new CameraPreview(getActivity().getBaseContext(), camera);
		surfaceContainer.addView(preview);
	}

	private void addListener() {
		cameraBu.setOnTouchListener(new OnTouchListener() {

			@SuppressLint("ClickableViewAccessibility")
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					camera.autoFocus(null);
				}
				if (event.getAction() == MotionEvent.ACTION_UP) {
					camera.takePicture(null, null, new PictureCallback() {

						@Override
						public void onPictureTaken(byte[] data, Camera camera) {
							// TODO Auto-generated method stub
							Bitmap contant = BitmapFactory.decodeByteArray(
									data, 0, data.length);
							Bitmap rotateBitmap = RotateBitmap
									.adjustPhotoRotation(contant, 90);
							int picWidth = rotateBitmap.getWidth();
							int picHeight = rotateBitmap.getHeight();
							ViewPicSize.getInstance().initPicParam(picWidth,
									picHeight);
							BitmapRelease.recycleBitmap(contant);
							jump.getPic(rotateBitmap);
						}
					});
				}
				return true;
			}
		});

		pictureBu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(
						Intent.ACTION_PICK,
						android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
				startActivityForResult(intent, RESULT);
			}
		});
	}

	private void findViews(View view) {
		cameraBu = (Button) view.findViewById(R.id.cameraBu);
		pictureBu = (Button) view.findViewById(R.id.pictureBu);
		surfaceContainer = (LinearLayout) view
				.findViewById(R.id.surfaceContainer);
	}

	public interface Jump2View {
		public void getPic(Bitmap bitmap);
	}
}
