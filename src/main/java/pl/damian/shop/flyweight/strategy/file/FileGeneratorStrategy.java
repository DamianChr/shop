package pl.damian.shop.flyweight.strategy.file;

import pl.damian.shop.file.model.FileType;
import pl.damian.shop.flyweight.strategy.GenericStrategy;

public interface FileGeneratorStrategy extends GenericStrategy<FileType> {

    byte[] generateFile();

}
