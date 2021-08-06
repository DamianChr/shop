package pl.damian.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.damian.shop.file.GeneratorFactory;
import pl.damian.shop.file.model.FileType;
import pl.damian.shop.flyweight.GenericFactory;
import pl.damian.shop.flyweight.strategy.file.FileGeneratorStrategy;
import pl.damian.shop.flyweight.strategy.mail.MailStrategy;
import pl.damian.shop.flyweight.strategy.mail.model.MailType;

@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
public class FileController {
    private final GeneratorFactory generatorFactory;
    private final GenericFactory<MailType, MailStrategy> genericFactory;
    private final GenericFactory<FileType, FileGeneratorStrategy> genericFactoryFile;

    @GetMapping
    public void test(@RequestParam FileType fileType) {
        generatorFactory.getStrategyByType(fileType).generateFile();

    }

    @GetMapping("/generic-mail")
    public void testGenericStrategy(@RequestParam MailType mailType) {
        genericFactory.getStrategyByType(mailType).sent();

    }

    @GetMapping("/generic-file")
    public ResponseEntity<byte[]> testGenericStrategy(@RequestParam FileType fileType) {
        byte[] file = genericFactoryFile.getStrategyByType(fileType).generateFile();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);
        httpHeaders.set(HttpHeaders.CONTENT_LENGTH, Integer.toString(file.length));
        httpHeaders.set(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=report." + fileType.toString().toLowerCase());
        return ResponseEntity.ok().headers(httpHeaders).body(file);

    }


}
