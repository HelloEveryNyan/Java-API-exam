import java.util.*;

public class Laptop {
    private String модель;
    private int озуГб;
    private int объемHDГб;
    private String ОС;
    private String цвет;

    public Laptop(String модель, int озуГб, int объемHDГб, String ОС, String цвет) {
        this.модель = модель;
        this.озуГб = озуГб;
        this.объемHDГб = объемHDГб;
        this.ОС = ОС;
        this.цвет = цвет;
    }

    public String getМодель() {
        return модель;
    }

    public int getОзуГб() {
        return озуГб;
    }

    public int getОбъемHDГб() {
        return объемHDГб;
    }

    public String getОС() {
        return ОС;
    }

    public String getЦвет() {
        return цвет;
    }

    public static List<Laptop> фильтроватьНоутбуки(List<Laptop> ноутбуки, Map<String, Object> фильтры) {
        List<Laptop> нашлиЭто = new ArrayList<>();

        for (Laptop ноутбук : ноутбуки) {
            boolean соответствуетПоиску = true;

            for (Map.Entry<String, Object> entry : фильтры.entrySet()) {
                String параметр = entry.getKey();
                Object значение = entry.getValue();

                switch (параметр) {
                    case "ОЗУ":
                        соответствуетПоиску &= ноутбук.getОзуГб() >= (int) значение;
                        break;
                    case "ОбъемHD":
                        соответствуетПоиску &= ноутбук.getОбъемHDГб() >= (int) значение;
                        break;
                    case "ОС":
                        соответствуетПоиску &= ноутбук.getОС().equalsIgnoreCase((String) значение);
                        break;
                    case "Цвет":
                        String введенныйЦвет = ((String) значение).trim().toLowerCase();
                        соответствуетПоиску &= ноутбук.getЦвет().trim().toLowerCase().equals(введенныйЦвет);
                        break;

                }
            }

            if (соответствуетПоиску) {
                нашлиЭто.add(ноутбук);
            }
        }

        return нашлиЭто;
    }

    public static void main(String[] args) {
        List<Laptop> магазНоутбуков = new ArrayList<>();
        магазНоутбуков.add(new Laptop("Lenovo", 8, 512, "Windows", "Красный"));
        магазНоутбуков.add(new Laptop("ASUS", 16, 1024, "MacOS", "Желтый"));
        магазНоутбуков.add(new Laptop("MSI", 8, 256, "Linux", "Красный"));

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Введите параметры:");
            System.out.println("1 - ОЗУ");
            System.out.println("2 - Объем HD");
            System.out.println("3 - ОС");
            System.out.println("4 - Цвет");

            Map<String, Object> фильтры = new HashMap<>();
            int выбранныйПарам = scanner.nextInt();

            switch (выбранныйПарам) {
                case 1:
                    System.out.println("Какой объем ОЗУ?:");
                    int минОЗУ = scanner.nextInt();
                    фильтры.put("ОЗУ", минОЗУ);
                    break;
                case 2:
                    System.out.println("Какой объем HD?:");
                    int минОбъемHD = scanner.nextInt();
                    фильтры.put("ОбъемHD", минОбъемHD);
                    break;
                case 3:
                    System.out.println("Какая ОС?:");
                    scanner.nextLine(); // Добавим эту строку для очистки символа новой строки
                    String операционнаяСистема = scanner.nextLine();
                    фильтры.put("ОС", операционнаяСистема);
                    break;
                case 4:
                    System.out.println("Какой цвет?:");
                    scanner.nextLine(); // Добавим эту строку для очистки символа новой строки
                    String цвет = scanner.nextLine().toLowerCase();
                    фильтры.put("Цвет", цвет);
                    break;
                default:
                    System.out.println("Ошибка. Неверный параметр");
                    return;
            }

            List<Laptop> результатыПоиска = фильтроватьНоутбуки(магазНоутбуков, фильтры);

            System.out.println("Соответствуют выбранным параметрам:");
            for (Laptop ноутбук : результатыПоиска) {
                System.out.println(ноутбук.getМодель());
            }
        }
    }
}
