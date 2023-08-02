package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
	private static DataOutputStream outStream = null;
	private static DataInputStream inputStream = null;
	
	public static void main(String[] args) {
		try(ServerSocket serverSocket = new ServerSocket(9001)){
			System.out.println("Esperando en el puerto: 9001");
				Socket clientSocket = serverSocket.accept();
				System.out.println(clientSocket + " Conectado.");
				inputStream = new DataInputStream(clientSocket.getInputStream());
				outStream = new DataOutputStream(clientSocket.getOutputStream());
				
				llegadaDeArchivo("main_backup.dat");
				inputStream.close();
				outStream.close();
				clientSocket.close();
	            
				System.out.println("Puerto cerrado");
	        } catch (Exception e){
	        	e.printStackTrace();
	    }
	}

	private static void llegadaDeArchivo(String fileName) throws Exception{
		int bytes = 0;
		FileOutputStream fileOutputStream = new FileOutputStream(fileName);
        
		long size = inputStream.readLong();
        
        byte[] buffer = new byte[4*1024];
        
        while (size > 0 && (bytes = inputStream.read(buffer, 0, (int)Math.min(buffer.length, size))) != -1) {
        	fileOutputStream.write(buffer,0,bytes);
        	size -= bytes;
        }
        fileOutputStream.close();
    }
}