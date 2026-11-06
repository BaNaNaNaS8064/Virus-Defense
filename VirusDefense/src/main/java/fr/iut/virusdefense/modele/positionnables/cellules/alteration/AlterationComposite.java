package fr.iut.virusdefense.modele.positionnables.cellules.alteration;

import fr.iut.virusdefense.modele.positionnables.maladies.Maladie;

import java.util.ArrayList;
import java.util.List;

public class AlterationComposite extends Alteration{
    private List<Alteration> alterations;

    public AlterationComposite(List<Alteration> alterations) {
        super(calculeDurée(alterations));
        this.alterations = alterations;
    }

    private static int calculeDurée(List<Alteration> alterations){
        int max = 0;
        for(Alteration alteration : alterations){
            if (alteration.getDuree() > max){
                max = alteration.getDuree();
            }
        }
        return max;
    }

    @Override
    public void setMaladie(Maladie m) {
        super.setMaladie(m);
        for(Alteration alteration : alterations){
            alteration.setMaladie(m);
        }
    }

    @Override
    public void affecter(){
        for(Alteration alteration : alterations){
            if(!alteration.doitEtreRetiré()){
                alteration.agir();
            }
        }
    }

    @Override
    public Alteration copy() {
        List<Alteration> liste = new ArrayList<Alteration>();
        for(Alteration alteration : alterations){
            liste.add(alteration.copy());
        }
        Alteration temp = new AlterationComposite(liste);
        temp.setMaladie(this.getMaladie());
        return temp;
    }
}
