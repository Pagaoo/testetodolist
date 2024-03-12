package gabriel.testebackend.todolist.controllers;

import gabriel.testebackend.todolist.Dtos.ActivityDto;
import gabriel.testebackend.todolist.enities.Activity;
import gabriel.testebackend.todolist.services.ActivityService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/activity")
public class ActivityController {

    private final Logger logger = LoggerFactory.getLogger(ActivityController.class);
    private final ActivityService activityService;

    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Activity createActivity(@RequestBody @Valid ActivityDto activityDto) {
        logger.info("[ActivityController] Creating activity");
        return activityService.createActivity(activityDto);
    }

    @GetMapping
    public List<Activity> getAllActivities() {
        logger.info("[ActivityController] Searching all activities");
        return activityService.getAllActivitis();
    }


}
