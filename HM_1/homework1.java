/******************************************************************************

Реализуйте метод, который запрашивает у пользователя ввод дробного числа (типа float), 
и возвращает введенное значение. Ввод текста вместо числа не должно приводить к падению приложения,
вместо этого, необходимо повторно запросить у пользователя ввод данных.

*******************************************************************************/
import java.util.Scanner;
public class homework1
{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while (true) {
    		System.out.println("enter number : ");
    		if (sc.hasNextFloat()) {
    		    Float num = sc.nextFloat();
    		    System.out.println(num);
    		    break;
    		}
    		String line = sc.nextLine();
		}
	}
}
