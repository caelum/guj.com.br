package br.com.caelum.guj.repositories;

import net.jforum.dao.generic.GenericTopicDAO;
import net.jforum.entities.Topic;

public class TopicRepositoryWrapper implements TopicRepository {

	@Override
	public Topic getById(int id) {
		return new GenericTopicDAO().selectById(id);
	}

}
