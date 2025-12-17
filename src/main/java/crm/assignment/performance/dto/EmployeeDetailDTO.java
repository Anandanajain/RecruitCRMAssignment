package crm.assignment.performance.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeDetailDTO {
    // Getters and Setters
    private Long id;
        private String name;
        private String email;
        private String departmentName;
        private List<ProjectAssignmentDTO> projects;
        private List<PerformanceReviewDTO> last3Reviews;

        // Inner classes for projects and reviews
        @Getter
        @Setter
        public static class ProjectAssignmentDTO {
            // Getters/Setters...
            private String projectName;
            private String role;

            public ProjectAssignmentDTO(String projectName, String role) {
                this.projectName = projectName;
                this.role = role;
            }
        }

        @Getter
        @Setter
        public static class PerformanceReviewDTO {
            // Getters/Setters...
            private LocalDate reviewDate;
            private Integer score;
            private String comments;

            public PerformanceReviewDTO(LocalDate reviewDate, Integer score, String comments) {
                this.reviewDate = reviewDate;
                this.score = score;
                this.comments = comments;
            }
        }


    public void setId(Long id) { this.id = id; }

    public void setName(String name) { this.name = name; }

    public void setEmail(String email) { this.email = email; }

    public void setDepartmentName(String departmentName) { this.departmentName = departmentName; }

    public void setProjects(List<ProjectAssignmentDTO> projects) { this.projects = projects; }

    public void setLast3Reviews(List<PerformanceReviewDTO> last3Reviews) { this.last3Reviews = last3Reviews; }

}
