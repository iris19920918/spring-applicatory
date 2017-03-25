package com.wang.web.student;

import com.wang.common.WStatus;
import com.wang.common.WsResult;
import com.wang.entity.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by WANGDD on 2017/3/25.
 */
@Controller
@RequestMapping("/student/query")
public class StudentQueryController {

    private static final Logger logger = LoggerFactory.getLogger(StudentQueryController.class);

    @RequestMapping("/getStuInfo")
    @ResponseBody
    public WsResult getStuInfo(@Valid Student student, BindingResult bindingResult) {
        WsResult result = WsResult.create();
        if (bindingResult.hasErrors()) {
            List<ObjectError> errorList = bindingResult.getAllErrors();
            for (ObjectError error : errorList) {
                result.setErrorCode(WStatus.TRADEERROR.getCode());
                result.setErrorMessage(error.getDefaultMessage());
                logger.warn(error.getDefaultMessage());
            }
        }
        return result;
    }
}
