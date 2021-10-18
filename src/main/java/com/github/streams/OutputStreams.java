package com.github.streams;

import com.github.events.BindingsAndTopics;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.messaging.MessageChannel;

@EnableIntegration
@Configuration
public interface OutputStreams {

    @Output(BindingsAndTopics.BINDINGS_EMPLOYEE_DETAILS)
    MessageChannel employeeDetailsOutput();

}
