package com.cg.courseapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cg.courseapi.domain.Topic;
import com.cg.courseapi.service.TopicService;


@RestController
public class TopicController {

	@Autowired
	private TopicService topicService;
	
	//readAll
	@RequestMapping("/topics")
	public List<Topic> getAllTopic() {
		return topicService.getAllTopic();
	}
	
	//readById
	@RequestMapping("/topics/{id}")
	public Topic getTopic(@PathVariable String id) {
		return topicService.getTopic(id);
	}
	
	//create
	@RequestMapping(method = RequestMethod.POST, value = "/topics")
	public void addTopic(@RequestBody Topic topic) {
		topicService.addTopic(topic);
	}
	
	//update
	@RequestMapping(method = RequestMethod.PUT, value = "/topics/{id}")
	public void updateTopic(@RequestBody Topic topic, @PathVariable String id) {
		topicService.updateTopic(id, topic);
	}
	
	//delete
	@RequestMapping(method = RequestMethod.DELETE, value = "/topics/{id}")
	public void deletTopic(@PathVariable String id) {
		topicService.deletTopic(id);
	}
}
