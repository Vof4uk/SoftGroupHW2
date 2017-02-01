package mykytenko;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final String[] testFirstNames = {"Inna", "Dan", "Stas", "Nikola", "Alfred", "Donatello", "Roman", "Ivan", "Roberta", "Ricki"};
    private static final String[] testLastNames = {"Feng", "Chong", "Dong", "Tesla", "Hitchcock", "The Turtle", "Bezus", "Smith",
            "Gonsalez", "Martin"};


    public static void main(String[] args) {
        announceTask(1);
        employeeBonusTest();
        announceTask(2);
        printStudentsTest();
        announceTask(3);
        employeeIdTest();
        announceTask(4);
        vocabularyTest();
        announceTask(5);
        songsTest();
        announceTask(6);
        songsTest2();
        announceTask(7);
        nodeTest();
    }

    private static void announceTask(int taskNumber){
        System.out.println();
        System.out.println("=======================");
        System.out.printf("Task %d\n", taskNumber);
        System.out.println("=======================");
    }

    private static void employeeBonusTest(){
        EmployeesBonus eb = new EmployeesBonus();
        for (int i = 0; i < 10; i++) {
            eb.addEmployee(testFirstNames[i], testLastNames[i]);
        }
        System.out.println("All employees: " + eb.getEmployeesList());
        System.out.println("This month winner is " + eb.chooseEmployeeForBonus());
    }

    private static void printStudentsTest(){
        List<Student> list = new ArrayList<>();
        for (int i = 0; i < testFirstNames.length; i++) {
            list.add(new Student(testFirstNames[i] + " " + testLastNames[i], (int)(Math.random() * 5) + 1));
        }
        Util.printStudents(list, 3);
    }

    private static void employeeIdTest(){
        EmployeeDataBase employeeDataBase = new EmployeeDataBase();
        int[] ids = new int[10];
        for (int i = 0; i < 10; i++) {
            ids[i] = employeeDataBase.add(new Employee(testFirstNames[i], testLastNames[10 - 1 - i]));
        }
        System.out.println(employeeDataBase.getById(ids[6]).fullName() + " for id = " + ids[6]);
        System.out.println(employeeDataBase.getById(ids[9]).fullName() + " for id = " + ids[9]);
        System.out.println(employeeDataBase.getById(ids[0]).fullName() + " for id = " + ids[0]);
    }

    private static void vocabularyTest(){
        Vocabulary vocabulary = new Vocabulary();
        String s1 = "BLACK";
        String s2 = "red";
        String s3 = "yellow";
        System.out.printf("%s translates as %s\n", s1, vocabulary.translateWord(s1));
        System.out.printf("%s translates as %s\n", s2, vocabulary.translateWord(s2));
        System.out.printf("%s translates as %s\n", s3, vocabulary.translateWord(s3));
    }

    private static void songsTest(){
        Util.printSongNamesFromFile("src\\mykytenko\\songtitles.txt");
    }

    private static void songsTest2(){
        Song[] songs;
        songs = Util.readSongsFromFileOrderByArtist("src\\mykytenko\\songs.txt");
        System.out.println("Songs sorted by artist:");
        for (Song s : songs) {
            System.out.println(s);
        }
        System.out.println();

        songs = Util.readSongsFromFileOrderByName("src\\mykytenko\\songs.txt");
        System.out.println("Songs sorted by name:");
        for (Song s : songs) {
            System.out.println(s);
        }
    }

    private static void nodeTest(){
        Node n = new Node(0, null);
        Node head = n;
        for (int i = 1; i < 10; i++) {
            n.setNext(new Node(i, null));
            n = n.getNext();
        }

        Util.printNodes(head);
        head = Util.reverseNodes(head);
        Util.printNodes(head);
    }
}
