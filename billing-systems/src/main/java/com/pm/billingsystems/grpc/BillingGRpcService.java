package com.pm.billingsystems.grpc;

import billing.BillingResponse;
import billing.BillingServiceGrpc.BillingServiceImplBase;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



@GrpcService   ///GRPC Service
public class BillingGRpcService extends BillingServiceImplBase {




    private static final Logger log = LoggerFactory.getLogger(BillingGRpcService.class);

    @Override
    public void createBillingAccount(billing.BillingRequest billingRequest,
                                     io.grpc.stub.StreamObserver<billing.BillingResponse> responseObserver) {
        log.info("createBillingAccount request={}", billingRequest);

        // Bussiness Logic - e.g. save to database,perform calculates etc.

        BillingResponse billingResponse = BillingResponse.newBuilder()
                .setAccountId("12345")
                .setStatus("ACTIVE")
                .build();
        responseObserver.onNext(billingResponse);
        responseObserver.onCompleted();

    }


}
