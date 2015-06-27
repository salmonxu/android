package com.salmon.application;

import android.app.Application;

/**
 * 
 * Author: xuchenggong Date: 2015-6-11 Time: 下午7:40:16
 */
public class BaseApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		// 初始化LOG工具�?
		initLog();
		//初始化异常处�?
		initException();

	}

	private void initException() {
		ExceptionHandler handler  = ExceptionHandler.getInstance(getApplicationContext());
		handler.init();
	}

	private void initLog() {
	

}