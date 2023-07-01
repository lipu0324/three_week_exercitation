package com.sifu.sfcc.util;

import cn.hutool.core.lang.UUID;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CodeGeneratorUtil {

    public static String generatorCode(String perfix){

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

        return perfix.concat(simpleDateFormat.format(new Date()));
    }

}
