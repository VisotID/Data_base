package com.inessa.data_base;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCombination;
import javafx.stage.FileChooser;

import java.io.File;

/**
 * Контроллер базы данных
 * Автор: Высоцкая И.Д.
 */
public class DataBController {

    @FXML
    TableColumn fam_col; // колонка таблицы фамилия

    @FXML
    TableColumn name_col; // колонка таблицы имя

    @FXML
    TableColumn otch_col; // колонка таблицы отчество

    @FXML
    TableColumn gender_col; // колонка таблицы пол

    @FXML
    TableColumn strig_col; // колонка таблицы стрижка

    @FXML
    TableColumn price_col; // колонка таблицы цена

    @FXML
    TableView client_table; // таблица с клиентами

    @FXML
    TextField fam_f; // поле ввода фамилии

    @FXML
    TextField name_f; // поле ввода имени

    @FXML
    TextField otch_f; // поле ввода отчества

    @FXML
    TextField strig_f; // поле ввода названия стрижки

    @FXML
    TextField price_f; // поле ввода цены за стрижку

    @FXML
    CheckBox check_m; // чекбокс для выбора пола "муж"

    @FXML
    CheckBox check_w; // чекбокс для выбора пола "жен"

    @FXML
    MenuItem menu_add; // кнопка меню добавить

    @FXML
    MenuItem menu_del; // кнопка меню удалить

    @FXML
    MenuItem menu_edit; // кнопка меню изменить

    @FXML
    MenuItem menu_save; // кнопка меню сохранить

    @FXML
    MenuItem menu_load; // кнопка меню загрузить

    @FXML
    MenuItem menu_find; // кнопка меню найти

    @FXML
    MenuItem menu_about; // кнопка меню помощь

    @FXML
    Button Save_but; // кнопка сохранить

    @FXML
    Button Add_but; // кнопка добавить

    @FXML
    Button Del_but; // кнопка удалить

    @FXML
    Button Find_but; // кнопка найти

    /**
     * База данных с клиентами
     */
    private final Data_base data_base = new Data_base();

    /**
     * selectionmodel для получения доступа к объекту показанному в таблице
     */
    private TableView.TableViewSelectionModel<Client> selectionModel;

    /**
     * Инициализация
     */
    public void initialize()
    {
        // инициализация полей
        fam_col.setCellValueFactory(new PropertyValueFactory<>("fam"));
        name_col.setCellValueFactory(new PropertyValueFactory<>("name"));
        otch_col.setCellValueFactory(new PropertyValueFactory<>("otch"));
        gender_col.setCellValueFactory(new PropertyValueFactory<>("gen"));
        strig_col.setCellValueFactory(new PropertyValueFactory<>("strig"));
        price_col.setCellValueFactory(new PropertyValueFactory<>("price"));

        client_table.setItems(data_base.getClient_list()); // связывание списка и таблицы

        selectionModel = client_table.getSelectionModel(); // инициализация selectionmodel

        // Создание всплывающих окон при наведении на некоторые объекты
        fam_f.setTooltip(new Tooltip("Поле ввода фамилии, обязательное"));
        name_f.setTooltip(new Tooltip("Поле ввода имени, обязательное"));
        otch_f.setTooltip(new Tooltip("Поле ввода отчества, при наличии"));
        strig_f.setTooltip(new Tooltip("Поле ввода названия стрижки, обязательное"));
        price_f.setTooltip(new Tooltip("Поле ввода цены за стрижку, обязательное"));
        Save_but.setTooltip(new Tooltip("Сохранение базы данных в текстовый файл"));
        Add_but.setTooltip(new Tooltip("Добавление клиента в базу данных"));
        Del_but.setTooltip(new Tooltip("Удаление клиента из базы данных"));
        Find_but.setTooltip(new Tooltip("Поиск клиента в базе данных"));

        // Добавление горячих клавиш
        menu_add.setAccelerator(KeyCombination.keyCombination("Ctrl+A"));
        menu_del.setAccelerator(KeyCombination.keyCombination("Ctrl+D"));
        menu_edit.setAccelerator(KeyCombination.keyCombination("Ctrl+E"));
        menu_find.setAccelerator(KeyCombination.keyCombination("Ctrl+F"));
        menu_save.setAccelerator(KeyCombination.keyCombination("Ctrl+S"));
        menu_load.setAccelerator(KeyCombination.keyCombination("Ctrl+L"));
        menu_about.setAccelerator(KeyCombination.keyCombination("Ctrl+P"));

        /**
         * Добавление слушателя для обновления полей ввода при выборе объекта в таблице
         */
        selectionModel.selectedItemProperty().addListener(new ChangeListener<Client>() {
            @Override
            public void changed(ObservableValue<? extends Client> observableValue, Client client, Client t1) {
                if (t1 != null)
                {
                    fam_f.setText(t1.getFam());
                    name_f.setText(t1.getName());
                    otch_f.setText(t1.getOtch());
                    if (t1.getGen().equals("муж")) // если выбран чек муж
                    {
                        if (!check_m.isSelected()) // если нажали на вариант муж
                        {
                            check_m.fire(); // нажимаем на чек муж
                        }
                        if (check_w.isSelected()) // если нажали на вариант жен
                        {
                            check_w.fire(); // нажимаем ещё раз на вариант жен, чтобы убрать чек
                        }
                    }
                    else // иначе
                    {
                        if (!check_w.isSelected()) // если нажали на вариант жен
                        {
                            check_w.fire(); // нажимаем на чек жен
                        }
                        if (check_m.isSelected()) // если нажали на вариант муж
                        {
                            check_m.fire(); // нажимаем ещё раз на вариант муж, чтобы убрать чек
                        }
                    }
                    strig_f.setText(t1.getStrig());
                    price_f.setText(Integer.toString(t1.getPrice()));
                }
            }
        });
    }

