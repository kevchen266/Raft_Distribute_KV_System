package com.github.raftimpl.raft.example.client;

import com.baidu.brpc.client.BrpcProxy;
import com.baidu.brpc.client.RpcClient;
import com.github.raftimpl.raft.example.server.service.ExampleProto;
import com.github.raftimpl.raft.example.server.service.ExampleService;
import com.googlecode.protobuf.format.JsonFormat;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by raftimpl on 2017/5/14.
 */
public class ConcurrentClientMain {
    private static JsonFormat jsonFormat = new JsonFormat();

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.printf("Usage: ./run_concurrent_client.sh THREAD_NUM\n");
            System.exit(-1);
        }

        // parse args for server side ip and port parameter and create RPC client to obtain proxy.

        String ipPorts = args[0];
        RpcClient rpcClient = new RpcClient(ipPorts);
        ExampleService exampleService = BrpcProxy.getProxy(rpcClient, ExampleService.class);

        //create two threadpool to handle write and read tasks.
        ExecutorService readThreadPool = Executors.newFixedThreadPool(3);
        ExecutorService writeThreadPool = Executors.newFixedThreadPool(3);
        Future<?>[] future = new Future[3];
        for (int i = 0; i < 3; i++) {
            future[i] = writeThreadPool.submit(new SetTask(exampleService, readThreadPool));
        }
    }

    public static class SetTask implements Runnable {
        private ExampleService exampleService;
        ExecutorService readThreadPool;

        public SetTask(ExampleService exampleService, ExecutorService readThreadPool) {
            this.exampleService = exampleService;
            this.readThreadPool = readThreadPool;
        }

        @Override
        public void run() {
            while (true) {
                String key = UUID.randomUUID().toString();
                String value = UUID.randomUUID().toString();
                //create protobuf instance
                //Once the SetRequest message is built, it can be used to perform operations such as sending it over a network or processing it locally within an application.
                //创建写请求，交由RPC客户端处理。
                ExampleProto.SetRequest setRequest = ExampleProto.SetRequest.newBuilder()
                        .setKey(key).setValue(value).build();

                long startTime = System.currentTimeMillis();
                ExampleProto.SetResponse setResponse = exampleService.set(setRequest);
                try {
                    if (setResponse != null) {
                        System.out.printf("set request, key=%s, value=%s, response=%s, elapseMS=%d\n",
                                key, value, jsonFormat.printToString(setResponse), System.currentTimeMillis() - startTime);
                        //如果写请求得到响应，将读任务提交到线程池，读取写请求的结果。
                        readThreadPool.submit(new GetTask(exampleService, key));
                    } else {
                        System.out.printf("set request failed, key=%s value=%s\n", key, value);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public static class GetTask implements Runnable {
        private ExampleService exampleService;
        private String key;

        public GetTask(ExampleService exampleService, String key) {
            this.exampleService = exampleService;
            this.key = key;
        }

        @Override
        public void run() {
            ExampleProto.GetRequest getRequest = ExampleProto.GetRequest.newBuilder()
                    .setKey(key).build();
            long startTime = System.currentTimeMillis();
            ExampleProto.GetResponse getResponse = exampleService.get(getRequest);
            try {
                if (getResponse != null) {
                    System.out.printf("get request, key=%s, response=%s, elapseMS=%d\n",
                            key, jsonFormat.printToString(getResponse), System.currentTimeMillis() - startTime);
                } else {
                    System.out.printf("get request failed, key=%s\n", key);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

}
