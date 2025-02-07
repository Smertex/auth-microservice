package by.smertex.core.database.repository.impl;

import by.smertex.core.database.model.impl.EmailCode;
import by.smertex.core.database.repository.EmailCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import java.time.Duration;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class EmailCodeRepositoryImpl implements EmailCodeRepository {

    private final RedisTemplate<String, EmailCode> redisTemplate;

    @Value("${spring.cache.redis.code.lifetime}")
    private Duration lifetime;

    @Override
    public Optional<EmailCode> findByEmail(String email) {
        ValueOperations<String, EmailCode> operations = redisTemplate.opsForValue();
        return Optional.ofNullable(operations.get(email));
    }

    @Override
    public void save(EmailCode emailCode) {
        ValueOperations<String, EmailCode> operations = redisTemplate.opsForValue();
        operations.set(emailCode.getEmail(), emailCode, lifetime);
    }
}
