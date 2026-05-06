package DTO;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class GameItem{
    @CsvBindByName(column = "id")
    private Integer id;

    @CsvBindByName(column = "name")
    private String name;

    @CsvBindByName(column = "type")
    private String type;

    @CsvBindByName(column = "level")
    private Integer level;

    @CsvBindByName(column = "weight")
    private Double weight;

    @CsvBindByName(column = "rarity")
    private String rarity;
}


