package com.example.demo.service.grpc;

import com.example.demo.proto.CreateLogRequest;

public interface GrpcServiceClient {

    void createLog(CreateLogRequest createLogRequest);
}
