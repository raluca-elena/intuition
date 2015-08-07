package com.example.android.momintuition; /**
 * @author Raghav Sood
 * @version 1
 * @date 26 January, 2013
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * The Class CircularSeekBar.
 */
public class CircularSeekBar extends View {

	/** The context */
	private Context mContext;

	/** The listener to listen for changes */
	private OnSeekChangeListener mListener;

	/** The color of the progress ring */
	private Paint circleColor;

	/** the color of the inside circle. Acts as background color */
	private Paint innerColor;

	/** The progress circle ring background */
	private Paint circleRing;

	/** The angle of progress */
	private int angle = 0;

	/** The start angle (12 O'clock */
	private int startAngle = 270;

	/** The width of the progress ring */
	private int barWidth = 5;

	/** The width of the view */
	private int width;

	/** The height of the view */
	private int height;

	private static boolean alreadyDone = false;



	private int dividew = 1;
	private int divideh = 1;
	/** The maximum progress amount */
	private int maxProgress = 100;

	/** The current progress */
	private int progress;

	/** The progress percent */
	private int progressPercent;

	/** The radius of the inner circle */
	private float innerRadius;

	private float innerRadius1;

	/** The radius of the outer circle */
	private float outerRadius;
	private float outerRadius1;


	///no need fo other center as all we'll have the same center
	/** The circle's center X coordinate */
	private float cx;

	/** The circle's center Y coordinate */
	private float cy;

	/** The left bound for the circle RectF */
	private float left;
	private float left1;

	/** The right bound for the circle RectF */
	private float right;
	private float right1;

	/** The top bound for the circle RectF */
	private float top;
    private float top1;

	/** The bottom bound for the circle RectF */
	private float bottom;
    private float bottom1;

	/** The X coordinate for the top left corner of the marking drawable */
	private float dx;
	private float dx1;

	/** The Y coordinate for the top left corner of the marking drawable */
	private float dy;
	private float dy1;

	/** The X coordinate for 12 O'Clock */
	private float startPointX;
	private float startPointX1;

	/** The Y coordinate for 12 O'Clock */
	private float startPointY;
	private float startPointY1;

	/**
	 * The X coordinate for the current position of the marker, pre adjustment
	 * to center
	 */
	private float markPointX;
	private float markPointX1;

	/**
	 * The Y coordinate for the current position of the marker, pre adjustment
	 * to center
	 */
	private float markPointY;
	private float markPointY1;


	/**
	 * The adjustment factor. This adds an adjustment of the specified size to
	 * both sides of the progress bar, allowing touch events to be processed
	 * more user friendly (yes, I know that's not a word)
	 */
	private float adjustmentFactor = 100;

	/** The progress mark when the view isn't being progress modified */
	private Bitmap progressMark;

	/** The progress mark when the view is being progress modified. */
	private Bitmap progressMarkPressed;

	/** The flag to see if view is pressed */
	private boolean IS_PRESSED = false;

	/**
	 * The flag to see if the setProgress() method was called from our own
	 * View's setAngle() method, or externally by a user.
	 */
	private boolean CALLED_FROM_ANGLE = false;

	private boolean SHOW_SEEKBAR = true;

	public void adjustRadius( int w, int h){

		dividew = w;
		divideh = h;

	}

	/** The rectangle containing our circles and arcs. */
	private RectF rect = new RectF();
	private RectF rect1 = new RectF();

	{
		mListener = new OnSeekChangeListener() {

			@Override
			public void onProgressChange(CircularSeekBar view, int newProgress) {

			}
		};

		circleColor = new Paint();
		innerColor = new Paint();
		circleRing = new Paint();

		circleColor.setColor(Color.parseColor("#ff33b5e5")); // Set default
		// progress
		// color to holo
		// blue.
		innerColor.setColor(Color.parseColor("#ff33b5e5")); // Set default background color to
		// black
		circleRing.setColor(Color.GREEN);// Set default background color to Gray

		circleColor.setAntiAlias(true);
		innerColor.setAntiAlias(true);
		//circleRing.setAntiAlias(true);

		circleColor.setStrokeWidth(5);
		innerColor.setStrokeWidth(5);
		circleRing.setStrokeWidth(5);

		//circleColor.setStyle(Paint.Style.FILL);
		innerColor.setStyle(Paint.Style.STROKE);
		circleRing.setStyle(Paint.Style.STROKE);
		//innerColor.setStrokeWidth(20);

	}

