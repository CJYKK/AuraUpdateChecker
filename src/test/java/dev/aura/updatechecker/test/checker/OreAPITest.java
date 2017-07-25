package dev.aura.updatechecker.test.checker;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.spongepowered.api.plugin.PluginContainer;

import dev.aura.updatechecker.checker.OreAPI;

public class OreAPITest {
    @Before
    @After
    public void resetCounter() {
        OreAPI.resetErrorCounter();
    }

    @Test
    public void availablityTest() {
        // TODO: Replace with AuraUpdateChecker.ID
        final PluginContainer existingContainer = new DummyPluginContainer("invsync");
        final PluginContainer nonExistingContainer = new DummyPluginContainer(
                "thisIDdoesntexistbfjsdgbhjbhghhsfgdsghfh");

        assertTrue("Expected invsync to be available", OreAPI.isOnOre(existingContainer));
        assertFalse("Expected thisIDdoesntexistbfjsdgbhjbhghhsfgdsghfh not to be available",
                OreAPI.isOnOre(nonExistingContainer));
        assertEquals("No errors should have happend", 0, OreAPI.getErrorCounter());
    }

    @Test
    public void errorTest()
            throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        final int count = 10;
        final PluginContainer errorContainer = new DummyPluginContainer("Spaces are bad&!?&=?");

        for (int i = 0; i < count; ++i) {
            assertFalse("Expected \"Spaces are bad&!?&=?\" not to be available", OreAPI.isOnOre(errorContainer));
        }

        assertEquals(count + " errors should have happend", count, OreAPI.getErrorCounter());
    }
}
