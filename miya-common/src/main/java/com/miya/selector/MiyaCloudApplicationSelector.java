package com.miya.selector;

import com.miya.configure.MiyaAuthExceptionConfigure;
import com.miya.configure.MiyaOAuth2FeignConfigure;
import com.miya.configure.MiyaServerProtectConfigure;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author Caixiaowei
 * @ClassName MiyaCloudApplicationSelector.java
 * @Description TODO
 * @createTime 2020年05月13日 15:18:00
 */
public class MiyaCloudApplicationSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        return new String[]{
                MiyaAuthExceptionConfigure.class.getName(),
                MiyaOAuth2FeignConfigure.class.getName(),
                MiyaServerProtectConfigure.class.getName()
        };
    }
}
