package by.smertex.core.util;

import by.smertex.core.AbstractTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = Cryptographer.class)
public class CryptographerTest extends AbstractTest {

    @Autowired
    private Cryptographer cryptographer;

    @Test
    @DisplayName("Проверка на шифрование и последующее дешифрование почты")
    void encryptDecryptTest(){
        String email = "smertexx@gmail.com";

        String encrypt = cryptographer.encrypt(email);
        Assertions.assertNotEquals(email, encrypt);

        String decrypt = cryptographer.decrypt(encrypt);
        Assertions.assertEquals(email, decrypt);
    }

}
