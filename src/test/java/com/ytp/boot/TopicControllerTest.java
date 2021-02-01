package com.ytp.boot;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
@RunWith(SpringRunner.class)
@WebMvcTest(value=TopicController.class)                                     
class TopicControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private TopicService topicService;
	@Test
	public void testGetAllTopics() throws Exception{
		Topics mockTopic1=new Topics();
		mockTopic1.setId("104");
		mockTopic1.setName("alex");

		Topics mockTopic2=new Topics();
		mockTopic2.setId("102");
		mockTopic2.setName("jasmin");
	
		Topics mockTopic3=new Topics();
		mockTopic3.setId("103");
		mockTopic3.setName("meera");

		List<Topics> topicList=new ArrayList<>();
		//topicList.add(mockTopic1);
		//topicList.add(mockTopic2);
		//topicList.add(mockTopic3);


		Mockito.when(topicService.getAllTopics()).thenReturn(topicList);
		String URI="/topic";
		RequestBuilder requestBuilder=MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON);
	
	MvcResult result=mockMvc.perform(requestBuilder).andReturn();
	String expected=this.mapToJson(topicList);
	
	String output=result.getResponse().getContentAsString();
assertThat(output).isEqualTo(expected);
}
	private String mapToJson(Object object) throws JsonProcessingException {
		ObjectMapper objectMapper=new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}
}
