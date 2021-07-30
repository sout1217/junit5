package com.inflearn.junit5;

import com.inflearn.junit5.annotations.SlowTest;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.lang.reflect.Method;

public class FindSlowTestExtension implements BeforeTestExecutionCallback, AfterTestExecutionCallback {

    // 1초 = 1000ms
    private final long threshold;

    public FindSlowTestExtension(long threshold) {
        this.threshold = threshold;
    }

    private ExtensionContext.Store getStore(ExtensionContext context) {
        String testClassName = context.getRequiredTestClass().getName();
        String testMethodName = context.getRequiredTestMethod().getName();
        return context.getStore(ExtensionContext.Namespace.create(testClassName, testMethodName));
    }

    // 테스트가 실행 전 context 를 가져옴
    @Override
    public void beforeTestExecution(ExtensionContext context) throws Exception {

        // 값들을 저장할 수 있는 store 라는 인터페이스가 있다
        // 데이터를 넣고 빼기를 할 때 사용
        ExtensionContext.Store store = getStore(context);
        store.put("START_TIME",  System.currentTimeMillis());

    }

    // 테스트가 실행 후 context 를 가져옴
    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {
        Method requiredTestMethod = context.getRequiredTestMethod();

        // 메소드 명을 가져온다
        String testMethodName = requiredTestMethod.getName();

        // SlowTest 어노테이션이 있는 경우 가져온다 없으면 null
        SlowTest annotation = requiredTestMethod.getAnnotation(SlowTest.class);

        ExtensionContext.Store store = getStore(context);

        // Store 에서 'START_TIME' 을 찾아 가져온다
        Long start_time = store.remove("START_TIME", long.class);
        long duration = System.currentTimeMillis() - start_time;

        if (duration > threshold && annotation == null) {
            System.err.printf("[%s]에 @SlowTest 를 붙여야 합니다 \n", testMethodName);
        }
    }
}
