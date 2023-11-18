package jpabook.jpashop.controller;

import jpabook.jpashop.domain.Student;
import jpabook.jpashop.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/students/new")
    public String createForm(Model model) {
        model.addAttribute("studentForm", new StudentForm());
        return "students/createStudentForm";
    }

    @PostMapping("/students/new")
    public String create(StudentForm form) {
        Student student = new Student();
        student.setName(form.getName());
        student.setResidence(form.getResidence());
        student.setUniversity(form.getUniversity());
        student.setMajor(form.getMajor());

        studentService.join(student);
        return "redirect:/students";

    }

    @GetMapping("/students")
    public String list(Model model) {
        List<Student> students = studentService.findStudents();
        model.addAttribute("students", students);
        return "students/studentList";
    }

    @GetMapping("/students/{studentId}/edit")
    public String updateStudentForm(@PathVariable("studentId") Long studentId, Model model) {
        Student student = studentService.findOne(studentId);

        StudentUpdateForm form = new StudentUpdateForm();
        form.setId(student.getId());
        form.setName(student.getName());
        form.setResidence(student.getResidence());
        form.setUniversity(student.getUniversity());
        form.setMajor(student.getMajor());

        model.addAttribute("form", form);
        return "students/updateStudentForm";
    }

    @PostMapping("/students/{studentId}/edit")
    public String updateStudent(@PathVariable Long studentId, @ModelAttribute("form") StudentUpdateForm form) {
        studentService.updateStudent(studentId, form);
        return "redirect:/students";
    }

}