	/**
	 * Instantiates a new circular seek bar.
	 * 
	 * @param context
	 *            the context
	 * @param attrs
	 *            the attrs
	 * @param defStyle
	 *            the def style
	 */
	public CircularSeekBar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		mContext = context;
		initDrawable();
	}

	/**
	 * Instantiates a new circular seek bar.
	 * 
	 * @param context
	 *            the context
	 * @param attrs
	 *            the attrs
	 */
	public CircularSeekBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		initDrawable();
	}

	/**
	 * Instantiates a new circular seek bar.
	 * 
	 * @param context
	 *            the context
	 */
	public CircularSeekBar(Context context) {
		super(context);
		alreadyDone = true;

		mContext = context;
		initDrawable();
	}

	/**
	 * Inits the drawable.
	 */
	public void initDrawable() {
		progressMark = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.scrubber_control_normal_holo);
		progressMarkPressed = BitmapFactory.decodeResource(mContext.getResources(),
				R.drawable.scrubber_control_pressed_holo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View#onMeasure(int, int)
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		Log.i("Circle draw", "CIRCLE");
		width = getWidth(); // Get View Width
		height = getHeight();// Get View Height

		int size = (width > height) ? height : width; // Choose the smaller
		// between width and
		// height to make a
		// square

		cx = width / 2; // Center X for circle
		cy = height / 2; // Center Y for circle

		//aici se seteaza raza cercului
		outerRadius1 = (size / 2)/Math.max(divideh, dividew); // Radius of the outer circle
		outerRadius = size / 2;

		innerRadius = outerRadius - barWidth; // Radius of the inner circle
        innerRadius1 = outerRadius1 - barWidth;

		left = cx - outerRadius; // Calculate left bound of our rect
		left1 = cx - outerRadius1;

		right = cx + outerRadius;// Calculate right bound of our rect
		right1 = cx + outerRadius1;

		top = cy - outerRadius;// Calculate top bound of our rect
		top1 = cy - outerRadius1;

		bottom = cy + outerRadius;// Calculate bottom bound of our rect
		bottom1 = cy + outerRadius1;

		startPointX = cx; // 12 O'clock X coordinate

		startPointY = cy - outerRadius;// 12 O'clock Y coordinate
		startPointY1 = cy - outerRadius1;

		markPointX = startPointX;// Initial locatino of the marker X coordinate
		markPointX1 = startPointX1;
		markPointY = startPointY;// Initial locatino of the marker Y coordinate
		markPointY1 = startPointY1;

		rect.set(left, top, right, bottom); // assign size to rect
		rect1.set(left1, top1, right1, bottom1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View#onDraw(android.graphics.Canvas)
	 */
	@Override
	protected void onDraw(Canvas canvas) {


	//canvas.drawBitmap(b, 0, 0, p);//}
		//canvas.drawCircle(cx, cy, outerRadius, circleRing);
		//aloha
		canvas.drawColor(Color.TRANSPARENT);
		canvas.drawArc(rect, startAngle, angle, false, innerColor);
		canvas.drawArc(rect1, startAngle, angle, false, innerColor);

		//aloha
		//canvas.drawCircle(cx, cy, innerRadius, innerColor);

		//canvas.drawCircle(cx, cy, innerRadius, innerColor);


		if(SHOW_SEEKBAR){
			dx = getXFromAngle();
			dy = getYFromAngle();
			drawMarkerAtProgress(canvas);
		}
		super.onDraw(canvas);
		//c = canvas;
	}

	/**
	 * Draw marker at the current progress point onto the given canvas.
	 * 
	 * @param canvas
	 *            the canvas
	 */
	public void drawMarkerAtProgress(Canvas canvas) {
		if (IS_PRESSED) {
			canvas.drawBitmap(progressMarkPressed, dx, dy, null);
			Log.i("draw parker  dx", dx + "");
			Log.i("draw marker  dy" , dy + "");
		} else {
			canvas.drawBitmap(progressMark, dx, dy, null);
			//canvas.drawBitmap(progressMarkPressed, dx, dy, null);

		}
	}

	/**
	 * Gets the X coordinate of the arc's end arm's point of intersection with
	 * the circle
	 * 
	 * @return the X coordinate
	 */
	public float getXFromAngle() {
		int size1 = progressMark.getWidth();
		int size2 = progressMarkPressed.getWidth();
		int adjust = (size1 > size2) ? size1 : size2;
		float x = markPointX - (adjust / 2);
		return x;
	}

	/**
	 * Gets the Y coordinate of the arc's end arm's point of intersection with
	 * the circle
	 * 
	 * @return the Y coordinate
	 */
	public float getYFromAngle() {
		int size1 = progressMark.getHeight();
		int size2 = progressMarkPressed.getHeight();
		int adjust = (size1 > size2) ? size1 : size2;
		float y = markPointY - (adjust / 2);
		return y;
	}

	/**
	 * Get the angle.
	 * 
	 * @return the angle
	 */
	public int getAngle() {
		return angle;
	}

	/**
	 * Set the angle.
	 * 
	 * @param angle
	 *            the new angle
	 */
	public void setAngle(int angle) {
		this.angle = angle;
		float donePercent = (((float) this.angle) / 360) * 100;
		float progress = (donePercent / 100) * getMaxProgress();
		setProgressPercent(Math.round(donePercent));
		CALLED_FROM_ANGLE = true;
		setProgress(Math.round(progress));
	}

	/**
	 * Sets the seek bar change listener.
	 * 
	 * @param listener
	 *            the new seek bar change listener
	 */
	public void setSeekBarChangeListener(OnSeekChangeListener listener) {
		mListener = listener;
	}

	/**
	 * Gets the seek bar change listener.
	 * 
	 * @return the seek bar change listener
	 */
	public OnSeekChangeListener getSeekBarChangeListener() {
		return mListener;
	}

	/**
	 * Gets the bar width.
	 * 
	 * @return the bar width
	 */
	public int getBarWidth() {
		return barWidth;
	}

	/**
	 * Sets the bar width.
	 * 
	 * @param barWidth
	 *            the new bar width
	 */
	public void setBarWidth(int barWidth) {
		this.barWidth = barWidth;
	}

	/**
	 * The listener interface for receiving onSeekChange events. The class that
	 * is interested in processing a onSeekChange event implements this
	 * interface, and the object created with that class is registered with a
	 * component using the component's
	 * <code>setSeekBarChangeListener(OnSeekChangeListener)<code> method. When
	 * the onSeekChange event occurs, that object's appropriate
	 * method is invoked.
	 * 
	 * @see OnSeekChangeEvent
	 */
	public interface OnSeekChangeListener {

		/**
		 * On progress change.
		 * 
		 * @param view
		 *            the view
		 * @param newProgress
		 *            the new progress
		 */
		public void onProgressChange(CircularSeekBar view, int newProgress);
	}

	/**
	 * Gets the max progress.
	 * 
	 * @return the max progress
	 */
	public int getMaxProgress() {
		return maxProgress;
	}

	/**
	 * Sets the max progress.
	 * 
	 * @param maxProgress
	 *            the new max progress
	 */
	public void setMaxProgress(int maxProgress) {
		this.maxProgress = maxProgress;
	}

	/**
	 * Gets the progress.
	 * 
	 * @return the progress
	 */
	public int getProgress() {
		return progress;
	}

	/**
	 * Sets the progress.
	 * 
	 * @param progress
	 *            the new progress
	 */
	public void setProgress(int progress) {
		if (this.progress != progress) {
			this.progress = progress;
			if (!CALLED_FROM_ANGLE) {
				int newPercent = (this.progress * 100) / this.maxProgress;
				int newAngle = (newPercent * 360) / 100 ;
				this.setAngle(newAngle);
				//aloha

				this.setProgressPercent(newPercent);
			}
			mListener.onProgressChange(this, this.getProgress());
			CALLED_FROM_ANGLE = false;
		}
	}

	/**
	 * Gets the progress percent.
	 * 
	 * @return the progress percent
	 */
	public int getProgressPercent() {
		return progressPercent;
	}

	/**
	 * Sets the progress percent.
	 * 
	 * @param progressPercent
	 *            the new progress percent
	 */
	public void setProgressPercent(int progressPercent) {
		this.progressPercent = progressPercent;
	}

	/**
	 * Sets the ring background color.
	 * 
	 * @param color
	 *            the new ring background color
	 */
	public void setRingBackgroundColor(int color) {
		circleRing.setColor(color);
	}

	/**
	 * Sets the back ground color.
	 * 
	 * @param color
	 *            the new back ground color
	 */
	public void setBackGroundColor(int color) {
		innerColor.setColor(color);
	}

	/**
	 * Sets the progress color.
	 * 
	 * @param color
	 *            the new progress color
	 */
	public void setProgressColor(int color) {
		circleColor.setColor(color);
		//circleColor.setColor(Color.TRANSPARENT);
	}


	//aici dar fara event
	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View#onTouchEvent(android.view.MotionEvent)
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		float x = event.getX();
		float y = event.getY();

		Log.i("--moved x", x + "");
		Log.i("--moved y", y + "");

		boolean up = false;
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			moved(x, y, up);
			break;
		case MotionEvent.ACTION_MOVE:
			moved(x, y, up);
			break;
		case MotionEvent.ACTION_UP:
			up = true;
			moved(x, y, up);
			break;
		}
		return true;
	}

	/**
	 * Moved.
	 * 
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 * @param up
	 *            the up
	 */
	private void moved(float x, float y, boolean up) {
		float distance = (float) Math.sqrt(Math.pow((x - cx), 2) + Math.pow((y - cy), 2));
		if (distance < outerRadius + adjustmentFactor && distance > innerRadius - adjustmentFactor && !up) {
			IS_PRESSED = true;

			markPointX = (float) (cx + outerRadius * Math.cos(Math.atan2(x - cx, cy - y) - (Math.PI /2)));
			markPointX1 = (float) (cx + outerRadius * Math.cos(Math.atan2(x - cx, cy - y) - (Math.PI /2)));
			markPointY = (float) (cy + outerRadius * Math.sin(Math.atan2(x - cx, cy - y) - (Math.PI / 2)));
			markPointY1 = (float) (cy + outerRadius * Math.sin(Math.atan2(x - cx, cy - y) - (Math.PI /2)));


			float degrees = (float) ((float) ((Math.toDegrees(Math.atan2(x - cx, cy - y)) + 360.0)) % 360.0);
			Log.i("---degrees", degrees+"");
			// and to make it count 0-360
			if (degrees < 0) {
				degrees += 2 * Math.PI;
			}

			setAngle(Math.round(degrees));
			invalidate();

		} else {
			IS_PRESSED = false;
			invalidate();
		}

	}

	/**
	 * Gets the adjustment factor.
	 * 
	 * @return the adjustment factor
	 */
	public float getAdjustmentFactor() {
		return adjustmentFactor;
	}

	/**
	 * Sets the adjustment factor.
	 * 
	 * @param adjustmentFactor
	 *            the new adjustment factor
	 */
	public void setAdjustmentFactor(float adjustmentFactor) {
		this.adjustmentFactor = adjustmentFactor;
	}

	/**
	 * To display seekbar
	 */
	public void ShowSeekBar() {
		SHOW_SEEKBAR = true;
	}

	/**
	 * To hide seekbar
	 */
	public void hideSeekBar() {
		SHOW_SEEKBAR = false;
	}
}