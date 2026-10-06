package fr.iut.virusdefense.modele.maladies;

import fr.iut.virusdefense.modele.Environnement;

public class GrandChampignon extends Maladie{
    private int delaiInvocation;
    public GrandChampignon(Environnement environnement, int ligne, int colonne){
        super(environnement, ligne, colonne, 1000, 0.015, 100);
        resetDelai();
    }

    @Override
    public void capaciteActive() {
        if(--delaiInvocation <= 0){
            resetDelai();
            for (int i = 0; i < (int)((Math.random()*3) + 2); i++)
                getEnvironnement().ajouterMaladie(new PetitChampignon(getEnvironnement(), (int)getLigne(), (int)getColonne()));
        }
    }

    private void resetDelai(){
        delaiInvocation = (int)(300 + Math.random()*120);
    }

    @Override
    public boolean peutSubirAlteration() {
        return true;
    }
}
