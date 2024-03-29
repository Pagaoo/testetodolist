package gabriel.testebackend.todolist.controllers;

import gabriel.testebackend.todolist.Dtos.ActivityDto;
import gabriel.testebackend.todolist.enities.Activity;
import gabriel.testebackend.todolist.exceptions.ActivityNotFoundException;
import gabriel.testebackend.todolist.services.ActivityService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/{name}")
    public Optional<Activity> getActivityById(@PathVariable("name") String name) {
        logger.info("[ActivityController] Searching activity by name");
        return Optional.ofNullable(activityService.findActivityByName(name).orElseThrow(() -> new ActivityNotFoundException("Atividade não encontrada")));
    }

    @DeleteMapping("/{id}")
    public void deleteActivityById(@PathVariable("id") Long id) {
        logger.info("[ActivityController] Deleting activity using the id");
        activityService.deleteActivityById(id);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> updateDoneField(@PathVariable Long id, @RequestParam("done") Boolean done) {
        logger.info("[updateDoneField] Updating done field to false or true");
        activityService.updateActivytyDoneField(id, done);
        return ResponseEntity.ok("Campo 'done' atualizado com sucesso");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateActivityFields(@PathVariable Long id, @RequestBody ActivityDto activityDto) {
        logger.info("[updateActivityFields] Updating fields from the activity");
        activityService.updateActivityFields(id, activityDto);
        return ResponseEntity.ok("Campos atualizados com sucesso");
    }
}