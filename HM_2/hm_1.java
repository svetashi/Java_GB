package HM_2;

import java.util.Locale;
import java.util.Scanner;
import java.io.*;

import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

// Напишите приложение, которое будет запрашивать у пользователя следующие данные в произвольном порядке, разделенные пробелом (данные вводятся одной строкой без запятых):
// Фамилия Имя Отчество, дата рождения, номер телефона, пол
// Форматы данных:
// фамилия, имя, отчество - строки
// дата рождения - строка формата dd.mm.yyyy
// номер телефона - целое беззнаковое число без форматирования
// пол - символ латиницей f или m.

// Приложение должно проверить введенные данные по количеству. Если количество не совпадает с требуемым, вернуть код ошибки. Создать метод, который обработает его и покажет пользователю сообщение, что он ввел меньше или больше данных, чем требуется.

// Приложение должно попытаться распарсить полученные значения и выделить из них требуемые параметры. Если форматы данных не совпадают, нужно бросить исключение, соответствующее типу проблемы. Можно использовать встроенные типы java или создать свои. Исключение должно быть корректно обработано, пользователю выведено сообщение с информацией, что именно неверно.

// Если всё введено и обработано верно, должен создаться файл с названием, равным фамилии. В него в одну строку должны записаться полученные данные, вида:
// Фамилия Имя Отчество, дата рождения, номер телефона, пол

// Однофамильцы должны записаться в один и тот же файл, в отдельные строки.

// Не забудьте закрыть соединение с файлом.

// При возникновении проблемы с чтением-записью в файл, исключение должно быть корректно обработано, пользователь должен увидеть стектрейс ошибки.

public class hm_1{
    public static boolean verify_sex(String prediction) throws IllegalArgumentException {
        if  (!((prediction.matches("f")) | (prediction.matches("m")))){
            throw new IllegalArgumentException("Write your male with correct 'f' or 'm' word ");
        } else {
            return true;
        }
    }
    public static boolean verify_number(String prediction) throws IllegalArgumentException {
        if  (!(prediction.matches("[0-9]+")) | prediction.length()!=11){
            throw new IllegalArgumentException("The number can contain only 11 digits");
        } else {
            return true;
        }
    }
    public static boolean verify_FIO(String prediction) throws IllegalArgumentException {
        String[] all_data = prediction.split(" ");
        if  (!(all_data[0].matches("[a-zA-Z]+"))){
            throw new IllegalArgumentException("Second name should be a string");
        } else if (!(all_data[1].matches("[a-zA-Z]+"))){
            throw new IllegalArgumentException("First name should be a string");
        } else if (!(all_data[2].matches("[a-zA-Z]+"))){
            throw new IllegalArgumentException("Last name should be a string");
        } else {
            return true;
        }
    }
    public static Boolean verify_date(String prediction) throws IllegalArgumentException {
        String[] all_data = prediction.split("\\.");
        if  (Integer.parseInt(all_data[0])>31){
            throw new IllegalArgumentException("Day cant be more than 31");
        } else if (Integer.parseInt(all_data[1])>12){
            throw new IllegalArgumentException("Month cant be more than 12");
        } else if (Integer.parseInt(all_data[2])>Year.now().getValue()){
            throw new IllegalArgumentException("Year cant be more than curent");
        } else {
            try {
                LocalDate.parse(prediction, DateTimeFormatter.ofPattern("dd.MM.uuuu", Locale.US).withResolverStyle(ResolverStyle.STRICT));
                return true;
            } catch (DateTimeParseException e) {
                throw new IllegalArgumentException("Bad date");
            }
        }
    }

    public static void main(String[] args) throws IOException{
        Scanner sc = new Scanner(System.in);
        System.out.print("Name, date birthsday, telephone, male: ");

        // splitting
        String general = sc.nextLine();
        String[] all_data = general.split(" ");
        
        // verifying amount
        if (all_data.length == 6) {
        } else {
            System.out.println("Data is missing");
            return;
        }
        
        String date = "";
        String FIO = "";
        String number = "";
        String sex = "";

        Boolean failed = false;

        // verifying type

        for (int i = 0; i < all_data.length; i++) {
            if  (all_data[i].matches("[a-zA-Z]")){
                sex = all_data[i];
                try {
                    verify_sex(sex);
                } catch (Exception e) {
                    System.out.println(e);
                    failed = true;
                }
                continue;
            }
            if  (all_data[i].matches("[a-zA-Z]+")) {
                FIO = all_data[i] + " " + all_data[i+1] + " " + all_data[i+2];
                i+=2;
                try {
                    verify_FIO(FIO);
                } catch (Exception e) {
                    System.out.println(e);
                    failed = true;
                }
                continue;
            }
            if  (all_data[i].matches("[0-9]{2}\\.[0-9]{2}\\.[0-9]{4}")) {
                date = all_data[i];
                try {
                    verify_date(date);
                } catch (Exception e) {
                    System.out.println(e);
                    failed = true;
                }
                continue;
            }
            if  ((all_data[i].matches("[0-9]+"))){
                number = all_data[i];
                try {
                    verify_number(number);
                } catch (Exception e) {
                    System.out.println(e);
                    failed = true;
                }
                continue;
            }
            System.out.println("Data format is not found: "+all_data[i]);
            failed = true;
            
        }

        // file writer
        if (!failed) {
            try(FileWriter writer = new FileWriter(FIO.split(" ")[0], true)) {
                writer.write(FIO+" "+date+" "+number+" "+sex+"\n");
                writer.flush();
                writer.close();                                 
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Verification failed");
        }   
    }
}