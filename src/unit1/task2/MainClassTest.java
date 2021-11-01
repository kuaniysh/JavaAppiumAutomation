package unit1.task2;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class MainClassTest extends MainClass {

    /*
    В классе MainClassTest написать тест (назвать testGetClassNumber), который проверяет, что метод getClassNumber возвращает число больше 45.
    Не забываем в проверку добавлять понятный текст ошибки.
     */

    @Test
    public void testGetClassNumber() {
        assertTrue("Class number < 45", getClassNumber() > 45);
    }
}
