package com.example.handlerpractice;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class forthActivity extends Activity implements OnClickListener{
	
	
	private Button button1;
	private Button button2;
	
	private Handler handlerThread;
	
	//�������̵߳�handler message
	private Handler handler = new Handler(){
		
		public void handleMessage(android.os.Message msg) {
			
			Message message = new Message();
			System.out.println("main Handler");
			Toast.makeText(forthActivity.this, "main Handler", Toast.LENGTH_SHORT).show();
			//�����̷߳�����Ϣ
			handlerThread.sendMessageDelayed(message, 1000);
			
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ll);
		button1 = (Button) findViewById(R.id.button1);
		button2 = (Button) findViewById(R.id.button2);
		button1.setOnClickListener(this);
		button2.setOnClickListener(this);
		
		HandlerThread thread = new HandlerThread("handler Thread");
		thread.start();
		
		//�������߳� handler
		handlerThread = new Handler(thread.getLooper()){

			public void handleMessage(Message msg) {
				
				Message message = new Message();
				System.out.println("Thread Handler");
				Toast.makeText(forthActivity.this, "Thread Handler", Toast.LENGTH_SHORT).show();
				
				//�����̷߳�����Ϣ
				handler.sendMessageDelayed(message, 1000);
				

			}
		};
		
		
		
	}

	@Override
	public void onClick(View v) {
		
		switch (v.getId()) {
		case R.id.button1:
			handler.sendEmptyMessage(1);
			break;
        case R.id.button2:
			handler.removeMessages(1);
			break;

		default:
			break;
		}
	}

}
