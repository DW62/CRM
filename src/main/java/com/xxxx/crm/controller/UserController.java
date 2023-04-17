package com.xxxx.crm.controller;

import com.xxxx.crm.base.BaseController;
import com.xxxx.crm.base.ResultInfo;
import com.xxxx.crm.model.UserModel;
import com.xxxx.crm.query.UserQuery;
import com.xxxx.crm.service.UserService;
import com.xxxx.crm.utils.LoginUserUtil;
import com.xxxx.crm.vo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 */
@Controller
@RequestMapping("user")
public class UserController extends BaseController {

    @Resource
    private UserService userService;


    /**
     * 用户登录
     * @param userName
     * @param userPwd
     * @return com.xxxx.crm.base.ResultInfo
     */
    @PostMapping("login")
    @ResponseBody
    public ResultInfo userLogin(String userName, String userPwd) {

        ResultInfo resultInfo = new ResultInfo();

        // 调用service层登录方法
        UserModel userModel = userService.userLogin(userName, userPwd);

        // 设置ResultInfo的result的值 （将数据返回给请求）
        resultInfo.setResult(userModel);

       /* // 通过try catch捕获service层的异常，如果service层抛出异常，则表示登录失败，否则登录失败
        try{

            // 调用service层登录方法
            UserModel userModel = userService.userLogin(userName, userPwd);

            // 设置ResultInfo的result的值 （将数据返回给请求）
            resultInfo.setResult(userModel);

        } catch (ParamsException p) {
            resultInfo.setCode(p.getCode());
            resultInfo.setMsg(p.getMsg());
            p.printStackTrace();
        } catch (Exception e) {
            resultInfo.setCode(500);
            resultInfo.setMsg("登录失败！");
        }*/

        return resultInfo;
    }


    /**
     * 用户修改密码
     * @param request
     * @param oldPassword
     * @param newPassword
     * @param repeatPassword
     * @return com.xxxx.crm.base.ResultInfo
     */
    @PostMapping("updatePwd")
    @ResponseBody
    public ResultInfo updateUserPassword(HttpServletRequest request,
             String oldPassword, String newPassword, String repeatPassword) {
        ResultInfo resultInfo = new ResultInfo();

        // 获取cookie中的userId
        Integer userId = LoginUserUtil.releaseUserIdFromCookie(request);
        // 调用Service层修改密码方法

       /* try {
            // 获取cookie中的userId
            Integer userId = LoginUserUtil.releaseUserIdFromCookie(request);
            // 调用Service层修改密码方法
            userService.updatePassWord(userId, oldPassword, newPassword, repeatPassword);

        } catch (ParamsException p) {
            resultInfo.setCode(p.getCode());
            resultInfo.setMsg(p.getMsg());
            p.printStackTrace();
        } catch (Exception e) {
            resultInfo.setCode(500);
            resultInfo.setMsg("修改密码失败！");
            e.printStackTrace();
        }*/

        return resultInfo;
    }

    /**
     * 进入修改密码的页面
     * @param
     * @return java.lang.String
     */
    @RequestMapping("toPasswordPage")
    public String toPasswordPage() {

        return "user/password";
    }


    /**
     * 查询所有的销售人员
     * @param
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @RequestMapping("queryAllSales")
    @ResponseBody
    public List<Map<String,Object>> queryAllSales(){
        return userService.queryAllSales();
    }


    /**
     * 分页多条件查询用户列表
     * @param userQuery
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> selectByParams(UserQuery userQuery) {
        return userService.queryByParamsForTable(userQuery);
    }

    /**
     * 进入用户列表页面
     * @param
     * @return java.lang.String
     */
    @RequestMapping("index")
    public String index() {
        return "user/user";
    }

    /**
     * 添加用户
     * @param user
     * @return com.xxxx.crm.base.ResultInfo
     */
    @PostMapping("add")
    @ResponseBody
    public ResultInfo addUser(User user) {
        userService.addUser(user);
        return success("用户添加成功！");
    }


    /**
     * 更新用户
     * @param user
     * @return com.xxxx.crm.base.ResultInfo
     */
    @PostMapping("update")
    @ResponseBody
    public ResultInfo updateUser(User user) {
        userService.updateUser(user);
        return success("用户更新成功！");
    }


    /**
     * 打开添加或修改用户的页面
     * @param
     * @return java.lang.String
     */
    @RequestMapping("toAddOrUpdateUserPage")
    public String toAddOrUpdateUserPage(Integer id, HttpServletRequest request) {

        // 判断id是否为空，不为空表示更新操作，查询用户对象
        if (id != null) {
            // 通过id查询用户对象
            User user = userService.selectByPrimaryKey(id);
            // 将数据设置到请求域中
            request.setAttribute("userInfo",user);
        }

        return "user/add_update";
    }


    /**
     * 用户删除
     * @param ids
     * @return com.xxxx.crm.base.ResultInfo
     */
    @PostMapping("delete")
    @ResponseBody
    public ResultInfo deleteUser(Integer[] ids) {

        userService.deleteByIds(ids);

        return success("用户删除成功！");
    }


    /**
     * 查询所有的客户经理
     * @param
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @RequestMapping("queryAllCustomerManagers")
    @ResponseBody
    public List<Map<String,Object>> queryAllCustomerManagers(){
        return userService.queryAllCustomerManagers();
    }

}
