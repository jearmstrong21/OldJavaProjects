package co.megadodo.udp;

import UDP.UDPClient;

public class Client {
	
	public static void main(String[] args) throws Throwable {
		System.out.println("Client");
		UDPClient client = new UDPClient(Main.HOST, Main.PORT);
//		UDPMulticastClient clientMulti = new UDPMulticastClient(Main.HOST, Main.PORT);
		
		
		String str = client.send("Client msg");
		System.out.println("Response: " + str);
		
	}

}
