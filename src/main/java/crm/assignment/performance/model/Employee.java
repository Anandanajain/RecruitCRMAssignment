package crm.assignment.performance.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "employee")
public class Employee {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String name;

        @Column(unique = true, nullable = false)
        private String email;

        private LocalDate dateOfJoining;
        private Double salary;

        // FK: Many Employees belong to One Department
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "department_id", nullable = false)
        private Department department;

        // FK: Self-referencing (Manager is also an Employee)
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "manager_id")
        private Employee manager;

        @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
        private Set<PerformanceReview> reviews = new HashSet<>();

        @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
        private Set<EmployeeProject> employeeProjects = new HashSet<>();
}

