package co.megadodo.udp;

import java.util.Scanner;

import UDP.UDPServer;

public class Main {
	
	public static int i = 0;
	
	public static final int PORT = 1245;
	public static final String HOST = "localhost";
	
	public static void main(String[] args) throws Throwable {
		System.out.println("Server");
		UDPServer server = new UDPServer(HOST, PORT);
//		UDPClient client = new UDPClient(HOST, PORT);
//		UDPMulticastServer server = new UDPMulticastServer(HOST, PORT);
//		UDPMulticastClient client = new UDPMulticastClient(HOST, PORT);
//		int i = 0;
//		int n = 20;
//		while(i<n){
//			new Thread(new Runnable() {
//			
//				@Override
//				public void run() {
//					System.out.println("CLIENT: " + client.send(getStr("Client msg:")));
//
//				}
//			}).start();
//
//			server.sendResponse("<Response from server: \"" + server.recieveRequest() + "\">");
////			for(int a = 0; a < 100000; a++) {
////				
////			}
//			System.out.println("Next iteration");
//			i++;
//		}
		
		String request = server.recieveRequest();
		System.out.println("Server request: <" + request + ">");
		server.sendResponse("Response");
		
		server.close();
		
	}
	
	public static final Scanner sc = new Scanner(System.in);
	public static String getStr(String prompt) {
		System.out.print(prompt);
		return sc.nextLine();
	}

}
