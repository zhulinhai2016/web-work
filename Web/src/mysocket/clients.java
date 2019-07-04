package mysocket; 
	import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;  
	public class clients {
		public static void main(String[] args) throws Exception   { 
			Socket socket = new Socket("192.168.1.55", 485);     // �����˳��������� 
			OutputStream ops = socket.getOutputStream();        
			OutputStreamWriter opsw = new OutputStreamWriter(ops);  
			BufferedWriter bw = new BufferedWriter(opsw);         
			bw.write("hello world\r\n\r\n");       
			bw.flush();            // �ӷ���˳����������    
			InputStream ips = socket.getInputStream();   
			InputStreamReader ipsr = new InputStreamReader(ips);   
			BufferedReader br = new BufferedReader(ipsr);     
			String s = "";        
			while((s = br.readLine()) != null)    
				System.out.println(s);        
			     socket.close();  
	   } 
		    
    

  }
