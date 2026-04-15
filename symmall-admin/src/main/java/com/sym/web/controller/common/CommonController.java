package com.sym.web.controller.common;

import com.sym.common.controller.BaseController;
import com.sym.common.result.AjaxResult;
import com.sym.common.utils.type.DateUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/common")
public class CommonController extends BaseController {

    @GetMapping("/index")
    public AjaxResult index() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("testDate" , DateUtil.getCurrentDateTime());
        return success(map);
    }
}
