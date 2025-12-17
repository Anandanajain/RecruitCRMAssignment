package crm.assignment.performance.controller;

import crm.assignment.performance.dto.EmployeeDetailDTO;
import crm.assignment.performance.dto.EmployeeSummaryDTO;
import crm.assignment.performance.service.EmployeeService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class PerformanceSystemController {

//    private final EmployeeService service;
    private final EmployeeService employeeService;

    public PerformanceSystemController(EmployeeService service, EmployeeService employeeService) {
//        this.service = service;
        this.employeeService = employeeService;
    }

//    @GetMapping
//    public List<Employee> getEmployees(
//            @RequestParam(required = false) List<String> departments,
//            @RequestParam(required = false) List<String> projects,
//            @RequestParam(required = false) Integer score,
//            @RequestParam(required = false)
//            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
//            LocalDate reviewDate) {
//
//        return service.getEmployeesByFilters(departments, projects, score, reviewDate);
//    }

//    @GetMapping("/{id}")
//    public Employee getEmployee(@PathVariable Long id) {
//        return service.getEmployee(id);
//    }


    /**
     * 1. Get a list of employees with filters.
     * Example URL: GET /api/employees?departments=HR,IT&projects=ERP&reviewDate=2023-12-01&minScore=4
     */
    @GetMapping("/employees")
    public List<EmployeeSummaryDTO> getEmployees(
            @RequestParam(required = false) List<String> departments,
            @RequestParam(required = false) List<String> projects,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate reviewDate,
            @RequestParam(required = false, defaultValue = "0") Integer minScore)
    {
        return employeeService.getEmployeesByFilters(departments, projects, reviewDate, minScore);
    }

    /**
     * 2. Fetch detailed employee information by ID.
     * Example URL: GET /api/employees/123/details
     */
    @GetMapping("/{id}/details")
    public ResponseEntity<EmployeeDetailDTO> getEmployeeDetails(@PathVariable Long id) {
        return employeeService.getDetailedEmployee(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
