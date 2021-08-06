package pl.damian.shop.flyweight.strategy.file.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Component;
import pl.damian.shop.domain.dao.Product;
import pl.damian.shop.file.model.FileType;
import pl.damian.shop.flyweight.strategy.file.FileGeneratorStrategy;
import pl.damian.shop.repository.ProductRepository;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RequiredArgsConstructor
@Slf4j
@Component
public class XlsFileGenerator implements FileGeneratorStrategy {
    private final ProductRepository productRepository;


    @Override
    public FileType getType() {
        return FileType.XLS;
    }

    @Override
    public byte[] generateFile() {
        try (Workbook workbook = WorkbookFactory.create(false)) {
            Sheet sheet = workbook.createSheet("arkusz");
            List<Product> productList = productRepository.findAll();
            AtomicInteger rowNumber = new AtomicInteger();
            productList.forEach(product -> {
                Row row = sheet.createRow(rowNumber.getAndIncrement());
                row.createCell(0).setCellValue(product.getId());
                row.createCell(1).setCellValue(product.getBrand());
                row.createCell(2).setCellValue(product.getType());
                row.createCell(3).setCellValue(product.getPrice());
                row.createCell(4).setCellValue(product.getQuantity());
            });
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            workbook.write(byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            log.error("file can not be created", e);
        }

        log.info("XLS generated");
        return new byte[0];
    }
}
