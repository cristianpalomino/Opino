package com.capr.interfaces_v2;

import com.capr.beans_v2.User_DTO;

/**
 * Created by Gantz on 26/12/14.
 */
public interface OnSuccessLogin {
    void onSuccessLogin(boolean success,User_DTO user_dto,String message);
}
