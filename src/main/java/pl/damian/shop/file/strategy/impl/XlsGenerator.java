package pl.damian.shop.file.strategy.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pl.damian.shop.file.model.FileType;
import pl.damian.shop.file.strategy.GeneratorStrategy;

@Component
@Slf4j
public class XlsGenerator extends GeneratorStrategy {

    public XlsGenerator() {
        super(FileType.XLS);
    }

    @Override
    public byte[] generateFile() {
        log.info("XLS generated");
        return new byte[0];
    }
}
