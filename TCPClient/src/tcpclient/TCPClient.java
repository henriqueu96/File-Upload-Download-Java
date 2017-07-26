package tcpclient;

import java.io.*;
import java.net.*;

class TCPClient {

    @SuppressWarnings("ConvertToTryWithResources")
    public static void main(String argv[]) throws Exception {
        String nomeArquivo;

        Socket clientSocket = new Socket("127.0.0.1", 6790); // Socket cliente ip/porta
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in)); // Entrada de dados console               
        OutputStream out = clientSocket.getOutputStream(); // Guarda outputstram
        OutputStreamWriter osw = new OutputStreamWriter(out);
        BufferedWriter writer = new BufferedWriter(osw);
        DataOutputStream outToServer = new DataOutputStream(out); // Cria uma stream de saída para enviar dados para o servidor
        //BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); // Cria uma stream de entrada para receber os dados do servidor   
        nomeArquivo = ConsoleCliente.PerguntaNomeArquivo(inFromUser);
        outToServer.writeBytes(nomeArquivo + '\n'); // Envia para o servidor. usar /n no fim

        // Ler Arquivo
        File arquivo = new File(nomeArquivo);
        arquivo.createNewFile();
        FileInputStream in = new FileInputStream(arquivo);

        writer.write(arquivo.getName() + "\n");
        writer.flush();

        int tamanho = 4096; // buffer de 4KB  
        byte[] buffer = new byte[tamanho];
        int lidos = -1;
        while ((lidos = in.read(buffer, 0, tamanho)) != -1) {
            out.write(buffer, 0, lidos);
        }

        // Para Ler Arquivo                        
        // echo = inFromServer.readLine(); // Lê resposta servidor      
        System.out.println("FROM SERVER: " + arquivo.getName());
        clientSocket.close(); // encerra conexao
    }
}
