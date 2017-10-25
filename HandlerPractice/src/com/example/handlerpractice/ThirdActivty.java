package com.example.handlerpractice;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.widget.TextView;
import android.widget.Toast;

public class ThirdActivty extends Activity {
	
	
	private TextView textView;
	private HandlerThread thread;
	private Handler handler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		textView = new TextView(this);
		textView.setText("handler thread");
		setContentView(textView);
		
		thread = new HandlerThread("handler Thread");
		thread.start();
		
		handler = new Handler(thread.getLooper()){//handler是在handlerThread的这个子线程中处理的
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				Toast.makeText(ThirdActivty.this, "current Thread --->" + Thread.currentThread(), 1).show();
			}
		};
		
		handler.sendEmptyMessage(1);
	}

}
