package CFun.performance_test;

import java.io.*;

public class DecoratorTest {

    public static void main(String[] args) {
        IPacketCreator pc = new PacketHTTPHeaderCreator(new PacketHTMLHeaderCreator(new PacketBodyCreator()));

        System.out.println(pc.handleContent());

        testOutputStream();
    }


    static void testOutputStream()
    {
        int max = 100000;
        try {
            DataOutputStream  dout = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("D:\\a.txt")));
         //  DataOutputStream  dout = new DataOutputStream(new FileOutputStream("D:\\a.txt"));
            long begin = System.currentTimeMillis();
            for(int i =0; i < max; i++)
            {
                dout.writeLong(i);
            }

            System.out.println("spend: "+(System.currentTimeMillis() - begin));

            //没有缓冲
            DataOutputStream  dout2 = new DataOutputStream(new FileOutputStream("D:\\b.txt"));

            begin = System.currentTimeMillis();
            for(int i =0; i < max; i++)
            {
                dout2.writeLong(i);
            }

            System.out.println("spend: "+(System.currentTimeMillis() - begin));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }





}


interface IPacketCreator
{
    public String handleContent();//用于内容处理
}

 class PacketBodyCreator implements IPacketCreator
{

    @Override
    public String handleContent() {
        return "Content of Packet";//构造核心数据，但不包括格式
    }
}

abstract class PacketDecorator implements  IPacketCreator
{
    IPacketCreator component;

    public PacketDecorator(IPacketCreator c)
    {
        component = c;
    }
}

class PacketHTMLHeaderCreator extends PacketDecorator
{
    public PacketHTMLHeaderCreator(IPacketCreator c)
    {
        super(c);
    }

    @Override
    public String handleContent() {
        StringBuffer sb = new StringBuffer();
        sb.append("<html>");
        sb.append("<body>");
        sb.append(component.handleContent());
        sb.append("</body");
        sb.append("</html>\n");
        return sb.toString();


    }
}

class PacketHTTPHeaderCreator extends PacketDecorator
{


    public PacketHTTPHeaderCreator(IPacketCreator c) {
        super(c);
    }

    @Override
    public String handleContent() {
        StringBuffer sb = new StringBuffer();
        sb.append("Cache-control:no-cache\n");
        sb.append(component.handleContent());
        return sb.toString();

    }
}
