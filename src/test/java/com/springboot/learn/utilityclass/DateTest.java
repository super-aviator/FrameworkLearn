package com.springboot.learn.utilityclass;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class DateTest {
    @Test
    public void equalsTest() {
        Date date1 = new Date(2019, 11, 2);
        Date date2 = new Date(2019, 11, 2);
        log.info(String.valueOf(date1.equals(date2)));
    }

    @Test
    public void equalDate() {
        Calendar startDate = Calendar.getInstance();
        startDate.set(2019, 5, 6, 0, 0, 0);
        Calendar endDate = Calendar.getInstance();
        endDate.setTimeInMillis(new Date().getTime());
        log.info("****" + startDate.toInstant());
        log.info("****" + endDate.toInstant());
        for (; !startDate.after(endDate); startDate.add(Calendar.DAY_OF_MONTH, 1)) {
//            log.info("****"+startDate.toInstant());
            log.info(startDate.getTime().toString());
        }
    }

    @Test
    public void dateFormat() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/M/d");
        log.info(dateFormat.format(new Date(1800, 4, 3)));
    }
}
