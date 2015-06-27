package com.salmon.exception;

import java.lang.Thread.UncaughtExceptionHandler;

import android.content.Context;
import android.widget.Toast;

/**
 * UncaughtException处理类,当程序发生Uncaught异常的时候,有该类来接管程序,并记录发送错误报告. Author:
 * xuchenggong Date: 2015-6-26 Time: 下午4:16:47
 */
public class ExceptionHandler implements UncaughtExceptionHandler {
	// 全局上下文
	private Context mContext;
	private static ExceptionHandler instance;

	private static UncaughtExceptionHandler mHandler;

	private ExceptionHandler(Context context) {
		this.mContext = context;
	}

	/**
	 * 异常处理类单例方法
	 * 
	 * @return
	 */
	public static ExceptionHandler getInstance(Context context) {
		if (null == instance) {
			instance = new ExceptionHandler(context);

		}
		return instance;
	}

	public void init() {
		// 获取系统默认的异常处理器
		mHandler = Thread.getDefaultUncaughtExceptionHandler();
		// 设置本异常处理类为程序默认的处理器
		Thread.setDefaultUncaughtExceptionHandler(this);
	}

	@Override
	public void uncaughtException(Thread thread, Throwable throwable) {
             if (!handleException(throwable)&&mHandler!=null) {
            	   // 如果用户没有处理则让系统默认的异常处理器来处理  
				mHandler.uncaughtException(thread, throwable);
			}else {
				Toast.makeText(mContext, "程序异常，即将处理", Toast.LENGTH_LONG).show();
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				android.os.Process.killProcess(android.os.Process.myPid());
				System.exit(1);
				  //重启程序
			// Intent intent = new Intent();
			// intent.setClass(mContext,MainActivity.class);
			// intent.addFlag(Intent.FLAG_ACTIVITY_NEW_TASK);
			// mContext.startActivity(intent);
				
			}
	}

	private boolean handleException(Throwable ex) {
		if (null == ex) {
			return false;
		}
		return true;

	}

}
