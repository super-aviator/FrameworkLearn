package com.xqk.learn.springboot.base.ioc.circularreference;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * ReferenceABean与ReferenceBBean形成了循环引用，启动时失败，解决方法是：
 * 解决方法是将其中一个Bean的依赖注入方式由构造函数注入修改为Setter方法注入或者Field注入即可
 * 日期 2019/10/15 10:07
 *
 * @author 熊乾坤
 */
@Component
@Data
public class ReferenceABean {
    //public ReferenceABean(ReferenceBBean referenceBBean){}

    public void setReferenceBBean(ReferenceBBean referenceBBean) {
    }

}
