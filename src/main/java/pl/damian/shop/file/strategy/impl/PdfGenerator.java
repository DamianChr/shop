package pl.damian.shop.file.strategy.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pl.damian.shop.file.model.FileType;
import pl.damian.shop.file.strategy.GeneratorStrategy;

@Slf4j
@Component
public class PdfGenerator extends GeneratorStrategy {

    public PdfGenerator() {
        super(FileType.PDF);
    }


    @Override
    public byte[] generateFile() {
        log.info("pdf generated");
        return new byte[0];
    }
}
