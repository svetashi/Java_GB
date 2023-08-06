/******************************************************************************
Разработайте программу, которая выбросит Exception, когда пользователь вводит пустую строку. 
Пользователю должно показаться сообщение, что пустые строки вводить нельзя.

*******************************************************************************/
import java.util.Scanner;

public class homework4
{
	public static void main(String[] args) {
	    Scanner sc = new Scanner(System.in);
		System.out.println("enter the string: ");
		String str = sc.nextLine();
		if (str.isEmpty()){
            throw new IllegalArgumentException("String is empty! Try again!");
        }

	}
}
