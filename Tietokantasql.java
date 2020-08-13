package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;



public class Tietokantasql {
	Connection con;


	public Connection yhteydenAvaaminen() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");  
			this.con=DriverManager.getConnection(  
					"jdbc:mysql://localhost:3306/salasana?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                    "...", "....");
			return con;
		}

		catch(Exception e){ System.out.println(e);}  


		return null;
	}

	public String LuoKayttaja(String kayttaja, String salasana) {

		if(kayttaja.length() > 70) {
			return "käyttäjänimi voi olla maximissaan 70 merkkiä";

		}

		if(salasana.length() > 20) {
			return "salasana voi olla maximissaan 10 merkkiä";

		}
		try {

			ResultSet haku = haeKayttaja(kayttaja);
			if(haku.next()) {
				return "Käyttäjä on jo olemassa";
			}else {
				PreparedStatement lauseke = con.prepareStatement("Insert into Kayttajat (kayttaja, salasana) values (?,?)");
				lauseke.setString(1, kayttaja);
				lauseke.setString(2, salasana);
				lauseke.executeUpdate();
				return "Käyttäjä on luotu. Kirjaudu sisään etusivulta.";
			}
		}

		catch(Exception e){ System.out.println(e);}  
		return null;
	}

	public ResultSet haeKayttaja(String kayttaja) {

		try {
			PreparedStatement lauseke = con.prepareStatement("Select * from Kayttajat where kayttaja = ?");
			lauseke.setString(1, kayttaja);
			ResultSet haku = lauseke.executeQuery();
			return haku;
		}

		catch(Exception e){ 
			System.out.println(e);
			return null;}  

	}

	public void salasananMuuttaminen(String kayttaja, String uusisalasana) {

		try {

			ResultSet haku = haeKayttaja(kayttaja);

			if(haku.next()) {
				PreparedStatement lauseke = con.prepareStatement("Update Kayttajat set salasana = ? where id = ?");
				lauseke.setString(1, uusisalasana);
				lauseke.setInt(2, haku.getInt("id"));
				lauseke.executeUpdate();

			}
		}

		catch(Exception e){ 
			System.out.println(e);
		} 

	}

	public int tarkistaKayttaja(String kayttaja, String salasana) {


		try {
			ResultSet haku = haeKayttaja(kayttaja);

			if(haku.next()) {
				if(haku.getString("salasana").equals(salasana)) {
					return haku.getInt("id");
				}
			}
			return -1;
		}
		catch(Exception e){ 
			System.out.println(e);
			return -1;
		} 



	}
}
