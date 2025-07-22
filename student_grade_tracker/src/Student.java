public class Student {
    private String name;
    private int[] marks;

    public Student(String name, int[] marks) {
        this.name = name;
        this.marks = marks;
    }

    public String getName() {
        return name;
    }

    public double getAverage() {
        int sum = 0;
        for (int m : marks) sum += m;
        return (double) sum / marks.length;
    }

    public int getHighest() {
        int max = marks[0];
        for (int m : marks) if (m > max) max = m;
        return max;
    }

    public int getLowest() {
        int min = marks[0];
        for (int m : marks) if (m < min) min = m;
        return min;
    }
}