package ttt;
 import java.io.BufferedReader;
 import java.io.IOException;
 import java.io.InputStreamReader;
 import java.io.OutputStreamWriter;
 import java.io.PrintWriter;
 import java.net.InetAddress;
 import java.net.Socket;
 import java.net.UnknownHostException;
 import java.util.Scanner;

import com.fasterxml.jackson.core.Versioned;
 
 
 public class Client {
     private static PrintWriter pw=null;
     private static BufferedReader br=null;
     private static Socket s;
     static Scanner scanner=new Scanner(System.in);
     /**
      * @param args
      */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
         try {
             Socket s=new Socket(InetAddress.getLocalHost(),5500);
             pw=new PrintWriter(new OutputStreamWriter(s.getOutputStream()));
             br=new BufferedReader(new InputStreamReader(s.getInputStream()));          
             while(true){
                 System.out.println("Client端请输入：");
                 String str = scanner.next();
                 pw.println(str);
                 pw.flush();
                 String string=br.readLine();
                 System.out.println("Client读到："+string);
                 if(str.equals("exit")){
                     break;
                }
             } 
         }catch (Exception e) {
                 // TODO Auto-generated catch block
                 e.printStackTrace();
         }
         try {
             br.close();
             pw.close();
         } catch (IOException e1) {
                 // TODO Auto-generated catch block
                 e1.printStackTrace();
         }
   
	 }
 }