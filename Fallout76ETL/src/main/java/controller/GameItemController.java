package controller;

import DTO.ItemDTO;
import org.springframework.http.ResponseEntity;
import service.ItemService;
import org.springframework.web.bind.annotation.*;  // Add this

import java.util.List;


@RestController
@RequestMapping("/api/ItemDTO")
public class GameItemController {

    private ItemService itemService;

    @GetMapping("/{id}")
    public ResponseEntity<ItemDTO> geItemById(@PathVariable Long id){
        return itemService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{type}")
    public ResponseEntity<List<ItemDTO>> getItemByType(@PathVariable String type){
        List<ItemDTO> list = itemService.getByType(type);
        return ResponseEntity.ok(list);
    }
}
