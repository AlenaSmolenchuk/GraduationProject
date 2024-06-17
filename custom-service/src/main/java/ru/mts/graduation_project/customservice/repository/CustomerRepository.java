package ru.mts.graduation_project.customservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mts.graduation_project.customservice.entity.Customer;

import java.util.Optional;

/**
 * Репозиторий для работы с пользователями в базе данных.
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    /**
     * Метод для поиска пользователя по его номеру телефона.
     *
     * @param phoneNumber номер телефона пользователя.
     * @return Объект пользователя с указанным номером телефона.
     */
    Optional<Customer> findByPhoneNumber(String phoneNumber);
}
