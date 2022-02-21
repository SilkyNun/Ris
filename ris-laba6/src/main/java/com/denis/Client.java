package com.denis;

import com.denis.model.Staff;
import com.denis.service.StaffService;
import org.apache.cxf.frontend.ClientProxyFactoryBean;

import java.util.List;

public class Client {
    public static void main(String[] args) {
        ClientProxyFactoryBean factoryBean = new ClientProxyFactoryBean();
        factoryBean.setAddress("http://localhost:5000/staff");
        StaffService staffService = factoryBean.create(StaffService.class);

        List<Staff> allStaff = staffService.getAllStaff();
        allStaff.forEach(System.out::println);
        System.out.println();

        Staff staff = staffService.findStaffByName("Denis");
        System.out.println(staff);
        System.out.println();

        List<Staff> ceo = staffService.findStaffByRank("CEO");
        ceo.forEach(System.out::println);
        System.out.println();
    }
}
