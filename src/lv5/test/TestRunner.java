package lv5.test;

import java.lang.reflect.Method;

public class TestRunner {
    public static void main(String[] args) {
        runTests(ParserTest.class);
        runTests(GeneratorTest.class);
        runTests(ValidationTest.class);
    }

    public static void runTests(Class<?> testClass) {
        try {
            // Create an instance of the test class
            Object testInstance = testClass.getDeclaredConstructor().newInstance();

            for (Method method : testClass.getDeclaredMethods()) {
                if (method.isAnnotationPresent(Test.class)) {
                    // Run the test method
                    try {
                        method.invoke(testInstance);
                        System.out.println("Test " + method.getName() + " passed.");
                    } catch (AssertionError e) {
                        System.out.println("Test " + method.getName() + " failed: " + e.getMessage());
                    }
                }
            }
            System.out.println();
            System.out.println("All tests passed.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}