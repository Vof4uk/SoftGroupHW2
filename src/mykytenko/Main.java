package mykytenko;

import mykytenko.car.Car;
import mykytenko.car.Engine;
import mykytenko.car.Wheel;
import mykytenko.employee.Employee;
import mykytenko.employee.EmployeeDataBase;
import mykytenko.employee.EmployeesBonus;
import mykytenko.song.Song;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final String[] testFirstNames = {"Inna", "Dan", "Stas", "Nikola", "Alfred", "Donatello", "Roman", "Ivan", "Roberta", "Ricki"};
    private static final String[] testLastNames = {"Feng", "Chong", "Dong", "Tesla", "Hitchcock", "The Turtle", "Bezus", "Smith",
            "Gonsalez", "Martin"};

    private final static String SONG_PACKAGE = "src\\mykytenko\\song\\";
    private final static String CAR_PACKAGE = "src\\mykytenko\\car\\";

    public static void main(String[] args) {
        int taskCount = 1;
        announceTask(taskCount++, "read from console -> save to file -> print from file");
        consoleTest();
        announceTask(taskCount++, "create new class -> serialize -> deserialize it");
        serializableCarTest();
        announceTask(taskCount++, "method must receive a list of workers and \nreturn a random worker to reward");
        employeeBonusTest();
        announceTask(taskCount++, "method must receive list of Student objects \nand chose only students from a specified course");
        printStudentsTest();
        announceTask(taskCount++, "create class to store workers with unique IDs");
        employeeIdTest();
        announceTask(taskCount++, "create vocabulary from a file and make method to translate words");
        vocabularyTest();
        announceTask(taskCount++, "read song names from a file");
        songsTest();
        announceTask(taskCount++, "create class Song with fields.\n read songs with fields from a file.\nSort by name and by artist");
        songsTest2();
        announceTask(taskCount++, "create class Node, make unidirectional node chain.\n make method to reverse Node chains");
        nodeTest();
    }

    private static void announceTask(int taskNumber, String message){
        System.out.println();
        System.out.println("=======================");
        System.out.printf(">>>>Task %d<<<< %s\n", taskNumber, message);
        System.out.println("=---------------------=");

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
        System.out.println("students: " + list);

        int course = 3;
        System.out.println(String.format("Students on cours %d: ", course));
        Util.printStudents(list, course);
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
        final String pattern = "%s translates as \'%s\'\n";
        System.out.printf(pattern, s1, vocabulary.translateWord(s1));
        System.out.printf(pattern, s2, vocabulary.translateWord(s2));
        System.out.printf(pattern, s3, vocabulary.translateWord(s3));
    }

    private static void songsTest(){
        Util.printSongNamesFromFile(SONG_PACKAGE + "song_titles.txt");
    }

    private static void songsTest2(){
        Song[] songs;
        songs = Util.readSongsFromFileOrderByArtist(SONG_PACKAGE + "songs.txt");
        System.out.println("Songs sorted by artist:");
        for (Song s : songs) {
            System.out.println(s);
        }
        System.out.println();

        songs = Util.readSongsFromFileOrderByName(SONG_PACKAGE + "songs.txt");
        System.out.println("Songs sorted by name:");
        for (Song s : songs) {
            System.out.println(s);
        }
    }

    private static void nodeTest(){
        System.out.println("creating example node...");
        Node n = new Node(0, null);
        Node head = n;
        for (int i = 1; i < 10; i++) {
            n.setNext(new Node(i, null));
            n = n.getNext();
        }

        System.out.println("printing...");
        Util.printNodes(head);
        System.out.println("reversing node links");
        head = Util.reverseNodes(head);
        System.out.println("printing...");
        Util.printNodes(head);
    }

    private static void serializableCarTest(){
        Engine engine = new Engine("porshe", 3400);
        Wheel[] wheels = {new Wheel("front-left", 22), new Wheel("front-right", 22), new Wheel("rear-left", 22), new Wheel("rear-right", 22)};
        Car car = new Car("zapor", wheels, engine);
        System.out.println(car);
        System.out.println("serializing...");
        try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(CAR_PACKAGE + "car.vroom"))){
            os.writeObject(car);
        }catch (IOException ex){
            ex.printStackTrace();
        }

        System.out.println("deserializing...");
        Car car2 = null;
        try (ObjectInputStream is = new ObjectInputStream(new FileInputStream(CAR_PACKAGE + "car.vroom"))){
             car2 = (Car) is.readObject();
        }catch (IOException | ClassNotFoundException ex){
            ex.printStackTrace();
        }
        System.out.println(car2);
    }

    private static void consoleTest(){
        final String location = "src\\text.txt";
        System.out.println("type something");
        String line = Util.readLineFromInputStream();
        System.out.println("saving to file@ " + location);
        Util.saveTextToFile(location, line);
        System.out.println("reading file...");
        System.out.println(Util.readTextFromFile(location));
    }
}
