package InternetProgram;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class serverUDP {
	public static void main(String[] args) {
		//服务端
		ServerSocket ss = null;
		Socket s = null;
		InputStream is = null;
		OutputStream os1 = null;
		try {
			ss = new ServerSocket(12090);
			s = ss.accept();
			is = s.getInputStream();
			byte[] b = new byte[20];
			int len;
			System.out.println("收到来自于" + s.getInetAddress().getHostAddress());
			while((len = is.read(b)) != -1) {
				String str = new String(b, 0, len);
				System.out.print(str);
			}
			os1 = s.getOutputStream();
			os1.write("我是客户端".getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			if(is != null) {
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(s != null) {
				try {
					s.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(ss != null) {
				try {
					ss.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
