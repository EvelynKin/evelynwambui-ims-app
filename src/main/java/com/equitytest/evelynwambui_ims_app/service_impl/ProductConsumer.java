package com.equitytest.evelynwambui_ims_app.service_impl;

import com.equitytest.evelynwambui_ims_app.domain.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.StringReader;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ProductConsumer {

    private final ProductServiceImpl productService;

   /* @KafkaListener(topics = "product-csv-topic")
    public void processCSV(String csvData) {
        CSVReader csvReader = new CSVReaderBuilder(new StringReader(csvData))
                .withSkipLines(1) // Skip header if present
                .build();
        List<String[]> csvRows = csvReader.readAll();

        for (String[] row : csvRows) {

            Product product = new Product();
            product.setName(row[0]);
            product.setDescription(row[1]);
            product.setPrice(Double.parseDouble(row[2]));
            product.setQuantity(Integer.parseInt(row[3]));
            product.setCategory(row[4]);
            product.setTag(row[5]);

            // Save product to the database
            productService.createProduct(product);
        }
    }*/
}