    /**
     * Событие при нажатии на кнопку/кнопку меню добавить
     * Если в чекбоксе не выбрано ни одного варианта, добавления не произойдёт, если выбрано оба варианта будет выбрано муж
     */
    public void onAdd()
    {
        if (!fam_f.getText().isEmpty() && !name_f.getText().isEmpty() && !strig_f.getText().isEmpty() && !price_f.getText().isEmpty() && ( !check_m.isDisable() || !check_w.isDisable() ))
        {
            if (check_m.isSelected()) // если галочка напротив муж
            {
                data_base.Add(fam_f.getText(), name_f.getText(), otch_f.getText(), "муж", strig_f.getText(), Integer.parseInt(price_f.getText()));
            }
            else if (check_w.isSelected()) // если галочка напротив жен
            {
                data_base.Add(fam_f.getText(), name_f.getText(), otch_f.getText(), "жен", strig_f.getText(), Integer.parseInt(price_f.getText()));
            }
        }
    }

    /**
     * Событие при нажатии на кнопку/кнопку меню удалить
     */
    public void onDel()
    {
        data_base.Del(selectionModel.getFocusedIndex());
    }

    /**
     * Событие при нажатии на кнопку/кнопку меню найти
     */
    public void onSearch()
    {
        if (!fam_f.getText().isEmpty() && !name_f.getText().isEmpty() && !strig_f.getText().isEmpty() && !price_f.getText().isEmpty() && ( !check_m.isDisable() || !check_w.isDisable() ))
        {
            try
            {
                if (check_m.isSelected()) // если галочка напротив муж
                {
                    int i = data_base.Search_c(fam_f.getText(), name_f.getText(), otch_f.getText(), "муж", strig_f.getText(), Integer.parseInt(price_f.getText()));
                    selectionModel.select(i);
                }
                else if (check_w.isSelected()) // если галочка напротив жен
                {
                    int i = data_base.Search_c(fam_f.getText(), name_f.getText(), otch_f.getText(), "жен", strig_f.getText(), Integer.parseInt(price_f.getText()));
                    selectionModel.select(i);
                }
            } catch (OutOfMemoryError e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Событие при нажатии на кнопку меню изменить
     * Если в чекбоксе не выбрано ни одного варианта, добавления не произойдёт, если выбрано оба варианта будет выбрано муж
     */
    public void onEdit()
    {
        if (!fam_f.getText().isEmpty() && !name_f.getText().isEmpty() && !strig_f.getText().isEmpty() && !price_f.getText().isEmpty() && ( !check_m.isDisable() || !check_w.isDisable() )) // проверяем введены ли обязательные поля
        {
            try
            {
                if (selectionModel.getFocusedIndex() != -1) // если не пустая база данных
                {
                    if (check_m.isSelected()) // если галочка напротив муж
                    {
                        data_base.Rever(fam_f.getText(), name_f.getText(), otch_f.getText(), "муж", strig_f.getText(), Integer.parseInt(price_f.getText()), selectionModel.getFocusedIndex()); // редактируем клиента в базе данных если введены
                        client_table.refresh(); // обновление таблицы
                    }
                    else if (check_w.isSelected()) // если галочка напротив жен
                    {
                        data_base.Rever(fam_f.getText(), name_f.getText(), otch_f.getText(), "жен", strig_f.getText(), Integer.parseInt(price_f.getText()), selectionModel.getFocusedIndex()); // редактируем клиента в базе данных если введены
                        client_table.refresh(); // обновление таблицы
                    }
                }
            }
            catch (OutOfMemoryError e) // если индекс за пределами списка
            {
                e.printStackTrace(); // ошибка
            }
        }
    }

    /**
     * Событие при нажатии на кнопку/кнопку меню сохранить
     */
    public void onSave()
    {
        FileChooser fileChooser = new FileChooser(); // диалоговое окно с выбором файла
        fileChooser.setTitle("Сохранить базу данных"); // задаем заголовок диалогового окна

        fileChooser.getExtensionFilters().addAll( // добавляем фильтры файлов
                new FileChooser.ExtensionFilter("Текстовые файлы", "*.txt"),
                new FileChooser.ExtensionFilter("Все файлы", "*.*")
        );

        File file = fileChooser.showSaveDialog(client_table.getScene().getWindow()); // показываем диалоговое окно для сохранения

        if (file != null) // если файл выбран
        {
            try {
                data_base.Save(file.getAbsolutePath()); // сохраняем базу данных в файл
            }
            catch (Exception e) // произошла ошибка
            {
                e.printStackTrace();
            }
        }
    }

    /**
     * Событие при нажатии на кнопку меню загрузить
     */
    public void onLoad()
    {
        FileChooser fileChooser = new FileChooser(); // диалоговое окно с выбором файла
        fileChooser.setTitle("Загрузить базу данных"); // задаем заголовок диалогового окна

        fileChooser.getExtensionFilters().addAll( // добавляем фильтры файлов
                new FileChooser.ExtensionFilter("Текстовые файлы", "*.txt"),
                new FileChooser.ExtensionFilter("Все файлы", "*.*")
        );

        File file = fileChooser.showOpenDialog(client_table.getScene().getWindow()); // показываем диалоговое окно для загрузкм

        if (file != null) // если файл выбран
        {
            try {
                client_table.getItems().clear(); // очищаем базу данных в таблице
                data_base.Load(file.getAbsolutePath()); // загружаем базу данных
            }
            catch (Exception e) // произошла ошибка
            {
                e.printStackTrace();
            }
        }
    }

    /**
     * Событие при нажатии на кнопку меню справка
     */
    public void About()
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Справка");
        alert.setHeaderText(null);
        alert.setContentText("База данных с информацией о клиентах парикмахерской: ФИО, пол, название стрижки, цена стрижки\n\n" +
                "Необязательные поля: отчество - заполняется при наличии, если отсутствует, автоматически ставится прочерк(-)\n\n" +
                "Работа c базой данных:\n" +
                "Добавление клиента: Вводим данные клиента и нажимаем на кнопку добавить во вкладке меню операции(Ctrl+A) или на панели\n\n" +
                "Удаление клиента: Выбираем в таблице клиента и нажимаем на кнопку удалить во вкладке меню операции(Ctrl+D) или на панели\n\n" +
                "Редактирование клиента: Выбираем в таблице клиента и меняем данные в полях, после нажимаем изменить во вкладке меню операции(Ctrl+E)\n\n" +
                "Поиск клиента: Вводим в поля данные клиента и нажимаем на кнопку найти во вкладке меню операции(Ctrl+F) или на панели\n\n" +
                "Сохранение базы данных: Нажимаем на кнопку во вкладке меню файл сохранить(Ctrl+S), после выбираем в появившемся окне текстовый файл или создаем его\n\n" +
                "Загрузка базы данных: Нажимаем на кнопку во вкладке меню файл загрузить(Ctrl+L), после выбираем в появившемся окне текстовый файл с базой данных\n\n" +
                "Получение информации о программе (данная справка): Нажимаем на кнопку во вкладке меню помощь справка(Ctrl+P)\n\n" +
                "Автор: Высоцкая И.Д.");
        alert.showAndWait();
    }
}