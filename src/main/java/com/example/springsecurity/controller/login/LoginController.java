//package com.example.springsecurity.controller.login;
//
//
//import com.example.springsecurity.util.response.Code;
//import com.example.springsecurity.util.response.Msg;
//import com.example.springsecurity.pojo.Response;
//import com.example.springsecurity.pojo.LoginResponse;
//
//import com.example.springsecurity.security.util.SecurityUtil;
//import com.example.springsecurity.service.LoginService;
//import com.example.springsecurity.util.jwt.JwtUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//@CrossOrigin
//@RestController
//public class LoginController {
//    @Autowired
//    private LoginService loginService;
//
//    @GetMapping("/allUser")
//    public List<Authority> allUser() {
//        return this.loginService.allUser();
//    }
//
//    @GetMapping("/admin")
////    @PreAuthorize("hasRole('admin')")
//    public String admin() {
//        return "admin on bridge";
//    }
//
//    @GetMapping("/boss")
//    @PreAuthorize("hasRole('boss')")
//    public String boss() {
//        return "boss on bridge";
//    }
//
//    @GetMapping("/employe")
//    public String employe() {
//        return "employe on bridge";
//    }
//
//    @GetMapping("/error403")
//    public String error() {
//        return "权限不足无法访问";
//    }
//
//    @GetMapping("/welcome")
//    public String welcome() {
//        return "欢迎,登陆成功";
//    }
//
//
//    /**
//     * 前端验证接口
//     * 解释：从前端返回一个json登录数据之后，将数据返回给工具类login（security的校验步骤都在其中）
//     */
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @PostMapping("/tologin")
//    public Response tologin(@RequestBody Authority authority) {
//        try {
//            // 使用authenticationManager2进行认证，如果失败会抛出AuthenticationException的子类
//            User user = SecurityUtil.login(authority.getUsername(),authority.getPassword(),authenticationManager);
//            // 如果认证成功，获取User对象
//            // 生成jwt
//            String jwt = JwtUtil.createJWT("001",authority.getUsername(),authority.getAuth());  //ps authority.getAuth()是null
//            System.out.println("欢迎回来,铁御");
//            LoginResponse loginResponse = new LoginResponse(100,"欢迎回来,铁御",jwt,loginService.getAuthority(authority.getUsername()));
//            return new Response(Code.LOGIN_SUCCESS,Msg.LOGIN_SUCCESS,loginResponse);
//        } catch (AuthenticationException e) {
//            // 如果认证失败，捕获异常，并返回自定义的信息
//            System.out.println("任务代号4-1-7...登录失败");
//            LoginResponse loginResponse = new LoginResponse(200,"任务代号4-1-7...登录失败",null,null);
//            return new Response(Code.LOGIN_ERR,Msg.LOGIN_ERR,loginResponse);
//        }
//    }
//
//}
