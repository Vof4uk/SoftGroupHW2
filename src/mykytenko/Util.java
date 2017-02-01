package mykytenko;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Util {

    private Util(){}

    public static void printStudents(Collection<Student> students, int course){
        Iterator<Student> iterator = students.iterator();
        System.out.println(String.format("Students on cours %d: ", course));
        boolean atLeastOne = false;
        while (iterator.hasNext()){
            Student s = iterator.next();
            if(s.getCourse() == course){
                if(atLeastOne){
                    System.out.print(", ");
                }
                System.out.print(s.getName());
                atLeastOne = true;
            }
        }
        System.out.println();
    }

    public static void printSongNamesFromFile(String fileName){
        Set<String> tracks = new TreeSet<>(new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.compareToIgnoreCase(s2);
            }
        });
        try (BufferedReader br = new BufferedReader(new FileReader(fileName));)
        {
            while (br.ready()){
                tracks.add(br.readLine());
            }
        }catch (IOException ex){
            ex.printStackTrace();
        }

        for (String s : tracks) {
            System.out.println(s);
        }
    }

    public static Song[] readSongsFromFileOrderByName(String location){
        //решил и через трисет и через саписок попробовать
        TreeSet<Song> songs = new TreeSet<>(new Comparator<Song>() {
            @Override
            public int compare(Song s1, Song s2) {
                return (s1.getTrackName() + s1.getArtist()).compareToIgnoreCase(s2.getTrackName() + s2.getArtist());
            }
        });
        readSongsFromFile(location, songs);
        return songs.toArray(new Song[songs.size()]);
    }

    public static Song[] readSongsFromFileOrderByArtist(String location){
        List<Song> songs = new ArrayList<>();
        readSongsFromFile(location, songs);
        Collections.sort(songs, new Comparator<Song>() {
            @Override
            public int compare(Song s1, Song s2) {
                return (s1.getTrackName() + s1.getArtist()).compareToIgnoreCase(s2.getTrackName() + s2.getArtist());
            }
        });
        return songs.toArray(new Song[songs.size()]);
    }

    private static void readSongsFromFile(String location, Collection<Song> destination){
        try(BufferedReader br = new BufferedReader(new FileReader(location))) {
            while (br.ready()){
                destination.add(Song.parse(br.readLine()));
            }
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    public static Node reverseNodes(Node head){
        if(head == null){
            return null;
        }
        else if(head.getNext() == null){
            return head;
        }
        else{
            Node rear = head;
            Node mid = rear.getNext();
            Node front = mid == null? null : mid.getNext();
            rear.setNext(null);
            while (front != null){
                mid.setNext(rear);
                rear = mid;
                mid = front;
                front = front.getNext();
            }
            mid.setNext(rear);
            return mid;
        }
    }

    public static void printNodes(Node headNode){
        Node n = headNode;
        do {
            System.out.printf("%d ", n.getElement());
            n = n.getNext();
        }while (n != null);
        System.out.println();
    }
}
