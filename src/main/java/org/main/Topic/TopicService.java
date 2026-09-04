package org.main.Topic;

import org.main.Topic.DTO.TopicDTO;

import java.util.List;


public interface TopicService {
    List<Topic> getAllTopics();

    Topic getTopicById(String id);

    List<TopicDTO> addTopic(List<TopicDTO> topics);

    Topic updateTopic(String id,Topic topic);

    Topic deleteTopic(String id);
}
