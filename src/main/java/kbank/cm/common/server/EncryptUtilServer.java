package kbank.cm.common.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

import kbank.cm.common.service.EncryptUtilImpl;

public class EncryptUtilServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        int port = 50051;
        Server server = ServerBuilder.forPort(port)
            .addService(new EncryptUtilImpl())
            .build()
            .start();

        System.out.println("Server started, listening on " + port);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.err.println("Shutting down gRPC server since JVM is shutting down");
            if (server != null) {
                server.shutdown();
            }
            System.err.println("Server shut down");
        }));
        server.awaitTermination();
    }
}
