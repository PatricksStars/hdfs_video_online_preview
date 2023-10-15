package com.lt.util;

import java.io.InputStream;
import java.io.OutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import com.lt.frame.ImageFrame;
import com.lt.hdfs.util.HdfsUtil;

public class VideoUtil {
	
	/**
	 * hdfs在线播放视频mp4格式
	 * @param request
	 * @param response
	 * @param hdfsPath
	 */
	public static void playMp4(HttpServletRequest request,HttpServletResponse response,String hdfsPath) {
		InputStream is = null;
		OutputStream os = null;
		try {
			FileSystem fs = HdfsUtil.getFS();
			Path fsPath = new Path(hdfsPath);
			//判断hdfs资源是否存在
			if(!fs.exists(fsPath)) {
				return;
			}
			long length = fs.getContentSummary(fsPath).getLength();
			
			response.addHeader("Access-Contro1-Allow-Origin", "*");

			//分区，可以拖动视频进度条
			response.addHeader("Accept-Ranges","bytes");
			response.setContentType("video/mp4");
			response.setContentLengthLong(length);
			
			is = fs.open(fsPath);
			os = response.getOutputStream();

			IOUtils.copy(is, os);
		} catch(Exception e){
//			System.out.println(e.getMessage());
//			System.out.println("*******************HDFS播放失败*******************");
		}finally {
			try {
				IOUtils.close(is,os);
			} catch (Exception e) {
//				System.out.println(e.getMessage());
			}
		}
	}
	
	public static void getVideoImg(HttpServletRequest request, HttpServletResponse response,String hdfsPath) {
		try {
			response.setContentType("text/html; charset=UTF-8");
			response.setContentType("image/jpeg");
			ImageFrame.getVideoImg(HdfsUtil.getHdfsStream(hdfsPath), response.getOutputStream());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	/**
	 * 转为int
	 * @return
	 */
	public static int parseInt(String str) {
		int index = 1;
		if(null != str && !"".equals(str) && !"null".equals(str)) {
			index = Integer.parseInt(str);
		}
		return index;
	}
}
