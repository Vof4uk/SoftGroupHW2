package mykytenko;

import mykytenko.song.Song;

import java.io.*;
import java.util.*;

class Util {

    private Util(){}

    static void printStudents(Collection<Student> students, int course){
        Iterator<Student> iterator = students.iterator();
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

    static void printSongNamesFromFile(String fileName){
        String file = readTextFromFile(fileName);
        String lineSep = System.getProperty("line.separator");

        Set<String> tracks = new TreeSet<>(new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.compareToIgnoreCase(s2);
            }
        });

        tracks.addAll(Arrays.asList(file.split(lineSep)));

        for (String s : tracks) {
            System.out.println(s);
        }
    }

    static Song[] readSongsFromFileOrderByName(String location){
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

    static Song[] readSongsFromFileOrderByArtist(String location){
        List<Song> songs = new ArrayList<>();
        readSongsFromFile(location, songs);
        Collections.sort(songs, new Comparator<Song>() {
            @Override
            public int compare(Song s1, Song s2) {
                return (s1.getArtist() + s1.getTrackName()).compareToIgnoreCase(s2.getArtist() + s2.getTrackName());
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

    static Node reverseNodes(Node head){
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

    static void printNodes(Node headNode){
        Node n = headNode;
        do {
            System.out.printf("%d ", n.getElement());
            n = n.getNext();
        }while (n != null);
        System.out.println();
    }

    static String readLineFromInputStream(){
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){
            return reader.readLine();
        }catch (IOException ex){
            ex.printStackTrace();
            throw new RuntimeException("something wrong with console");
        }
    }

    static void saveTextToFile(String fileLocation, String text){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(fileLocation))) {
            writer.write(text);
        }catch (IOException ex){
            throw new RuntimeException("something went wrong while saving file");
        }
    }

    static String readTextFromFile(String fileLocation){
        StringBuilder sb = new StringBuilder();
        String lineSep = System.getProperty("line.separator");
        try(BufferedReader reader = new BufferedReader(new FileReader(fileLocation))){
            while (reader.ready()){
                if(sb.length() > 0){
                    sb.append(lineSep);
                }
                sb.append(reader.readLine());
            }
        }catch (IOException ex){
            ex.printStackTrace();
        }
        return sb.toString();
    }
}
