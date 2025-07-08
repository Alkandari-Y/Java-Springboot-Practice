package com.project.kfhpractice.helloworld;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldApiController {

    @GetMapping("/api/v1/hello")
    public ResponseEntity<ResponseDto<HelloWorld>> helloResponse() {
        HelloWorld data = new HelloWorld("Hello World!");
        ResponseDto<HelloWorld> response = new ResponseDto<>(data, 200, true);
        return ResponseEntity.ok(response);
    }
}
