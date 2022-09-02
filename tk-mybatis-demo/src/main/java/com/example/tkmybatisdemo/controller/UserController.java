package com.example.tkmybatisdemo.controller;

import com.example.tkmybatisdemo.entity.User;
import com.example.tkmybatisdemo.entity.UserInfo;
import com.example.tkmybatisdemo.mapper.UserDao;
import com.example.tkmybatisdemo.utils.ListUtils;
import com.example.tkmybatisdemo.utils.Result;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.PageRowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * @author qzz
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserDao userDao;

    /**
     * 新增
     * @param user
     * @return
     */
    @PostMapping("/add")
    public Result add(@RequestBody User user){
        user.setCreateUser("admin");
        user.setCreateTime(new Date());
        //insertSelective 保存一个实体，null的属性不会保存，会使用数据库默认值
       int i= userDao.insertSelective(user);
       if(i>0){
           return new Result().ok("新增成功");
       }else{
           return new Result().error("新增失败");
       }
    }

    /**
     * 编辑
     * @param user
     * @return
     */
    @PostMapping("/update")
    public Result update(@RequestBody User user){
        user.setUpdateUser("admin");
        user.setUpdateTime(new Date());
        //根据主键id更新用户信息  updateByPrimaryKeySelective:根据主键更新属性不为null的值
       int i= userDao.updateByPrimaryKeySelective(user);
       if(i>0){
           return new Result().ok("更新成功");
       }else{
           return new Result().error("更新失败");
       }
    }

    /**
     * 查看用户详情
     * @return
     */
    @GetMapping("/userInfo")
    public Result getUserById(@RequestParam("userId") Integer id){
        User userInfo = userDao.selectByPrimaryKey(id);
        return new Result().ok(userInfo);
    }

    /**
     * 查看单条记录
     * @return
     */
    @GetMapping("/single")
    public Result getSingle(@RequestParam("userId") Integer id){
        User user = new User();
        user.setId(id);
        User userInfo = userDao.selectOne(user);
        return new Result().ok(userInfo);
    }

    /**
     * 查看所有
     * @return
     */
    @GetMapping("/list")
    public Result getList(){
        List<User> list = userDao.selectAll();
        return new Result().ok(list);
    }

    /**
     * 根据条件查看符合条件的列表   条件且 and
     * @return
     */
    @GetMapping("/getListByCondition")
    public Result getListByCondition(@RequestParam("name") String name,@RequestParam("age") Integer age){
        //根据名称获取用户信息

        //通用的Example查询对象
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andLike("name","%"+name+"%");
        criteria.andEqualTo("age",age);

        List<User> list = userDao.selectByExample(example);
        return new Result().ok(list);
    }

    /**
     * 根据条件查看符合条件的列表   条件或 or
     * @return
     */
    @GetMapping("/getListByOrCondition")
    public Result getListByOrCondition(@RequestParam("name") String name,@RequestParam("age") Integer age){
        //根据名称获取用户信息

        //通用的Example查询对象
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andLike("name","%"+name+"%");
        criteria.orEqualTo("age",age);
        List<User> list = userDao.selectByExample(example);
        return new Result().ok(list);
    }

    /**
     * 分页查看用户列表
     */
    @GetMapping("/page")
    public PageInfo<User> page(@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize){
        PageHelper.startPage(page,pageSize);
        List<User> list = userDao.selectAll();
        return new PageInfo<>(list);
    }

    /**
     * 条件分页查看用户列表
     */
    @GetMapping("/page1")
    public PageInfo<UserInfo> page(@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize,@RequestParam("age") Integer age){
        PageHelper.startPage(page,pageSize);
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andGreaterThan("age",age);
        example.setOrderByClause("id desc");
        //selectByExample几乎可以解决所有的查询
        List<User> list = userDao.selectByExample(example);
        //把结果集赋值给另一个结果集，分页的参数属性不起作用（如：total 等）
        List<UserInfo> list1 =new ListUtils<UserInfo>().copyProperties(list,UserInfo.class);
        return new PageInfo<>(list1);
    }

    /**
     * 限制条数查询
     * @param limit
     * @return
     */
    public List<UserInfo> getUserListByLimit(@RequestParam("limit") Integer limit,@RequestParam("age") Integer age){
        Example example = new Example(User.class);
        //创建查询标准
        Example.Criteria criteria = example.createCriteria();
        //查询条件
        criteria.andGreaterThan("age",age);
        //排序条件
        example.setOrderByClause("id desc");
        //查询后可以根据selectByExampleAndRowBounds指定获取几条特定的查询语句，类似 select中的limit。
        List<User> list = userDao.selectByExampleAndRowBounds(example,new PageRowBounds(0,limit));
        //把结果集赋值给另一个结果集，分页的参数属性不起作用（如：total 等）
        List<UserInfo> list1 =new ListUtils<UserInfo>().copyProperties(list,UserInfo.class);
        return list1;
    }

    /**
     * 根据（条件1 and 条件2） or ( 条件3 and 条件4)查看符合条件的列表
     * @return
     */
    @GetMapping("/getListByCondition2")
    public Result getListByCondition2(@RequestParam("name") String name,@RequestParam("age") Integer age){
        //根据名称获取用户信息

        //通用的Example查询对象
        Example example = new Example(User.class);
        //条件1 and 条件2
        Example.Criteria criteria1 = example.createCriteria();
        criteria1.andLike("name","%"+name+"%");
        criteria1.andEqualTo("age",age);

        //条件3 and 条件4
        Example.Criteria criteria2 = example.createCriteria();
        criteria2.andLike("phone","%"+name+"%");
        criteria2.andEqualTo("sex",0);

        //把上面的两个组合条件 进行or
        example.or(criteria2);

        List<User> list = userDao.selectByExample(example);
        return new Result().ok(list);
    }

    /**
     * 根据（条件1 or 条件2） and 条件3 查看符合条件的列表
     * 思路 ： 分拆 ： （条件1 or 条件2）and 条件3 ==> ( 条件1 and 条件3) or ( 条件2 and 条件3 )
     * @return
     */
    @GetMapping("/getListByCondition3")
    public Result getListByCondition3(@RequestParam("name") String name){
        //根据名称获取用户信息

        //通用的Example查询对象
        Example example = new Example(User.class);
        //条件1 and 条件3
        Example.Criteria criteria1 = example.createCriteria();
        criteria1.andLike("name","%"+name+"%");
        criteria1.andEqualTo("status",0);

        //条件2 and 条件3
        Example.Criteria criteria2 = example.createCriteria();
        criteria2.andLike("phone","%"+name+"%");
        criteria2.andEqualTo("status",0);

        //把上面的两个组合条件 进行or
        example.or(criteria2);

        List<User> list = userDao.selectByExample(example);
        return new Result().ok(list);
    }
}
