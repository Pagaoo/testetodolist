package gabriel.testebackend.todolist.repositories;

import gabriel.testebackend.todolist.enities.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<Activity, Long> {
    Activity findActivityByName(String name);
}
