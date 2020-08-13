package application;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Paaohjelma extends Application {
	
	int id = -1;

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage ikkuna) throws Exception {
	
		Tietokantasql t = new Tietokantasql();
		t.yhteydenAvaaminen();
		
		BorderPane nakyma = new BorderPane();
		
		GridPane asettelu = new GridPane();
		nakyma.setCenter(asettelu);
		asettelu.setAlignment(Pos.CENTER);
		asettelu.setVgap(10);
		asettelu.setHgap(10);
		asettelu.setPadding(new Insets(60, 10, 0, 10));
		asettelu.setPrefSize(1450, 800);
		
		Label sposti = new Label("käyttäjätunnus: ");
		TextField spkentta = new TextField();
		Label salasana = new Label("salasana: ");
		PasswordField salasanamaski = new PasswordField();
		CheckBox laatikko = new CheckBox("näytä/piilota salasana");
		Button kirjaudu = new Button("kirjaudu");
		Button luoTunnus = new Button("luo tunnus");
		Button uusiSalasana = new Button("salasana unohtunut");
		Label nayta = new Label(" ");

		laatikko.selectedProperty().addListener(new ChangeListener<Boolean>() {
		    @Override
		    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
		
					String password = salasanamaski.getText();
					
					if(newValue == true) {
						 nayta.setText(password);
					}if (newValue == false ){
						nayta.setText(" ");
					}
			}
		});
		Label teksti = new Label("");

		asettelu.add(sposti, 0, 0);
		asettelu.add(spkentta, 1, 0);
		asettelu.add(salasana, 0, 1);
		asettelu.add(salasanamaski, 1, 1);
		asettelu.add(laatikko,0, 2);
		asettelu.add(nayta, 1, 2);
		asettelu.add(kirjaudu, 0, 3);
		asettelu.add(uusiSalasana, 1, 3);
		asettelu.add(luoTunnus, 0, 4);
		asettelu.add(teksti, 1, 4);


		
		BorderPane kirjauduttu = new BorderPane();
		kirjauduttu.setPrefSize(1450, 800);
		Label tekstia = new Label("Olet kirjautunut onnistuneesti sisään!");
		kirjauduttu.setCenter(tekstia);
		
		
		
		GridPane luotunnusnakyma = new GridPane();
		luotunnusnakyma.setPrefSize(1450, 800);
		luotunnusnakyma.setAlignment(Pos.CENTER);
		luotunnusnakyma.setVgap(10);
		luotunnusnakyma.setHgap(10);
		luotunnusnakyma.setPadding(new Insets(10, 10, 10, 10));
		
		Label kayttaja = new Label("käyttäjätunnus: ");
		TextField luokayttaja = new TextField();
		Label luosala = new Label("salasana: ");
		TextField luosalasana = new TextField();
		Button luouusikayttaja = new Button("luo uusi käyttäjä ");
		Button etusivulle = new Button("etusivulle ");
		Label teksti1 = new Label(" ");
		
		luotunnusnakyma.add(kayttaja, 0, 0);
		luotunnusnakyma.add(luokayttaja, 0, 1);
		luotunnusnakyma.add(luosala, 0, 2);
		luotunnusnakyma.add(luosalasana, 0, 3);
		luotunnusnakyma.add(luouusikayttaja, 0, 4);
		luotunnusnakyma.add(teksti1, 0, 5);
		luotunnusnakyma.add(etusivulle, 0, 6);
		
		luouusikayttaja.setOnAction((event) -> {
			String kayttajantunnus = luokayttaja.getText();
			String kayttajansalasana = luosalasana.getText();
			String palautus = t.LuoKayttaja(kayttajantunnus, kayttajansalasana);
			teksti1.setText("Tunnus luotu. Kirjaudu etusivulta sisään.");
		});
		
		etusivulle.setOnAction((event) -> {
			nakyma.setCenter(asettelu);
		});
		
		
		GridPane uusisalasananakyma = new GridPane();
		uusisalasananakyma.setPrefSize(1450, 800);
		uusisalasananakyma.setAlignment(Pos.CENTER);
		uusisalasananakyma.setVgap(10);
		uusisalasananakyma.setHgap(10);
		uusisalasananakyma.setPadding(new Insets(10, 10, 10, 10));
		
		Label vaihtokayttaja = new Label("käyttäjätunnus: ");
		TextField vaihtokayttajatunnus = new TextField();
		Label uussalasana = new Label("uusi salasana: ");
		TextField uusisalasana = new TextField();
		Button tallenna = new Button("tallenna uusi salasana ");
		Button etusivulle2 = new Button("etusivulle ");
		Label teksti2 = new Label(" ");
		
		uusisalasananakyma.add(vaihtokayttaja, 0, 0);
		uusisalasananakyma.add(vaihtokayttajatunnus, 0, 1);
		uusisalasananakyma.add(uussalasana, 0, 2);
		uusisalasananakyma.add(uusisalasana, 0, 3);
		uusisalasananakyma.add(tallenna, 0, 4);
		uusisalasananakyma.add(teksti2, 0, 5);
		uusisalasananakyma.add(etusivulle2, 0, 6);
		
		tallenna.setOnAction((event) -> {
			String ktunnus = vaihtokayttajatunnus.getText();
			String usalasana = uusisalasana.getText();
			t.salasananMuuttaminen(ktunnus, usalasana);
			teksti2.setText("Salasana vaihdettu. Kirjaudu sisään etusivulta");
		});
		
		etusivulle2.setOnAction((event) -> {
			nakyma.setCenter(asettelu);
		});
		
		
		
		uusiSalasana.setOnAction((event) -> nakyma.setCenter(uusisalasananakyma));
		luoTunnus.setOnAction((event) -> nakyma.setCenter(luotunnusnakyma));
		kirjaudu.setOnAction((event) -> {
			id = t.tarkistaKayttaja(spkentta.getText(), salasanamaski.getText());
			if(id >= 0) {
				nakyma.setCenter(kirjauduttu);
			}else {
				teksti.setText("Tarkista tunnus tai salasana");
			}
		});

		

		Scene kulissi = new Scene(nakyma);
		ikkuna.setScene(kulissi);
		ikkuna.show();
		
	}

}
