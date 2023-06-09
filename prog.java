import java.util.*;
public class prog {
    public static void main(String[] args) {
        ArrayList<Laptop> laptops = getLaptopList();
        HashMap<Integer, String> sortingValues = getSortingMap();
        HashMap<String, ArrayList<String>> allStr = getStringChoices(sortingValues, laptops);
        Scanner sc = new Scanner(System.in);
        int firstChoice = filterFirst(sortingValues, sc);
        int secondChoice = filterSecond(sortingValues, laptops, allStr, sc, firstChoice);
        filterFinal(firstChoice, secondChoice, allStr, laptops);
        sc.close();
    }

    public static void filterFinal(int first, int second, HashMap<String, ArrayList<String>> strMap, ArrayList<Laptop> al) {
        ArrayList<Laptop> matching = new ArrayList<>();
        switch (first) {
            case 1:
                for (Laptop el: al)
                    if (el.ramGB >= second)
                        matching.add(el);
                break;

            case 2:
                for (Laptop el: al)
                    if (el.romGB >= second)
                        matching.add(el);
                break;

            case 3:
                if (strMap.get("Операционная система").size() <= second - 1 || second < 1) {
                    System.out.println("Такого выбора нет!");
                    break;
                }

                for (Laptop el: al)
                    if (strMap.get("Операционная система").get(second - 1).equals(el.os))
                        matching.add(el);
                break;

            case 4:
                if (strMap.get("Цвет").size() <= second - 1 || second < 1) {
                    System.out.println("Такого выбора нет!");
                    break;
                }

                for (Laptop el: al)
                    if (strMap.get("Цвет").get(second - 1).equals(el.color))
                        matching.add(el);
                break;

            default:
                break;
        }

        if (matching.size() < 1) System.out.println("Нет подходящих вариантов(");
        else {
            System.out.println("\n Подходящие варианты \n");
            for (Laptop el: matching) el.getInfo();
        }
    }

    public static int filterSecond(HashMap<Integer, String> hm, ArrayList<Laptop> al, HashMap<String, ArrayList<String>> strMap, Scanner sc, int firstChoice) {
        int choice = -1;

        if (firstChoice > 0 && firstChoice < 3) {
            System.out.println("Напишите желаемое кол-во памяти (" + hm.get(firstChoice) + ").");
            System.out.print("Кол-во памяти (ГБ): ");
            choice = sc.nextInt();
            sc.nextLine();
        }

        else if (firstChoice > 2 && firstChoice < 5) {
            ArrayList<String> strChoices = strMap.get(hm.get(firstChoice));
            System.out.println("Выберите один из подходящих вариантов (" + hm.get(firstChoice) + "):");

            for (int i = 0; i < strChoices.size(); i++) {
                System.out.println((i + 1) + ". " + strChoices.get(i));
                }

            System.out.print("\nВаш выбор: ");
            choice = sc.nextInt();
            sc.nextLine();
        }

        else {
            System.out.println("Такого выбора нет!");
            }

        return choice;
    }

    public static HashMap<String, ArrayList<String>> getStringChoices(HashMap<Integer, String> hm, ArrayList<Laptop> al) {
        HashMap<String, ArrayList<String>> strMap = new HashMap<>();

        HashSet<String> oses = new HashSet<>();
        HashSet<String> colors = new HashSet<>();
        ArrayList<String> osList = new ArrayList<>();
        ArrayList<String> colorList = new ArrayList<>();

        for (Laptop el: al) {
            oses.add(el.os);
            colors.add(el.color);
        }

        osList.addAll(oses);
        colorList.addAll(colors);

        strMap.put(hm.get(3), osList);
        strMap.put(hm.get(4), colorList);

        return strMap;
    }

    public static int filterFirst(HashMap<Integer, String> hm, Scanner sc) {
        System.out.println("Выберите цифру, соответствующую необходимому критерию:");

        for (var el: hm.entrySet()) System.out.println(el.getKey() + ". " + el.getValue());

        System.out.print("\nВаш выбор: ");
        int choice = sc.nextInt();
        sc.nextLine();

        return choice;
    }

    public static HashMap<Integer, String> getSortingMap() {
        HashMap<Integer, String> hm = new HashMap<>();
        hm.put(1, "ОЗУ");
        hm.put(2, "Объем ЖД");
        hm.put(3, "Операционная система");
        hm.put(4, "Цвет");

        return hm;
    }

    public static ArrayList<Laptop> getLaptopList() {
        ArrayList<Laptop> al = new ArrayList<>();
        Laptop testLaptop = new Laptop("Test Laptop", "White", 16, 512, "DOS");
        Laptop macbook = new Laptop("Apple MacBook Air 13\"", "Silver", 8, 256, "MacOS");
        Laptop irbis = new Laptop("IRBIS NB80", "Black", 2, 32, "Windows 10 Home");
        Laptop hpLaptop = new Laptop("hp 255 g9 5Y3X5EA", "Dark gray", 16, 512, "FreeDOS");
        Laptop acer = new Laptop("Ноутбук Acer Nitro 5", "Black", 16, 512, "DOS");

        al.add(acer);
        al.add(hpLaptop);
        al.add(irbis);
        al.add(macbook);
        al.add(testLaptop);
        return al;
    }
}