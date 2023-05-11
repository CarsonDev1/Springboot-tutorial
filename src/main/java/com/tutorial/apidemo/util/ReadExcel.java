package com.tutorial.apidemo.util;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


@Slf4j
public class ReadExcel {
	private ReadExcel() {};

	public static <T> List<T> readFileFromInputStream(InputStream fileInputStream, Class<T> tClass) throws Exception {
		try {
			//load file
			XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
			XSSFSheet sheet = workbook.getSheetAt(0);

			//read header
			List<String> fieldNames = new ArrayList<String>();
			Iterator<Row> rowIterator = sheet.iterator();
			Row rowHeader = rowIterator.next();
             Iterator<Cell> cellHeaderIterator = rowHeader.cellIterator();
             while (cellHeaderIterator.hasNext()) 
             {
                 Cell cell = cellHeaderIterator.next();
                 fieldNames.add(snakeToCamel(cell.getStringCellValue()) );
                
             }
         
             //read each row
             List<HashMap<String,Object>> dataHashMaps = new ArrayList<HashMap<String, Object>>();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				//For each row, iterate through all the columns
				//Integer cellIndex = 0;
				HashMap<String, Object> dataHashedMap = new HashMap<String, Object>();
				dataHashedMap.put("rowCount", row.getRowNum());

				for(int cellIndex =0 ; cellIndex < fieldNames.size(); cellIndex++) {
					try {
						Cell cell = row.getCell(cellIndex);
						//Check the cell type and format accordingly

						switch(cell.getCellType()) {
							case NUMERIC:
								dataHashedMap.put(fieldNames.get(cellIndex), cell.getNumericCellValue());
								if(DateUtil.isCellDateFormatted(cell)) {
									dataHashedMap.put(fieldNames.get(cellIndex), cell.getDateCellValue());
								}
								break;
							case STRING:
								dataHashedMap.put(fieldNames.get(cellIndex), cell.getStringCellValue());
								break;
							case BLANK:
								dataHashedMap.put(fieldNames.get(cellIndex), "");
								break;
							case BOOLEAN:
								dataHashedMap.put(fieldNames.get(cellIndex), cell.getBooleanCellValue());
								break;
							default:
								dataHashedMap.put(fieldNames.get(cellIndex), null);
								break;
						}
					} catch (Exception e) {
						dataHashedMap.put("error", fieldNames.get(cellIndex) + " " + e.getMessage());
						break;
					}
				}

				dataHashMaps.add(dataHashedMap);
			}

			//convert hash map to Object
			List<String> errorRows = new ArrayList<String>();
			List<T> resultList = new ArrayList<T>();
			Field[] objectFieldName = tClass.getDeclaredFields();
			for (HashMap<String, Object> dataHashMap : dataHashMaps) {
				if (dataHashMap.containsKey("error")) {
					errorRows.add("Data Error at row " + dataHashMap.get("rowCount") + " with message:" + dataHashMap.get("error"));
					continue;
				}
				T newT = tClass.getDeclaredConstructor().newInstance();
				try {


					for (Field field : objectFieldName) {
						try {
							Method method = null;
							if (dataHashMap.containsKey(field.getName())) {
								Object valueObject = dataHashMap.get(field.getName());
								if (valueObject == null) continue;
								method = tClass.getMethod("set" + capitalize(field.getName()), field.getType());

								if (field.getType() == String.class) {
									method.invoke(newT, valueObject.toString());
								} else if (field.getType() == Integer.class || field.getType() == int.class) {
									method.invoke(newT, (int) Double.parseDouble(valueObject.toString()));
								} else if (field.getType() == Double.class) {
									method.invoke(newT, Double.parseDouble(valueObject.toString()));
								}
							}
						} catch (Exception e) {
							errorRows.add("Parse Error at row :" + dataHashMap.get("rowCount") + " at field " + field.getName() + ":" + e.getMessage());
							throw e;
						}

					}
				} catch (Exception e) {
					continue;
				}
				resultList.add(newT);


			}
			workbook.close();
			log.info(String.valueOf(resultList.size()));
			resultList.forEach(el->log.info(el.toString()));
			errorRows.forEach(log::error);
			return resultList;
		} catch(Exception e) {
			log.error(e.getMessage());
			throw new Exception(e);
		}

	}

	private static String
    snakeToCamel(String str)
    {
		str = str.substring(0, 1).toLowerCase() + str.substring(1);
        // Convert to StringBuilder
        StringBuilder builder
            = new StringBuilder(str);
 
        // Traverse the string character by
        // character and remove underscore
        // and capitalize next letter
        for (int i = 0; i < builder.length(); i++) {
 
            // Check char is underscore
            if (builder.charAt(i) == '_' || builder.charAt(i) == ' ') {
 
                builder.deleteCharAt(i);
                builder.replace(
                    i, i + 1,
                    String.valueOf(
                        Character.toUpperCase(
                            builder.charAt(i))));
            }
        }
 
        // Return in String type
        return builder.toString();
    }

	private static String capitalize(String s) {
		if (s.length() == 0)
			return s;
		return s.substring(0, 1).toUpperCase() + s.substring(1);
	}

}

