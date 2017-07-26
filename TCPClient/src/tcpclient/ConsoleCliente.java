package tcpclient;

import java.io.BufferedReader;
import java.io.IOException;

public class ConsoleCliente {
    public static String PerguntaNomeArquivo(BufferedReader inFromUser) throws IOException{
         System.out.print("Digite o nome do arquivo: ");   
        return inFromUser.readLine();
    }
}
