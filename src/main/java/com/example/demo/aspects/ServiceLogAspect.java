package com.example.demo.aspects;

import com.example.demo.dto.LogJsonDto;
import com.example.demo.model.OperationType;
import com.example.demo.proto.CreateLogRequest;
import com.example.demo.model.OperationService;
import com.example.demo.service.grpc.GrpcServiceClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import static com.example.demo.model.OperationType.*;

@Slf4j
@RequiredArgsConstructor
@Component
@Aspect
public class ServiceLogAspect {

    private final ObjectMapper objectMapper;
    private final GrpcServiceClient grpcServiceClient;

    @Pointcut("execution(public * com.example.demo.service.impl.*.add*(..))")
    private void forCreate() {}

    @Pointcut("execution(public * com.example.demo.service.impl.*.update*(..))")
    private void forUpdate() {}

    @Pointcut("execution(public * com.example.demo.service.impl.*.delete*(..))")
    private void forDelete() {}

    @SneakyThrows
    @AfterReturning(pointcut = "forCreate()", returning = "result")
    public void afterCreateAdvice(JoinPoint joinPoint, Object result) {
        CreateLogRequest logRequest = createLogRequest(joinPoint, result, CREATE);
        grpcServiceClient.createLog(logRequest);
    }

    @SneakyThrows
    @AfterReturning(pointcut = "forUpdate()", returning = "result")
    public void afterUpdateAdvice(JoinPoint joinPoint, Object result) {
        CreateLogRequest logRequest = createLogRequest(joinPoint, result, UPDATE);
        grpcServiceClient.createLog(logRequest);
    }

    @SneakyThrows
    @AfterReturning(pointcut = "forDelete()", returning = "result")
    public void afterDeleteAdvice(JoinPoint joinPoint, Object result) {
        CreateLogRequest logRequest = createLogRequest(joinPoint, result, DELETE);
        grpcServiceClient.createLog(logRequest);
    }

    @SneakyThrows
    private CreateLogRequest createLogRequest(JoinPoint joinPoint, Object result, OperationType operationType) {
        Object[] args = joinPoint.getArgs();
        LogJsonDto logJson = LogJsonDto.builder()
                .args(args)
                .result(result)
                .build();
        String simpleClassName = joinPoint.getTarget().getClass().getSimpleName();
        log.info("simple class name: {}", simpleClassName);
        int operation_service = OperationService.findByClassName(simpleClassName)
                .map(Enum::ordinal)
                .orElseThrow(() -> new IllegalArgumentException("Operation service not found"));
        String username = args[0].toString();
        return CreateLogRequest.newBuilder()
                .setUsername(username)
                .setOperationService(operation_service)
                .setOperationType(operationType.ordinal())
                .setJson(objectMapper.writeValueAsString(logJson))
                .build();
    }
}
