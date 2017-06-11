package com.cg.courseapi.test.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cg.courseapi.domain.Topic;
import com.cg.courseapi.repository.TopicRepository;
import com.cg.courseapi.service.TopicServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
public class TestTopicService {

	@Mock
	private TopicRepository topicRepository;

	@InjectMocks
	private TopicServiceImpl topicService;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetAllToppic() {
		List<Topic> topics = Arrays.asList(new Topic("java", "corejava", "corejavadescription"),
				new Topic("spring", "springboot", "springbootdescription"),
				new Topic("webservice", "restwebservice", "restdescription"));
		when(topicRepository.findAll()).thenReturn(topics);

		List<Topic> result = topicService.getAllTopic();
		assertEquals(3, result.size());
	}

	@Test
	public void testGetTopic() {
		Topic topic = new Topic("java", "corejava", "corejavadescription");
		when(topicRepository.findOne(topic.getId())).thenReturn(topic);

		Topic result = topicService.getTopic(topic.getId());

		assertEquals("java", result.getId());
		assertEquals("corejava", result.getName());
		assertEquals("corejavadescription", result.getDescription());
	}

	@Test
	public void testAddTopic() {

		Topic topic = new Topic("java", "corejava", "corejavadescription");

		topicService.addTopic(topic);

		verify(topicRepository, times(1)).save(topic);

	}

	@Test
	public void testUpdateTopic() {

		Topic topic = new Topic("java", "corejava", "corejavadescription");

		topicService.updateTopic(topic.getId(), topic);

		verify(topicRepository, times(1)).save(topic);
	}

	@Test
	public void testDeletTopic() {

		Topic topic = new Topic("java", "corejava", "corejavadescription");

		topicService.deletTopic(topic.getId());

		verify(topicRepository, times(1)).delete(topic.getId());

	}

}
