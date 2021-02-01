package com.ytp.boot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
@Service
public class TopicService {
	
	private  List<Topics> topics= new ArrayList<>(Arrays.asList(
			new Topics("101","alex"),
			new Topics("102","jasmin"),
			new Topics("103","meera")
			));
	
	public  List<Topics> getAllTopics()
	{
		return topics;
	}
	
	public  Topics getTopics(String id)
	{
		return  topics.stream().filter(t ->t.getId().equals(id)).findFirst().get();
	}
	
	public  void addTopic(Topics topic)
	{
		topics.add(topic);
	}
	
	public void updateTopic(String id, Topics topic) {
		for(int i=0;i<topics.size();i++)
		{
			Topics t=topics.get(i);
			if(t.getId().equals(id)) {
				topics.set(i,topic);
				return;
			}
		}
	}

	public void deleteTopics(String id) {
		topics.removeIf(t ->t.getId().equals(id));
	}
	
}
