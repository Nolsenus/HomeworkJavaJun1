import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        System.out.println("Задание 1:");
        task1();
        splitTextInTerminal();
        System.out.println("Задание 2:");
        task2();
    }

    static void splitTextInTerminal() {
        System.out.println('\n');
    }

    static void task1() {
        List<Integer> list = task1_0();
        task1_1(list);
        splitTextInTerminal();
        task1_2(list);
        splitTextInTerminal();
        task1_3(list);
    }

    static List<Integer> task1_0() {
        return Stream.generate(() -> ThreadLocalRandom.current().nextInt(1_000_000))
                .limit(1000)
                .toList();
    }

    static void task1_1(List<Integer> list) {
        Optional<Integer> max = list.stream().max(Comparator.comparingInt(it -> it));
        max.ifPresent(integer -> System.out.printf("Максимальное число: %d%n", integer));
    }

    static void task1_2(List<Integer> list) {
        int sum = list.stream().filter(it -> it > 500_000)
                .map(it -> it * 5 - 150)
                .reduce(0, Integer::sum);
        System.out.printf("Сумма всех чисел больше 500_000, умноженных на 5 и уменьшенных на 150: %d%n", sum);
    }

    static void task1_3(List<Integer> list) {
        long count = list.stream().map(it -> (long) it)
                .filter(it -> it * it < 100_000)
                .count();
        System.out.printf("Количество чисел, квадрат которых меньше 100_000: %d%n", count);
    }

    static void task2() {
        List<Employee> list = task2_1();
        task2_2(list);
        splitTextInTerminal();
        task2_3(list);
        splitTextInTerminal();
        task2_4(list);
        splitTextInTerminal();
        task2_5(list);
    }

    static List<Employee> task2_1() {
        return Arrays.asList(
                new Employee("Олег", 24, 12_000, "Работа с клиентами"),
                new Employee("Ольга", 35, 18_000, "Работа с клиентами"),
                new Employee("Александр", 38, 17_000, "Охрана"),
                new Employee("Глеб", 29, 15_000, "Охрана"),
                new Employee("Лилия", 42, 24_000, "Менеджмент"),
                new Employee("Алла", 24, 15_000, "Соц. сети"),
                new Employee("Никита", 19, 9_000, "Рабочая сила"),
                new Employee("Дмитрий", 18, 9_000, "Рабочая сила"),
                new Employee("Наталья", 40, 18_000, "Работа с поставщиками"),
                new Employee("Вениамин", 72, 26_000, "Менеджмент"),
                new Employee("Анна", 30, 16_000, "Перевозки"),
                new Employee("Виталий", 44, 17_000, "Перевозки")
        );
    }

    static void task2_2(List<Employee> list) {
        List<String> departments = list.stream()
                .map(Employee::getDepartment)
                .distinct()
                .toList();
        System.out.println("Список различных департаментов:");
        departments.forEach(System.out::println);
    }

    static void task2_3(List<Employee> list) {
        List<Employee> underPaid = list.stream()
                                .filter(it -> it.getSalary() < 10_000)
                                .toList();
        System.out.println("Сотрудники, зарплата которых ниже 10_000, до повышения зарплаты:");
        underPaid.forEach(System.out::println);

        list.stream()
                .filter(it -> it.getSalary() < 10_000)
                .forEach(it -> it.setSalary(it.getSalary() * 1.2));


        System.out.println("Те же сотрудники после повышения зарплаты:");
        underPaid.forEach(System.out::println);
    }

    static void task2_4(List<Employee> list) {
        Map<String, List<Employee>> result = list.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
        System.out.println("Список департаментов с сотрудниками в них:");
        for (String department : result.keySet()) {
            System.out.print(department + ":");
            for (Employee employee : result.get(department)) {
                System.out.print(" " + employee);
            }
            System.out.println();
        }
    }

    static void task2_5(List<Employee> list) {
        Map<String, Double> result = list.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment,
                        Collectors.averagingDouble(Employee::getSalary)));
        System.out.println("Список департаментов со средней зарплатой по ним:");
        for (String department : result.keySet()) {
            System.out.printf("%s: %.2f%n", department, result.get(department));
        }
    }

}
