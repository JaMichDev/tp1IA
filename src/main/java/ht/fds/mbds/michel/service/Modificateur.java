package ht.fds.mbds.michel.service;

import jakarta.enterprise.context.Dependent;

import java.io.Serializable;
import java.text.Normalizer;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Locale;

/**
 * Classe responsable du traitement des questions
 * Pour ce TP, elle change simplement la casse et entoure de ||
 */
@Dependent
public class Modificateur implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * Traite la question en changeant la casse et en l'entourant de ||
     * @param roleApi Le rôle de l'API sélectionné
     * @param question La question posée par l'utilisateur
     * @return La réponse traitée
     */
    public String traiter(String roleApi, String question) {
        if (question == null || question.isEmpty()) {
            return "";
        }

            // Inverser la casse (maiuscules -> minuscules et vice versa)
            String inverserCasse = inverserCasse(question);

            // Entourer avec ||
            return "|| " + inverserCasse + " ||";

    }


    public String traitement_michel(String roleApi, String question) {
        if (question == null || question.isEmpty()) {
            return "";
        }
        else {
            // Inverser la casse (maiuscules -> minuscules et vice versa)
            String inverserCasse = inverserCasse(question);

            // Entourer avec ||
            return "|| " + inverserCasse + " ||";
        }
    }




    /**
     * Inverse la casse des caractères
        * Exemple : "Bonjour 123" devient "bONJOUR 123".
     * @param texte Le texte à inverser
     * @return Le texte avec la casse inversée
     */
    private String inverserCasse(String texte) {
        StringBuilder resultat = new StringBuilder();
        for (char c : texte.toCharArray()) {
            if (Character.isUpperCase(c)) {
                resultat.append(Character.toLowerCase(c));
            } else if (Character.isLowerCase(c)) {
                resultat.append(Character.toUpperCase(c));
            } else {
                resultat.append(c);
            }
        }
        return resultat.toString();
    }


    /**
     * Modificateur de question.
     * @param question La question à modifier.
     * @param roleSysteme Le rôle système à utiliser pour la modification de la question.
     * @return La question modifiée : le rôle système en majuscule au début de la question, s'il n'est pas null,
     * suivi d'un saut de ligne,
     * puis la question en minuscule, le tout entouré de "||".
     */
    public String modifier(String question, String roleSysteme) {
        String resultat = "||";
        if (roleSysteme != null) {
            // Ajouter le rôle système en majuscule au début du résultat, suivi d'un saut de ligne.
            resultat += roleSysteme.toUpperCase(Locale.FRENCH) + "\n";
        }
        resultat += question.toLowerCase(Locale.FRENCH) + "||";
        return resultat;
    }

    /**
     * Traitement bonus personnalisé fait côté serveur.
     *
     * @param question La question de l'utilisateur.
     * @param roleSysteme Le rôle système éventuel (affiché seulement au 1er message).
     * @return Une réponse synthétique avec statistiques et version triée des mots.
     */
    public String modifier_bonus(String question, String roleSysteme) {
        if (question == null || question.isBlank()) {
            return "";
        }

        String texte = question.trim();
        String[] mots = Arrays.stream(texte.split("\\s+"))
                .filter(mot -> !mot.isBlank())
                .toArray(String[]::new);

        String[] motsTries = Arrays.copyOf(mots, mots.length);
        Arrays.sort(motsTries, Comparator.comparingInt(String::length).reversed());

        long nbVoyelles = compterVoyelles(texte);
        long nbChiffres = texte.chars().filter(Character::isDigit).count();

        StringBuilder resultat = new StringBuilder();
        if (roleSysteme != null && !roleSysteme.isBlank()) {
            resultat.append("Role actif : ")
                    .append(roleSysteme.toUpperCase(Locale.FRENCH))
                    .append("\n");
        }
        resultat.append("Texte recu : ").append(texte).append("\n")
                .append("Nombre de mots : ").append(mots.length).append("\n")
                .append("Nombre de voyelles : ").append(nbVoyelles).append("\n")
                .append("Nombre de chiffres : ").append(nbChiffres).append("\n")
                .append("Mots tries (du plus long au plus court) : ")
                .append(String.join(" | ", motsTries));

        return resultat.toString();
    }


        public String modifier_michel(String question, String roleSystemePourModification) {
                return traitement_michel(roleSystemePourModification, question);
    }

    private long compterVoyelles(String texte) {
        String sansAccents = Normalizer.normalize(texte, Normalizer.Form.NFD)
                .replaceAll("\\p{M}+", "")
                .toLowerCase(Locale.FRENCH);

        return sansAccents.chars()
                .filter(c -> "aeiouy".indexOf(c) >= 0)
                .count();
    }
}
