
package controller;

import model.Harvest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class HarvestControllerTest {

    private HarvestController controller;

    @BeforeEach
    public void setUp() {
        controller = mock(HarvestController.class);
    }

    @Test
    public void testAddHarvest() {
        doNothing().when(controller).addHarvest("Milho", 1, 100, "2025-05-25");
        controller.addHarvest("Milho", 1, 100, "2025-05-25");
        verify(controller, times(1)).addHarvest("Milho", 1, 100, "2025-05-25");
    }

    @Test
    public void testGetAllHarvests() {
        when(controller.getAllHarvests()).thenReturn(List.of(new Harvest(1, "Milho", 1, 100, "2025-05-25")));
        List<Harvest> harvests = controller.getAllHarvests();
        assertEquals(1, harvests.size());
        assertEquals("Milho", harvests.get(0).getCropName());
    }
}
