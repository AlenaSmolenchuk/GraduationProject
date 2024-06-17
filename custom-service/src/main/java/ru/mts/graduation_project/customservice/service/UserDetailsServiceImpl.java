package ru.mts.graduation_project.customservice.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.mts.graduation_project.customservice.exception.CustomerPhoneNumberNotFoundException;
import ru.mts.graduation_project.customservice.repository.CustomerRepository;

/**
 * Реализация {@link UserDetailsService} для загрузки пользовательских данных.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final CustomerRepository customerRepository;

    public UserDetailsServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    /**
     * Загружает данные пользователя по номеру телефона.
     *
     * @param phoneNumber номер телефона пользователя
     * @return детали пользователя
     * @throws UsernameNotFoundException если пользователь не найден
     */
    @Override
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
        return customerRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new CustomerPhoneNumberNotFoundException("Пользователь не найден"));
    }
}
