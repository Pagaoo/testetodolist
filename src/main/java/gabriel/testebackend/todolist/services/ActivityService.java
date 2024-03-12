package gabriel.testebackend.todolist.services;

import gabriel.testebackend.todolist.Dtos.ActivityDto;
import gabriel.testebackend.todolist.enities.Activity;
import gabriel.testebackend.todolist.repositories.ActivityRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityService {

    public final ActivityRepository activityRepository;

    public ActivityService(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    public Activity createActivity(ActivityDto activityDto) {
        Activity newActivity = new Activity(activityDto);
        return activityRepository.save(newActivity);
    }

    public List<Activity> getAllActivitis(){
        return activityRepository.findAll();
    }
}
