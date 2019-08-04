package lesson3;
/**
 * Java. Level 2. Lesson 3
 *
 * @author Dzyubenko Vadim
 * @version dated 04.08.2019
 */


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HashMapTest {
    public static void main(String[] args) {
        task1();
        task2();
    }

    private static void task1() {
        Map<String, Integer> hm = new HashMap<>();
        String[] words = {
                "ALPHA", "BETA", "JAVA",
                "TEST", "BETA", "GAMMA",
                "DOG", "CAT", "JAVA",
                "SBER", "DOG", "GAMMA",
                "CAT", "GAMMA", "OMEGA",
                "CAT", "OMEGA", "SBER", "ALPHA"
        };

        for (int i = 0; i < words.length; i++) {
            if (hm.containsKey(words[i]))
                hm.put(words[i], hm.get(words[i]) + 1);
            else
                hm.put(words[i], 1);
        }
        System.out.println(hm);
    }

    private static void task2() {
        PhoneBook phoneBook = new PhoneBook();

        phoneBook.add("Antonov", "89058665540");
        phoneBook.add("Antonov", "84958663525");
        phoneBook.add("Borisov", "89018256699");
        phoneBook.add("Borisov", "89326542321");
        phoneBook.add("Avramenko", "88000506644");
        phoneBook.add("Antonov", "9265645214");
        phoneBook.add("Avramenko", "9632145687");
        phoneBook.add("Kalach", "88889996633");
        phoneBook.add("Kalach", "632145451315");

        System.out.println(phoneBook.get("Kalach"));
        System.out.println(phoneBook.get("Avramenko"));
        System.out.println(phoneBook.get("Borisov"));
        System.out.println(phoneBook.get("Antonov"));
    }
}

class PhoneBook {
    private Map<String, List<String>> phoneBook_hm = new HashMap<>();
    private List<String> phone_number_list;

    public void add(String surname, String phone_number) {
        if (phoneBook_hm.containsKey(surname)) {
            phone_number_list = phoneBook_hm.get(surname);
            phone_number_list.add(phone_number);
            phoneBook_hm.put(surname, phone_number_list);
        } else {
            phone_number_list = new ArrayList<>();
            phone_number_list.add(phone_number);
            phoneBook_hm.put(surname, phone_number_list);
        }
    }

    public List<String> get(String surname) {
        return phoneBook_hm.get(surname);
    }

}

