package com.inessa.data_base;
// Автор:Высоцкая И. Д.
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

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
        Client client = new Client(fam, name, otch, gen, strig, price);
        Client_list.add(client);
    }

    /**
     * Метод удаления клиента
     * i - индекс списка
     */
    public void Del(int i)
    {
        Client_list.remove(i);
    }

    /**
     * Получение списка клиентов
     */
    public ObservableList<Client> getClient_list()
    {
        return Client_list;
    }

}
