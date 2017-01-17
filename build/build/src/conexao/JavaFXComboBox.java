package conexao;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

public class JavaFXComboBox extends Application {

	class Site {
		String name;
		String webaddr;

		Site(String n, String a) {
			name = n;
			webaddr = a;
		}

		@Override
		public String toString() {
			return name;
		}
	}

	ObservableList<Site> siteList = FXCollections.observableArrayList(new Site("Google", "http://www.google.com"),
			new Site("MicroSoft", "http://www.microsoft.com"), new Site("Apple", "http://www.apple.com"),
			new Site("Java-Buddy", "http://java-buddy.blogspot.com/"));

	@Override
	public void start(Stage primaryStage) {

		final ComboBox comboBox = new ComboBox(siteList);
		comboBox.getSelectionModel().selectFirst(); // select the first element

		comboBox.setCellFactory(new Callback<ListView<Site>, ListCell<Site>>() {

			@Override
			public ListCell<Site> call(ListView<Site> p) {

				final ListCell<Site> cell = new ListCell<Site>() {

					@Override
					protected void updateItem(Site t, boolean bln) {
						super.updateItem(t, bln);

						if (t != null) {
							setText(t.name + ":" + t.webaddr);
						} else {
							setText(null);
						}
					}

				};

				return cell;
			}
		});

		final Label label = new Label();

		Button btn = new Button();
		btn.setText("Read comboBox");
		btn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				label.setText("selected: " + comboBox.getValue());
			}
		});

		VBox vBox = new VBox();
		vBox.setPadding(new Insets(5, 5, 5, 5));
		vBox.setSpacing(5);
		vBox.getChildren().addAll(label, comboBox, btn);

		StackPane root = new StackPane();
		root.getChildren().add(vBox);

		Scene scene = new Scene(root, 300, 250);

		primaryStage.setTitle("Hello World!");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}