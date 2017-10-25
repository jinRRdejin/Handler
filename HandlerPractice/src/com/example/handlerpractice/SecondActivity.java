package com.example.handlerpractice;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends Activity {
	
	private MyThread thread;
	
	private Handler handler = new Handler(){
		public void handleMessage(Message msg) {
		
			Toast.makeText(SecondActivity.this , " UI ----->Thresd ="+Thread.currentThread(), 1).show();
		};
	};
	
	/*
	 * 主线程创建的handler他默认里面会有一个looper与之对应,所以我们自己在子线程中创建handler对象的时候我们应该要自主创建一个looper
	 * 
	 * 所以Handler是在哪个线程创建就有哪个线程处理Message和轮询，而由别的线程负责给这个Handler发送Message。
	 */
	
	class MyThread extends Thread{
		
		private Handler handler;
		public Looper looper;
	@Override
	public void run() {
		
		 Looper.prepare();//在线程中通过Looper.prepare()方法创建一个与线程相关的Looper对象
		 looper = Looper.myLooper();
		 
		
		handler = new Handler(){
			
		@Override	
		public void handleMessage(Message msg) {
			
//			Log.i("jrr", "current Thread = "+Thread.currentThread());
//			System.out.println("current Thread = "+Thread.currentThread());
			Toast.makeText(SecondActivity.this , "Thread = "+Thread.currentThread(), 1).show();
		}};
		
		
	   Looper.loop();//调用Looper对象的loop()方法去轮询它的MessageQueue
	}
}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		TextView textview = new TextView(this);
		textview.setText("hello handler");
		setContentView(textview);
		
		
		
		thread = new MyThread();
		thread.start();
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		thread.handler.sendEmptyMessage(1);
		handler.sendEmptyMessage(1);
		
		/*handler = new Handler(thread.looper) {//多线程并发，两个线程在切换的过程中，可能looper对象还没有创建，会爆出这行的空指针
			@Override                           //解决多线程并发问题，可以通过handlerThread
			public void handleMessage(Message msg) {
				super.handleMessage(msg);

			}
		};
		
		handler.sendEmptyMessage(1);*/
	}
}
