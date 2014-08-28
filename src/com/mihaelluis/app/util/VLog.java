package com.mihaelluis.app.util;

import android.os.Handler;
import android.os.Message;

import com.mihaelluis.app.customview.LogTextBox;

public class VLog {
	
	private static class LogHandler extends Handler{
		static LogTextBox mLogBox;
		
		static LogHandler mInstance = null;
		
		static LogHandler getinstance(LogTextBox logBox) {
			if(mInstance == null)
			{
				mInstance = new LogHandler(logBox);
			}
			if(logBox != mLogBox)
			{
				mLogBox = logBox;
			}
			return mInstance;
		}

		private LogHandler(LogTextBox logBox) {
			mLogBox = logBox;
		}

		@Override
		public void handleMessage(Message msg) {
			LogMessage content = (LogMessage) msg.obj;
			mLogBox.log(msg.what, content.tag, content.log);
		}
	}
	
	private static LogHandler mHandler;

	public static void initialize(LogTextBox logBox) {
		mHandler = LogHandler.getinstance(logBox);
	}
	
	private static Message makeHandleMessage(int level, String tag, String log){
		Message msg = mHandler.obtainMessage();
		switch (level) {
		case LogTextBox.Level.D: {
			msg.what = LogTextBox.Level.D;
		}
			break;
		case LogTextBox.Level.I: {
			msg.what = LogTextBox.Level.I;
		}
			break;
		case LogTextBox.Level.W: {
			msg.what = LogTextBox.Level.W;
		}
			break;
		case LogTextBox.Level.E: {
			msg.what = LogTextBox.Level.E;
		}
			break;
		case LogTextBox.Level.V: {
			msg.what = LogTextBox.Level.V;
		}
		default: {
			msg.what = LogTextBox.Level.V;
		}
		}
		LogMessage content = new LogMessage(tag, log);
		msg.obj = content;
		return msg;
	}
	
	
	synchronized public static boolean d(String tag, String log)
	{
		Message msg = makeHandleMessage(LogTextBox.Level.D, tag, log);
		mHandler.sendMessage(msg);
		return true;
	}
	
	synchronized public static boolean i(String tag, String log)
	{
		Message msg = makeHandleMessage(LogTextBox.Level.I, tag, log);
		mHandler.sendMessage(msg);
		return true;
	}
	
	synchronized public static boolean w(String tag, String log)
	{
		Message msg = makeHandleMessage(LogTextBox.Level.W, tag, log);
		mHandler.sendMessage(msg);
		return true;
	}
	synchronized public static boolean e(String tag, String log)
	{
		Message msg = makeHandleMessage(LogTextBox.Level.E, tag, log);
		mHandler.sendMessage(msg);
		return true;
	}
	
	synchronized public static boolean v(String tag, String log)
	{
		Message msg = makeHandleMessage(LogTextBox.Level.V, tag, log);
		mHandler.sendMessage(msg);
		return true;
	}
	
	synchronized public static boolean d(String log)
	{
		Message msg = makeHandleMessage(LogTextBox.Level.D, null, log);
		mHandler.sendMessage(msg);
		return true;
	}
	
	synchronized public static boolean i(String log)
	{
		Message msg = makeHandleMessage(LogTextBox.Level.I, null, log);
		mHandler.sendMessage(msg);
		return true;
	}
	
	synchronized public static boolean w(String log)
	{
		Message msg = makeHandleMessage(LogTextBox.Level.W, null, log);
		mHandler.sendMessage(msg);
		return true;
	}
	synchronized public static boolean e(String log)
	{
		Message msg = makeHandleMessage(LogTextBox.Level.E, null, log);
		mHandler.sendMessage(msg);
		return true;
	}
	
	synchronized public static boolean v(String log)
	{
		Message msg = makeHandleMessage(LogTextBox.Level.V, null, log);
		mHandler.sendMessage(msg);
		return true;
	}
	
	

}
