//package com.talvoypartners.empmanagement.employeemanagement.services;
//
//import com.talvoypartners.empmanagement.employeemanagement.dtos.EmployeeDTO;
//import com.talvoypartners.empmanagement.employeemanagement.dtos.RegisterRequest;
//import com.talvoypartners.empmanagement.employeemanagement.entity.Employee;
//import com.talvoypartners.empmanagement.employeemanagement.entity.EmployeeAddress;
//import com.talvoypartners.empmanagement.employeemanagement.entity.Office;
//import com.talvoypartners.empmanagement.employeemanagement.repositories.EmployeeRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//@RequiredArgsConstructor
//public class EmployeeService {
//
//    private final EmployeeRepository repo;
//
//    public List<EmployeeDTO> getAllEmployees() {
//        return repo.findAll().stream().map(e -> {
//            EmployeeDTO dto = new EmployeeDTO();
//            dto.setName(e.getName());
//            dto.setEmail(e.getUser() != null ? e.getUser().getEmail() : null);
//            dto.setMobile(e.getMobile());
//
//            if (e.getAddress() != null) {
//                dto.setPermanentAddress(e.getAddress().getPermanentAddress());
//                dto.setCorrespondenceAddress(e.getAddress().getCorrespondenceAddress());
//            } else {
//                dto.setPermanentAddress(null);
//                dto.setCorrespondenceAddress(null);
//            }
//
//            if (e.getOffice() != null) {
//                dto.setOfficeName(e.getOffice().getOfficeName());
//                dto.setOfficeAddress(e.getOffice().getOfficeAddress());
//            } else {
//                dto.setOfficeName(null);
//                dto.setOfficeAddress(null);
//            }
//
//            return dto;
//        }).collect(Collectors.toList());
//    }
//
//    public Employee getMyDetails(String email) {
//        return repo.findByUserEmail(email).orElseThrow();
//    }
//
//    public void updateDetails(String email, RegisterRequest req) {
//        Employee emp = repo.findByUserEmail(email).orElseThrow();
//
//        emp.setName(req.getName());
//        emp.setMobile(req.getMobile());
//
//        // Update or create EmployeeAddress
//        if (emp.getAddress() == null) {
//            emp.setAddress(new EmployeeAddress());
//        }
//        emp.getAddress().setPermanentAddress(req.getPermanentAddress());
//        emp.getAddress().setCorrespondenceAddress(req.getCorrespondenceAddress());
//
//        // Update or create Office
//        if (emp.getOffice() == null) {
//            emp.setOffice(new Office());
//        }
//        emp.getOffice().setOfficeName(req.getOfficeName());
//        emp.getOffice().setOfficeAddress(req.getOfficeAddress());
//
//        repo.save(emp);
//    }
//}


package com.talvoypartners.empmanagement.employeemanagement.services;

import com.talvoypartners.empmanagement.employeemanagement.dtos.EmployeeDTO;
import com.talvoypartners.empmanagement.employeemanagement.dtos.RegisterRequest;
import com.talvoypartners.empmanagement.employeemanagement.entity.Employee;
import com.talvoypartners.empmanagement.employeemanagement.entity.EmployeeAddress;
import com.talvoypartners.empmanagement.employeemanagement.entity.Office;
import com.talvoypartners.empmanagement.employeemanagement.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository repo;

    public List<EmployeeDTO> getAllEmployees() {
        return repo.findAll().stream().map(e -> {
            EmployeeDTO dto = new EmployeeDTO();
            dto.setName(e.getName());
            dto.setEmail(e.getUser() != null ? e.getUser().getEmail() : null);
            dto.setMobile(e.getMobile());

            if (e.getAddress() != null) {
                dto.setPermanentAddress(e.getAddress().getPermanentAddress());
                dto.setCorrespondenceAddress(e.getAddress().getCorrespondenceAddress());
            } else {
                dto.setPermanentAddress(null);
                dto.setCorrespondenceAddress(null);
            }

            if (e.getOffice() != null) {
                dto.setOfficeName(e.getOffice().getOfficeName());
                dto.setOfficeAddress(e.getOffice().getOfficeAddress());
            } else {
                dto.setOfficeName(null);
                dto.setOfficeAddress(null);
            }

            return dto;
        }).collect(Collectors.toList());
    }

    public Employee getMyDetails(String email) {
        return repo.findByUserEmail(email).orElseThrow();
    }

    public void updateDetails(String email, RegisterRequest req) {
        Employee emp = repo.findByUserEmail(email).orElseThrow();

        // Only update if values are provided
        if (req.getName() != null) emp.setName(req.getName());
        if (req.getMobile() != null) emp.setMobile(req.getMobile());

        // Address logic
        if (emp.getAddress() == null) {
            emp.setAddress(new EmployeeAddress());
        }

        if (req.getPermanentAddress() != null) {
            emp.getAddress().setPermanentAddress(req.getPermanentAddress());
        }

        if (req.getCorrespondenceAddress() != null) {
            emp.getAddress().setCorrespondenceAddress(req.getCorrespondenceAddress());
        }

        // Office logic
        if (emp.getOffice() == null) {
            emp.setOffice(new Office());
        }

        if (req.getOfficeName() != null) {
            emp.getOffice().setOfficeName(req.getOfficeName());
        }

        if (req.getOfficeAddress() != null) {
            emp.getOffice().setOfficeAddress(req.getOfficeAddress());
        }

        repo.save(emp);
    }
}

