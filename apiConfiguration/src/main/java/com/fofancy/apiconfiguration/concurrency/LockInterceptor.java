package com.fofancy.apiconfiguration.concurrency;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by shaylin3 on 19.05.2017.
 */
@Lock
@Interceptor
public class LockInterceptor {

    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock(true);

    @AroundInvoke
    public Object concurrencyControl(InvocationContext ctx) throws Exception {
        Lock lockAnnotation = ctx.getMethod().getAnnotation(Lock.class);

        if (lockAnnotation == null) {
            lockAnnotation = ctx.getTarget().getClass().getAnnotation(Lock.class);
        }

        Object returnValue = null;
        switch (lockAnnotation.value()) {
            case WRITE:
                ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();
                try {
                    writeLock.lock();
                    returnValue = ctx.proceed();
                } finally {
                    writeLock.unlock();
                }
                break;
            case READ:
                ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
                try {
                    readLock.lock();
                    returnValue = ctx.proceed();
                } finally {
                    readLock.unlock();
                }
                break;
        }
        return returnValue;
    }
}