package mypackage;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    @SuppressWarnings("resource")
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("MyConfig.xml");
        context.registerShutdownHook();
        MyRESTClient obj = context.getBean(MyRESTClient.class);
        obj.doRESTCalls();
    }
}
