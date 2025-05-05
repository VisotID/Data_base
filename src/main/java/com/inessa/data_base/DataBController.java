package com.inessa.data_base;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Контроллер базы данных
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

    /**
     * База данных с клиентами
     */
    private final Data_base data_base = new Data_base();

    /**
     *
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

        selectionModel = client_table.getSelectionModel();

        selectionModel.selectedItemProperty().addListener(new ChangeListener<Client>() {
            @Override
            public void changed(ObservableValue<? extends Client> observableValue, Client client, Client t1) {
                if (t1 != null)
                {
                    fam_f.setText(t1.getFam());
                    name_f.setText(t1.getName());
                    otch_f.setText(t1.getOtch());
                    if (t1.getGen().equals("муж"))
                    {
                        if (!check_m.isSelected())
                        {
                            check_m.fire();
                        }
                        if (check_w.isSelected())
                        {
                            check_w.fire();
                        }
                    }
                    else
                    {
                        if (!check_w.isSelected())
                        {
                            check_w.fire();
                        }
                        if (check_m.isSelected())
                        {
                            check_m.fire();
                        }
                    }
                    strig_f.setText(t1.getStrig());
                    price_f.setText(Integer.toString(t1.getPrice()));
                }
            }
        });
    }

    /**
     * Событие при нажатии на кнопку добавить
     */
    public void onAdd()
    {
        if (!fam_f.getText().isEmpty() && !name_f.getText().isEmpty() && !strig_f.getText().isEmpty() && !price_f.getText().isEmpty() && ( !check_m.isDisable() || !check_w.isDisable() ))
        {
            if (check_m.isSelected())
            {
                data_base.Add(fam_f.getText(), name_f.getText(), otch_f.getText(), "муж", strig_f.getText(), Integer.parseInt(price_f.getText()));
            }
            else if (check_w.isSelected())
            {
                data_base.Add(fam_f.getText(), name_f.getText(), otch_f.getText(), "жен", strig_f.getText(), Integer.parseInt(price_f.getText()));
            }
        }
    }

    /**
     * Событие при нажатии на кнопку удалить
     */
    public void onDel()
    {
        data_base.Del(selectionModel.getFocusedIndex());
    }
}