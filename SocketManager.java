import java.util.Scanner;

public class SocketManager {

    public static void main(String[] args){

        // Get port for this server to listen on.
        System.out.print("Enter port for this server to listen on: ");
        int iThisServerPort = new Scanner(System.in).nextInt();
        SocketServer oServer = new SocketServer(iThisServerPort);

        Thread oServerThread = new Thread(oServer);
        oServerThread.start();

        // Get details of server to connect.
        System.out.println("Enter IP address of server to connect to: ");
        String sOtherServerIP = new Scanner(System.in).nextLine();
        System.out.println("Enter port of server to connect to: ");
        int iOtherServerPort = new Scanner(System.in).nextInt();

        while (true) {

            System.out.print("Enter message you'd like to send to server: ");
            String sMessage = new Scanner(System.in).nextLine();
            SocketClient oClient = new SocketClient();
            String sReply = oClient.connectForOneMessage(sOtherServerIP, iOtherServerPort, sMessage);
            System.out.println("[client]: Remote server reply: " + sReply);
        }
    }
}
