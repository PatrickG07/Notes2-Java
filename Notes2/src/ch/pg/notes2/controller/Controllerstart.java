package ch.pg.notes2.controller;

import java.sql.SQLException;
import java.util.List;

import ch.pg.notes2.model.Database;
import ch.pg.notes2.view.Start;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

/**
 * @author Patrick
 *
 * 
 */
public class Controllerstart {

	@FXML
	protected TextArea TA;

	@FXML
	public ListView<String> lvAnzeige;

	Database DB = new Database();
	Start ST = new Start();

	/**
	 * shows the Database entitys in the ListView when the scene is open (class is
	 * running)
	 * @throws SQLException 
	 */
	@FXML
	public void initialize() throws SQLException {
		refreshListView();
	}

	/**
	 * Sets the data form the database to the Text Area
	 */
	public void refreshTextArea() {
		TA.setText(DB.data);
	}

	/**
	 * Clears the ListView and insets it with the text from the database
	 * @throws SQLException 
	 */
	public void refreshListView() throws SQLException {
		DB.Liste01.clear();
		lvAnzeige.getItems().clear();
		lvAnzeige.getSelectionModel().clearSelection();
		DB.databaseselect();
		List<String> Listname2 = DB.Liste01;
		for (String element : Listname2) {
			lvAnzeige.getItems().add(element);
		}
	}

	/**
	 * gets the text from the clicked item in the ListView and ads it to the
	 * TextArea
	 * 
	 * @param e
	 * @throws SQLException
	 */
	@FXML
	public void clike(MouseEvent e) throws SQLException {
		DB.data = lvAnzeige.getSelectionModel().getSelectedItem();
		DB.databaseselectID();
		System.out.println(DB.data);
		System.out.println(DB.ID);
		refreshTextArea();
	}

	/**
	 * gets the text from the selected item in the ListView and deletes it in the
	 * database
	 * 
	 * @param event
	 * @throws SQLException
	 */
	@FXML
	protected void delete(ActionEvent e) throws SQLException {
		DB.data = lvAnzeige.getSelectionModel().getSelectedItem();
		System.out.println(DB.data);
		DB.databasedelete();
		refreshListView();
		newpage(e);
	}

	/**
	 * Clears the Text Area and Sets the ID to 0 to insert a new data and not
	 * Updating the old one
	 * 
	 * @param e
	 */
	@FXML
	protected void newpage(ActionEvent e) {
		TA.setText("");
		DB.ID = 0;
	}

	/**
	 * Saves the Text from the Text Area to the database or updates the database
	 * entity with the ID
	 * 
	 * @param e
	 * @throws SQLException
	 */
	@FXML
	protected void save(ActionEvent e) throws SQLException {
		DB.data = TA.getText();
		System.out.println(DB.ID);
		System.out.println(DB.data);
		if (DB.ID == 0) {
			DB.databaseinsert();
		} else {
			DB.databaseupdate();
		}
		refreshListView();
	}
}
