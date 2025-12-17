package crm.assignment.performance.repository;


import crm.assignment.performance.model.Employee;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // Filter by multiple departments
    List<Employee> findByDepartment_NameIn(List<String> departmentNames);

    // 2. Filter by Project Names (Traversing the join table)
    List<Employee> findByEmployeeProjects_Project_NameIn(List<String> names);

    // 3. Filter by Performance (Looking into the reviews list)
    List<Employee> findByReviews_ReviewDateGreaterThanEqualAndReviews_ScoreGreaterThanEqual(LocalDate date, Integer score);

    @EntityGraph(attributePaths = {"department", "employeeProjects.project"})
    List<Employee> findAllByIdIn(List<Long> employeeIds);

    // --- Query for Endpoint 2 (Detailed Fetch by ID) ---
    @EntityGraph(attributePaths = {"department", "employeeProjects.project", "reviews"})
    Optional<Employee> findDetailedById(Long id);
}



