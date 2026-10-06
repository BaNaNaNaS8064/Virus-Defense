package fr.iut.virusdefense.modele;

public abstract class Identifiable {

    private static long dernierID = 0;
    private final String id;

    public Identifiable(){
        id = String.valueOf(++dernierID);
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null)
            return false;
        else if (!(o instanceof Identifiable))
            return false;
        else
            return ((Identifiable) o).getId().equals(id);
    }
}
