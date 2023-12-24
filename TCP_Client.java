import java.io.*;
import java.net.*;
import java.util.*;

class TCPClient {
    public static void main(String argv[]) throws Exception {
        String number1,number2,sentence; 
         String addition;
         Scanner inFromUser = null;
         Socket clientSocket = null;
         DataOutputStream outToServer = null;
         Scanner inFromServer = null;
         try { 
		      inFromUser = new Scanner(System.in);
            clientSocket = new Socket("localhost", 1667); 
            outToServer = 
               new DataOutputStream(clientSocket.getOutputStream()); 
			   inFromServer = new Scanner(clientSocket.getInputStream());
               
               while(true){
                    System.out.print("enter number 1 (to end just press enter): ");
                    number1 = inFromUser.nextLine();
                    if(number1.trim().isEmpty()){
                        break;
                    }
                    number1 = number1+ " ";

                    System.out.print("enter number 2 (to end just press enter): ");
                    number2 = inFromUser.nextLine();
                    if(number2 == null){
                        break;
                    }

                    sentence = number1+number2;
                    //System.out.println(sentence);
                    outToServer.writeBytes(sentence + '\n');
                    addition = inFromServer.nextLine();
                    System.out.println("The result is: " + addition);
                    
               }
               inFromUser.close();
         }
         catch (IOException e) {    
             System.out.println("Error occurred: Closing the connection");
             //e.printStackTrace();
         }
         finally {
            try {
                if (inFromServer != null)
                    inFromServer.close();
                if (outToServer != null)
                    outToServer.close();
                if (clientSocket != null)
                    clientSocket.close();
            }
            catch (IOException e) {
               e.printStackTrace();
            }
         } 
    }
}