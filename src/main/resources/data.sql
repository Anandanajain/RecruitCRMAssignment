INSERT INTO department (name, budget) VALUES
('Engineering', 500000),
('HR', 200000),
('Finance', 300000);
INSERT INTO employee (name, email, department_id, date_of_joining, salary, manager_id)
VALUES
(
  'Alice',
  'alice@company.com',
  (SELECT id FROM department WHERE name = 'Engineering'),
  '2022-01-10',
  90000,
  NULL
),
(
  'Bob',
  'bob@company.com',
  (SELECT id FROM department WHERE name = 'Engineering'),
  '2021-03-15',
  120000,
  NULL
),
(
  'Charlie',
  'charlie@company.com',
  (SELECT id FROM department WHERE name = 'HR'),
  '2020-07-01',
  70000,
  NULL
),
(
  'Diana',
  'diana@company.com',
  (SELECT id FROM department WHERE name = 'Finance'),
  '2019-11-20',
  110000,
  NULL
);
UPDATE employee
SET manager_id = (SELECT id FROM employee WHERE email = 'alice@company.com')
WHERE email = 'bob@company.com';

UPDATE employee
SET manager_id = (SELECT id FROM employee WHERE email = 'alice@company.com')
WHERE email = 'charlie@company.com';

UPDATE employee
SET manager_id = (SELECT id FROM employee WHERE email = 'bob@company.com')
WHERE email = 'diana@company.com';
INSERT INTO project (name, start_date, end_date, department_id)
VALUES
(
  'CRM System',
  '2023-01-01',
  '2023-12-31',
  (SELECT id FROM department WHERE name = 'Engineering')
),
(
  'HR Automation',
  '2023-02-01',
  '2023-09-30',
  (SELECT id FROM department WHERE name = 'HR')
),
(
  'Budget Planning',
  '2023-03-01',
  '2023-10-31',
  (SELECT id FROM department WHERE name = 'Finance')
);
INSERT INTO employee_project (employee_id, project_id, assigned_date, role)
VALUES
(
  (SELECT id FROM employee WHERE email = 'alice@company.com'),
  (SELECT id FROM project WHERE name = 'CRM System'),
  '2023-01-05',
  'Backend Developer'
),
(
  (SELECT id FROM employee WHERE email = 'bob@company.com'),
  (SELECT id FROM project WHERE name = 'CRM System'),
  '2023-01-10',
  'Tech Lead'
),
(
  (SELECT id FROM employee WHERE email = 'charlie@company.com'),
  (SELECT id FROM project WHERE name = 'HR Automation'),
  '2023-02-05',
  'HR Analyst'
),
(
  (SELECT id FROM employee WHERE email = 'diana@company.com'),
  (SELECT id FROM project WHERE name = 'Budget Planning'),
  '2023-03-10',
  'Financial Planner'
);
INSERT INTO performance_review (employee_id, review_date, score, review_comments)
VALUES
(
  (SELECT id FROM employee WHERE email = 'alice@company.com'),
  '2023-06-30',
  4,
  'Consistent and reliable performance'
),
(
  (SELECT id FROM employee WHERE email = 'alice@company.com'),
  '2023-12-31',
  5,
  'Outstanding contribution'
),
(
  (SELECT id FROM employee WHERE email = 'bob@company.com'),
  '2023-06-30',
  5,
  'Strong leadership and delivery'
),
(
  (SELECT id FROM employee WHERE email = 'charlie@company.com'),
  '2023-06-30',
  3,
  'Meets expectations'
),
(
  (SELECT id FROM employee WHERE email = 'diana@company.com'),
  '2023-06-30',
  4,
  'Solid financial planning work'
);