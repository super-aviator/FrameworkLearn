package com.xqk.lean.framework.springboot.spi;

import java.util.ServiceLoader;

/**
 * SPITest
 *
 * @author xiongqiankun
 * @since 2022/9/21 14:00
 */
public class SPITest {
    public static void main(String[] args) {
        ServiceLoader<Service> loader = ServiceLoader.load(Service.class);
        for (Service service : loader) {
            service.doService();
        }
    }
}
