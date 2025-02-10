package by.smertex.core.util;

import by.smertex.core.configuration.CodeGeneratorConfiguration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = {CodeGeneratorConfiguration.class, CodeGenerator.class})
public class CodeGeneratorTest {

    @Autowired
    private CodeGenerator codeGenerator;

    @Test
    @DisplayName("Проверка на генерацию кода")
    void generateTest(){
        String code = codeGenerator.generate();
        Assertions.assertNotNull(code);
        Assertions.assertEquals(code.length(), 6);
    }
}
