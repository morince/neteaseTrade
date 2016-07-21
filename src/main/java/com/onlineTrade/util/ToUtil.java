package com.onlineTrade.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Blob;
import java.sql.SQLException;

import javax.imageio.ImageIO;

public class ToUtil {
	public static String  BlobToString(Blob blob) throws SQLException, IOException {

        String reString = "";
        InputStream is =  blob.getBinaryStream();
        ByteArrayInputStream bais = (ByteArrayInputStream)is;
        byte[] byte_data = new byte[bais.available()]; //bais.available()返回此输入流的字节数
        bais.read(byte_data, 0,byte_data.length);//将输入流中的内容读到指定的数组
        reString = new String(byte_data,"utf-8"); //再转为String，并使用指定的编码方式
        is.close();

        return reString;
    }
		private ToUtil(){
				
			}
		public static Image getImage(String path){
			URL u = ToUtil.class.getClassLoader().getResource(path);
			BufferedImage img = null;
			try {
				img = ImageIO.read(u);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return img;
		}

}
