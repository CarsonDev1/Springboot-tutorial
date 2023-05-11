package com.tutorial.apidemo.service;

import com.tutorial.apidemo.util.ReadExcel;
import com.tutorial.apidemo.entity.Product;
import com.tutorial.apidemo.reponsitory.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExcelUploadService {
    @Autowired
    private ProductRepository repository;
//    public boolean isValidExcelFile(MultipartFile file) {
//        return Objects.equals(file.getContentType(), "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" );
//    }
//    public List<Product> getProductsDataFromExcel(MultipartFile file) {
//        List<Product> products = new ArrayList<>();
//        try {
//            InputStream inputStream = file.getInputStream();
//            Workbook workbook = new XSSFWorkbook(inputStream);
//            Sheet sheet = workbook.getSheet("products");
//
//            int rowIndex = 0;
//            for (Row row : sheet) {
//                if (rowIndex == 0) {
//                    rowIndex++;
//                    continue;
//                }
//                Iterator<Cell> cellIterator = row.iterator();
//                int cellIndex = 0;
//                Product product = new Product();
//                while (cellIterator.hasNext()) {
//                    Cell cell = cellIterator.next();
//                    switch (cellIndex) {
//                        case 0: {
//                            product.setYear((int) cell.getNumericCellValue());
//                            break;
//                        }
//                        case 1: {
//                            product.setPrice(cell.getNumericCellValue());
//                            break;
//                        }
//                        case 2: {
//                            product.setUrl(cell.getStringCellValue());
//                            break;
//                        }
//                        case 3: {
//                            product.setProductName(cell.getStringCellValue());
//                            break;
//                        }
//                        default:
//                            break;
//                    }
//                    cellIndex++;
//                }
//                products.add(product);
//                inputStream.close();
//            }
//        } catch (IOException e) {
//            e.getStackTrace();
//        }
//
//        repository.saveAll(products);
//        return products;
//    }

    public List<Product> getProductsDataFromExcel2(MultipartFile file) {
        List<Product> products = new ArrayList<>();
        try {
            products.addAll(ReadExcel.readFileFromInputStream(file.getInputStream(), Product.class));
            return repository.saveAll(products);
        } catch (Exception e) {
            System.out.println("loi excel");
        }
        return products;
    }
}
