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
        if (m instanceof BacterieBanale)
            return BACTERIEBANALE;

        if (m instanceof Parasite)
            return PARASITE;

        if (m instanceof Virus)
            return VIRUS;

        if (m instanceof VirusComposé)
            return CodeMaladie.valueOf("VIRUSCOMPOSE_"+ ((VirusComposé) m).getStade());

        if (m instanceof PetitChampignon)
            return PETITCHAMPIGNON;

        if (m instanceof GrandChampignon)
            return GRANDCHAMPIGNON;

        if (m instanceof Tumeur)
            return TUMEUR;

        return MALADIEINCONNUE;
    }
}
