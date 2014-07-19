package com.youku.qa.performance.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.youku.qa.performance.util.GetProcessInfo;
import com.youku.qa.performance.util.ProcessInfo;

import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class PerformanceService extends Service{
	
	private String TAG = "YoukuPerformance:"+PerformanceService.class.getSimpleName();
	private Handler handler = new Handler();
	private int uid,pid;
	private String processName;
	private GetProcessInfo getProcessInfo;
	
	public String resultFilePath;
	public BufferedWriter bw;
	public FileOutputStream out;
	public OutputStreamWriter osw;

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void onCreate(){
		super.onCreate();
		Log.i(TAG, "service create");
	}
	
	public void onStart(Intent intent, int startId){
		Toast.makeText(this, TAG, 1000).show();
		Log.i(TAG, "service start");
		initialProcessInfo();
		createResultFile();
//		handler.postDelayed(task, 1000);
		Toast.makeText(this, TAG+":file created", 1000).show();
	}
	
	public void initialProcessInfo(){
		getProcessInfo = new GetProcessInfo();
		ProcessInfo youkuProcess = new ProcessInfo();
		youkuProcess = getProcessInfo.getYoukuProcessInfo(getBaseContext());
		uid = youkuProcess.getUid();
		pid = youkuProcess.getPid();
		processName = youkuProcess.getProcessName();
	}
	
	Runnable task = new Runnable(){

		@Override
		public void run() {
			// TODO Auto-generated method stub
			dataRefresh();
			handler.postDelayed(task, 1000);
		}
		
	};
	
	private void dataRefresh(){
		
	}
	
	private void createResultFile(){
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String mDateTime;
		if((Build.MODEL.equals("sdk"))||(Build.MODEL.equals("google_sdk"))){
			mDateTime = formatter.format(cal.getTime().getTime()+8*60*60*1000);
		}else{
			mDateTime = formatter.format(cal.getTime().getTime());
		}
		if(android.os.Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED)){
			resultFilePath = android.os.Environment.getExternalStorageDirectory()
					+File.separator
					+"YoukuPerformance_TestResult_"+mDateTime+".csv";
		}else{
			resultFilePath = getBaseContext().getFilesDir().getPath()
					+File.separator+"YoukuPerformance_TestResult_"+mDateTime+".csv";
		}
		try {
			out = new FileOutputStream(resultFilePath);
			osw = new OutputStreamWriter(out,"GBK");
			bw = new BufferedWriter(osw);
			bw.write("packagename:,"+processName+"\r\n"
					+"pid:,"+pid+"\r\n"
					+"uid:,"+uid);
			try {
				if (bw != null)
					bw.close();
				if (osw != null)
					osw.close();
				if (out != null)
					out.close();
			} catch (Exception e) {
				Log.d(TAG, e.getMessage());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.e(TAG, e.getMessage());
		}
		
	}
	
	public void onDestroy(){
		super.onDestroy();
		Log.i(TAG, "service destroy");
	}

}
