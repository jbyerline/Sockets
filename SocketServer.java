import java.io.*;
import java.net.*;

public class SocketServer implements Runnable{

    private int thisServerPort;

    // Constructor
    public SocketServer(int iPort){
        thisServerPort = iPort;
    }

    public void run(){

        try(ServerSocket oServerSocket = new ServerSocket(thisServerPort)){

            System.out.println("Server is listening on port: " + thisServerPort);

            while (true){

                Socket oSocket = oServerSocket.accept();
                System.out.println("[server]: new client connected: " + oSocket.getRemoteSocketAddress());

                InputStream input = oSocket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));

                OutputStream output = oSocket.getOutputStream();
                PrintWriter writer = new PrintWriter(output, true);

                // Get one time message from one client.
                String sReceivedMessage = reader.readLine();

                System.out.println("[server]: Server received message: " + sReceivedMessage);
                // Send message back to calling client.
                writer.println("Server received message: " + sReceivedMessage);
                writer.flush();

            }
        }
        catch (IOException ex){

            System.out.println("[server]: Server exception: " + ex.getMessage());
            // Prints more details on the error
            //ex.printStackTrace();
        }
    }
}
