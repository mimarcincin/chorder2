package sk.mmarcincin.chorder2.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import sk.mmarcincin.chorder2.models.Task;

@Repository
public interface TaskRepository extends MongoRepository<Task,String> {

    Task findTaskById(String id);
}
