package service;

import com.opencsv.bean.CsvToBeanBuilder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.apache.bcel.util.ClassPath;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import DTO.GameItem;

@Slf4j
@Service
@NoArgsConstructor
@AllArgsConstructor
public class CSVReaderService {
    @Value("classpath:items.csv")
    private Resource csvFile;

    public static void main(String[] args) {
        CSVReaderService testService = new CSVReaderService();
        testService.itemReader();
    }
    public List<GameItem> itemReader(){
        try {

            Reader reader = new InputStreamReader(csvFile.getInputStream());
            List<GameItem> itemList = new CsvToBeanBuilder<GameItem>(reader)
                    .withType(GameItem.class)
                    .build()
                    .parse();

            itemList.forEach( item -> System.out.println("Game Item: "+ item.getName()));
            log.info("Game Items list: {}", itemList.size());
            return itemList;

        } catch (FileNotFoundException e) {
            log.error("Error thrown parsing CSV File: {}", e.getMessage());
            throw new RuntimeException("Failed to parse CSV" , e);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
