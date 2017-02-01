package mykytenko;

class Student {
    private String name;
    private int course;

    Student(String name, int course) {
        this.name = name;
        this.course = course;
    }

    int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                name +
                ", course " + course +
                '}';
    }
}
