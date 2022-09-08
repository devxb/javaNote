package xb.note;

import xb.note.client.HelloServiceClient;

public class Main {
    public static void main(String[] args) {
        HelloServiceClient helloServiceClient = new HelloServiceClient("localhost", 4321);
        for(int i = 0; i < 100; i++) (new Thread(i + "", helloServiceClient)).run();
    }

    private static class Thread implements Runnable{

        private final String threadName;
        private final HelloServiceClient helloServiceClient;

        public Thread(String threadName, HelloServiceClient helloServiceClient){
            this.threadName = threadName;
            this.helloServiceClient = helloServiceClient;
        }

        @Override
        public void run(){
            for(int i = 0; i < 100; i++) System.out.println(helloServiceClient.sayHelloWithBlocking(threadName).getResponse());
        }

    }

}