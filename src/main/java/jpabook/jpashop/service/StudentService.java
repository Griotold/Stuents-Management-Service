package jpabook.jpashop.service;

import jpabook.jpashop.domain.Student;
import jpabook.jpashop.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    @Transactional
    public Long join(Student student) {
        studentRepository.save(student);
        return student.getId();
    }

    public List<Student> findStudents() {
        return studentRepository.findAll();
    }

    public Student findOne(Long studentId) {
        return studentRepository.findOne(studentId);
    }

}
