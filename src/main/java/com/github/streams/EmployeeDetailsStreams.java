package com.github.streams;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface EmployeeDetailsStreams {

    String INPUT = "input";

    @Input(INPUT)
    SubscribableChannel input();
}
