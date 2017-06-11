package com.cg.courseapi.service;

import java.util.List;
import com.cg.courseapi.domain.Topic;


public interface TopicService {

	public List<Topic> getAllTopic();
	public Topic getTopic(String id);
	public void addTopic(Topic topic);
	public void updateTopic(String id, Topic topic);
	public void deletTopic(String id);
	
}
