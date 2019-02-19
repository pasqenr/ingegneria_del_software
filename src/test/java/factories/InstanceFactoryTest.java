package factories;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InstanceFactoryTest {
    @Test
    void getInstanceTest() {
        Object obj1 = InstanceFactory.getInstance(Object.class);
        Object obj2 = InstanceFactory.getInstance(Object.class);

        assertEquals(obj1, obj2);
    }
}
