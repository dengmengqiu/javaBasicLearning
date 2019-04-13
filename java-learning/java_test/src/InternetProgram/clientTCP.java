package InternetProgram;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class clientTCP {
	public static void main(String[] args) {
		//客户端
		Socket socket = null;
		OutputStream os = null;
		InputStream is1 = null;
		try {
			socket = new Socket(InetAddress.getByName("127.0.0.1"), 12090);
			os = socket.getOutputStream();
			os.write("这是客户端发送的消息".getBytes());
			
			socket.shutdownOutput();
			
			byte[] b = new byte[20];
			int len;
			is1 = socket.getInputStream();
			System.out.println("收到来自于服务端" + socket.getInetAddress().getHostAddress());
			while((len = is1.read(b)) != -1) {
				String str = new String(b, 0, len);
				System.out.print(str);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(os != null) {
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
			if(socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
		}
	}
}
