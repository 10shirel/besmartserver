package com.besmart.api;

import com.besmart.utils.Constants;
import com.google.gson.Gson;

public abstract class BaseResource {
    protected Gson gson = new Gson();
    String response = gson.toJson(Constants.SOMETHING_IS_WRONG_PLEASE_TRY_LATER);
}
