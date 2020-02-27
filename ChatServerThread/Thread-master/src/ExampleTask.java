public class ExampleTask implements Runnable {

    private NumberExample number;

    public ExampleTask(NumberExample number) {
        this.number = number;
    }

    @Override
    public void run() {
        System.out.println("Inside the second thread: ExampleTask");
        while (true) {
            int i = number.getNumber();
            number.addOne(i);
            number.printNumber();
        }

    }

}
