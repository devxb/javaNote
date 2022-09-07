package xb.note.client;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import xb.note.protos.HelloServiceGrpc;

public class HelloServiceClient {

    private ManagedChannel managedChannel;

    public HelloServiceClient(String host, int port){
        managedChannel = ManagedChannelBuilder.forAddress(host, port).build();
        HelloServiceGrpc.HelloServiceBlockingStub blockingStub = xb.note.protos.HelloServiceGrpc.newBlockingStub(managedChannel);
        HelloServiceGrpc.HelloServiceFutureStub asyncStub = xb.note.protos.HelloServiceGrpc.newFutureStub(managedChannel);
    }

}
