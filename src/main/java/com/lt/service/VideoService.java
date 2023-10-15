package com.lt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lt.bean.VideoBean;
import com.lt.repository.VideoRepository;

@Service
public class VideoService {

	@Autowired
	private VideoRepository videoRepository;
	/**
	 * 获取名称
	 * @param count
	 * @return
	 */
	public List<VideoBean> getVideoDatas(int count){
		int size = 10;
		StringBuilder querySQL = new StringBuilder();
		querySQL.append("select name,code from (select name,code,rownum rown from (select * from videos order by name desc)) where rown <= ")
		.append(count*size + size).append(" and rown > ").append(count*size).append(" order by name desc");
		return videoRepository.query(querySQL.toString());
	}
	
	/**
	 * 获取公司总数
	 * @return
	 */
	public int getVideoCount() {
		String countSQL = "select count(*) from videos";
		return videoRepository.count(countSQL);
		
	}
}
