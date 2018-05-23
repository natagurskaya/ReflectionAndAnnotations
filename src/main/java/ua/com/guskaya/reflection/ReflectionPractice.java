package ua.com.guskaya.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

public class ReflectionPractice {

    public static void main(String[] args) {
        Company company = null;
        Class clazz = null;
        try {
            clazz = Class.forName(Company.class.getName());
            company = getObject(clazz);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(company);
        printMethodsSignaturesWithFinal(company);
        invokeMethodsWithoutParams(company);
        getNonpublicMethods(clazz);
        getParentsAndInterfaces(clazz);
        getAnnotatedMethod(company);
    }

    //Метод принимает класс и возвращает созданный объект этого класса
    public static Company getObject(Class<?> clazz) {
        Company company = null;
        try {
            company = (Company) clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return company;
    }

    //Метод принимает object и вызывает у него все методы без параметров
    public static void invokeMethodsWithoutParams(Company company) {
        Class clazz = company.getClass();
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            if (method.getParameterTypes().length == 0) {
                method.setAccessible(true);
                try {
                    method.invoke(company, null);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //Метод принимает object и выводит на экран все сигнатуры методов в который есть final
    public static void printMethodsSignaturesWithFinal(Company company) {
        Class clazz = company.getClass();
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            int mods = method.getModifiers();
            if (Modifier.isFinal(mods)) {
                System.out.println(method.getName() + Arrays.toString(method.getParameterTypes()));
            }
        }
    }

    //Метод принимает Class и выводит все не публичные методы этого класса
    public static void getNonpublicMethods(Class clazz) {
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            method.setAccessible(true);
            int mods = method.getModifiers();
            if (!Modifier.isPublic(mods)) {
                System.out.println(method);
            }
        }
    }

    //Метод принимает Class и выводит всех предков класса и все интерфейсы которое класс имплементирует
    public static void getParentsAndInterfaces(Class clazz) {
   /* while(clazz.getSuperclass() != null){
        System.out.println(clazz.getSuperclass());
    }*/
        System.out.println(clazz.getSuperclass());
        Class[] interfaces = clazz.getInterfaces();
        for (Class anInterface : interfaces) {
            System.out.println(anInterface.getName());
        }
    }
    //Метод принимает объект и меняет всего его привантые поля на их нулевые значение (null, 0, false etc)

    //Принимает объект и вызывает методы проанотированные аннотацией @Run (аннотация Ваша, написать самим)
    public static void getAnnotatedMethod(Company company) {
        Class clazz = company.getClass();
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            method.setAccessible(true);
            if (method.isAnnotationPresent(Run.class)) {
                try {
                    method.invoke(company, null);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    //Принимает объект. Поля проаннотиваные аннотацией @Inject заполняет объектом того класса
    // который находиться в поле аннотации Class clazz(). Если поле аннотации пустое.
    // Создает пустой экзепляр класса, базируясь на типе поля (аннотация Ваша, написать самим)
}
