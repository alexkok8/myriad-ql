package com.Qlmain.errorTypes.errorCodes;

import com.Qlmain.errorTypes.ErrorCodes;

/**
 * Created by sotos on 8/4/2017.
 */
public class UndefinedVar extends ErrorCodes {
    private String err_name;
    public UndefinedVar(String name) {
        err_name = name;
    }

    public String getErrorCode() {
        return err_name;
    }
}
