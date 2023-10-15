package com.lt.action;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import com.lt.bean.VideoBean;
import com.lt.hdfs.util.HdfsUtil;
import com.lt.service.VideoService;
import com.lt.util.VideoUtil;

/**
 * 
 * 视频类
 * @author luot
 * @date   2023年6月20日
 *
 *
 */
@Controller
public class ActionVideos {
	
	@PostConstruct
	public void initHadoop() {
		try {
			HdfsUtil.getFS().getStatus();
		} catch (Exception e) {
			try {
				Runtime.getRuntime().exec(HdfsUtil.STOP_ALL_BAT);
				Runtime.getRuntime().exec(HdfsUtil.START_ALL_BAT);
			} catch (IOException eio) {
				eio.printStackTrace();
			}
		}
	}
	
	@Autowired
	private VideoService videoService;
	
	@GetMapping("/view")
	public String videoView(HttpServletRequest request,HttpServletResponse response) {
		String indexStr = request.getParameter("index");
		List<VideoBean> list = videoService.getVideoDatas(VideoUtil.parseInt(indexStr) - 1);
		int count = videoService.getVideoCount();
		request.setAttribute("videolist", list);
		request.setAttribute("count", (int)Math.ceil((double)count/10) + 1);
		return "videos";
	}
	
	@GetMapping("/video")
	public String video(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String videoname = request.getParameter("videoname");
		request.setAttribute("videoname", videoname);
		return "video";
	}
	
	
	@GetMapping("/videoplay")
	@CrossOrigin
	public void videoPlay(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String videoname = request.getParameter("videoname");
		String hdfsPath = HdfsUtil.VIDEO_DIR + videoname;
		VideoUtil.playMp4(request, response, hdfsPath);
	}
	
	@GetMapping("/videoimage")
	@CrossOrigin
	public void image(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String videoname = request.getParameter("videoname");
		String hdfsPath = HdfsUtil.VIDEO_DIR + videoname;
		VideoUtil.getVideoImg(request, response, hdfsPath);
	}
	
}
