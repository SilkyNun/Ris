package com.denis.service;

import com.denis.model.Staff;

import java.util.ArrayList;
import java.util.List;

public interface StaffService {
    List<Staff> getAllStaff();
    Staff findStaffByName(String name);
    List<Staff> findStaffByRank(String rank);
}
