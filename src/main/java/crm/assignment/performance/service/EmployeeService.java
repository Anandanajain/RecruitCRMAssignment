package crm.assignment.performance.service;

import crm.assignment.performance.dto.EmployeeDetailDTO;
import crm.assignment.performance.dto.EmployeeSummaryDTO;
import crm.assignment.performance.mapper.Mapper;
import crm.assignment.performance.model.Employee;
import crm.assignment.performance.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final Mapper employeeMapper;

    public EmployeeService(EmployeeRepository employeeRepository, Mapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }

    public List<EmployeeSummaryDTO> getEmployeesByFilters(
            List<String> departmentNames,
            List<String> projectNames,
            LocalDate reviewDate,
            Integer minScore) {

        // Step 1: Start with all IDs
        Set<Long> resultIds = employeeRepository.findAll().stream()
                .map(Employee::getId)
                .collect(Collectors.toSet());

        // Step 2: Intersection for Departments
        if (departmentNames != null && !departmentNames.isEmpty()) {
            Set<Long> matchIds = employeeRepository.findByDepartment_NameIn(departmentNames)
                    .stream().map(Employee::getId).collect(Collectors.toSet());
            resultIds.retainAll(matchIds);
        }

        // Step 3: Intersection for Projects
        if (projectNames != null && !projectNames.isEmpty()) {
            Set<Long> matchIds = employeeRepository.findByEmployeeProjects_Project_NameIn(projectNames)
                    .stream().map(Employee::getId).collect(Collectors.toSet());
            resultIds.retainAll(matchIds);
        }

        // Step 4: Intersection for Performance
        if (reviewDate != null && minScore != null) {
            Set<Long> matchIds = employeeRepository
                    .findByReviews_ReviewDateGreaterThanEqualAndReviews_ScoreGreaterThanEqual(reviewDate, minScore)
                    .stream().map(Employee::getId).collect(Collectors.toSet());
            resultIds.retainAll(matchIds);
        }

        // Step 5: Fetch final list and map to DTO
        if (resultIds.isEmpty()) return List.of();

        return employeeRepository.findAllByIdIn(resultIds.stream().toList())
                .stream()
                .map(employeeMapper::toSummaryDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<EmployeeDetailDTO> getDetailedEmployee(Long id) {
        return employeeRepository.findDetailedById(id)
                .map(employeeMapper::toDetailDto);
    }

}
