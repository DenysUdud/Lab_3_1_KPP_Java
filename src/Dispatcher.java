import java.net.*;
import java.io.*;

public class Dispatcher {
    public static void main(String args[]) throws Exception {
        Socket socket = new Socket("localhost", 1234);
        DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Client Onboard!!! Socket - 1234");

        String inputString = "";
        String resultString = "";

        while (!inputString.equalsIgnoreCase("stop")) {
            System.out.print("Enter a space-separated sequence of integers (e.g., '3 -1 5'): ");
            inputString = bufferedReader.readLine();
            dataOutputStream.writeUTF(inputString);
            dataOutputStream.flush();
            resultString = dataInputStream.readUTF();
            System.out.println("Server Returns Result: " + resultString);
        }

        dataOutputStream.close();
        socket.close();
    }
}
