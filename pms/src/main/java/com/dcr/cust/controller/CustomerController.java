package com.dcr.cust.controller;

import com.dcr.cust.bean.Customer;
import com.dcr.cust.service.CustomerService;
import com.dcr.utils.ExceUtils;
import com.dcr.utils.MapUtil;
import com.github.pagehelper.PageInfo;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/cust")
public class CustomerController {

    @Resource
    private CustomerService customerService;

    @Resource
    private HttpSession session;


//    public ModelAndView getCustomerList(){
//        ModelAndView mv = new ModelAndView("customer");
//        List<Customer> list = customerService.getCustomerList();
//        mv.addObject("list",list);
//        return mv;
//    }
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ModelAndView getCustomerList(@RequestParam(value = "pageNo",defaultValue = "1") Integer pageNo, HttpServletRequest request){
        //分页查询获取uri传回去
        String requestURI = request.getRequestURI();

        Map<String, Object> paramMap = WebUtils.getParametersStartingWith(request, "search_");

        ModelAndView mv = new ModelAndView("customer");
        //查询客户信息
        PageInfo<Customer> page = customerService.getCustomerPage(pageNo,paramMap);
        String queryStr = MapUtil.parseParamMapToQueryString(paramMap);

        mv.addObject("queryStr",queryStr);
        mv.addObject("requestURI",requestURI);
        mv.addObject("page",page);
        return mv;
    }




