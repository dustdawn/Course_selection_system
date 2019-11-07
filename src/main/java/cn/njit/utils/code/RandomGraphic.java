package cn.njit.utils.code;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

public class RandomGraphic {

    //字符的高度和宽度，单位为像素
    private int wordHeight = 10;
    private int wordWidth = 15;
    //字符大小
    private int fontSize = 18;
    //最大字符串个数
    private static final int MAX_CHARCOUNT = 16;
    //垂直方向起始位置
    private final int initypos = 5;
    //要生成的字符个数，由工厂方法得到
    private int charCount = 0;
    //颜色数组，绘制字串时随机选择一个
    private static final Color[] CHAR_COLOR = { Color.MAGENTA, Color.ORANGE,
            Color.DARK_GRAY, Color.BLACK, Color.PINK, Color.GREEN };
    //随机数生成器
    private Random r = new Random();
    /**
     * 生成图像的格式常量，JPEG格式,生成为文件时扩展名为.jpg； 输出到页面时需要设置MIME type 为image/jpeg
     */
    public static String GRAPHIC_JPEG = "JPEG";
    /**
     * 生成图像的格式常量，PNG格式,生成为文件时扩展名为.png； 输出到页面时需要设置MIME type 为image/png
     */
    public static String GRAPHIC_PNG = "PNG";

    //用工厂方法创建对象
    protected RandomGraphic(int charCount) {
        this.charCount = charCount;
    }

    /**
     * 创建对象的工厂方法
     * @param charCount 要生成的字符个数，个数在1到16之间
     * @return 返回RandomGraphic对象实例
     * @throws Exception 参数charCount错误时抛出
     */
    public static RandomGraphic createInstance(int charCount) throws Exception {
        if (charCount < 1 || charCount > MAX_CHARCOUNT) {
            throw new Exception("Invalid parameter charCount,charCount should between in 1 and 16");
        }
        return new RandomGraphic(charCount);
    }

    /**
     * 随机生成一个数字串，并以图像方式绘制，绘制结果输出到流out中
     * @param graphicFormat 设置生成的图像格式，值为GRAPHIC_JPEG或GRAPHIC_PNG
     * @param out 图像结果输出流
     * @return 随机生成的串的值
     * @throws IOException
     */
    public String drawNumber(String graphicFormat, OutputStream out)
            throws IOException {
        // 随机生成的串的值
        String charValue = "";
        charValue = randNumber();
        return draw(charValue, graphicFormat, out);
    }

    /**
     * 随机生成一个字母串，并以图像方式绘制，绘制结果输出到流out中
     * @param graphicFormat 设置生成的图像格式，值为GRAPHIC_JPEG或GRAPHIC_PNG
     * @param out 图像结果输出流
     * @return 随机生成的串的值
     * @throws IOException
     */
    public String drawAlpha(String graphicFormat, OutputStream out)
            throws IOException {
        // 随机生成的串的值
        String charValue = "";
        charValue = randAlpha();
        return draw(charValue, graphicFormat, out);
    }

    /**
     * 以图像方式绘制字符串，绘制结果输出到流out中
     * @param charValue 要绘制的字符串
     * @param graphicFormat 设置生成的图像格式，值为GRAPHIC_JPEG或GRAPHIC_PNG
     * @param out 图像结果输出流
     * @return 随机生成的串的值
     * @throws IOException
     */
    public String draw(String charValue, String graphicFormat, OutputStream out)
            throws IOException {
        // 计算图像的宽度和高度
        int w = (charCount + 2) * wordWidth;
        int h = wordHeight * 3;

        // 创建内存图像区
        BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_3BYTE_BGR);
        Graphics2D g = bi.createGraphics();

        // 设置背景色
        Color backColor = Color.WHITE;
        g.setBackground(backColor);
        g.fillRect(0, 0, w, h);

        // 设置font
        g.setFont(new Font(null, Font.BOLD, fontSize));
        // 绘制charValue,每个字符颜色随机
        for (int i = 0; i < charCount; i++) {
            String c = charValue.substring(i, i + 1);
            Color color = CHAR_COLOR[randomInt(0, CHAR_COLOR.length)];
            g.setColor(color);
            int xpos = (i + 1) * wordWidth;
            // 垂直方向上随机
            int ypos = randomInt(initypos + wordHeight, initypos + wordHeight
                    * 2);
            g.drawString(c, xpos, ypos);
        }
        Random random = new Random();
        int x1, y1, x2, y2;
        g.setColor(Color.RED);
        int lineNum=30;
        //添加噪点
        for (int i = 0; i < lineNum; i++) {
            x1=random.nextInt(w)+wordWidth/2;
            y1=random.nextInt(h)+wordHeight/2;
            x2=x1+1;
            y2=y1+1;
            int color = Integer.valueOf(randomHexStr(6), 16);
            g.setColor(new Color(color));
            g.drawLine(x1, y1, x2, y2);
        }
        //添加斜线
        g.setColor(Color.RED);
        x1=random.nextInt(w/2);
        y1=random.nextInt(h/2);
        x2=random.nextInt(w);
        y2=random.nextInt(h);
        g.drawLine(x1, y1, x2, y2);


        g.dispose();
        bi.flush();
        // 输出到流
        ImageIO.write(bi, graphicFormat, out);
        return charValue;
    }

    public static String randomHexStr(int len) {
        try {
            StringBuffer result = new StringBuffer();
            for(int i=0;i<len;i++) {
                //随机生成0-15的数值并转换成16进制
                result.append(Integer.toHexString(new Random().nextInt(16)));
            }
            return result.toString().toUpperCase();
        } catch (Exception e) {
            System.out.println("获取16进制字符串异常，返回默认...");
            return "00CCCC";
        }
    }



    protected String randNumber() {
        String charValue = "";
        // 生成随机数字串
        for (int i = 0; i < charCount; i++) {
            charValue += String.valueOf(randomInt(0, 10));
        }
        return charValue;
    }
    /**
     * 生成随机字符串
     * @return
     */
    public String randAlpha() {
        String charValue = "";
        // 生成随机字母串
        for (int i = 0; i < charCount; i++) {
            char c = (char) (randomInt(65, 90));
            charValue += String.valueOf(c);
        }
        return charValue;
    }

    /**
     * 返回[from,to)之间的一个随机整数
     * @param from 起始值
     * @param to 结束值
     * @return [from,to)之间的一个随机整数
     */
    protected int randomInt(int from, int to) {
        // Random r = new Random();
        return from + r.nextInt(to - from);
    }

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        RandomGraphic s = new RandomGraphic(4);
        char c = (char) (s.randomInt(65, 90));
        System.out.println(c);
    }

}