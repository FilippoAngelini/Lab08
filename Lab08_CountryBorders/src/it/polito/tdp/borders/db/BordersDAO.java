package it.polito.tdp.borders.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.borders.model.Border;
import it.polito.tdp.borders.model.Country;

public class BordersDAO {

	public List<Country> loadAllCountries() {

		String sql = "SELECT ccode,StateAbb,StateNme " + "FROM country " + "ORDER BY StateAbb ";

		try {
			Connection conn = DBConnect.getInstance().getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				System.out.format("%d %s %s\n", rs.getInt("ccode"), rs.getString("StateAbb"), rs.getString("StateNme"));
			}

			conn.close();
			return new ArrayList<Country>();

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Database Error -- loadAllCountries");
			throw new RuntimeException("Database Error");
		}
	}

	public List<Border> getCountryPairs(int anno) {
		
		List<Border> ris = new ArrayList<Border>();
		
		String sql = "SELECT state1no, state1ab, c1.StateNme as c1Name, state2no, state2ab, c2.stateNme as c2Name FROM contiguity, country as c1, country as c2 WHERE year <= ? AND conttype = 1 AND c1.CCode=contiguity.state1no AND c2.CCode=contiguity.state2no";

		try {
			Connection conn = DBConnect.getInstance().getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setInt(1, anno);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Border b = new Border(new Country(rs.getString("state1ab"),rs.getInt("state1no"),rs.getString("c1Name")),new Country(rs.getString("state2ab"),rs.getInt("state2no"),rs.getString("c2Name")));
				//if(!ris.contains(b))
				ris.add(b);
			}

			conn.close();
			return ris;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Database Error -- loadAllCountries");
			throw new RuntimeException("Database Error");
		}

	}
}
