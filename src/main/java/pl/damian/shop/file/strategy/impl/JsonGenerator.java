package pl.damian.shop.file.strategy.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pl.damian.shop.file.model.FileType;
import pl.damian.shop.file.strategy.GeneratorStrategy;

@Slf4j
@Component
public class JsonGenerator extends GeneratorStrategy {
    public JsonGenerator() {
        super(FileType.JSON);
    }

    @Override
    public byte[] generateFile() {
        log.info("JSON generated");
        return new byte[0];
    }
}
