import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class EndFrame extends JFrame {
    private final int FRAME_WIDTH = 500;
    private final int FRAME_HEIGHT = 600;
    JPanel mainPanel = new JPanel(null);
    static JLabel titolo=new JLabel();
    static JLabel n2=new JLabel();
    static JLabel n3=new JLabel();
    static JLabel n1=new JLabel();

    static JScrollPane ilRestoScrollable;

    static {
        try {
            JTextArea testo= new JTextArea(1,1);
            testo.setText(readWritePlayers());
            testo.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 10));
            testo.setForeground (Color.WHITE);
            testo.setBackground(Color.BLACK);
            ilRestoScrollable = new JScrollPane(testo);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public EndFrame() throws IOException {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(FRAME_WIDTH,FRAME_HEIGHT);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        mainPanel.setBounds(0,0,500,600);

        titolo.setBounds(0,0,500,50);
        titolo.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 25));
        titolo.setForeground (Color.RED);
        titolo.setText("LEADERBOARD");
        titolo.setHorizontalAlignment(0);

        n1.setBounds(0,40,500,30);
        n2.setBounds(0,70,500,30);
        n3.setBounds(0,100,500,30);


        n1.setHorizontalAlignment(0);
        n2.setHorizontalAlignment(0);
        n3.setHorizontalAlignment(0);



        n1.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 20));
        n1.setForeground (Color.CYAN);
        n2.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 15));
        n2.setForeground (Color.GREEN);
        n3.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 13));
        n3.setForeground (Color.YELLOW);

        mainPanel.setBackground(Color.BLACK);
        ilRestoScrollable.setBounds(0,130,500,450);

        mainPanel.add(n1);
        mainPanel.add(n2);
        mainPanel.add(n3);
        mainPanel.add(ilRestoScrollable);

        this.add(titolo);
        this.add(mainPanel);
        this.setVisible(true);
    }

    public static String readWritePlayers() throws IOException {
        String line = null;

        ArrayList<Giocatore> players = new ArrayList<Giocatore>();

        BufferedReader readLeaderboard = new BufferedReader(new FileReader("scoreboard.txt"));
        while ((line = readLeaderboard.readLine()) != null) {
            System.out.println(line.substring(0,line.indexOf(":")));
            System.out.println(line.substring(line.indexOf(":")+1,line.length()));
            players.add(new Giocatore(line.substring(0,line.indexOf(":")),Integer.parseInt(line.substring(line.indexOf(":")+1,line.length()))));
        }
        readLeaderboard.close();

        System.out.println(players);


        /************************************************************************************************************/
        BufferedReader readerNome = new BufferedReader(new FileReader("nome.txt"));
        BufferedReader readerPunteggio = new BufferedReader(new FileReader("punteggio.txt"));
        Giocatore player = new Giocatore();

        while ((line = readerNome.readLine()) != null) {
            player.setNome(line);
        }
        while ((line = readerPunteggio.readLine()) != null) {
            player.setPunteggio(Integer.parseInt(line));
        }
        readerNome.close();
        readerPunteggio.close();
        System.out.println(player.toString());

        players.add(player);

        BufferedWriter write = new BufferedWriter(new FileWriter("scoreboard.txt"));
        for(Giocatore x : players){
            write.write(x.toString()+"\n");
        }
        write.close();
        System.out.println(players);

        //Bubble sort
        for (int i = 0; i < players.size() - 1; i++)
            for (int j = 0; j < players.size() - i - 1; j++)
                if (players.get(j).getPunteggio() < players.get(j+1).getPunteggio()) {
                    Giocatore temp = new Giocatore(players.get(j).getNome(),players.get(j).getPunteggio());

                    players.get(j).setNome(players.get(j+1).getNome());
                    players.get(j).setPunteggio(players.get(j+1).getPunteggio());

                    players.get(j+1).setNome(temp.getNome());
                    players.get(j+1).setPunteggio(temp.getPunteggio());
                }

        System.out.println(players);
        n1.setText("N1: "+players.get(0).toString());
        n2.setText("N2: "+players.get(1).toString());
        n3.setText("N3: "+players.get(2).toString());
        String s = "";
        for(int i=3;i<players.size();i++){
            s+=players.get(i).toString()+"\n";
        }
        System.out.println(s);
        return s;
    }
}
