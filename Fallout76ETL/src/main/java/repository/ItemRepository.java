package repository;

import DTO.ItemDTO;
import Entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, Long> {

    List<ItemEntity> findByType(String type);
    List<ItemEntity> findByWeight(Double weight);
    List<ItemEntity> findByLevel(Integer level);

}
