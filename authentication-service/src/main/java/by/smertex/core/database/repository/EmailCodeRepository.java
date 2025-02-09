package by.smertex.core.database.repository;

import by.smertex.core.database.model.impl.EmailCode;

import java.util.Optional;

public interface EmailCodeRepository {
    Optional<EmailCode> findByEmail(String email);

    void save(EmailCode emailCode);

    void remove(String email);
}
