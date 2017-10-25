package com.example.handlerpractice;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Handler.Callback;

public class MainActivity extends Activity {
    
	private TextView textv;
	
	private Handler handler = new Handler(new Callback() {
		
		@Override
		public boolean handleMessage(Message msg) {
			
			Toast.makeText(getApplicationContext(), "" + 1,1).show();
			
			return false;
		}
	}){
		public void handleMessage(Message msg){
			
			Toast.makeText(getApplicationContext(), "" + 2,1).show();
			
			textv.setText("" + msg.arg1 + "-" + "" + msg.arg2 + msg.obj);
		}
	};
	
/*	private Handler handler = new Handler(){
		
		
		public void handleMessage(android.os.Message msg) {
			
			textv.setText("" + msg.arg1 + "-" + "" + msg.arg2 + msg.obj);
		};
	};*/
	private ImageView imageView;
    private int images[] = {R.drawable.page05,R.drawable.page06,R.drawable.page07};
    private int index = 0;
    private Button button1;
    
    MyRunnable myRunnable = new MyRunnable();
    
    class Person{
    	public int age;
    	public String name;
    	@Override
    	public String toString() {
    		// TODO Auto-generated method stub
    		return "age =" + age + "name = " + name;
    	}
    }
	
    class MyRunnable implements Runnable{

		@Override
		public void run() {
			index++;
			index = index % 3;
			imageView.setImageResource(images[index]);
			handler.postDelayed(myRunnable, 1000);
			
		}
    	
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textv = (TextView) findViewById(R.id.text);
        imageView = (ImageView) findViewById(R.id.imageView1);
        button1 = (Button) findViewById(R.id.button1);
        handler.postDelayed(myRunnable, 1000);
        
        new Thread(){
        	
        	public void run() {
        	 try {
				Thread.sleep(2000);
//				android.os.Message message = new android.os.Message();
				android.os.Message message = handler.obtainMessage();
				message.arg1 = 88;
				message.arg2 = 1000;
				Person person = new Person();
				person.age = 18;
				person.name = "tony";
				message.obj = person;
//				handler.sendMessage(message);
				message.sendToTarget();//sendToTarget中封装的也是sendMesssage方法
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	};
        	
        }.start();
        
        button1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
//				handler.removeCallbacks(myRunnable);  //将线程终止了
				handler.sendEmptyMessage(1);
				
			}
		});
        /*new Thread(){
        	public void run() {
        		try {
					Thread.sleep(3000);

					handler.post(new Runnable() {
						
						@Override
						public void run() {
							textv.setText("update handler");							
							
						}
					});
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	};
       
        	
        }.start();*/
           /*//查看当前线程
           Log.d("jrr", "current thread name :"+Thread.currentThread().getName());
            //线程休眠5s制造ANR
           Thread thread = Thread.currentThread();
           try {
			thread.sleep(3000);
			textv.setText("handler practice");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
       
    }
    
}
