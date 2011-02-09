package br.com.caelum.guj.vraptor.filter;

import net.jforum.entities.Topic;
import br.com.caelum.guj.repositories.TopicRepository;

public class TopicRepositoryStub implements TopicRepository{
	private int callsToRepository;

	@Override
	public Topic getById(int id) {
		Topic topic = new Topic(20);
		topic.setTitle("erich created jforum");
		
		callsToRepository++;
		return topic;
	}
	
	public int getCallsToRepository() {
		return callsToRepository;
	}
}
