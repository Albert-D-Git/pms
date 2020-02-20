package com.dcr;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.*;
import org.junit.Test;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestReadExcel {
    @Test
    public void test02() throws Exception {
        Workbook wb = WorkbookFactory.create(new File("C:\\Users\\CRnnn\\Desktop\\customer.xls"));//获取excel对象
        Sheet sheet = wb.getSheetAt(0);//获取excel的sheet
        if(sheet != null){
            for(int i =sheet.getFirstRowNum()+1;i<=sheet.getLastRowNum();i++){
                Row row = sheet.getRow(i);
                for(int j = row.getFirstCellNum();j<row.getLastCellNum();j++){
                    Cell cell = row.getCell(j);
                    String cellValue = paraseCellToString(cell);
                    if(j==0){
                        //如果是第一个单元格 截取序号的.0
                        cellValue=cellValue.substring(0,cellValue.indexOf("."));
                    }
                    System.out.print(cellValue+"\t");
                }
                System.out.println();
            }


        }
    }

    private String paraseCellToString(Cell cell) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        CellType type = cell.getCellTypeEnum();
        String result = "";

        if(type.equals(CellType.BOOLEAN)){
            boolean booleanCellValue = cell.getBooleanCellValue();
            result = String.valueOf(booleanCellValue);
        }else if(type.equals(CellType.NUMERIC)){
            //如果没有时间类型 只能转化成为数字类型
            if(HSSFDateUtil.isCellDateFormatted(cell)){
                Date date = cell.getDateCellValue();
                result = sdf.format(date);
            }else {
                result = cell.getNumericCellValue()+"";
            }
        }else if(type.equals(CellType.STRING)){
            result = cell.getStringCellValue();
        }

        return  result;

    }
}
