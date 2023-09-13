package connections.databaseOperations;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanSearcher {
    private static final BeanSearcher searcher = new BeanSearcher();
    private static final String beanXml = "bean.xml";
    private static final ApplicationContext baseContext = configs();

    private static ApplicationContext configs(){
        ApplicationContext applicationContext = null;
        try{
            applicationContext = new ClassPathXmlApplicationContext(BeanSearcher.beanXml);
        } catch (BeansException e) {
            e.printStackTrace();
            System.out.println("error in loading config file: " + BeanSearcher.beanXml);
        }
        return applicationContext;
    }

    public Object lookUp(String name){
        return baseContext.getBean(name);
    }

    public static BeanSearcher getInstance(){
        return searcher;
    }
}
