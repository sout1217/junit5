package com.inflearn.junit5;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregationException;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StudyTest {

    @DisplayName("스터디 만들기")
    @ParameterizedTest(name= "{index} {displayName} message={0}")
    @ValueSource(strings = {"날씨가", "많이", "더워지고", "있네요"})
    @NullAndEmptySource
    void parameterTest(String message) {
        System.out.println(message);
    }

    @DisplayName("스터디 만들기")
    @ParameterizedTest(name= "{index} {displayName} message={0}")
    @ValueSource(ints = {10, 20, 40})
    void parameterTest2(int limit) {
        System.out.println(limit);
    }

    @DisplayName("스터디 만들기")
    @ParameterizedTest(name= "{index} {displayName} message={0}")
    @ValueSource(ints = {10, 20, 40})
    void parameterTest3(@ConvertWith(StudyConverter.class) Study study) {
        System.out.println(study.getLimit());
    }

    // 1개의 argument 에만 적용 될 때 SimpleArgumentConverter
    static class StudyConverter extends SimpleArgumentConverter {
        @Override
        protected Object convert(Object source, Class<?> targetClass) throws ArgumentConversionException {
            assertEquals(Study.class, targetClass, "Can only convert to Study");
            return new Study(Integer.parseInt(source.toString()));
        }
    }

    @DisplayName("스터디 만들기")
    @ParameterizedTest(name= "{index} {displayName} message={0}")
    @CsvSource({"10, '자바 스터디'", "20, 스프링"})
    void parameterTest4(Integer limit, String name) {
        Study study = new Study(limit, name);
        System.out.println(study);
    }

    @DisplayName("스터디 만들기")
    @ParameterizedTest(name= "{index} {displayName} message={0}")
    @CsvSource({"10, '자바 스터디'", "20, 스프링"})
    void parameterTest5(ArgumentsAccessor  argumentsAccessor) {
        Study study = new Study(argumentsAccessor.getInteger(0), argumentsAccessor.getString(1));
        System.out.println(study);
    }

    // 여러개의 arg 를 할 때는 aggregate 를 사용
    // inner static 클래스 또는 public 클래스 형태로 만들어야 한다
    static class StudyAggregator implements ArgumentsAggregator {

        @Override
        public Object aggregateArguments(ArgumentsAccessor argumentsAccessor, ParameterContext parameterContext) throws ArgumentsAggregationException {
            return new Study(argumentsAccessor.getInteger(0), argumentsAccessor.getString(1));
        }
    }
    @DisplayName("스터디 만들기")
    @ParameterizedTest(name= "{index} {displayName} message={0}")
    @CsvSource({"10, '자바 스터디'", "20, 스프링"})
    void parameterTest6(@AggregateWith(StudyAggregator.class) Study study) {
        System.out.println(study);
    }
}
