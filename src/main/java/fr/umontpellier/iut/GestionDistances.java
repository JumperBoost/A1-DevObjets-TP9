package fr.umontpellier.iut;

import java.util.HashMap;
import java.util.Map;

public class GestionDistances {
    private static Map<String, Integer> distances;

    // Initialisation de la Map au chargement de la classe
    static {
        distances = new HashMap<>();
        distances.put("Montpellier", 0);
        distances.put("Sète", 36);
        distances.put("Nîmes", 58);
        distances.put("Lunel", 30);
        distances.put("Béziers", 80);
        distances.put("Sommières", 30);
    }

    public static int getDistance(String ville) {
        throw new RuntimeException("Méthode à implémenter");
    }
}
