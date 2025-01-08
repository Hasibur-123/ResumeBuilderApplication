CREATE DATABASE ResumeDB;
USE ResumeDB;

CREATE TABLE resumes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    email VARCHAR(100),
    phone VARCHAR(15),
    qualification VARCHAR(100),
    institution VARCHAR(100),
    skills TEXT
);
