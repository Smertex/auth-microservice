package by.smertex.core.service;

import by.smertex.core.AbstractTest;
import by.smertex.core.configuration.RedisConfiguration;
import by.smertex.core.database.model.impl.EmailCode;
import by.smertex.core.database.repository.EmailCodeRepository;
import by.smertex.core.database.repository.impl.EmailCodeRepositoryImpl;
import by.smertex.core.dto.input.SecurityEmailCode;
import by.smertex.core.exception.VerificationException;
import by.smertex.core.service.impl.VerificationServiceImpl;
import by.smertex.core.util.Cryptographer;
import by.smertex.core.util.JwtUtil;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;


@Testcontainers
@ContextConfiguration(classes = {RedisConfiguration.class, EmailCodeRepositoryImpl.class, VerificationServiceImpl.class, Cryptographer.class, JwtUtil.class})
public class VerificationServiceTest extends AbstractTest {

    @Autowired
    private EmailCodeRepository emailCodeRepository;

    @Autowired
    private VerificationService verificationService;

    @Autowired
    private Cryptographer cryptographer;

    @Container
    @ServiceConnection
    final static GenericContainer<?> redisContainer =
            new GenericContainer<>("redis:latest")
                    .withExposedPorts(6379);

    @BeforeEach
    void init(){
        redisContainer.start();

        emailCodeRepository.save(EmailCode.builder()
                .email("smertexx@gmail.com")
                .code("111111")
                .build());
    }

    @AfterEach
    void destroy(){
        emailCodeRepository.remove("smertexx@gmail.com");
    }

    @Test
    @DisplayName("Проверка на исключение при неверном коде")
    void verificationExceptionWhereWrongCodeTest(){
        String encryptEmail = cryptographer.encrypt("smertexx@gmail.com");
        SecurityEmailCode securityEmailCode = new SecurityEmailCode("1");

        Assertions.assertThrows(
                VerificationException.class,
                () -> verificationService.generateToken(encryptEmail, securityEmailCode)
        );
    }

    @Test
    @DisplayName("Проверка на верификацию и последующее удаление email с кодом")
    void verificationTest(){
        String encryptEmail = cryptographer.encrypt("smertexx@gmail.com");
        SecurityEmailCode securityEmailCode = new SecurityEmailCode("111111");

        Assertions.assertNotNull(
                verificationService.generateToken(encryptEmail, securityEmailCode)
        );

        Assertions.assertThrows(
                VerificationException.class,
                () -> verificationService.generateToken(encryptEmail, securityEmailCode)
        );
    }
}
