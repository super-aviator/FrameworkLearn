package com.xqk.lean.framework.springboot.mvc.validation;

import com.xqk.lean.framework.springboot.mvc.validated.bean.Item;
import com.xqk.lean.framework.springboot.mvc.validated.util.ValidatorUtils;
import jakarta.validation.ConstraintViolation;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Set;

/**
 * @author 熊乾坤
 * @since 2020-05-24 18:56
 */
@Slf4j
public class ValidatorTest {

    @Test
    public void test() {
        Item item = new Item();
        item.setAge(100);
        item.setName("xqk");
        item.setId(1L);
        item.setIdNumber("1234567891");
        Set<ConstraintViolation<Item>> result = ValidatorUtils.getValidator().validate(item);
        if (result.isEmpty()) {
            log.info("校验成功！");
        } else {
            log.error("校验失败，原因：");
            for (ConstraintViolation<Item> r : result) {
                log.error(r.getMessage());
            }
        }
    }
}
