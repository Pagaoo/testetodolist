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
        logger.info("[createActivity] Creating activity funciton");
        Activity newActivity = new Activity(activityDto);
        return activityRepository.save(newActivity);
    }

    public List<Activity> getAllActivitis(){
        logger.info("[getAllActivitis] Searching all activities and then sorting by name function");
        Sort sort = Sort.by("name").ascending();
        return activityRepository.findAll(sort);
    }

    public Optional<Activity> findActivityByName(String name) {
        logger.info("[findActivityByName] Searching activity by name function");
        return Optional.ofNullable(activityRepository.findActivityByName(name));
    }

    public void deleteActivityById(Long id) {
        logger.info("[deleteActivityById] Searching activity to delete function");
        if (!activityRepository.existsById(id)) {
            logger.info("Atividade de id: {} não encontrada", id);
            throw new ActivityNotFoundException("Atividade não encontrada para a exclusão");
        }
        activityRepository.deleteById(id);
    }

    public void updateActivytyDoneField(Long id, Boolean done) {
        logger.info("[updateActivytyDoneField] Updating done field function");
        Optional<Activity> activity = activityRepository.findById(id);
        if (activity.isPresent()) {
            Activity updateDoneFieldActivity = activity.get();
            updateDoneFieldActivity.setDone(done);
            activityRepository.save(updateDoneFieldActivity);
        } else {
            logger.info("Atividade de id: {} não encontrada", id);
            throw new ActivityNotFoundException("Atividade não encontrada para atualizar o campo 'done'");
        }
    }

    public void updateActivityFields(Long id, ActivityDto activityDto) {
        logger.info("[updateActivityFields] Updating activity fields function");
        Optional<Activity> activity = activityRepository.findById(id);
        if (activity.isPresent()) {
            Activity updateActivityFields = activity.get();
            updateActivityFields.setName(activityDto.name());
            updateActivityFields.setDescription(activityDto.description());
            updateActivityFields.setPriorityEnum(activityDto.priorityEnum());
            activityRepository.save(updateActivityFields);
        } else {
            logger.info("Atividade de id: {} não encontrada", id);
            throw new ActivityNotFoundException("Atividade não encontrada para atualizar os campos 'nome', 'descrição' e 'prioridade'");
        }
    }
}
