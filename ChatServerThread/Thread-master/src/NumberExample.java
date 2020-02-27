
public class NumberExample {

    private int number = 0;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void addOne(int i) {
        number = i + 1;
    }

    public void printNumber() {
        System.out.println(number);
    }

}
