package com.pm.billingsystems.grpc;

import billing.BillingResponse;
import billing.BillingServiceGrpc.BillingServiceImplBase;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



@GrpcService   ///GRPC Service
public class BillingGRpcService extends BillingServiceImplBase {

    public static String getRandomNumberString(int length) {
        java.security.SecureRandom random = new java.security.SecureRandom();
        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            sb.append(random.nextInt(10)); // generates digit 0–9
        }

        return sb.toString();
    }


    private static final Logger log = LoggerFactory.getLogger(BillingGRpcService.class);

    @Override
    public void createBillingAccount(billing.BillingRequest billingRequest,
                                     io.grpc.stub.StreamObserver<billing.BillingResponse> responseObserver) {
        log.info("createBillingAccount request={}", billingRequest);

        // Bussiness Logic - e.eg save to database,perform calculates etc.

        BillingResponse billingResponse = BillingResponse.newBuilder()
                .setAccountId(getRandomNumberString(7))
                .setStatus("ACTIVE")
                .build();
        responseObserver.onNext(billingResponse);
        responseObserver.onCompleted();

    }


}
