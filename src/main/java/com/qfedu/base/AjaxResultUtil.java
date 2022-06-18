package com.qfedu.base;

import java.util.List;

public class AjaxResultUtil {

    public static final String OK_CODE = "200";
    public static final String FAIL_CODE = "500";
    public static final String ERROR_CODE = "800";

    public static AjaxResult ok(Object o) {
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.setCode(OK_CODE);
        ajaxResult.setMsg("ok");
        ajaxResult.setData(o);
        return ajaxResult;
    }

    public static AjaxResult fail(Object o, String msg) {
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.setCode(FAIL_CODE);
        ajaxResult.setMsg(msg);
        ajaxResult.setData(o);
        return ajaxResult;
    }

    public static AjaxResult error(Object o) {
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.setCode(ERROR_CODE);
        ajaxResult.setMsg("error");
        ajaxResult.setData(o);
        return ajaxResult;
    }

    public static AjaxResult pageOK(Long dataCount, List o) {
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.setCode(OK_CODE);
        ajaxResult.setMsg("ok");

        PageResultObejct pageResultObejct = new PageResultObejct();
        pageResultObejct.setDataCount(dataCount);
        pageResultObejct.setList(o);

        //注意这句
        ajaxResult.setData(pageResultObejct);
        return ajaxResult;
    }
}
