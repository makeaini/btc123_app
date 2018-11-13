package com.btc123.app.user;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by shining on 2018/5/21.
 */
public class TTT {
    private final static Pattern VERSION_PREFIX_PATTERN = Pattern
            .compile("(v[0-9].[0-9])|(v[0-9])");

    public static void main(String[] args) {

        Matcher m = VERSION_PREFIX_PATTERN.matcher("v1.0/appVersion/findVersion");
        Boolean flag = m.find();
        System.out.println(flag);

    }
}
