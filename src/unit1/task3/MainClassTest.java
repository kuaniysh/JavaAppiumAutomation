package unit1.task3;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class MainClassTest extends MainClass {

    /*
    В классе MainClassTest написать тест (назвать testGetClassString),
    который проверяет, что метод getClassString возвращает строку, в которой есть подстрока “hello” или “Hello”, если нет ни одной из подстрок - тест падает.
    Не забываем в проверку добавлять понятный текст ошибки.
     */

    @Test
    public void testGetClassString() {
        assertTrue("Class not contains - Hello", getClassString().contains("hello") || getClassString().contains("Hello"));
    }
}
