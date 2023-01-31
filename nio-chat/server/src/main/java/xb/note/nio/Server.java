package xb.note.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

public class Server{

    private Selector selector;
    private ServerSocketChannel serverSocketChannel;

    public static void main(String[] args){
        (new Server()).run();
    }

    public Server(){
        initiateServerSocketChannel();
    }

    public void initiateServerSocketChannel(){
        try{
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress("localhost", 4999));
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.
        } catch (IOException IOE){
            throw new IllegalStateException("Wrong occurred during \"initiateServerSocketChannel()\"");
        }
    }

    public void run(){

    }

}