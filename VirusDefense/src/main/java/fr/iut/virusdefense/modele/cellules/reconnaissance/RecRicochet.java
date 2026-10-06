package fr.iut.virusdefense.modele.cellules.reconnaissance;

import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.maladies.Maladie;

import java.util.ArrayList;
import java.util.List;

public class RecRicochet extends Reconnaissance{
    private int nbRicochets;

    public RecRicochet(Environnement environnement, double ligne, double colonne, double portee, int nbRicochets){
        super(environnement, ligne, colonne, portee, 1);
        this.nbRicochets = nbRicochets;
    }

    public int getNbRicochets() {
        return nbRicochets;
    }

    public void setNbRicochets(int nbRicochets) {
        this.nbRicochets = nbRicochets;
    }

    @Override
    public boolean estValide(Maladie m) {
        return m.estVivant() && aPortee(m) && voit(m,true);
    }

    public boolean ricochetValide(Maladie m){
        return !getCibles().contains(m) && m.estVivant() && aPortee(m) && voit(m,false);
    }

    private boolean aAssezDeRicochets(){
        return getCibles().size() >= 1 + nbRicochets;
    }

    @Override
    public boolean valide() {
        return super.valide() && aAssezDeRicochets();
    }

    @Override
    public void reconnaissanceSecondaire() {
        if (aAuMoinsUneCible()){
            double posLigne = getLigne(), posColonne = getColonne();
            ArrayList<Maladie> candidats = new ArrayList<>();
            boolean resteCandidats = true;
            Maladie maladie;

            setLigne(getCibles().get(0).getLigne());
            setColonne(getCibles().get(0).getColonne());
            while (!aAssezDeRicochets() && resteCandidats){
                candidats.clear();

                for (Maladie m : getEnvironnement().getGestionnaireActeur().getMaladies()) {
                    if (ricochetValide(m)) {
                        candidats.add(m);
                    }
                }

                if (candidats.isEmpty()){
                    resteCandidats = false;
                }
                else {
                    maladie = maladiePlusProche(candidats);

                    getCibles().add(maladie);
                    setLigne(maladie.getLigne());
                    setColonne(maladie.getColonne());
                }
            }

            setLigne(posLigne);
            setColonne(posColonne);
        }
    }

    private Maladie maladiePlusProche(List<Maladie> candidats){
        double distanceMin;
        double distance;
        int indMaladiePlusProche;

        indMaladiePlusProche = 0;
        distanceMin = candidats.get(0).distanceEuclidienne(getLigne(), getColonne());

        for (int i = 1; i < candidats.size(); i++){
            distance = candidats.get(i).distanceEuclidienne(getLigne(), getColonne());
            if (distance < distanceMin){
                indMaladiePlusProche = i;
                distanceMin = distance;
            }
        }

        return candidats.get(indMaladiePlusProche);
    }

}