    @RequestMapping(value = "/validateComname",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> validateComname(String comname){
        boolean flag = customerService.validateComname(comname);
        Map<String,Object> map = new HashMap<String,Object>();
        if (flag==false){
            map.put("status",200);
            map.put("msg","公司名可用");
        }else {
            map.put("status",1000);
            map.put("msg","公司名不可用");
        }
        return map;
    }

    @RequestMapping(value = "/insertCustomer",method = RequestMethod.POST)
    public String insertCustomer(Customer customer){
        customerService.insertCustomer(customer);
        return "redirect:/cust/list";
    }

    @RequestMapping(value = "/search",method = RequestMethod.GET)
    public String search(Integer search_cid,String search_keyword,Integer search_orderby,Map<String,Object> map){
        List<Customer> list = customerService.search(search_cid,search_keyword,search_orderby);
        map.put("list",list);
        return "customer";
    }

    @RequestMapping(value = "/batchDel",method = RequestMethod.DELETE)
    @ResponseBody
    public Map<String,Object> batchDel(@RequestParam("ids[]") Integer[] ids){
        Map<String,Object> map = new HashMap<String,Object>();
        try{
            Boolean flag = customerService.batchDel(ids);
            map.put("status",200);
            map.put("message","删除成功！");
        }catch (Exception e){
            map.put("status",1000);
            map.put("message","删除失败！");
        }
        return map;
    }

    @RequestMapping(value = "/edit/{id}",method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable("id") Integer id){
        ModelAndView mv = new ModelAndView("customer-edit");
        Customer customer = customerService.getCustomerListById(id);
        mv.addObject("customer",customer);
        return mv;
    }

    @RequestMapping(value = "/edit",method = RequestMethod.PUT)
    public String edit(Customer customer){
        customerService.editCustomer(customer);

        return "redirect:/cust/list";
    }

    @RequestMapping(value = "/detail/{id}",method = RequestMethod.GET)
    public ModelAndView detail(@PathVariable("id") Integer id){
        ModelAndView mv = new ModelAndView("customer-look");
        Customer customer = customerService.getCustomerListById(id);
        mv.addObject("customer",customer);
        return mv;
    }

    @RequestMapping(value = "/customers",method = RequestMethod.GET)
    @ResponseBody
    public List<Customer> findCustomers(HttpServletRequest request){

        List<Customer> list = customerService.findCustomers();
        //存session域
//        for (Customer customer:list) {
//            request.setAttribute(customer.getId()+"",customer.getCompanyperson());
//        }

        return list;
    }

    @RequestMapping(value = "/importExcel",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> importExcel(MultipartFile file) {
        Map<String,Object> map = new HashMap<String, Object>();
        List<Customer> list = new ArrayList<Customer>();
        try{
            Workbook wb = WorkbookFactory.create(file.getInputStream());//获取excel对象
            Sheet sheet = wb.getSheetAt(0);//获取excel的sheet
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            if(sheet != null){
                for(int i =sheet.getFirstRowNum()+1;i<=sheet.getLastRowNum();i++){//第一行不用解析
                    Row row = sheet.getRow(i);
                    Customer customer = new Customer();
                    customer.setComname(row.getCell(1).getStringCellValue());
                    customer.setCompanyperson(row.getCell(2).getStringCellValue());
                    customer.setComaddress(row.getCell(3).getStringCellValue());
                    customer.setComphone(new BigDecimal(ExceUtils.paraseCellToString(row.getCell(4)))+"");
                    customer.setCamera(row.getCell(5).getStringCellValue());
                    customer.setPresent(row.getCell(6).getStringCellValue());
                    customer.setRemark(row.getCell(7).getStringCellValue());
                    Cell cell = row.getCell(8);
                    String s = ExceUtils.paraseCellToString(cell);//将excel中的时间转化成String
                    Date addtime = sdf.parse(s);
                    customer.setAddtime(addtime);
                    //将遍历的每个对象加入到list
                    list.add(customer);
                }
            }
            customerService.batchInsert(list);
            map.put("msg","导入成功！");
        }catch (Exception e){
            map.put("msg","导入失败！");
            e.printStackTrace();
        }
        return map;
    }



    @RequestMapping(value = "/exportExcel",method = RequestMethod.GET)
    public String exportExcel(HttpServletResponse response){
        String fileName = "customer.xls";

        Map<String,Object> map = new HashMap<String, Object>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        FileOutputStream fos = null;
        ServletOutputStream os = null;


        try{
            response.setHeader("Content-disposition", "attachment;filename="
                    + new String(fileName.getBytes("gb2312"), "ISO8859-1"));//设置文件头编码格式
            response.setContentType("APPLICATION/OCTET-STREAM;charset=UTF-8");//设置类型
            response.setHeader("Cache-Control", "no-cache");//设置头
            response.setDateHeader("Expires", 0);//设置日期头



            List<Customer> list = customerService.getCustomerList();
            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet sheet = wb.createSheet("sheet");
            sheet.setColumnWidth(3,5000);
            sheet.setColumnWidth(4,5000);
            HSSFRow row = sheet.createRow(0);
            HSSFCell[] cells = new HSSFCell[9];
            for (int i = 0;i<cells.length;i++){
                cells[i]=row.createCell(i);
            }
            cells[0].setCellValue("公司id");
            cells[1].setCellValue("公司名称");
            cells[2].setCellValue("联系人");
            cells[3].setCellValue("公司地址");
            cells[4].setCellValue("联系电话");
            cells[5].setCellValue("公司座机");
            cells[6].setCellValue("公司简介");
            cells[7].setCellValue("备注");
            cells[8].setCellValue("添加时间");



            for (int i = 0; i <list.size(); i++) {
                Customer customer = list.get(i);
                HSSFRow row1 = sheet.createRow(i + 1);
                HSSFCell[] newcells = new HSSFCell[9];
                for (int j = 0;j<cells.length;j++){
                    newcells[j]=row1.createCell(j);
                }
                newcells[0].setCellValue(customer.getId());
                newcells[1].setCellValue(customer.getComname());
                newcells[2].setCellValue(customer.getCompanyperson());
                newcells[3].setCellValue(customer.getComaddress());
                newcells[4].setCellValue(customer.getComphone());
                newcells[5].setCellValue(customer.getCamera());
                newcells[6].setCellValue(customer.getPresent());
                newcells[7].setCellValue(customer.getRemark());
                newcells[8].setCellValue(sdf.format(customer.getAddtime()));

            }
            //fos = new FileOutputStream("C:\\Users\\CRnnn\\Desktop\\customer.xls");//本地输出流
             os = response.getOutputStream();//返回到浏览器端的输出流
            wb.write(os);
            os.flush();

            map.put("status",200);
            map.put("msg","导出成功！");
        }catch (Exception e){
            e.printStackTrace();
            map.put("status",1000);
            map.put("msg","导出失败！");
        }finally {

            if(os != null){
                try {
                    os.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
