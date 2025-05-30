CREATE DATABASE QuanLySinhVien;

USE QuanLySinhVien;

drop database QuanLySinhVien; 

CREATE TABLE Class(
         ClassID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
          ClassName VARCHAR(60) NOT NULL,
          StartDate DATETIME NOT NULL,
          Status BIT
);

CREATE TABLE Student(

          StudentId INT NOT NULL AUTO_INCREMENT PRIMARY KEY,

          StudentName VARCHAR(30) NOT NULL,

          Address VARCHAR(50),

          Phone VARCHAR(20),

          Status BIT,

          ClassId INT NOT NULL,

          FOREIGN KEY (ClassId) REFERENCES Class (ClassID)

);

CREATE TABLE Subject(

         SubId INT NOT NULL AUTO_INCREMENT PRIMARY KEY,

         SubName VARCHAR(30) NOT NULL,

         Credit TINYINT NOT NULL DEFAULT 1 CHECK ( Credit >= 1 ),

         Status BIT DEFAULT 1

);

CREATE TABLE Mark(

        MarkId INT NOT NULL AUTO_INCREMENT PRIMARY KEY,

        SubId INT NOT NULL,

        StudentId INT NOT NULL,

        Mark FLOAT DEFAULT 0 CHECK ( Mark BETWEEN 0 AND 100),

        ExamTimes TINYINT DEFAULT 1,

        UNIQUE (SubId, StudentId),

        FOREIGN KEY (SubId) REFERENCES Subject (SubId),

        FOREIGN KEY (StudentId) REFERENCES Student (StudentId)

 );
 
INSERT INTO Class
VALUES (1, 'A1', '2008-12-20', 1);
INSERT INTO Class
VALUES (2, 'A2', '2008-12-22', 1);
INSERT INTO Class
VALUES (3, 'B3', current_date, 0);

INSERT INTO Student (StudentName, Address, Phone, Status, ClassId)
VALUES ('Hung', 'Ha Noi', '0912113113', 1, 1);
INSERT INTO Student (StudentName, Address, Status, ClassId)
VALUES ('Hoa', 'Hai phong', 1, 1);
INSERT INTO Student (StudentName, Address, Phone, Status, ClassId)
VALUES ('Manh', 'HCM', '0123123123', 0, 2);

drop table student;
drop table mark;

INSERT INTO Subject
VALUES (1, 'CF', 5, 1),
 (2, 'C', 6, 1),
 (3, 'HDJ', 5, 1),
 (4, 'RDBMS', 10, 1);
 
INSERT INTO Mark (SubId, StudentId, Mark, ExamTimes)
VALUES (1, 1, 8, 1),
 (1, 2, 10, 2),
 (2, 1, 12, 1);
 
-- 1. Hiển thị danh sách tất cả các học viên
select * from student;

-- 2. Hiển thị danh sách các học viên đang theo học.
select * 
from student
where Status = true;

-- 3. Hiển thị danh sách các môn học có thời gian học nhỏ hơn 10 giờ.
select * 
from subject
where Credit < 10;

-- 4. Hiển thị danh sách học viên lớp A1
select student.*, class.classname
from student
join class on student.classid = class.classid
where class.classname = "A1";


-- 5. Hiển thị điểm môn CF của các học viên.
select student.*, subject.subname, subject.credit, mark.mark
from student
join mark on student.studentid = mark.studentid
join subject on subject.subid = mark.subid
where subject.subname = "CF"
