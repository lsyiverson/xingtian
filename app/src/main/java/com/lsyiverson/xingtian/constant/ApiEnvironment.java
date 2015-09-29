package com.lsyiverson.xingtian.constant;

import com.lsyiverson.xingtian.utils.PropertiesUtil;

public class ApiEnvironment {
    public static class Juhe {
        public static String getApiBasePath() {
            return PropertiesUtil.getString("juhe.api_server");
        }
    }
}
