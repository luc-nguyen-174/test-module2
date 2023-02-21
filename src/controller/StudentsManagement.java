package controller;

import model.Student;
import storage.ReadAndWrite;

import java.util.List;


public class StudentsManagement {
    List<Student> students;

    public StudentsManagement(List<Student> students) {
        this.students = students;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public void addStudent(Student student) {
        students.add(student);

    }

    public void addStudentToFile(List<Student> students) {
        this.students = students;
        new ReadAndWrite().writeFile(students);
    }

    public void removeStudent(String id) {
        int deleteCount =0;
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId().equals(id)) {
                deleteCount++;
                students.remove(i);
                System.out.println("Đã xóa thành công sinh viên " + id);
                break;
            } else if (students.size() == 0) {
                System.out.println("Không có thành viên nào để xóa.");
            }
        }if(deleteCount==0){
            System.out.println("Không tìm được sinh viên với mã sinh viên trên.");
            System.out.println("Hãy nhập lại.");
        }
    }
    public void display(){
        for(Student student : students){
            System.out.println(student);
        }
    }
}
