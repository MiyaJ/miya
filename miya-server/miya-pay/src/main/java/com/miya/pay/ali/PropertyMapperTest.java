package com.miya.pay.ali;

import org.springframework.boot.context.properties.PropertyMapper;

/**
 * @author Caixiaowei
 * @ClassName PropertyMapperTest
 * @Description
 * @createTime 2021/2/7 17:17
 */
public class PropertyMapperTest {

    public static void main(String[] args) {

        PropertyMapper propertyMapper = PropertyMapper.get().alwaysApplyingWhenNonNull();

        Demo demo = new Demo();
        demo.setAge(12);

        DemoTest test = new DemoTest();
        DemoTest test1 = new DemoTest();
        propertyMapper.from(demo::getName).to(test::setName);
        propertyMapper.from(demo::getAge).to(test::setAge);
        System.out.println(test);

        test1.setName(demo.getName());
        test1.setAge(demo.getAge());
        System.out.println(test1);
    }
}
