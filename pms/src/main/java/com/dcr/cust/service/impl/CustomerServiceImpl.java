package com.dcr.cust.service.impl;

import com.dcr.cust.bean.Customer;
import com.dcr.cust.bean.CustomerExample;
import com.dcr.cust.dao.CustomerMapper;
import com.dcr.cust.service.CustomerService;
import com.dcr.utils.ConstantUtils;
import com.dcr.utils.MapUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Resource
    private CustomerMapper customerMapper;

    @Override
    public List<Customer> test() {
        CustomerExample example = new CustomerExample();
        //pageHelper在查询之前设置条件
        PageHelper.startPage(1,3);
        List<Customer> list = customerMapper.selectByExample(example);
        //将查询结果封装成pageInfo 其中包含着当前页数，显示条数，查询的列表数、总页数，数据库总记录数，当前页是否为第一页，当前页是否为末页，是否有上一页，是否有下一页
        PageInfo<Customer> pageInfo =  new PageInfo<Customer>(list);
        return list;
    }

    @Override
    public List<Customer> getCustomerList() {
        CustomerExample example = new CustomerExample();
        List<Customer> list = customerMapper.selectByExample(example);
        return list;
    }

    @Override
    public boolean validateComname(String comname) {
        CustomerExample example = new CustomerExample();
        CustomerExample.Criteria criteria = example.createCriteria();
        criteria.andComnameEqualTo(comname);
        return customerMapper.selectByExample(example).size()>0?true:false;

    }

    @Override
    public void insertCustomer(Customer customer) {
        customer.setAddtime(new Date());
        customerMapper.insert(customer);
    }

    @Override
    public List<Customer> search(Integer cid, String keyword, Integer orderby) {
        CustomerExample example = new CustomerExample();
        CustomerExample.Criteria criteria = example.createCriteria();

        if(cid==0){
            criteria.andComnameLike("%"+keyword+"%");
            CustomerExample.Criteria criteria2 = example.createCriteria();
            criteria2.andCompanypersonLike("%"+keyword+"%");
            example.or(criteria2);
        }else if(cid==1){
            criteria.andComnameLike("%"+keyword+"%");
        }else {
            criteria.andCompanypersonLike("%"+keyword+"%");
        }
        if(orderby==1){
            example.setOrderByClause("id desc");//根据id降序 参数还可以继续跟limit ...
        }
        List<Customer> list = customerMapper.selectByExample(example);
        return list;
    }

    @Transactional(rollbackFor = SQLException.class)
    @Override
    public Boolean batchDel(Integer[] ids) {

        CustomerExample example = new CustomerExample();
        CustomerExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(Arrays.asList(ids));
        int i = customerMapper.deleteByExample(example);

        return i==ids.length;

    }

    @Override
    public Customer getCustomerListById(Integer id) {
        return customerMapper.selectByPrimaryKey(id);

    }

    @Override
    public void editCustomer(Customer customer) {

        customerMapper.updateByPrimaryKeySelective(customer);
    }

    @Override
    public List<Customer> findCustomers() {
        CustomerExample example = new CustomerExample();
        return customerMapper.selectByExample(example);
    }

    @Override
    public void batchInsert(List<Customer> list) {
        customerMapper.batchInsert(list);
    }

    @Override
    public PageInfo<Customer> getCustomerPage(Integer pageNo, Map<String, Object> paramMap) {
        CustomerExample example = new CustomerExample();
        CustomerExample.Criteria criteria = example.createCriteria();
        Map<String,Object> myBatisMap = MapUtil.parseParamMapToMyBatisMap(paramMap);

        Object cid = myBatisMap.get("cid");
        if(cid != null){
            int id = Integer.valueOf(cid.toString());
            String keyword = (String) myBatisMap.get("keyword");

            if(id == 0 && keyword != null){
                criteria.andComnameLike(keyword);
                CustomerExample.Criteria criteria2 = example.createCriteria();
                criteria2.andCompanypersonLike(keyword);
                example.or(criteria2);
            }else if(id == 1){
                criteria.andComnameLike(keyword);
            }else{
                criteria.andCompanypersonLike(keyword);
            }
        }

        Object orderby = myBatisMap.get("orderby");
        if( orderby != null){
            int order = Integer.valueOf(orderby.toString());
            if(order == 1){
                example.setOrderByClause("id desc");
            }
        }
        //做分页
        PageHelper.startPage(pageNo, ConstantUtils.PAGE_SIZE);
        List<Customer> list = customerMapper.selectByExample(example);
        //将查询的list用PageInfo包装一下就是分页效果
        PageInfo<Customer> pageInfo = new PageInfo<Customer>(list,ConstantUtils.NAVIGATE_PAGENUM);
        return pageInfo;
    }


}
