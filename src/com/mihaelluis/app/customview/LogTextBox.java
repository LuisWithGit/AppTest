package com.mihaelluis.app.customview;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.method.MovementMethod;
import android.text.method.ScrollingMovementMethod;
import android.text.style.BackgroundColorSpan;
import android.text.style.CharacterStyle;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

public class LogTextBox extends TextView {
	public interface Level
	{
		public final int V = 1;
		public final int D = 2;
		public final int I = 3;
		public final int W = 4;
		public final int E = 5;
		
	}
	public LogTextBox(Context context) {
		this(context, null);
		setMovementMethod(new ScrollingMovementMethod());
	}

	public LogTextBox(Context context, AttributeSet attrs) {
		this(context, attrs, android.R.attr.textViewStyle);
		setMovementMethod(new ScrollingMovementMethod());
	}

	public LogTextBox(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		setMovementMethod(new ScrollingMovementMethod());
	}

	@Override
	protected MovementMethod getDefaultMovementMethod() {
		return ScrollingMovementMethod.getInstance();
	}

	@Override
	public Editable getText() {
		return (Editable) super.getText();
	}

	@Override
	public void setText(CharSequence text, BufferType type) {
		super.setText(text, BufferType.EDITABLE);
	}
	
	private String getCurrentTimeText()
	{
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm:ss.SSS", Locale.getDefault());
		return sdf.format(now);
	}
	
	private void scrollToBottom()
	{

		int scroll_amount = (int) (getLineCount() * getLineHeight()) - (getBottom() - getTop());
	    scrollTo(0, scroll_amount+5);
	}

	synchronized public void log(String text)
	{
		String time = getCurrentTimeText() + "\t";
		String log = text + "\n";

		super.append(time + log);
		scrollToBottom();
	}
	synchronized public void log(int level, String tag, String text)
	{
		if(tag == null)
		{
			log(level, text);
			return;
		}
		
		String newLine = "";
		if(getLineCount() > 0)
		{
			newLine = "\n";
		}
		String prefix = newLine + getCurrentTimeText() + "\t" + tag + "\t";
		String log = text;
		super.append(
				getSpannableString(
						level, 
						prefix.length() - tag.length() -1,
						prefix.length() + log.length(),
						prefix + log)
						);
		scrollToBottom();
	}
	
	synchronized public void log(int level, String text)
	{
		String newLine = "";
		if(getLineCount() > 0)
		{
			newLine = "\n";
		}
		String prefix = newLine + getCurrentTimeText() + "\t";
		String log = text;
		super.append(
				getSpannableString(
						level, 
						prefix.length(),
						prefix.length() + log.length(),
						prefix + log)
						);
		scrollToBottom();
		
	}
	
	private SpannableStringBuilder getSpannableString(int level, int start, int end, String text)
	{
		SpannableStringBuilder span = new SpannableStringBuilder(text);
		CharacterStyle color = null;
		switch(level)
		{
		case Level.D:
			color = new ForegroundColorSpan(Color.BLUE);
			break;
		case Level.I:
			color = new ForegroundColorSpan(Color.GREEN);
			break;
		case Level.W:
			color = new BackgroundColorSpan(Color.YELLOW);
			break;
		case Level.E:
			color = new ForegroundColorSpan(Color.RED);
			break;
		default:
			color = new ForegroundColorSpan(Color.BLACK);
		}
		span.setSpan(color,start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		return span;
	}
}