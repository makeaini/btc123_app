package com.btc123.app.annotations;

import java.lang.annotation.*;

/**
 * Created by shining on 2018/5/22.
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Validate {
    Class validateClass();
}
