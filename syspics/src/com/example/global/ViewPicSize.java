package com.example.global;

public class ViewPicSize {

	private static ViewPicSize size = null;

	private ViewPicSize() {
	}

	public static ViewPicSize getInstance() {
		if (null == size) {
			size = new ViewPicSize();
		}
		return size;
	}

	private int intScreenWidth;
	private int intScreenHeight;

	public void init(int screenWidth, int screenHeight) {
		intScreenWidth = screenWidth;
		intScreenHeight = screenHeight;
		order = 0;
		initViewParam();
	}

	private int order = -1;

	public int getPreViewWidth() {
		judgePre();
		return preViewWidth;
	}

	public int getPreViewHeight() {
		judgePre();
		return preViewHeight;
	}

	public int getPreViewLeft() {
		judgePre();
		return preViewLeft;
	}

	public int getPreViewTop() {
		judgePre();
		return preViewTop;
	}

	private void judgePre() {
		if (order < 1) {
			try {
				throw new Exception();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public int getCutWidth() {
		judgeCut();
		return cutWidth;
	}

	public int getCutHeight() {
		judgeCut();
		return cutHeight;
	}

	public int getCutX() {
		judgeCut();
		return cutX;
	}

	public int getCutY() {
		judgeCut();
		return cutY;
	}

	private void judgeCut() {
		if (order < 2) {
			try {
				throw new Exception();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	final private double PRESIZERATE = 1.0;
	final private double PRETOPRATE = 0.0;

	private int preViewWidth;
	private int preViewHeight;
	private int preViewLeft;
	private int preViewTop;

	private int cutWidth;
	private int cutHeight;
	private int cutX;
	private int cutY;
	private double heightRate;

	private void initViewParam() {
		if (order < 0) {
			try {
				throw new Exception();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		preViewWidth = (int) (intScreenWidth * PRESIZERATE) + 1;
		// check preViewWidth<intScreenWidth
		preViewWidth = preViewWidth > intScreenWidth ? intScreenWidth
				: preViewWidth;

		preViewLeft = (intScreenWidth - preViewWidth) / 2;
		preViewTop = (int) (intScreenHeight * PRETOPRATE);
		preViewHeight = preViewWidth;
		heightRate = ((double) preViewHeight + 1) / (double) intScreenHeight;
		order = 1;
	}

	public void initPicParam(int picWidth, int picHeight) {
		if (order != 1) {
			try {
				throw new Exception();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		cutY = (int) (picHeight * PRETOPRATE) - 1;
		cutY = cutY < 0 ? 0 : cutY;
		cutWidth = (int) (picWidth * PRESIZERATE) + 1;
		cutWidth = cutWidth > picWidth ? picWidth : cutWidth;
		cutX = (picWidth - cutWidth) / 2;
		cutX = cutX < 0 ? 0 : cutX;
		cutHeight = (int) (picHeight * heightRate * 1.1) + 1;
		order = 2;
	}
}
