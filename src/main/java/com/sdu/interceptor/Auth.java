package com.sdu.interceptor;

import java.lang.annotation.*;

/**
 * Created by J on 2017/3/3.
 */

@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Auth {
}
