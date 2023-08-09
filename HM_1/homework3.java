public class homework3 {
    public static void main(String[] args) {
        try {
            int a = 90;
            int b = 3;
            System.out.println(a / b);
            printSum(23, 234);
            int[] abc = new int[]{ 1, 2 };
            abc[3] = 9;
        } catch (NullPointerException ex) {
            System.out.println("Pointer can`t point to null!");
        } catch (IndexOutOfBoundsException ex) {
            System.out.println("Index out of bounds of array!");
        } catch (Throwable ex) {
            System.out.println("Smth went wrong...");
        }
    }
    public static void printSum(Integer a, Integer b) {
        System.out.println(a + b);
    }
}