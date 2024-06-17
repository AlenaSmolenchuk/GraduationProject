package ru.mts.graduation_project.deposit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mts.graduation_project.deposit.entity.Request;

/**
 * Репозиторий для работы с запросами.
 */
@Repository
public interface RequestRepository extends JpaRepository<Request, Integer> {
}
