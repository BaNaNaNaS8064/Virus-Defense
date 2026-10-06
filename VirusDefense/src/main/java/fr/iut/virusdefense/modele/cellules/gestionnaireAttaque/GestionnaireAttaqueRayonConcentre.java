package fr.iut.virusdefense.modele.cellules.gestionnaireAttaque;

import fr.iut.virusdefense.modele.Coordonees;
import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.maladies.Maladie;

import java.util.ArrayList;

public class GestionnaireAttaqueRayonConcentre extends GestionnaireAttaqueRayon {
    private ArrayList<Maladie> ciblesTourPrecedent;
    private ArrayList<Integer> dureeCiblageTourPrecedent;
    private double delaiAugmentation;

    public GestionnaireAttaqueRayonConcentre(Environnement environnement, Coordonees coordonees, double degats, double delaiAugmentation, ArrayList<Maladie> cibles) {
        super(environnement, coordonees, degats, cibles);
        ciblesTourPrecedent = new ArrayList<>();
        dureeCiblageTourPrecedent = new ArrayList<>();
        this.delaiAugmentation = delaiAugmentation;
    }

    public double getDelaiAugmentation() {
        return delaiAugmentation;
    }

    public void setDelaiAugmentation(double delaiAugmentation) {
        this.delaiAugmentation = delaiAugmentation;
    }

    @Override
    public final void attaqueCibles() {
        ArrayList<Integer> dureeCiblageTourActuel = new ArrayList<>();
        Maladie m;

        for (int i=0; i<getCibles().size(); i++){
            m = getCibles().get(i);
            if (ciblesTourPrecedent.contains(m))
                dureeCiblageTourActuel.add(dureeCiblageTourPrecedent.get(ciblesTourPrecedent.indexOf(m)) + 1);
            else
                dureeCiblageTourActuel.add(0);

            attaque(m, calculerDegats(dureeCiblageTourActuel.get(i)));
        }

        ciblesTourPrecedent = (ArrayList<Maladie>) getCibles().clone();
        dureeCiblageTourPrecedent = dureeCiblageTourActuel;
    }

    private double calculerDegats(int dureeCiblage){
        if (dureeCiblage < delaiAugmentation)
            return getDegats();
        else if (dureeCiblage < delaiAugmentation * 2)
            return getDegats() * 3;
        else
            return getDegats() * 9;
    }
}
