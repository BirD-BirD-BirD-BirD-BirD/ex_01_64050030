import java.io.*; 
import java.net.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UDPServer{

    public static void main(String[] args)throws Exception{
        DatagramSocket serverSocket = new DatagramSocket(9876); 
        byte[] receiveData = new byte[1024]; 
        byte[] sendData  = new byte[1024];

        while(true){
            System.out.println("The server is waiting ");
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();

            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length); 
            serverSocket.receive(receivePacket);

            InetAddress IPAddress = receivePacket.getAddress(); 
            int port = receivePacket.getPort();
            sendData = dtf.format(now).getBytes();

            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
            serverSocket.send(sendPacket);            
        }

    }
    
}
