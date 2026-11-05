package fr.iut.virusdefense.modele.cellules;

import fr.iut.virusdefense.modele.Acteur;
import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.Positionnable;
import fr.iut.virusdefense.modele.cellules.gestionnaireAttaque.GestionnaireAttaque;
import fr.iut.virusdefense.modele.cellules.reconnaissance.Reconnaissance;

public abstract class Cellule extends Positionnable implements Acteur {
    private Reconnaissance reconnaissance ;
    private GestionnaireAttaque gestionnaireAttaque;

    private int frequenceAttaque;
    private int delai;

    private int niveau;
    private final int cout;

    public Cellule(int ligne, int colonne, int frequenceAttaque, int cout){
        super(ligne, colonne);

        this.frequenceAttaque = frequenceAttaque;
        this.delai = frequenceAttaque;

        this.niveau = 1;

        this.cout = cout;
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
        return cout;
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
        return switch (niveau){
            case 1 -> coutNiveau2();
            case 2 -> coutNiveau3();
            default -> -1;
        };
    }

    public abstract String getNom();

    public abstract int coutNiveau2();

    public abstract int coutNiveau3();

    public boolean resteAmeliorations(){
        return niveau <= 2;
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
            if (!reconnaissance.valide())
                reconnaissance.actualiser();

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
