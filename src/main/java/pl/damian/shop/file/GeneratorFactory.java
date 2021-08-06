package pl.damian.shop.file;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.damian.shop.file.model.FileType;
import pl.damian.shop.file.strategy.GeneratorStrategy;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class GeneratorFactory {
    private final List<GeneratorStrategy> generatorStrategies;

    private Map<FileType, GeneratorStrategy> strategyMap;

    @PostConstruct
    void init() {
        strategyMap = generatorStrategies.stream()
                .collect(Collectors.toMap(GeneratorStrategy::getFileType, Function.identity()));
    }

    public GeneratorStrategy getStrategyByType(FileType fileType){
        return strategyMap.get(fileType);
    }


}
