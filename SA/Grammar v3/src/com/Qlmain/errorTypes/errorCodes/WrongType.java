package com.Qlmain.errorTypes.errorCodes;

import com.Qlmain.errorTypes.ErrorCodes;

/**
 * Created by sotos on 8/4/2017.
 */
public class WrongType extends ErrorCodes {
    private String err_name;
    public WrongType(String name) {
        err_name = name;
    }

    public String getErrorCode() {
        return err_name;
    }
}
