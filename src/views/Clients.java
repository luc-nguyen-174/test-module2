package views;

import controller.StudentsManagement;
import model.Student;
import storage.ReadAndWrite;

import java.util.List;
import java.util.Scanner;

public class Clients {
    public static Scanner scanner = new Scanner(System.in);
    public static List<Student> students = (List<Student>) new ReadAndWrite().readFile();
    public static StudentsManagement management = new StudentsManagement(students);

    public static void main(String[] args) {
        Student student1 = new Student("1", "Nam", 26, "Nam", "Thanh Hoa", 5.5);
        Student student2 = new Student("2", "Hoa", 26, "Nu", "Ha Noi", 6.7);
        Student student3 = new Student("3", "Luc", 26, "Nam", "Ninh Binh", 7.3);
        Student student4 = new Student("4", "Thanh", 26, "Nam", "Hai Phong", 8.1);
        Student student5 = new Student("5", "Hoang", 26, "Nam", "Hai Duong", 9.0);
        students.add(student1);
        students.add(student2);
        students.add(student3);
        students.add(student4);
        students.add(student5);
        menu();
    }

    private static void menu() {
        do {
            System.out.println("""
                    ---- CHƯƠNG TRÌNH QUẢN LÝ SINH VIÊN ----
                    Chọn chức năng theo số (để tiếp tục)
                    1. Xem danh sách sinh viên
                    2. Thêm mới
                    3. Cập nhật
                    4. Xóa
                    5. Sắp xếp
                    6. Đọc từ file
                    7. Ghi từ file
                    8. Thoát
                    """);
            System.out.print("Chọn chức năng: ");
            int choice = checkValid();
            switch (choice) {
                case 1 -> {//display
                    management.display();
                }
                case 2 -> {
                    createNewStudent();
                }
                case 3 -> {
                    editStudent();
                }
                case 4 -> {
                    scanner.nextLine();
                    System.out.println("Nhập vào ID cần xóa: ");
                    String id = scanner.nextLine();

                    management.removeStudent(id);
                }
            }
        } while (true);
    }

    private static void editStudent() {
        scanner.nextLine();
        System.out.println("Nhập vào ID cần chỉnh sửa:");
        String findByID = scanner.nextLine();
        int changeCount = 0;
        for (Student student : students) {
            if (findByID.equals(student.getId())) {
                changeCount++;

                System.out.println("Nhập vào ID mới: ");
                String id = scanner.nextLine();
                student.setId(id);

                System.out.println("Nhập vào tên mới: ");
                String newName = scanner.nextLine();
                student.setName(newName);

                System.out.println("Nhập tuổi mới: ");
                int age = scanner.nextInt();
                student.setAge(age);

                System.out.println("Lựa chọn giới tính (1/2): ");
                int sexChoice= scanner.nextInt();
                String sex ="";
                if (sexChoice==1){
                    sex = "Nam";
                }else if(sexChoice==2){
                    sex = "Nữ";
                }else {
                    System.out.println("Lựa chọn sai, mời nhập lại!");
                }
                student.setSex(sex);

                System.out.println("Nhập địa chỉ mới: ");
                String address = scanner.nextLine();
                student.setAddress(address);

                System.out.println("Nhập điểm trung bình: ");
                double avg = scanner.nextDouble();
                student.setAveragePoints(avg);
            }
        }
        if (changeCount==0){
            System.out.println("Không tìm được sinh viên với mã sinh viên trên, hãy nhập lại.");
            System.out.println("Hãy nhấn Enter nếu muốn trở về Menu, nhập bất kỳ để quay lại menu sửa.");
            String confirm = scanner.nextLine();
            if(confirm.equals("")){
                menu();
            }else{
                editStudent();
            }
        }
    }

    private static void createNewStudent() {

        System.out.print("Nhập mã sinh viên:");
        String id = getId();

        System.out.print("Nhập họ tên:");
        String name = scanner.nextLine();

        System.out.print("Nhập tuổi:");
        int age = checkValid();

        System.out.print("Lựa chọn giới tính (1: nam/2: nu): ");
        int choiceSex = scanner.nextInt();
        String sex = "";
        if (choiceSex == 1) {
            sex = "Nam";
        } else if (choiceSex == 2) {
            sex = "Nữ";
        }else {
            System.out.println("Lựa chọn sai, mời nhập lại 1 hoặc 2!");
        }
        scanner.nextLine();
        System.out.print("Địa chỉ: ");
        String address = scanner.nextLine();

        System.out.print("Nhập điểm trung bình: ");
        double avg = scanner.nextDouble();
        Student student = new Student(id, name, age, sex, address, avg);
        students.add(student);
        System.out.println("Đã tạo sinh viên mới " + name);
    }

    private static String getId() {
        String id = "";
        int checkIdValid;
        do {
            checkIdValid = 0;
            id = scanner.nextLine();
            for (Student student : students) {
                if (student.getId().equals(id)) {
                    checkIdValid++;
                }
            }
            if (checkIdValid != 0) {
                System.out.println("ID này đã tồn tại, mời nhập lại ID khác.");
            }
        } while (checkIdValid != 0);
        return id;
    }

    private static int checkValid() {
        int number = 0;
        boolean validInput = false;
        while (!validInput) {
            try {
                String input = scanner.nextLine();
                number = Integer.parseInt(input);
                validInput = true;
            } catch (NumberFormatException e) {
                System.out.print("Bạn đã nhập sai định dạng. Vui lòng nhập số nguyên: ");
            }
        }
        return number;
    }
}
