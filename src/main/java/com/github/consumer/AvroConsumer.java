package com.github.consumer;

import com.github.classes.avro.Employee;
import com.github.streams.EmployeeDetailsStreams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@EnableBinding(Sink.class)
public class AvroConsumer {

    @StreamListener(EmployeeDetailsStreams.INPUT)
    public void consumeEmployeeDetails(Employee employeeDetails) {
        log.info("Let's process employee details: {}", employeeDetails);
    }
}
