package tcpClient;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class client {
	public static void main(String[] args) { 
        try { 
            Socket socket = new Socket("192.168.1.51", 554); 
            // 鍚戞湇鍔″櫒绔彂閫佹暟鎹� 
            OutputStream os =  socket.getOutputStream(); 
            DataOutputStream bos =null; 
            while(true){
            	bos = new DataOutputStream(os); 
            	Scanner sc = new Scanner(System.in);
            	System.out.println("输入要发送的内容："+"\n");
            	String str = sc.nextLine();
            	bos.write(str.getBytes()); 
                bos.flush(); 
            }
        } catch (UnknownHostException e) { 
            e.printStackTrace(); 
        } catch (IOException e) { 
            e.printStackTrace(); 
        } 
    }  
	
	/*public static byte[] read(DataInput in) throws IOException {
		int len = in.readInt();
		byte[] bytes = new byte[len];
		in.readFully(bytes);
		return bytes;
		}*/

}
