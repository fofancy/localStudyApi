package com.fofancy.apiconfiguration.concurrency;

import javax.ejb.LockType;
import javax.interceptor.InterceptorBinding;
import java.lang.annotation.*;

/**
 * Created by shaylin3 on 19.05.2017.
 */
/*
* Custom cdi concurrency implementation annotation
* */

@Inherited
@InterceptorBinding
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE})
public @interface Lock {
    LockType value() default LockType.WRITE;
}