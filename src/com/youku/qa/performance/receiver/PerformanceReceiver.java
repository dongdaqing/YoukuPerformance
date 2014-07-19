package com.youku.qa.performance.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class PerformanceReceiver extends BroadcastReceiver{
	
	String TAG = "YoukuPerformance:"+PerformanceReceiver.class.getSimpleName();
	String SERVICE_ACTION = "com.youku.qa.performance.service.PERFORMANCE_SERVICE";

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		Toast.makeText(context, TAG+"receive broadcast form youku home page", 1000).show();
		Log.i(TAG, "receive broadcast form youku home page");
		
		startPerformanceService(context);
	}
	
	private void startPerformanceService(Context context){
		
		Intent intent = new Intent();
		intent.setAction(SERVICE_ACTION);
		context.startService(intent);
	}

}
