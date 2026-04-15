package com.sym.common.result;

import org.junit.jupiter.api.Test;

public class ResultTest {

    @Test
    public void testResult() {
        AjaxResult result = AjaxResult.success("测试结果集");
        System.out.println(result);
    }
}
