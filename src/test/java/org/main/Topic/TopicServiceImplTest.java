package org.main.Topic;

import org.main.Topic.DTO.TopicDTO;
import org.main.exceptions.TopicNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TopicServiceImplTest {

    @Mock
    private TopicRepository repository;

    @InjectMocks
    private TopicServiceImpl service;

    private Topic topic;

    @BeforeEach
    void setUp() {
        topic = new Topic(
                "1",
                "Java",
                "Java programming"
        );
    }

    // 1. getAllTopics()
    @Test
    void shouldGetAllTopics() {

        List<Topic> topics = List.of(topic);

        when(repository.getAllTopic()).thenReturn(topics);

        List<Topic> result = service.getAllTopics();

        assertEquals(1, result.size());
        assertEquals("Java", result.get(0).getName());

        verify(repository).getAllTopic();
    }

    // 2. getTopicById() - success
    @Test
    void shouldGetTopicById() {

        when(repository.findById("1"))
                .thenReturn(Optional.of(topic));

        Topic result = service.getTopicById("1");

        assertNotNull(result);
        assertEquals("1", result.getId());
        assertEquals("Java", result.getName());

        verify(repository).findById("1");
    }

    // 3. getTopicById() - not found
    @Test
    void shouldThrowExceptionWhenTopicNotFound() {

        when(repository.findById("1"))
                .thenReturn(Optional.empty());

        TopicNotFoundException exception = assertThrows(
                TopicNotFoundException.class,
                () -> service.getTopicById("1")
        );

        assertEquals(
                "Topic not found for Id:1",
                exception.getMessage()
        );

        verify(repository).findById("1");
    }

    // 4. addTopic()
    @Test
    void shouldAddTopics() {

        TopicDTO dto = new TopicDTO(
                "1",
                "Java",
                "Java programming"
        );

        List<TopicDTO> input = List.of(dto);

        when(repository.saveAll(anyList()))
                .thenReturn(List.of(topic));

        List<TopicDTO> result = service.addTopic(input);

        assertEquals(1, result.size());
        assertEquals("1", result.get(0).getId());
        assertEquals("Java", result.get(0).getName());
        assertEquals("Java programming", result.get(0).getDescription());

        verify(repository).saveAll(anyList());
    }

    // 5. updateTopic() - success
    @Test
    void shouldUpdateTopic() {

        Topic updatedTopic = new Topic(
                "1",
                "Spring Boot",
                "Spring Boot programming"
        );

        when(repository.findById("1"))
                .thenReturn(Optional.of(topic));

        when(repository.save(any(Topic.class)))
                .thenReturn(topic);

        Topic result = service.updateTopic("1", updatedTopic);

        assertEquals("Spring Boot", result.getName());
        assertEquals(
                "Spring Boot programming",
                result.getDescription()
        );

        verify(repository).findById("1");
        verify(repository).save(topic);
    }

    // 6. updateTopic() - not found
    @Test
    void shouldThrowExceptionWhenUpdatingNonExistingTopic() {

        Topic updatedTopic = new Topic(
                "1",
                "Spring Boot",
                "Spring Boot programming"
        );

        when(repository.findById("1"))
                .thenReturn(Optional.empty());

        assertThrows(
                TopicNotFoundException.class,
                () -> service.updateTopic("1", updatedTopic)
        );

        verify(repository).findById("1");
        verify(repository, never()).save(any());
    }

    // 7. deleteTopic() - success
    @Test
    void shouldDeleteTopic() {

        when(repository.findById("1"))
                .thenReturn(Optional.of(topic));

        Topic result = service.deleteTopic("1");

        assertNotNull(result);
        assertEquals("1", result.getId());

        verify(repository).findById("1");
        verify(repository).delete(topic);
    }

    // 8. deleteTopic() - not found
    @Test
    void shouldThrowExceptionWhenDeletingNonExistingTopic() {

        when(repository.findById("1"))
                .thenReturn(Optional.empty());

        assertThrows(
                TopicNotFoundException.class,
                () -> service.deleteTopic("1")
        );

        verify(repository).findById("1");
        verify(repository, never()).delete(any());
    }
}