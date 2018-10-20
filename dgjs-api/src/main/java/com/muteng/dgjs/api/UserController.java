package com.muteng.dgjs.api;

import com.muteng.dgjs.common.system.BaseResponse;
import com.muteng.dgjs.common.system.ResponseEntity;
import com.muteng.dgjs.domain.User;
import com.muteng.dgjs.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * date: 2018/10/20 00:23
 * author: snowxuyu
 * describe:
 */
@RestController
@RequestMapping("/api")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private UserService userService;

    @GetMapping("/getAllUser")
    public ResponseEntity getAllUser() {
        logger.info(">>>>> userApi getAllUser");
        List<User> userList = userService.getAllUser();
        return BaseResponse.buildSuccess(userList, "success");
    }

    @PostMapping(value = "/addUser", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addUser(@RequestBody @Valid User user,  BindingResult bindingResult) {
        logger.info(">>>>>> userApi addUser params:{}", user);
        userService.addUser(user);
        return BaseResponse.buildSuccess("success");
    }
}
