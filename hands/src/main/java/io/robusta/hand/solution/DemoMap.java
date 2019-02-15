package io.robusta.hand.solution;

import java.util.HashMap;
import java.util.Map;

public class DemoMap {
	public static void main(String[] args) {
		HashMap<Integer, String> map = new HashMap<>();
		
		map.put(3, "3s, 3h, 3d");
		map.put(6, "6s, 6d");
		
		System.out.println(map);
		
		Map<String, String> players = new HashMap<>();
		players.put("Platini", "France");
		players.put("Tigana", "France");
		players.put("Zico", "Brasil");
		players.put("Rossi", "Italy");
		System.out.println(players);
	}

}
