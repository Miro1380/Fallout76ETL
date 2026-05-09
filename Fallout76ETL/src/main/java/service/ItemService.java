package service;

import DTO.ItemDTO;
import Entity.ItemEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.ItemRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor

public class ItemService {
    private final CSVReaderService csvReaderService;
    private final ItemRepository itemRepository;


    public void loadItems(){
        List<ItemEntity> itemEntityList = csvReaderService.itemReader()
                .stream()
                .filter(item -> {
                    if(item.getName() == null || item.getName().isBlank()){
                        log.warn("Skipping item with blank name: {}", item);
                        return false;
                    }
                    //If Item has negative weight(Impossible) or is null.
                    if(item.getWeight() == null || item.getWeight() <=0){
                        log.warn("Skipping item with negative or null weight val: {}", item);
                        return false;
                    }
                    if(item.getType() == null || item.getType().isBlank()){
                        log.warn("Skipping item with null Type: {}", item);
                        return false;
                    }
                    if(item.getLevel() == null || item.getLevel() <=0){
                        log.warn("Skipping item with null or 0 level: {}", item);
                        return false;
                    }
                    return true;
                })
                .map(this::toEntity)
                .toList();

        if(!itemEntityList.isEmpty()){
            log.info("Saving {} items to database", itemEntityList.size());
            itemRepository.saveAll(itemEntityList);
        }
    }

    private ItemEntity toEntity(ItemDTO dto){
        ItemEntity itemEntity = new ItemEntity();
        itemEntity.setName(dto.getName());
        itemEntity.setType(dto.getType());
        itemEntity.setLevel(dto.getLevel());
        itemEntity.setWeight(dto.getWeight());
        itemEntity.setRarity(dto.getRarity());
        itemEntity.setMigrationDate(LocalDateTime.now());
        return itemEntity;
    }

    private ItemDTO toGameItemDTO (ItemEntity item){
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setName(item.getName());
        itemDTO.setType(item.getType());
        itemDTO.setLevel(item.getLevel());
        itemDTO.setWeight(item.getWeight());
        itemDTO.setRarity(item.getRarity());
        return itemDTO;
    }

    public Optional<ItemDTO> getById(Long id){
        return itemRepository.findById(id).map(this::toGameItemDTO);
    }

    public List<ItemDTO> getByType(String type){
        List<ItemDTO> itemList = itemRepository.findByType(type).stream().map(this::toGameItemDTO).toList();
        if(itemList.isEmpty()){
            throw new RuntimeException("No items found for type: "+ type);
        }

        return itemList;
    }

    public Optional<ItemDTO> getByLevel(Integer level){
        return itemRepository.findByLevel(level).map(this::toGameItemDTO);
    }

}
