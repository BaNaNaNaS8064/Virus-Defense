package fr.iut.virusdefense.modele.positionnables.cellules.reconnaissance;

import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.positionnables.cellules.reconnaissance.priorite.CiblageProche;
import fr.iut.virusdefense.modele.positionnables.maladies.Maladie;

import java.util.ArrayList;
import java.util.List;

public class ReconnaissanceRicochet extends Reconnaissance{
    private int nbRicochets;
    private Reconnaissance reconnaissanceBasique;

    public ReconnaissanceRicochet(Reconnaissance reconnaissanceBasique, int nbRicochets) {
        super(reconnaissanceBasique.getCoordonees(), reconnaissanceBasique.getPortee(), 1, new CiblageProche(reconnaissanceBasique.getCoordonees()));
        this.reconnaissanceBasique = reconnaissanceBasique;
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
        return reconnaissanceBasique.estValide(m);
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
    public void actualiser() {
        reconnaissanceBasique.actualiser();
        getCibles().clear();
        if(reconnaissanceBasique.aAuMoinsUneCible()){
            getCibles().add(reconnaissanceBasique.getCibles().get(0));

            double posLigne = getLigne();
            double posColonne = getColonne();
            ArrayList<Maladie> candidats = new ArrayList<>();
            boolean resteCandidats = true;
            Maladie maladie;

            setLigne(getCibles().get(0).getLigne());
            setColonne(getCibles().get(0).getColonne());

            while(!aAssezDeRicochets() && resteCandidats){
                candidats.clear();

                for(Maladie m : Environnement.getUniqueInstance().getGestionnaireActeur().getMaladies()){
                    if(ricochetValide(m)){
                        candidats.add(m);
                    }
                }

                if(candidats.isEmpty()){
                    resteCandidats = false;
                }
                else{
                    maladie = reconnaissanceBasique.getCiblage().selectionnerCibles(candidats, 1).get(0);
                    getCibles().add(maladie);
                    setLigne(maladie.getLigne());
                    setColonne(maladie.getColonne());
                }
            }

            setLigne(posLigne);
            setColonne(posColonne);
        }
    }
}
