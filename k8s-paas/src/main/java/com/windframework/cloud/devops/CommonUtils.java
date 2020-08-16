package com.windframework.cloud.devops;

import cn.hutool.core.codec.Base64Decoder;

public class CommonUtils {
    public static String base64DecodePlus(String source) {
        String ret =  Base64Decoder.decodeStr(source);
        ret = ret.replace("\n","");
        return ret;
    }
}
