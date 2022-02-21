package com.denis.service;

import com.denis.model.Staff;

import java.util.ArrayList;
import java.util.List;

public class StaffServiceImpl implements StaffService {
    private List<Staff> staffList = new ArrayList<>();

    {
        staffList.add(new Staff("Denis", "MANAGER"));
        staffList.add(new Staff("Victor", "CEO"));
        staffList.add(new Staff("Fedor", "CONSULTANT"));
    }


    @Override
    public List<Staff> getAllStaff() {
        return staffList;
    }

    @Override
    public Staff findStaffByName(String name) {
        return staffList.stream()
                .filter(staff -> staff.getName().equals(name))
                .findFirst().get();
    }

    @Override
    public List<Staff> findStaffByRank(String rank) {
        return staffList.stream()
                .filter(staff -> staff.getRank().equals(rank))
                .toList();
    }
}
