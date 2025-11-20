package com.pm.patientservices.RPC;

import billing.BillingRequest;
import billing.BillingResponse;
import billing.BillingServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Slf4j
@Service

public class BillingServiceGrpcClient {

    private final BillingServiceGrpc.BillingServiceBlockingStub stub;

    public BillingServiceGrpcClient(
        @Value("${billing.service.address:localhost}") String serverAddress,
        @Value("${billing.service.grpc.port:9001}") int serverPort)
    {
         log.info("Connecting to billing service at {}:{}", serverAddress, serverPort);
         ManagedChannel channel = ManagedChannelBuilder.forAddress(serverAddress, serverPort)
                 .usePlaintext().build();
     stub = BillingServiceGrpc.newBlockingStub(channel);

      }
      public BillingResponse createBilling(String patientid,String name,String email) {

        BillingRequest request = BillingRequest.newBuilder().setPatientid(patientid)
                .setName(name).setEmail(email).build();

        BillingResponse response = stub.createBillingAccount(request);

        log.info("Response from BillingServiceGrpc is {}", response);

    return response;
    }

    }

