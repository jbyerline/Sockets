import java.io.*;
import java.net.*;

public class SocketClient {

    public String connectForOneMessage(String sIP, int iPort, String sMessage){

        try(Socket oSocket = new Socket()){

            // Connect to server.
            oSocket.connect(new InetSocketAddress(sIP, iPort), 5000);

            OutputStream output = oSocket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);

            // Send message to server.
            writer.println(sMessage);
            writer.flush();

            InputStream input = oSocket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            String sReturn = reader.readLine();

            oSocket.close();

            return sReturn;
        }
        catch (Exception ex){
            System.out.println("[client]: Client exception: " + ex.getMessage());
            // Prints more details on the error
            //ex.printStackTrace();
            return null;
        }
    }
}
