package org.main.Topic;

import jakarta.validation.Valid;
import org.main.Topic.DTO.TopicDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class TopicController {

    private final TopicService topicService;

    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @GetMapping("/topics")
    public ResponseEntity<List<Topic>> getAllTopics() {
        return ResponseEntity.status(HttpStatus.OK).body(topicService.getAllTopics());
    }

    @GetMapping("/topic/{topicId}")
    public ResponseEntity<Topic> getTopicById(@PathVariable String topicId) {
        return ResponseEntity.status(HttpStatus.OK).body(topicService.getTopicById(topicId));
    }

    @PostMapping("/topics")
    public ResponseEntity<List<TopicDTO>> addTopic(
            @RequestBody List<@Valid TopicDTO> topics) {
        return ResponseEntity.status(HttpStatus.CREATED).body(topicService.addTopic(topics));
    }

    @PutMapping("/topics/{id}")
    public ResponseEntity<Topic> updateTopic(
            @PathVariable String id,
            @RequestBody Topic topic) {

        Topic updated = topicService.updateTopic(id, topic);
        return ResponseEntity.ok(updated);
    }


    @DeleteMapping("topics/{id}")
    public ResponseEntity<Topic> deleteTopic(@PathVariable(name = "id") String topicId) {
        topicService.deleteTopic(topicId);
        return ResponseEntity.noContent().build();
    }
}