package com.dcr.utils;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExceUtils {
    public static String paraseCellToString(Cell cell) {
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
