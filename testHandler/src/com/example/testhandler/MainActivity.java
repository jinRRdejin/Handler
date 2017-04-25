package com.example.testhandler;

import java.util.Timer;
import java.util.TimerTask;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.text.format.Time;
import android.view.Menu;
import android.widget.ImageView;

public class MainActivity extends Activity {
	
	public ImageView iv;
	int [] imageIds = new int[]{
		R.drawable.page09,R.drawable.page10,
		R.drawable.page11,R.drawable.page13	
	};
	
	int currentImageId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        iv = (ImageView) findViewById(R.id.iv);
        
        final Handler myHandler= new Handler(){
        	
        	@Override
        	public void handleMessage(Message msg) {
        		
        		if(msg.what == 0x1233){
        			//动态修改显示的图片
        			iv.setImageResource(imageIds[currentImageId ++ ]);
        			
        			if(currentImageId >= 4){
        				currentImageId = 0;
        			}
        		}
        		
        	}
        	
        };
        
       new Timer().schedule(new TimerTask() {
		
		@Override
		public void run() {
			
		Message	msg = new Message();
		msg.what = 0x1233;
		
		myHandler.sendMessage(msg);
		  }
	    }, 0 ,800);
    }

}
