package com.example.handlerpractice;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import android.widget.Toast;


/*
 * 线程更新UI的几种方法
 * runOnUiThread
 * handler post
 * handler sendMessage
 * view post
 */

public class fiveActivity extends Activity {
	
	private TextView textview;
    private Handler handler = new Handler(){
    	public void handleMessage(android.os.Message msg) {
    		textview.setText("update Message");
    		
    		switch (msg.what) {
			case 1:
				Toast.makeText(fiveActivity.this, "1", 1).show();
				break;
			case 2:
				Toast.makeText(fiveActivity.this, "2", 1).show();
				break;

			default:
				break;
			}
    	};
    };
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.five);
		textview = (TextView) findViewById(R.id.textView1);
		
		Thread thread = new Thread();
		thread.start();
		
		try {
			Thread.sleep(2000);
			
//			byHandlerPost();
//			byrunOnUiThread();
			bySendMessage();
//			byViewPost();
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void byHandlerPost(){
		
		handler.post(new Runnable() {
			
			@Override
			public void run() {
				textview.setText("update post");
			}
		});
	}
	
	public void byrunOnUiThread(){
		runOnUiThread(new Runnable() {
			
			@Override
			public void run() {

				textview.setText("update RunUi");
			}
		});	
	}
	
	public void bySendMessage(){
		
		handler.sendEmptyMessage(2);
		
	}
	
	public void byViewPost(){
		textview.post(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				textview.setText("update ViewPost");
			}
		});
	}
	
}
