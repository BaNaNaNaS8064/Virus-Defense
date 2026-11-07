package fr.iut.virusdefense.modele.positionnables.cellules;

import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.acteurs.Acteur;
import fr.iut.virusdefense.modele.positionnables.Positionnable;
import fr.iut.virusdefense.modele.positionnables.cellules.gestionnaireAttaque.GestionnaireAttaque;
import fr.iut.virusdefense.modele.positionnables.cellules.reconnaissance.Reconnaissance;
import java.util.ArrayList;
import java.util.List;

public abstract class Cellule extends Positionnable implements Acteur {
    private Reconnaissance reconnaissance ;
    private GestionnaireAttaque gestionnaireAttaque;
    private int frequenceAttaque;
    private int delai;

    private int niveau;
    private final int[] couts;

    private final List <Amelioration> ameliorations;

    public Cellule(int ligne, int colonne, int frequenceAttaque, int[] couts) {
        super(ligne, colonne);

        this.frequenceAttaque = frequenceAttaque;
        this.delai = frequenceAttaque;

        this.niveau = 1;

        this.couts = couts;

        this.ameliorations = new ArrayList<>();
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

    protected void ajouterAmelioration(Amelioration amelioration){
        this.ameliorations.add(amelioration);
    }

    public boolean resteAmeliorations(){
        return niveau < couts.length && niveau <= ameliorations.size();
    }

    public void niveauSuperieur(){
        if (resteAmeliorations() && Environnement.getUniqueInstance().getJoueur().getPc() >= getCoutAmelioration()) {
            Environnement.getUniqueInstance().getJoueur().retirerPc(getCoutAmelioration());
            ameliorations.get(niveau - 1).appliquer();
            niveau++;
        }
    }

    @Override
    public void agir(){
        if (--delai <= 0){
            if(!reconnaissance.valide())
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
