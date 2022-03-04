package com.dun.controller;

import cn.hutool.core.map.MapUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dun.common.lang.Result;
import com.dun.entity.User;
import com.dun.service.UserService;
import com.dun.util.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;

@RestController
public class LoginController {

    @Autowired
    UserService userService;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/login")
    public Result login(@RequestBody Map<String,Object> map, HttpServletResponse response){

        String account = map.get("account").toString();
        String password = map.get("password").toString();

        User user = userService.getOne(new QueryWrapper<User>().eq("account",account));
        if (user == null){
            return Result.fail("用户不存在");
        }

        if (!user.getPassword().equals(SecureUtil.md5(password))){
            return Result.fail("密码不正确");
        }

        //jwt生成
        String jwt = jwtUtils.generateToken(user.getId());

        response.setHeader("Authorization",jwt);
        response.setHeader("Access-control-Expose-Headers","Authorization");

        return Result.succ(MapUtil.builder()
                .put("id",user.getId())
                .put("username",user.getUsername())
                .map()
        );
    }

    @PostMapping("/register")
    public Result register(@RequestBody Map<String,Object> map){
        System.out.println(map);
        User user = new User();
        user.setUsername(map.get("username").toString());
        user.setAccount(map.get("account").toString());
        user.setPassword(SecureUtil.md5(map.get("password").toString()));

        if (userService.save(user)){
            return Result.succ(true);
        }else {
            return Result.fail("注册失败");
        }

    }

    @GetMapping("/checkToken")
    public Result checkToken(HttpServletRequest request){
        Boolean checkResult;
        String token = request.getHeader("token");
        //校验jwt
        Claims claim = jwtUtils.getClaimByToken(token);
        if (claim == null || jwtUtils.isTokenExpired(claim.getExpiration())){
            checkResult = false;
        }else {
            checkResult = true;
        }

        return Result.succ(MapUtil.builder()
                .put("checkResult",checkResult)
                .map()
        );
    }



}
