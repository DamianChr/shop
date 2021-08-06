package pl.damian.shop.flyweight.strategy.file.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pl.damian.shop.file.model.FileType;
import pl.damian.shop.flyweight.strategy.file.FileGeneratorStrategy;

@Slf4j
@Component
public class PdfFileGenerator implements FileGeneratorStrategy {
    @Override
    public FileType getType() {
        return FileType.PDF;
    }

    @Override
    public byte[] generateFile() {
        log.info("PDF Generated");
        return new byte[0];
    }
}
