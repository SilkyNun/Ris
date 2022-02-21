package com.denis;

import com.denis.service.StaffService;
import com.denis.service.StaffServiceImpl;
import org.apache.cxf.frontend.ServerFactoryBean;

public class Server {
    protected Server() throws Exception {
        ServerFactoryBean serverFactoryBean = new ServerFactoryBean();
        serverFactoryBean.setServiceClass(StaffService.class);
        serverFactoryBean.setAddress("http://localhost:5000/staff");
        serverFactoryBean.setServiceBean(new StaffServiceImpl());
        serverFactoryBean.create();
    }

    public static void main(String[] args) throws Exception {
        new Server();
        System.out.println("Listening on port 5000...");
        Thread.sleep(50 * 60 * 1000);
        System.out.println("Server exiting");
        System.exit(0);
    }
}
