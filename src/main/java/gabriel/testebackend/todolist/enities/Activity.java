package gabriel.testebackend.todolist.enities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import gabriel.testebackend.todolist.Dtos.ActivityDto;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "activities")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String description;
    @JsonIgnoreProperties(value = "put")
    Boolean done;
    PriorityEnum priorityEnum = PriorityEnum.LOW;

    public Activity(ActivityDto activityDto) {
        this.name = activityDto.name();
        this.description = activityDto.description();
        this.done = activityDto.done();
        this.priorityEnum = activityDto.priorityEnum();
    }
}
