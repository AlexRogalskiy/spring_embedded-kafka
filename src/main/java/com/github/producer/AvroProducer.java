package com.github.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.classes.avro.Employee;
import com.github.streams.OutputStreams;
import lombok.extern.slf4j.Slf4j;
import org.awaitility.Awaitility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;

@Service
@Slf4j
public class AvroProducer {

    ModelMapper mapper = new ModelMapper();
    Object eventRecord;

    @Autowired
    private OutputStreams outputStreams;

    public void produceEmployeeDetails(int empId, String firstName, String lastName) {

        Awaitility.setDefaultPollInterval(5, TimeUnit.SECONDS);
        Awaitility.setDefaultPollDelay(Duration.ZERO);
        Awaitility.setDefaultTimeout(Duration.ofSeconds(30));

        Employee employee = new Employee();
        employee.setId(empId);
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        eventRecord = employee;

//        log.info("Submitting event to {} {}", currentTopic, System.getProperty("line.separator"));

        Employee employee1 = mapper.map(eventRecord, Employee.class);
        final Message<Employee> build = MessageBuilder.withPayload(employee1).build();
        outputStreams.employeeDetailsOutput().send(build);

//        await().atMost(25, TimeUnit.SECONDS).until(() -> ConsumedEvents.getConsumedCardIssuanceCreatedEvents().size() == 1);
//        break;

    }

}
