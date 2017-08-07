package tw.com.abc.myfileio;

public class student {
    int ch, math, eng;

    public student(int ch, int math, int eng) {
        this.ch = ch;
        this.math = math;
        this.eng = eng;
    }

    public double sum() {
        return ch + math + eng;
    }

    public double avg() {
        return sum() / 3.0;
    }
}
