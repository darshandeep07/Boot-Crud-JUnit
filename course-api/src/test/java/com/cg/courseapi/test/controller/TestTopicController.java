package com.cg.courseapi.test.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.cg.courseapi.controller.TopicController;
import com.cg.courseapi.domain.Topic;
import com.cg.courseapi.service.TopicService;
import com.google.gson.Gson;

@RunWith(SpringRunner.class)
@WebMvcTest(TopicController.class)
public class TestTopicController {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private TopicService topicService;

	//readById
	@Test
	public void testGetTopic() throws Exception {

		Topic topic = new Topic("java", "corejava", "corejavadescription");
		when(topicService.getTopic(topic.getId())).thenReturn(topic);

		this.mockMvc.perform(get("/topics/{id}", topic.getId()))
					.andExpect(status().isOk())
					.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andExpect(jsonPath("$.id", is("java")))
					.andExpect(jsonPath("$.name", is("corejava")))
					.andExpect(jsonPath("$.description", is("corejavadescription")));
		verify(topicService).getTopic(topic.getId());
	}

	//readAll
	@Test
	public void testGetAllTopic() throws Exception {

		List<Topic> topics = Arrays.asList(
				new Topic("java", "corejava", "corejavadescription"),
				new Topic("spring", "springboot", "springbootdescription"));
		when(topicService.getAllTopic()).thenReturn(topics);

		this.mockMvc.perform(get("/topics"))
					.andExpect(status().isOk())
					.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andExpect(jsonPath("$[0].id", is("java")))
					.andExpect(jsonPath("$[1].id", is("spring")));
		verify(topicService).getAllTopic();
	}

	//create
	@Test
	public void testAddTopic() throws Exception {

		Topic topic = new Topic("java", "corejava", "corejavadescription");
		Gson gson = new Gson();
		String json = gson.toJson(topic);

		this.mockMvc.perform(post("/topics")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(json))
				.andExpect(status().isOk());
		verify(topicService).addTopic(any(Topic.class));
	}
	
	//update
	@Test
	public void testUpdateTopic() throws Exception {

		Topic topic = new Topic("java", "corejava", "updatedcorejavadescription");
		Gson gson = new Gson();
		String json = gson.toJson(topic);

		this.mockMvc.perform(put("/topics/{id}", topic.getId())
					.contentType(MediaType.APPLICATION_JSON_UTF8)
					.content(json))
					.andExpect(status().isOk());
		verify(topicService).updateTopic(any(String.class), any(Topic.class));
	}
	
	//delete
	@Test
	public void testDeletTopic() throws Exception {

		Topic topic = new Topic("java", "corejava", "corejavadescription");

		this.mockMvc.perform(delete("/topics/{id}", topic.getId()))
					.andExpect(status().isOk());
		verify(topicService).deletTopic(topic.getId());
	}

}
