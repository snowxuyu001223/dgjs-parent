package com.muteng.dgjs.api;

import com.muteng.dgjs.common.system.BaseResponse;
import com.muteng.dgjs.common.system.ResponseEntity;
import com.muteng.dgjs.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * date: 2018/10/21 10:55
 * author: snowxuyu
 * describe:
 */

@RestController
@RequestMapping("/api")
@Api(value="method26之后",description="method26之后")
public class Method3Controller {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private UserService userService;


    @PostMapping("/method27")
    @ApiOperation(value="身份认证提交",httpMethod="POST")
    public ResponseEntity method27(Long id,String name, String idcard) {
        logger.info(">>>> 身份认证提交 userId:{}, name:{}, idcard:{}", id, name, idcard);
        userService.method27(id, name, idcard);
        return BaseResponse.buildSuccess("认证成功");
    }
}


