package cn.haitaoss.hotswap;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

import java.io.File;
import java.io.FileInputStream;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2022-07-09 12:36
 *
 */
public class MyClassLoader extends ClassLoader {
    private String rootDir;

    public MyClassLoader(String rootDir) {
        // super(Thread.currentThread().getContextClassLoader());
        super(null);
        this.rootDir = rootDir;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        Class<?> c = findLoadedClass(name);
        if (null != c) {
            return c;
        }

        File file = getClassFile(name);

        byte[] buf = new byte[1024];
        int i;
        ByteOutputStream byteStream = new ByteOutputStream();
        try (FileInputStream fis = new FileInputStream(file)) {
            while ((i = fis.read(buf)) != -1) {
                byteStream.write(buf, 0, i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return defineClass(name, byteStream.getBytes(), 0, byteStream.size());
    }

    private File getClassFile(String name) {
        return new File(rootDir + name.replace(".", "/") + ".class");
    }
}
