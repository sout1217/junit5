package com.inflearn.junit5;


import com.inflearn.junit5.annotations.SlowTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;


public class StudyTest {

    // RegisterExtension 방법
    @RegisterExtension
    static FindSlowTestExtension findSlowTestExtension = new FindSlowTestExtension(1000L);

    @Test
    void normalTest() {
        System.out.println("normalTest");
    }

    @Test
    void slowTest() throws InterruptedException {
        Thread.sleep(2000);
        System.out.println("slow test ~");
    }

    @Test
    void fastTest() {
        System.out.println("fast test ~ ");

    }

    @SlowTest
    void verySlow() throws InterruptedException {
        Thread.sleep(3000);
        System.out.println("very Slow");
    }
}
