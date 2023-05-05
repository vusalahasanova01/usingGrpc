package com.example.demo.service.grpc;

import com.example.demo.proto.CreateLogRequest;
import com.example.demo.proto.LogServiceGrpc;
import com.example.demo.service.grpc.GrpcServiceClient;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GrpcServiceClientImpl implements GrpcServiceClient {

    @Value("${grpc.server.port}")
    private Integer port;

    @Value("${grpc.server.host}")
    private String host;
    private LogServiceGrpc.LogServiceBlockingStub client;

    @PostConstruct
    private void init() {
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress(host, port)
                .usePlaintext()
                .build();
        this.client = LogServiceGrpc.newBlockingStub(channel);
    }

    @Override
    public void createLog(CreateLogRequest createLogRequest) {
        client.createLog(createLogRequest);
    }
}
