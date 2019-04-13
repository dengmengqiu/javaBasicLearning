package InternetProgram;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UDPclient {
	public static void main(String[] agrs) {
		//发送端
		DatagramSocket ds = null;
		try {
			byte[] b =  "我是被发送的数据".getBytes();
			ds = new DatagramSocket();
			DatagramPacket pack = new DatagramPacket(b, 0, b.length, 
					InetAddress.getByName("127.0.0.1"), 12090);
			ds.send(pack);
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(ds != null) {
				ds.close();
			}
		}
	}
}
