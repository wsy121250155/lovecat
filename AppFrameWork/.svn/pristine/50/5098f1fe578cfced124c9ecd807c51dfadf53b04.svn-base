package com.mobile.base.widget.photo;

import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

import com.mdx.framework.utility.BitmapRead;
import com.mdx.framework.utility.Util;
import com.mobile.base.R;
import com.mobile.base.tools.Device;
import com.mobile.base.util.MLog;
import com.mobile.base.widget.cropper.CropImageView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class CameraFile extends Activity{

	
    public static final String RECEIVER_PHOTO = "com.mdx.receivePhoto";
	public static final int DISPOSE_GET_PIC_PASH = 48;
    public static final int REQUEST_CAMERA = 16;
    public static final int RESULT_CAMERA = 32;
	public static final String RESULT_PHONE = "result_phone";
	private static final int DEFAULT_ASPECT_RATIO_VALUES = 10;
	private static final String ASPECT_RATIO_X = "ASPECT_RATIO_X";
	private static final int ROTATE_NINETY_DEGREES = 90;
	private static final String ASPECT_RATIO_Y = "ASPECT_RATIO_Y";
	private int mAspectRatioY = 10;
	private int mAspectRatioX = 10;
	private int userType = 0, aspectX = 1, aspectY = 1, outputX = 350, outputY = 350;
	private Button submit;
	private Button cancle;
	private Button rotate;
	private String picpathsave;
	private String picpathcrop;
	private CropImageView cropImageView;
	
    protected void onSaveInstanceState(Bundle bundle)
	{
      super.onSaveInstanceState(bundle);
      bundle.putInt("ASPECT_RATIO_X", this.mAspectRatioX);
	  bundle.putInt("ASPECT_RATIO_Y", this.mAspectRatioY);
    }

    protected void onRestoreInstanceState(Bundle bundle)
    {
      super.onRestoreInstanceState(bundle);
      this.mAspectRatioX = bundle.getInt("ASPECT_RATIO_X");
      this.mAspectRatioY = bundle.getInt("ASPECT_RATIO_Y");
    }
	
	private void showPick(String type){
		if(type.equals("1")){
			Intent pick = new Intent("android.intent.action.GET_CONTENT");
			pick.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
			startActivityForResult(pick, 1);
		}
		if(type.equals("2")){
			Intent capture = new Intent("android.media.action.IMAGE_CAPTURE");
			
			capture.putExtra("output", Uri.fromFile(new File(this.picpathsave)));
			
			capture.putExtra("android.intent.extra.screenOrientation", true);
			
			startActivityForResult(capture, 2);
		}
	}
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.getphoto_activity_photo);
		submit = (Button) findViewById(R.id.submit);
		cancle = (Button) findViewById(R.id.cancle);
		rotate = ((Button)findViewById(R.id.rotate));
		cropImageView = ((CropImageView)findViewById(R.id.CropImageView));
		
		this.userType = getIntent().getIntExtra("gettype", 0);
		this.aspectX = getIntent().getIntExtra("aspectX", -1);
		this.aspectY = getIntent().getIntExtra("aspectY", -1);
		this.outputX = getIntent().getIntExtra("outputX", 0);
		this.outputY = getIntent().getIntExtra("outputY", 0);
		
		String temptime = UUID.randomUUID().toString().replace("-", "");

	    this.picpathsave = (Util.getDPath(this, "/temp/csimages/").getPath() + temptime + "_cstempsave.jpg");
		this.picpathcrop = (Util.getDPath(this, "/temp/csimages/").getPath() + temptime + "_cstempcrop.jpg");
	
		this.cropImageView.setFixedAspectRatio(false);
	
		this.rotate.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View v){
				CameraFile.this.cropImageView.rotateImage(90);
				}
			});
		this.submit.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v){
				new CameraFile.SaveAsyncTask(CameraFile.this, null).execute(new String[] { "" });
				Log.d("MYAPP", " 发广播啦 :" + CameraFile.this.picpathcrop);
			}
			});
		this.cancle.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v)
			{
				Intent intent = new Intent();
				intent.setAction("com.mdx.receivePhoto");
				intent.putExtra("type", "0");
				CameraFile.this.sendBroadcast(intent);
				CameraFile.this.finish();
				}
			});
		if (this.userType == 1)
			showPick("1");
		else if (this.userType == 2)
			showPick("2");
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		if (resultCode != -1)
		{
			Intent intent = new Intent();
			intent.setAction(CameraFile.RECEIVER_PHOTO);
			intent.putExtra("type", "0");
			sendBroadcast(intent);
			finish();
			return;
			}
		switch (requestCode) {
		case 1:
			new mAsyncTask().execute(new String[] { getFilePath(this, data.getData()) });
			Log.d("aric", "地址是"+getFilePath(this, data.getData()));
			break;
		case 2:
			if (data != null) {
				Bitmap bmap = (Bitmap)data.getParcelableExtra("data");
				if (bmap == null) break;
				setBitmap(bmap);
				}
			else {
				new mAsyncTask().execute(new String[] { this.picpathsave });
				Log.d("bitmap", "data为空");
				}
			}
		}
	/*     */ 
	public void savePhoto(Bitmap bitmap, String path){
		FileOutputStream out = null;
		try {
			float bw = bitmap.getWidth();
			float bh = bitmap.getHeight();
			float w = this.outputX;
			float h = this.outputY;
			if((w != 0.0F) || (h != 0.0F)){
		      if (w / bw > h / bh)
		        bitmap = big(bitmap, w / bw);
			  else {
				 bitmap = big(bitmap, h / bh);
			  }
			}
			out  = new FileOutputStream(path);
			bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
		} catch (Exception e) {
			MLog.e(e);
		}
		try {
			out.flush();
			out.close();
		} catch (Exception e) {
			MLog.e(e);
		}
	}
	/*
	 * 删除照片
	 */
	public void delPhoto(String path){
		File f = new File(path);
		f.delete();
	}
	
	public void setBitmap(Bitmap result){
		if(result == null){
			finish();
		}
		float bw = result.getWidth();
		float bh = result.getHeight();
		float w = 720.0F;
		float h = 1080.0F;
		if((result.getWidth() < w) && (result.getHeight() < h)){
			if(w / bw > h / bh){
				result = big(result, w / bw);
			}else {
				result = big(result, h / bh);
			}
		}
		this.cropImageView.setImageBitmap(result);
		if ((this.aspectX != -1) && (this.aspectY != -1)){
			this.cropImageView.postDelayed(new Runnable()
			{
				public void run()
				{
					CameraFile.this.cropImageView.setFixedAspectRatio(true);
					CameraFile.this.cropImageView.setAspectRatio(CameraFile.this.aspectX, CameraFile.this.aspectY);
					}
				}
			, 20L);
			}
	}
	@SuppressLint({"NewApi"})
	private String getFilePath(Context context, Uri contentUri)
	{
		String filePath = contentUri.toString();
		if (Build.VERSION.SDK_INT <= 19) {
			return selectImage(context, contentUri);
			}
		if (DocumentsContract.isDocumentUri(context, contentUri)) {
			String wholeID = DocumentsContract.getDocumentId(contentUri);
			String id = wholeID.split(":")[1];
			String[] column = { "_data" };
			String sel = "_id=?";
			Cursor cursor = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, 
					column, 
					sel, 
					new String[] { id }, 
					null);
			int columnIndex = cursor.getColumnIndex(column[0]);
			if (cursor.moveToFirst()) {
				filePath = cursor.getString(columnIndex);
				return filePath;
				}
			cursor.close();
			}
		return null;
		}
	
	public static String selectImage(Context context, Uri selectedImage) {
		if (selectedImage != null) {
			String uriStr = selectedImage.toString();
			String path = uriStr.substring(10, uriStr.length());
			if (path.startsWith("com.sec.android.gallery3d")) {
				return null;
				}
			}
		String[] filePathColumn = { "_data" };
		Cursor cursor = context.getContentResolver().query(selectedImage, filePathColumn, null, null, null);
		cursor.moveToFirst();
		int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
		String picturePath = cursor.getString(columnIndex);
		cursor.close();
		return picturePath;
		}
	
	private static Bitmap big(Bitmap bitmap, float scale){
		Matrix matrix = new Matrix();
		matrix.postScale(scale, scale);
		Bitmap resizeBmp = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
		return resizeBmp;
	}
	private class SaveAsyncTask extends AsyncTask<String, String, String>{

	public SaveAsyncTask(CameraFile cameraFile, Object object) {
		// TODO Auto-generated constructor stub
	}
	protected String doInBackground(String[] params)
	{
		try
		{
			Bitmap bitmap = CameraFile.this.cropImageView.getCroppedImage();
			CameraFile.this.savePhoto(bitmap, CameraFile.this.picpathcrop);
			CameraFile.this.delPhoto(CameraFile.this.picpathsave);
			Intent intent = new Intent();
			intent.setAction(CameraFile.RECEIVER_PHOTO);
			intent.putExtra("why", "更换头像");
			intent.putExtra("to", "MainActivity");
			intent.putExtra("what", CameraFile.this.picpathcrop);
			Log.d("MYAPP", " 发广播啦 :" + CameraFile.this.picpathcrop);
			CameraFile.this.sendBroadcast(intent);
			Intent data = new Intent();
			data.putExtra("result_phone", CameraFile.this.picpathcrop);
			CameraFile.this.setResult(32, data);
			return CameraFile.this.picpathsave;
			} catch (Exception e) {
				e.printStackTrace();
			}
		return null;
		}
	
	protected void onPostExecute(String result)
	{
		super.onPostExecute(result);
		CameraFile.this.finish();
		}
	
	protected void onPreExecute()
	{
		
	}
	
	protected void onProgressUpdate(String[] values)
	{
	}
	protected void onCancelled()
	{
		
	}
	}
	private class mAsyncTask extends AsyncTask<String, String, Bitmap>
	{
		private mAsyncTask()
		{
			
		}
		protected Bitmap doInBackground(String[] params)
		{
			return BitmapRead.decodeSampledBitmapFromFile(params[0], Device.getMeticsW(CameraFile.this) * 2, 0.0F);
			}
		protected void onPostExecute(Bitmap result) 
		{
			super.onPostExecute(result);
			CameraFile.this.setBitmap(result);
			}
		
		protected void onPreExecute() {
			MLog.d("start");
			}
		
		protected void onProgressUpdate(String[] values)
		{
			MLog.d("onProgressUpdate");
			}
		protected void onCancelled()
		{
			MLog.d("onCancelled");
			}
		}
}