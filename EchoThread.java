import java.io.*; 
import java.net.*; 
import java.util.*;
public class EchoThread extends Thread {
    private Socket connectionSocket;
    public EchoThread(Socket connectionSocket) {
        this.connectionSocket = connectionSocket;
    }
    public void run() {
         System.out.println("Thread running");
         Scanner inFromClient = null;
         DataOutputStream outToClient = null;
         try {
            
               inFromClient = new Scanner(connectionSocket.getInputStream());
			      outToClient = new DataOutputStream(connectionSocket.getOutputStream());
               
               while(true){
                  String clientSentence = inFromClient.nextLine();

                  if (clientSentence.trim().isEmpty()) {
                     break;
                  }

                  String[] numbersAsStrings = clientSentence.split(" ");
                  
                  int num1 = Integer.parseInt(numbersAsStrings[0]);
                  //System.out.println(num1);
                  int num2 = Integer.parseInt(numbersAsStrings[1]);
                  //System.out.println(num2);
                  int result = num1 + num2;
                  //System.out.println(result); 
                  outToClient.writeBytes(Integer.toString(result) + "\n");
               }   
		   }
        catch (IOException e) {
            System.err.println("Closing Socket connection");
        }
        finally {
            try {
               if (inFromClient != null)
                  inFromClient.close();
               if (outToClient != null)
                  outToClient.close();
               if (connectionSocket != null)
                  connectionSocket.close();
               }
            catch (IOException e) {
               e.printStackTrace();
            }
        }
    }
}