import java.net.*;
import java.io.*;

public class Server {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(1234);
        Socket socket = serverSocket.accept();
        DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

        System.out.println("Server On!!! ServerSocket - 1234");

        String inputString = "";
        String resultString = "";

        while (!inputString.equalsIgnoreCase("stop")) {
            inputString = dataInputStream.readUTF();
            System.out.println("Client Requests: " + inputString);

            int[] numbers = parseNumbers(inputString);
            int sum = findSumOfMinMax(numbers);

            resultString = String.valueOf(sum);
            dataOutputStream.writeUTF(resultString);
            dataOutputStream.flush();
        }

        dataInputStream.close();
        socket.close();
        serverSocket.close();
    }

    private static int[] parseNumbers(String inputString) {
        String[] stringNumbers = inputString.split(" ");
        int[] numbers = new int[stringNumbers.length];

        for (int i = 0; i < stringNumbers.length; i++) {
            numbers[i] = Integer.parseInt(stringNumbers[i]);
        }

        return numbers;
    }

    private static int findSumOfMinMax(int[] numbers) {
        if (numbers.length == 0) {
            return 0;
        }

        int min = numbers[0];
        int max = numbers[0];

        for (int number : numbers) {
            if (number < min) {
                min = number;
            }

            if (number > max) {
                max = number;
            }
        }

        return min + max;
    }
}
