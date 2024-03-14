package gabriel.testebackend.todolist.services;

import gabriel.testebackend.todolist.Dtos.ActivityDto;
import gabriel.testebackend.todolist.enities.Activity;
import gabriel.testebackend.todolist.exceptions.ActivityNotFoundException;
import gabriel.testebackend.todolist.repositories.ActivityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActivityService {

    private final Logger logger = LoggerFactory.getLogger(ActivityService.class);
    public final ActivityRepository activityRepository;

    public ActivityService(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    public Activity createActivity(ActivityDto activityDto) {
        Activity newActivity = new Activity(activityDto);
        return activityRepository.save(newActivity);
    }

    public List<Activity> getAllActivitis(){
        Sort sort = Sort.by("name").ascending();
        return activityRepository.findAll(sort);
    }

    public Optional<Activity> findActivityByName(String name) {
        return Optional.ofNullable(activityRepository.findActivityByName(name));
    }

    public void deleteActivityById(Long id) {
        if (!activityRepository.existsById(id)) {
            logger.error("Atividade de id: {} não encontrada", id);
            throw new ActivityNotFoundException("Atividade não encontrada para a exclusão");
        }
        activityRepository.deleteById(id);
    }
}
