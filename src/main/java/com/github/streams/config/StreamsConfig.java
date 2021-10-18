package com.github.streams.config;

import com.github.streams.EmployeeDetailsStreams;
import org.springframework.cloud.stream.annotation.EnableBinding;

@EnableBinding({EmployeeDetailsStreams.class})
public class StreamsConfig {
}
