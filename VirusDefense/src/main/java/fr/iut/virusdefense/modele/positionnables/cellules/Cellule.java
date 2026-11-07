package fr.iut.virusdefense.modele.positionnables.cellules;

import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.acteurs.Acteur;
import fr.iut.virusdefense.modele.positionnables.Positionnable;
import fr.iut.virusdefense.modele.positionnables.cellules.gestionnaireAttaque.GestionnaireAttaque;
import fr.iut.virusdefense.modele.positionnables.cellules.reconnaissance.Reconnaissance;

public abstract class Cellule extends Positionnable implements Acteur {
    private Reconnaissance reconnaissance ;
    private GestionnaireAttaque gestionnaireAttaque;
    private int[] coutsAmeliorations;

    private int frequenceAttaque;
    private int delai;

    private int niveau;
    private final int[] couts;

    public Cellule(int ligne, int colonne, int frequenceAttaque, int[] couts) {
        super(ligne, colonne);

        this.frequenceAttaque = frequenceAttaque;
        this.delai = frequenceAttaque;

        this.niveau = 1;

        this.couts = couts;
    }

    public abstract void initRec();

    public abstract void initGestionnaireAttaque();

    public Reconnaissance getReconnaissance(){
        return reconnaissance;
    }

    public void setReconnaissance(Reconnaissance reconnaissance){
        this.reconnaissance = reconnaissance;
    }

    public void setGestionnaireAttaque(GestionnaireAttaque gestionnaireAttaque){
        this.gestionnaireAttaque = gestionnaireAttaque;
    }

    public int getCout() {
        return couts[0];
    }

    public int getNiveau() {
        return niveau;
    }

    public int getFrequenceAttaque() {
        return frequenceAttaque;
    }

    public GestionnaireAttaque getAttaque() {
        return gestionnaireAttaque;
    }

    public void setFrequenceAttaque(int frequenceAttaque) {
        this.frequenceAttaque = frequenceAttaque;
    }

    public int getCoutAmelioration() {
        if(resteAmeliorations()){
            return couts[niveau];
        }
        return -1;
    }

    public abstract String getNom();

    public boolean resteAmeliorations(){
        return niveau < couts.length;
    }

    public void niveauSuperieur(){
        if (resteAmeliorations() && Environnement.getUniqueInstance().getJoueur().getPc() >= getCoutAmelioration()) {
            Environnement.getUniqueInstance().getJoueur().retirerPc(getCoutAmelioration());
            switch (++niveau){
                case 2:
                    ameliorerAuNiveau2();
                    break;
                case 3:
                    ameliorerAuNiveau3();
                    break;
            }
        }
    }

    public abstract void ameliorerAuNiveau2();

    public abstract void ameliorerAuNiveau3();

    @Override
    public void agir(){
        if (--delai <= 0){
            if (!reconnaissance.valide()) {
                reconnaissance.actualiser();
            }

            if (reconnaissance.aAuMoinsUneCible()) {
                gestionnaireAttaque.attaqueCibles();
                delai = frequenceAttaque;
            }
            else {
                delai = frequenceAttaque / 10;
            }
        }
    }
}
