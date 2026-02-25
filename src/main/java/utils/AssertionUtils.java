package utils;

import org.testng.asserts.SoftAssert;

public class AssertionUtils {

    private static ThreadLocal<SoftAssert> softAssert = 
            ThreadLocal.withInitial(SoftAssert::new);

    public static SoftAssert getSoftAssert() {
        return softAssert.get();
    }

    public static void assertTrue(boolean condition, String message) {
        getSoftAssert().assertTrue(condition, message);
    }

    public static void assertEquals(Object actual, Object expected, String message) {
        getSoftAssert().assertEquals(actual, expected, message);
    }

    public static void assertContains(String actual, String expected) {
        getSoftAssert().assertTrue(actual.contains(expected));
    }

    public static void assertAll() {
        getSoftAssert().assertAll();
        softAssert.remove(); // important for parallel
    }
}
