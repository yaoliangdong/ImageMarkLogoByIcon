package com.test;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


public class ImageMarkLogoByIcon {

    public static void main(String[] args) {   
        String srcImgPath = "F:/ZHmodel.jpg"; //需要处理的图片路径
        String iconPath = "F:/ico.jpg";   	  //ICON图标
        
        //=================================添加文字水印======================================
        String[] str={"ECSH-CC-001999","广州美发市场","张三三","2015-10-10","10年"};
        int[] x={300,300,300,300,300};
        int[] y={100,150,200,250,300};
        pressText(str,srcImgPath,"F:/DEMO1.jpg","行楷",Font.BOLD,255,28,x,y);
        System.out.println("添加文字水印，完成...");
        //=================================添加图标水印======================================
        markImageByIcon(iconPath, srcImgPath, "F:/DEMO2.jpg", 45,0.5f);
        System.out.println("添加图标水印，完成...");
        
  
    }   
    
   
    
    /** *//**
     * 打印文字水印图片
     * 
     * @param pressText[]
     * --添加文字数组
     * @param targetImg --
     * 目标图片
     * @param fontName --
     * 字体名
     * @param fontStyle --
     * 字体样式
     * @param color --
     * 字体颜色
     * @param fontSize --
     * 字体大小
     * @param x --
     * 偏移量
     * @param y
     */
     public static void pressText(String pressText[], String targetImg,String newImg,
     String fontName, int fontStyle, int color, int fontSize, int x[],
     int y[]) {
    	 OutputStream os=null;
 		try {
 			File _file = new File(targetImg);
 			Image src = ImageIO.read(_file);
 			int wideth = src.getWidth(null);
 			int height = src.getHeight(null);
 			BufferedImage image = new BufferedImage(wideth, height,
 					BufferedImage.TYPE_INT_RGB);
 			Graphics g = image.createGraphics();
 			g.drawImage(src, 0, 0, wideth, height, null);
 			// String s="www.qhd.com.cn";
 			g.setColor(new Color(color, false));
 			g.setFont(new Font(fontName, fontStyle, fontSize));
 			/*g.drawString(pressText, wideth - fontSize - x, height - fontSize
 					/ 2 - y);*/
 			g.drawString(pressText[0],x[0],y[0]);
 			g.drawString(pressText[1],x[1],y[1]);
 			g.drawString(pressText[2],x[2],y[2]);
 			g.drawString(pressText[3],x[3],y[3]);
 			g.drawString(pressText[4],x[4],y[4]);
 			g.dispose();
 			//FileOutputStream out = new FileOutputStream(targetImg);
 			
 			//JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
 			//encoder.encode(image);
 			
 			  
            // 生成图片   
 			os = new FileOutputStream(newImg); 
            ImageIO.write(image, "JPG", os);
 			
 		} catch (Exception e) {
 			System.out.println(e);
 		}finally {   
            try {   
                if (null != os)   
                    os.close();   
            } catch (Exception e) {   
                e.printStackTrace();   
            }   
        } 
     }
  
    /**  
     * 给图片添加水印、可设置水印图片旋转角度  
     * @param iconPath 水印图片路径  
     * @param srcImgPath 源图片路径  
     * @param targerPath 目标图片路径  
     * @param degree 水印图片旋转角度  
     * @param alpha 透明度
     */  
    public static void markImageByIcon(String iconPath, String srcImgPath,   
            String targerPath, Integer degree,float alpha) {   
        OutputStream os = null;   
        try {   
            Image srcImg = ImageIO.read(new File(srcImgPath));   
  
            BufferedImage buffImg = new BufferedImage(srcImg.getWidth(null),   
                    srcImg.getHeight(null), BufferedImage.TYPE_INT_RGB);   
  
            // 得到画笔对象   
            // Graphics g= buffImg.getGraphics();   
            Graphics2D g = buffImg.createGraphics();   
  
            // 设置对线段的锯齿状边缘处理   
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,   
                    RenderingHints.VALUE_INTERPOLATION_BILINEAR);   
  
            g.drawImage(srcImg.getScaledInstance(srcImg.getWidth(null), srcImg   
                    .getHeight(null), Image.SCALE_SMOOTH), 0, 0, null);   
  
            if (null != degree) {   
                // 设置水印旋转   
                g.rotate(Math.toRadians(degree),   
                        (double) buffImg.getWidth() / 2, (double) buffImg   
                                .getHeight() / 2);   
            }   
  
            // 水印图象的路径 水印一般为gif或者png的，这样可设置透明度   
            ImageIcon imgIcon = new ImageIcon(iconPath);   
  
            // 得到Image对象。   
            Image img = imgIcon.getImage();   
  
            //float alpha = 0.5f; // 透明度   
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,   
                    alpha));   
  
            // 表示水印图片的位置   
            g.drawImage(img, 150, 300, null);   
  
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));   
  
            g.dispose();   
  
            os = new FileOutputStream(targerPath);   
  
            // 生成图片   
            ImageIO.write(buffImg, "JPG", os);   
  
            //System.out.println("图片完成添加Icon印章。。。。。。");   
        } catch (Exception e) {   
            e.printStackTrace();   
        } finally {   
            try {   
                if (null != os)   
                    os.close();   
            } catch (Exception e) {   
                e.printStackTrace();   
            }   
        }   
    }
    
    
    
}
