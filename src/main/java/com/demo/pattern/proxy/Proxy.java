package com.demo.pattern.proxy;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.google.common.io.Files;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

/**
 * 动态代理类实现
 *
 * @author xiaol
 * @date 2019/9/15
 */
public class Proxy {

    public static Object newProxyInstance(Class infce, InvocationHandler h) throws IOException, ClassNotFoundException, NoSuchMethodException,
            IllegalAccessException,
            InvocationTargetException, InstantiationException {
        String methodStr = "";
        for (Method m : infce.getMethods()) {
            methodStr += "    @Override\n" +
                    "    public void " + m.getName() + "() {\n" +
                    "       try {\n" +
                    "           Method md = " + infce.getName() + ".class.getMethod(\"" + m.getName() + "\");\n" +
                    "           h.invoke(this, md);\n" +
                    "       } catch (Exception e) {e.printStackTrace();}\n" +
                    "    }\n";
        }
        String str =
                "package com.demo.pattern.proxy;\n" +
                        "import com.demo.pattern.proxy.InvocationHandler;\n" +
                        "import java.lang.reflect.Method;\n" +
                        "public class $Proxy0 implements " + infce.getName() + " {\n" +
                        "    private final InvocationHandler h;\n" +
                        "    public $Proxy0(InvocationHandler h) {\n" +
                        "        this.h = h;\n" +
                        "    }\n" +
                        methodStr +
                        "}\n";

        // 产生代理类的java文件
        String filename = System.getProperty("user.dir") + "\\target\\classes\\com\\demo\\pattern\\proxy\\$Proxy0.java";
        File file = new File(filename);
        Files.write(str.getBytes(), file);

        // 编译
        // 拿到编译器
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        // 文件管理者
        StandardJavaFileManager fileMgr = compiler.getStandardFileManager(null, null, null);
        // 获取文件
        Iterable<? extends JavaFileObject> units = fileMgr.getJavaFileObjects(filename);
        // 编译任务
        JavaCompiler.CompilationTask t = compiler.getTask(null, fileMgr, null, null, null, units);
        // 进行编译
        t.call();
        fileMgr.close();

        // load到内存
        ClassLoader cl = ClassLoader.getSystemClassLoader();
        Class<?> c = cl.loadClass("com.demo.pattern.proxy.$Proxy0");
        Constructor<?> ctr = c.getConstructor(InvocationHandler.class);
        return ctr.newInstance(h);
    }
}
