import model.Plane;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlaneTest {

    Plane plane;

    @Before
    public void setUp(){
        plane = new Plane();
    }

    @Test
    public void getCapacity() {
        int capacity = 300;

        plane.setCapacity(capacity);
        assertEquals(capacity, plane.getCapacity());
    }

    @Test
    public void getModel() {
        String model = "Boeing 777";

        plane.setModel(model);
        assertEquals(model, plane.getModel());
    }

    @Test
    public void getManufacturer() {
        String manufacturer = "Boeing Commercial Airplanes";

        plane.setManufacturer(manufacturer);
        assertEquals(manufacturer, plane.getManufacturer());
    }

    @Test
    public void getYear() {
        int year = 2018;

        plane.setYear(year);
        assertEquals(year, plane.getYear());
    }
}