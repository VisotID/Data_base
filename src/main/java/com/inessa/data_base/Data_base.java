// Автор:Высоцкая И. Д.
package com.inessa.data_base;

import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Data_base {

    /**
     * Список с клиентами
     * ObservableList используем для наблюдения, таблица наблюдатель, список наблюдаемый(чтобы отслеживать изменения)
     */
    ObservableList<Client> Client_list = FXCollections.observableArrayList();

    /**
     * Метод добавления клиента
     * fam - фамилия
     * name - имя
     * otch - отчество
     * gen - пол
     * strig - название стрижки
     * price - цена за стрижку
     */
    public void Add(String fam, String name, String otch, String gen, String strig, int price)
    {
        Client client = new Client(fam, name, otch, gen, strig, price); // создаем клиента
        Client_list.add(client); // добавляем в список
    }

    /**
     * Метод удаления клиента
     * Кидает исключение, если индекс отсутствует
     * i - индекс списка
     */
    public void Del(int i)
    {
        if (i < Client_list.size()) // если индекс в пределах базы данных (списка)
        {
            Client_list.remove(i); // удаляем клиента из списка
        }
        else // иначе
        {
            throw new OutOfMemoryError("Такого индекса нет"); // кидаем ошибку
        }
    }

    /**
     * Метод изменения данных о клиенте
     * Кидает исключение, если индекс за пределами
     * fam - фамилия
     * name - имя
     * otch - отчество
     * gen - пол
     * strig - название стрижки
     * price - цена за стрижку
     * i - индекс списка
     */
    public void Rever(String fam, String name, String otch, String gen, String strig, int price, int i)
    {
        if (i < Client_list.size()) // проходим по списку(базе дынных)
        {
            Client_list.get(i).setFam(fam); // задаем новую информацию о клиенте
            Client_list.get(i).setName(name);
            Client_list.get(i).setOtch(otch);
            Client_list.get(i).setGen(gen);
            Client_list.get(i).setStrig(strig);
            Client_list.get(i).setPrice(price);
        }
        else
        {
            throw new OutOfMemoryError("Индекс за пределами"); // если индекс за пределами массива
        }
    }

    /**
     * Метод поиска клиента
     * Кидаем исключение если такого человека нет
     * fam - фамилия
     * name - имя
     * otch - отчество
     * gen - пол
     * strig - название стрижки
     * price - цена за стрижку
     */
    public int Search_c(String fam, String name, String otch, String gen, String strig, int price)
    {
        if (otch.isEmpty()) // проверяем введено ли отчество
        {
            otch = "-"; // если нет, то ставится прочерк
        }
        for (int i = 0; i < Client_list.size(); i++) // проходим по списку (базе данных)
        {
            if (
                    Client_list.get(i).getFam().equalsIgnoreCase(fam) && Client_list.get(i).getName().equalsIgnoreCase(name) && // сверяем данные о клиенте
                            Client_list.get(i).getOtch().equalsIgnoreCase(otch) && Client_list.get(i).getGen().equals(gen) &&
                            Client_list.get(i).getStrig().equalsIgnoreCase(strig) && Client_list.get(i).getPrice()== price
            )
            {
                return i; // возвращаем индекс
            }
        }
        throw new OutOfMemoryError("Человек не найден"); // если нет человека
    }

    /**
     * Метод поиска клиента по названию стрижки
     * Кидаем исключение если такого человека нет
     * strig - название стрижки
     */
    public int Search_strig(String strig)
    {
        for (int i = 0; i < Client_list.size(); i++) // проходим по списку (базе данных)
        {
            if (Client_list.get(i).getStrig().equalsIgnoreCase(strig)) // сверяем данные о клиенте
            {
                return i; // возвращаем индекс
            }
        }
        throw new OutOfMemoryError("Человек не найден"); // если нет человека
    }

    /**
     * Метод поиска клиента по цене за стрижку
     * Кидаем исключение если такого человека нет
     * price - цена за стрижку
     */
    public int Search_price(Integer price)
    {
        for (int i = 0; i < Client_list.size(); i++) // проходим по списку (базе данных)
        {
            if (Client_list.get(i).getPrice() == price) // сверяем данные о клиенте
            {
                return i; // возвращаем индекс
            }
        }
        throw new OutOfMemoryError("Человек не найден"); // если нет человека
    }

    /**
     * Метод поиска клиента по фамилии или имени или отчеству
     * Кидаем исключение если такого человека нет
     * fam - фамилия
     * name - имя
     * otch - отчество
     */
    public int Search_fio(String fam, String name, String otch)
    {
        for (int i = 0; i < Client_list.size(); i++) // проходим по списку (базе данных)
        {
            if (Client_list.get(i).getFam().equalsIgnoreCase(fam) || // сверяем данные о клиенте
                    Client_list.get(i).getName().equalsIgnoreCase(name) ||
                    Client_list.get(i).getOtch().equalsIgnoreCase(otch))
            {
                return i; // возвращаем индекс
            }
        }
        throw new OutOfMemoryError("Человек не найден"); // если нет человека
    }

    /**
     * Получение списка клиентов
     */
    public ObservableList<Client> getClient_list()
    {
        return Client_list;
    }

    /**
     * Сохранение базы данных в файл.
     * Работает с текстовыми файлами(txt).
     * Каждая строка файла - отдельный клиент(разделитель ;): фамилия ; имя ; отчество ; пол ; стрижка ; цена стрижки
     * Пример: gng ; hk ; jhttk ; муж ; uggm ; 8
     * @param file имя файла
     */
    public void Save(String file)
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) // инициализация записи в файл
        {
            for (Client client : Client_list) // пока есть клиенты
            {
                writer.write(client.toString()); // записываем в файл
                writer.newLine(); // переход на следующую строку
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Загрузка базы данных из файла.
     * Работает с текстовыми файлами(txt).
     * Каждая строка файла - отдельный клиент(разделитель ;): фамилия ; имя ; отчество ; пол ; стрижка ; цена
     * Пример: gng ; hk ; jhttk ; муж ; uggm ; 8
     * @param file имя файла
     */
    public void Load(String file)
    {
        if (Files.exists(Paths.get(file))) // если файл существует
        {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) // инициализация чтения из файла
            {
                String line; // строка
                while ((line = reader.readLine()) != null) // пока есть строки
                {
                    String[] part = line.split(" ; ");
                    Client_list.add(new Client(part[0], part[1], part[2], part[3], part[4], Integer.parseInt(part[5])));
                }
            }
            catch (IOException e) // если исключение
            {
                e.printStackTrace();
            }
        }
    }
}
