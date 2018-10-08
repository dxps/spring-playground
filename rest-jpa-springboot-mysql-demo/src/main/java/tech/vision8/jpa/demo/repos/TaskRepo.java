package tech.vision8.jpa.demo.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.vision8.jpa.demo.model.Task;


@Repository
public interface TaskRepo extends JpaRepository<Task, Long> {

}
