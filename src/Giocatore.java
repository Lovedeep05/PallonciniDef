public class Giocatore {
    private String nome = "Giocatore";
    private int punteggio;

    public Giocatore(String nome, int punteggio) {
        this.nome = nome;
        this.punteggio = punteggio;
    }

    public Giocatore() {

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPunteggio() {
        return punteggio;
    }

    public void setPunteggio(int punteggio) {
        this.punteggio = punteggio;
    }

    @Override
    public String toString() {
        return nome + ':' +
                 punteggio ;
    }
}
