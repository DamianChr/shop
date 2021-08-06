package pl.damian.shop.flyweight.strategy.file.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pl.damian.shop.file.model.FileType;
import pl.damian.shop.flyweight.strategy.file.FileGeneratorStrategy;

@Component
@Slf4j
public class CsvFileGenerator implements FileGeneratorStrategy {
    @Override
    public FileType getType() {
        return FileType.CSV;
    }

    @Override
    public byte[] generateFile() {
        log.info("CSV generated");
        return new byte[0];
    }
}
