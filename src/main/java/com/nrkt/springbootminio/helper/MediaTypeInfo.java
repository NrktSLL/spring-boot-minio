package com.nrkt.springbootminio.helper;

import lombok.experimental.UtilityClass;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@UtilityClass
public class MediaTypeInfo {
    public String getCurrentMediaType() {
        var requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) throw new IllegalArgumentException();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        return request.getParameter("mediaType");
    }
}
