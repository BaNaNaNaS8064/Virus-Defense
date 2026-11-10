package fr.iut.virusdefense.modele.enums;

import fr.iut.virusdefense.modele.positionnables.maladies.Maladie;
import fr.iut.virusdefense.modele.positionnables.maladies.sousclasses.*;

public enum CodeMaladie {
    MALADIEINCONNUE,
    BACTERIEBANALE,
    PARASITE,
    VIRUS,
    VIRUSCOMPOSE_1,
    PETITCHAMPIGNON,
    GRANDCHAMPIGNON,
    TUMEUR,
    VIRUSCOMPOSE_2,
    VIRUSCOMPOSE_3;

    public static CodeMaladie codeDe(Maladie m) {
        if (m == null) {
            return CodeMaladie.MALADIEINCONNUE;
        }

        return m.getCodeMaladie();
    }
}
