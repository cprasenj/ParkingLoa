import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CarTest {

    @Test
    public void testIdentifyCarGivesTheCarNumber() throws Exception {
        Car car = new Car("1234");
        assertEquals(car.giveIdentity(), "1234");
    }


}