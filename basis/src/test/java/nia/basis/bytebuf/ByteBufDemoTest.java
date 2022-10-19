package nia.basis.bytebuf;

import java.util.concurrent.atomic.AtomicInteger;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import org.junit.Test;

/**
 * @author yuwenbo@kkworld.com
 * @date 2022/10/18
 */
public class ByteBufDemoTest {

    private static final ByteBuf BYTE_BUF_1024 = Unpooled.buffer(16);
    private static final AtomicInteger COUNTER = new AtomicInteger(0);

    @Test
    public void test1() {
        ByteBuf buffer = BYTE_BUF_1024;
        // write
        while (buffer.writableBytes() >= 4) {
            buffer.writeInt(COUNTER.incrementAndGet());
        }
        // read 随机访问
//        for (int i = 0; i < buffer.capacity(); i++) {
//            byte b = buffer.getByte(i);
//            System.out.println((char) b);
//        }
        // read 读取所有数据
        while (buffer.isReadable()) {
            System.out.println(buffer.readByte());
        }
        // ???
        System.out.println(ByteBufUtil.hexDump(buffer));
        System.out.printf(String.format("COUNTER=%d%n", COUNTER.get()));
    }
}