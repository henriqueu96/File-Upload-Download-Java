package tcpserver;

import java.io.*;
import java.net.*;

class TCPServer {

    @SuppressWarnings("ConvertToTryWithResources")
    public static void main(String argv[]) throws Exception {
        String nomeArquivo;
        String echo;       
        ServerSocket welcomeSocket = new ServerSocket(6790); /* Cria socket do servidor */
        
        
        while (true) {            
            Socket connectionSocket = welcomeSocket.accept(); // Aguarda o recebimento de uma conexão
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream())); // Cria uma stream de entrada para receber os dados do cliente
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream()); /* Cria uma stream de saída para enviar dados para o cliente */
            nomeArquivo = inFromClient.readLine(); // Aguarda nome do arquivo          
            
            InputStream in = connectionSocket.getInputStream();
            File arquivo = new File(nomeArquivo);
            FileOutputStream out = new FileOutputStream(arquivo);
			    int tamanho = 4096; // buffer de 4KB  
			    byte[] buffer = new byte[tamanho];  
			    int lidos = -1;  
			    while ((lidos = in.read(buffer, 0, tamanho)) != -1) {  
			    	System.out.println(lidos);
			        out.write(buffer, 0, lidos);  
			    }  
			    out.flush();  
            
            InetAddress IPAddress = connectionSocket.getInetAddress();  /* Determina o IP e Porta de origem */
            int port = connectionSocket.getPort();            
            System.out.println(IPAddress.getHostAddress() + ":" + port + " => " + nomeArquivo); /* Exibe, IP:port => msg */
            echo = nomeArquivo + '\n'; /* Adiciona o \n para que o cliente também possa ler usando readLine() */
            outToClient.writeBytes(echo); /* Envia mensagem para o cliente*/
            connectionSocket.close();  /* Encerra socket do cliente */
        }
    }
}
