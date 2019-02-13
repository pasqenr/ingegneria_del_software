package model;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import util.TestUtilities;

public class GenericModelTest {
    @BeforeAll
    static void setUp() throws Exception {
        TestUtilities.init();
    }

    @AfterAll
    static void setDown() throws Exception {
        TestUtilities.done();
    }
}
