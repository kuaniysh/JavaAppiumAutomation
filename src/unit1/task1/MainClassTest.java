package unit1.task1;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MainClassTest extends MainClass {
    /*
    Сделать класс unit1.task1.MainClassTest.
    В классе unit1.task1.MainClassTest написать тест, проверяющий, что метод getLocalNumber возвращает число 14 (назвать: testGetLocalNumber).
    Не забываем в проверку добавлять понятный текст ошибки.
     */

    @Test
    public void testGetLocalNumber() {
        assertEquals("Local number != 14", 14, getLocalNumber());
    }
}
