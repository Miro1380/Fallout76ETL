package service;

import org.junit.jupiter.api.Test;
import java.util.List;
import DTO.GameItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = CSVReaderService.class)
@Import(CSVReaderService.class)
class CSVReaderServiceTest {

    // We just instantiate it manually for a unit test
    @Autowired
    private CSVReaderService readerService;

    @Test
    void testReadItems() {
        List<GameItem> items = readerService.itemReader();
        assertNotNull(items, "The list should not be null");

        // If CSV has 8 rows, check for 8
        assertFalse(items.isEmpty(), "Should have found items in the CSV");

        // check if the first item name is what we expect
        assertEquals("T-60 Power Armor", items.get(0).getName());

        System.out.println("Verified " + items.size() + " items successfully.");
    }
}