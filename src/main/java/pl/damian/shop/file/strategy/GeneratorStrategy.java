package pl.damian.shop.file.strategy;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pl.damian.shop.file.model.FileType;

@RequiredArgsConstructor
public abstract class GeneratorStrategy {
    @Getter
    private final FileType fileType;

    public abstract byte[] generateFile();


}
