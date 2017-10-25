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
	 * ���̴߳�����handler��Ĭ���������һ��looper��֮��Ӧ,���������Լ������߳��д���handler�����ʱ������Ӧ��Ҫ��������һ��looper
	 * 
	 * ����Handler�����ĸ��̴߳��������ĸ��̴߳���Message����ѯ�����ɱ���̸߳�������Handler����Message��
	 */
	
	class MyThread extends Thread{
		
		private Handler handler;
		public Looper looper;
	@Override
	public void run() {
		
		 Looper.prepare();//���߳���ͨ��Looper.prepare()��������һ�����߳���ص�Looper����
		 looper = Looper.myLooper();
		 
		
		handler = new Handler(){
			
		@Override	
		public void handleMessage(Message msg) {
			
//			Log.i("jrr", "current Thread = "+Thread.currentThread());
//			System.out.println("current Thread = "+Thread.currentThread());
			Toast.makeText(SecondActivity.this , "Thread = "+Thread.currentThread(), 1).show();
		}};
		
		
	   Looper.loop();//����Looper�����loop()����ȥ��ѯ����MessageQueue
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
		
		/*handler = new Handler(thread.looper) {//���̲߳����������߳����л��Ĺ����У�����looper����û�д������ᱬ�����еĿ�ָ��
			@Override                           //������̲߳������⣬����ͨ��handlerThread
			public void handleMessage(Message msg) {
				super.handleMessage(msg);

			}
		};
		
		handler.sendEmptyMessage(1);*/
	}
}
