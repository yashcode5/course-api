package org.main.Topic.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class TopicDTO {
    @NotBlank(message = "Id cannot be blank")
    private String id;

    @NotBlank(message = "Name cannot be blank")
    @Size(min = 3, max = 50,
            message = "Name must be between 3 and 50 characters")
    private String name;

    @NotBlank(message = "Description cannot be blank")
    @Size(min = 5, max = 200,
            message = "Description must be between 5 and 200 characters")
    private String description;

    public TopicDTO() {
    }

    public TopicDTO(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
