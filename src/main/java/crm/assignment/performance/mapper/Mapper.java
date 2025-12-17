package crm.assignment.performance.mapper;

import crm.assignment.performance.dto.EmployeeDetailDTO;
import crm.assignment.performance.dto.EmployeeSummaryDTO;
import crm.assignment.performance.model.Employee;
import crm.assignment.performance.model.PerformanceReview;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.stream.Collectors;

@Component
public class Mapper {

        public EmployeeSummaryDTO toSummaryDto(Employee employee) {
            if (employee == null) return null;

            EmployeeSummaryDTO dto = new EmployeeSummaryDTO();
            dto.setId(employee.getId());
            dto.setName(employee.getName());
            dto.setEmail(employee.getEmail());
            dto.setDepartmentName(employee.getDepartment() != null ? employee.getDepartment().getName() : "N/A");
            return dto;
        }

        public EmployeeDetailDTO toDetailDto(Employee employee) {
            if (employee == null) return null;

            EmployeeDetailDTO dto = new EmployeeDetailDTO();
            dto.setId(employee.getId());
            dto.setName(employee.getName());
            dto.setEmail(employee.getEmail());
            dto.setDepartmentName(employee.getDepartment() != null ? employee.getDepartment().getName() : "N/A");

            // Map Projects Assignment
            dto.setProjects(employee.getEmployeeProjects().stream()
                    .map(ep -> new EmployeeDetailDTO.ProjectAssignmentDTO(
                            ep.getProject().getName(),
                            ep.getRole()))
                    .collect(Collectors.toList()));

            // Map and Filter: Last 3 Reviews
            dto.setLast3Reviews(employee.getReviews().stream()
                    .sorted(Comparator.comparing(PerformanceReview::getReviewDate).reversed())
                    .limit(3)
                    .map(r -> new EmployeeDetailDTO.PerformanceReviewDTO(
                            r.getReviewDate(),
                            r.getScore(),
                            r.getReviewComments()))
                    .collect(Collectors.toList()));

            return dto;
        }

}
