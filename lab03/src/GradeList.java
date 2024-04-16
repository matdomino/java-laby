import java.util.ArrayList;
import static java.lang.Double.NaN;

public class GradeList {
    private final ArrayList<Double> grades = new ArrayList<>();

    public double getHighestGrade() {
        if (this.grades.isEmpty()) {
            return NaN;
        }

        double highest = this.grades.getFirst();
        for (double elem : this.grades) {
            if (elem > highest) {
                highest = elem;
            }
        }

        return highest;
    }

    public double getLowestGrade() {
        if (this.grades.isEmpty()) {
            return NaN;
        }

        double lowest = this.grades.getFirst();
        for (double elem : this.grades) {
            if (elem < lowest) {
                lowest = elem;
            }
        }

        return lowest;
    }

    public double calculateMean() {
        if (this.grades.isEmpty()) {
            return NaN;
        }

        double sum = 0;
        for (double elem : this.grades) {
            sum += elem;
        }

        return sum / this.grades.size();
    }

    public void addGrade(double grade) {
        this.grades.add(grade);
    }
}