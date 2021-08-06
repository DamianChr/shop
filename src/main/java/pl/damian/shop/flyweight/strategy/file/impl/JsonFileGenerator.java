package pl.damian.shop.flyweight.strategy.file.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pl.damian.shop.file.model.FileType;
import pl.damian.shop.flyweight.strategy.file.FileGeneratorStrategy;

@Component
@Slf4j
public class JsonFileGenerator implements FileGeneratorStrategy {
    @Override
    public FileType getType() {
        return FileType.JSON;
    }

    @Override
    public byte[] generateFile() {
        log.info("JSON Generated");
        return new byte[0];
    }
}
