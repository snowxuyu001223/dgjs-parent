package com.muteng.dgjs.validate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

/**
 * Created with IntelliJ IDEA.
 * User: snowxuyu
 * Date: 2017-8-24
 * Time: 22:16
 */
public class Validator {

    private static Logger logger = LoggerFactory.getLogger(Validator.class);

    public static void checkValidate(BindingResult bindingResult) {
        if ( bindingResult.hasErrors() ) {
            ObjectError error = bindingResult.getAllErrors().get(bindingResult.getAllErrors().size() - 1);
            logger.error("validate error :{}", error);
            throw new RuntimeException(error.getDefaultMessage());
        }
    }
}
