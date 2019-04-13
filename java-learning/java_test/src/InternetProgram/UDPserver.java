package InternetProgram;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPserver {
	public static void main(String[] args) {
		//Ω” ’∂À
		DatagramSocket ds = null;
		try {
			ds = new DatagramSocket(12090);
			byte[] b = new byte[1024];
			DatagramPacket pack = new DatagramPacket(b, 0, b.length);
			ds.receive(pack);
			String str = new String(pack.getData(), 0, pack.getLength());
			System.out.println(str);
		} catch (SocketException e) {
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
