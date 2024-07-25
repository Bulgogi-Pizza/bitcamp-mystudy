package study.library.apache_poi;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Test03 {
  public static void main(String[] args) throws Exception {
    XSSFWorkbook workbook = new XSSFWorkbook();
    XSSFSheet sheet = workbook.createSheet("users");

    Map<Integer, Object[]> data = new HashMap<>();
    data.put(1, new Object[] {"no", "name", "email", "password", "tel"});
    data.put(2, new Object[] {"1", "user1", "user1@test.com", "1111", "010-1987-1756"});
    data.put(3, new Object[] {"2", "user2", "user2@test.com", "1111", "010-5364-7356"});
    data.put(4, new Object[] {"3", "user3", "user3@test.com", "1111", "010-3898-5346"});
    data.put(5, new Object[] {"4", "user4", "user4@test.com", "1111", "010-4533-4536"});
    data.put(6, new Object[] {"5", "user5", "user5@test.com", "1111", "010-6878-5375"});
    data.put(7, new Object[] {"6", "user6", "user6@test.com", "1111", "010-3895-3987"});

    List<Object[]> data2 = new ArrayList<>();
    data2.add(new Object[] {"no", "name", "email", "password", "tel"});
    data2.add(new Object[] {1, "user1", "user1@test.com", "1111", "010-1987-1756"});
    data2.add(new Object[] {2, "user2", "user2@test.com", "1111", "010-5364-7356"});
    data2.add(new Object[] {3, "user3", "user3@test.com", "1111", "010-3898-5346"});
    data2.add(new Object[] {4, "user4", "user4@test.com", "1111", "010-4533-4536"});
    data2.add(new Object[] {5, "user5", "user5@test.com", "1111", "010-6878-5375"});
    data2.add(new Object[] {6, "user6", "user6@test.com", "1111", "010-3895-3987"});

    for (Object[] value : data.values()) {
      System.out.printf("%s %s %s %s %s\n", value[0], value[1], value[2], value[3], value[4]);
    }

    for (Object[] value : data2) {
      System.out.printf("%s %s %s %s %s\n", value[0], value[1], value[2], value[3], value[4]);
    }

    for (int i = 0; i < data2.size(); i++) {
      Object[] values = data2.get(i);
      Row row = sheet.createRow(i);

      for (int j = 0; j < values.length; j++) {
        if (values[j] instanceof String) {
          row.createCell(j).setCellValue((String) values[j]);
        } else if (values[j] instanceof Integer) {
          row.createCell(j).setCellValue((Integer) values[j]);
        }
      }
    }

    try(FileOutputStream out = new FileOutputStream("temp/test2.xlsx")){
      workbook.write(out);
    }

    System.out.println("create Excel file!");
  }

}
