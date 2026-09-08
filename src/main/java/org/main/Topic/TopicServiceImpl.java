package org.main.Topic;

import org.main.Topic.DTO.TopicDTO;
import org.main.exceptions.TopicNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicServiceImpl implements TopicService {

    private final TopicRepository repository;

    public TopicServiceImpl(TopicRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Topic> getAllTopics() {
        return repository.getAllTopic();
    }

    @Override
    public Topic getTopicById(String id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new TopicNotFoundException("Topic not found for Id:" + id));
    }

    @Override
    public List<TopicDTO> addTopic(List<TopicDTO> topicDTOs) {

        List<Topic> topics = topicDTOs.stream()
                .map(dto -> new Topic(
                        dto.getId(),
                        dto.getName(),
                        dto.getDescription()))
                .toList();

        List<Topic> savedTopics = repository.saveAll(topics);

        return savedTopics.stream()
                .map(topic -> new TopicDTO(
                        topic.getId(),
                        topic.getName(),
                        topic.getDescription()))
                .toList();
    }

    @Override
    public Topic updateTopic(String id, Topic topic) {

        Topic existingTopic = repository.findById(id)
                .orElseThrow(() ->
                        new TopicNotFoundException("Topic not found for Id:" + id));

        existingTopic.setName(topic.getName());
        existingTopic.setDescription(topic.getDescription());

        return repository.save(existingTopic);
    }

    @Override
    public Topic deleteTopic(String id) {

        Topic topic = repository.findById(id)
                .orElseThrow(() ->
                        new TopicNotFoundException("Topic not found for Id:" + id));

        repository.delete(topic);

        return topic;
    }

}
