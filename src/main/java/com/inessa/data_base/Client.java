// Автор: Высоцкая И. Д.
package com.inessa.data_base;

/**
 * Класс клиент парикмахерской
 */
public class Client {

    private String fam; // поле фамилия
    private String name; // поле имя
    private String otch; // поле отчество
    private String gen; // поле пол
    private String strig; // поле название стрижки
    private int price; // поле цена за стрижку

    /**
     * Вставка фамилии
     * Не может быть пустым
     * String fam - фамилия
     */
    public void setFam(String fam) {
        if (!fam.isEmpty()) {
            this.fam = fam;
        }
    }

    /**
     * Вставка имени
     * Не может быть пустым
     * String name - имя
     */
    public void setName(String name) {
        if (!name.isEmpty()) { // если не пустое
            this.name = name;
        }
    }

    /**
     * Вставка отчества
     * Указывается при наличии, при отсутствии ставится прочерк
     * String otch - отчество
     */
    public void setOtch(String otch) {
        if (!otch.isEmpty()) { // если не пустое, пишем отчество
            this.otch = otch;
        } else { // иначе ставим прочерк
            this.otch = "-";
        }
    }

    /**
     * Вставка пола
     * Не может быть пустым
     * String gen - пол
     */
    public void setGen(String gen) {
        if (!gen.isEmpty()) {
            this.gen = gen;
        }
    }

    /**
     * Вставка названия стрижки
     * Не может быть пустым
     * String strig - название стрижки
     */
    public void setStrig(String strig) {
        if (!strig.isEmpty()) {
            this.strig = strig;
        }
    }

    /**
     * Вставка цены за стрижку
     * Не может быть пустым
     * String fam - фамилия
     */
    public void setPrice(int price) {
        if (price >= 0) {
            this.price = price;
        }
    }

    /**
     * Получение фамилии
     */
    public String getFam() {
        return fam;
    }

    /**
     * Получение имени
     */
    public String getName() {
        return name;
    }

    /**
     * Получение отчества
     */
    public String getOtch() {
        return otch;
    }

    /**
     * Получение пола
     */
    public String getGen() {
        return gen;
    }

    /**
     * Получение названия стрижки
     */
    public String getStrig() {
        return strig;
    }

    /**
     * Получение цены за стрижку
     */
    public int getPrice() {
        return price;
    }

    /**
     * Получение полной информации о клиенте
     * Нужен для записи в файл
     */
    @Override
    public String toString() {
        return fam + " ; " + name + " ; " + otch + " ; " + gen + " ; " + strig + " ; " + Integer.toString(price);
    }

    /**
     * Конструктор с параметрами
     * fam - фамилия
     * name - имя
     * otch - отчество
     * gen - пол
     * strig - название стрижки
     * price - цена за стрижку
     */
    Client(String fam, String name, String otch, String gen, String strig, int price)
    {
        setFam(fam);
        setName(name);
        setOtch(otch);
        setGen(gen);
        setStrig(strig);
        setPrice(price);
    }
}
