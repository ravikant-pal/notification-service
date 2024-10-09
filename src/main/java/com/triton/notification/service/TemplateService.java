package com.triton.notification.service;

import java.util.Map;

public interface TemplateService {
    String parseTemplate(String templateName, Map<String, Object> variables);
    String parseTemplate(String templateName);
}
