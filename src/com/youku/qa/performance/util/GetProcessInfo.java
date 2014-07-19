package com.youku.qa.performance.util;

import java.util.ArrayList;
import java.util.List;

import android.app.ActivityManager;
import android.content.Context;

public class GetProcessInfo {
	
	private String TAG = "YoukuPerformance:"+GetProcessInfo.class.getSimpleName();
	private String YoukuPackageName = "com.youku.phone";
	
	public ProcessInfo getYoukuProcessInfo(Context context){
		ActivityManager am;
		ProcessInfo youkuProcess = new ProcessInfo();
		
		am = (ActivityManager)context.getSystemService(context.ACTIVITY_SERVICE);
		List<ActivityManager.RunningAppProcessInfo> appProcessList = am.getRunningAppProcesses();
		for(ActivityManager.RunningAppProcessInfo appProcessInfo : appProcessList){
			if(appProcessInfo.processName.equals(YoukuPackageName)){
				youkuProcess.setPid(appProcessInfo.pid);
				youkuProcess.setUid(appProcessInfo.uid);
				youkuProcess.setProcessName(appProcessInfo.processName);
				break;
			}
		}
		return youkuProcess;
	}
	
//	public List<ProcessInfo> getYoukuProcessInfo(Context context){
//		ActivityManager am;
//		List<ProcessInfo> youkuProcessList = new ArrayList<ProcessInfo>();
//		ProcessInfo youkuProcess = new ProcessInfo();
//		
//		am = (ActivityManager)context.getSystemService(context.ACTIVITY_SERVICE);
//		List<ActivityManager.RunningAppProcessInfo> appProcessList = am.getRunningAppProcesses();
//		for(ActivityManager.RunningAppProcessInfo appProcessInfo : appProcessList){
//			if(appProcessInfo.processName.equals(YoukuPackageName)){
//				youkuProcess.setPid(appProcessInfo.pid);
//				youkuProcess.setUid(appProcessInfo.uid);
//				youkuProcess.setPackageName(appProcessInfo.processName);
//				youkuProcessList.add(youkuProcess);
//				break;
//			}
//		}
//		return youkuProcessList;
//	}
}
