import java.util.Scanner;

public class TicTacToe {
    private final char[] feld = {'1','2','3','4','5','6','7','8','9'};
    private int aktuellerSpieler = 1;
    private final Scanner scanner = new Scanner(System.in);

    public void start() {
        boolean spielLaeuft = true;

        while (spielLaeuft) {
            zeichneSpielfeld();
            int auswahl = feldAuswaehlen();
            feld[auswahl - 1] = getZeichen();

            if (hatGewonnen()) {
                zeichneSpielfeld();
                System.out.println("Spieler " + aktuellerSpieler + " hat gewonnen!");
                spielLaeuft = false;
            } else if (istUnentschieden()) {
                zeichneSpielfeld();
                System.out.println("Unentschieden!");
                spielLaeuft = false;
            } else {
                spielerWechseln();
            }
        }
    }

    private void zeichneSpielfeld() {
        System.out.println();
        System.out.println(" " + feld[0] + " | " + feld[1] + " | " + feld[2]);
        System.out.println("---+---+---");
        System.out.println(" " + feld[3] + " | " + feld[4] + " | " + feld[5]);
        System.out.println("---+---+---");
        System.out.println(" " + feld[6] + " | " + feld[7] + " | " + feld[8]);
        System.out.println();
    }

    private int feldAuswaehlen() {
        int auswahl;

        while (true) {
            System.out.print("Spieler " + aktuellerSpieler + ": Feld auswählen! ");

            if (!scanner.hasNextInt()) {
                System.out.println("Bitte eine Zahl von 1 bis 9 eingeben.");
                scanner.next();
                continue;
            }

            auswahl = scanner.nextInt();

            if (auswahl < 1 || auswahl > 9) {
                System.out.println("Ungültige Zahl. Wähle ein Feld von 1 bis 9.");
                continue;
            }

            if (feld[auswahl - 1] == 'X' || feld[auswahl - 1] == 'O') {
                System.out.println("Dieses Feld ist schon belegt.");
                continue;
            }

            return auswahl;
        }
    }

    private char getZeichen() {
        if (aktuellerSpieler == 1) {
            return 'X';
        }
        return 'O';
    }

    private void spielerWechseln() {
        if (aktuellerSpieler == 1) {
            aktuellerSpieler = 2;
        } else {
            aktuellerSpieler = 1;
        }
    }

    private boolean hatGewonnen() {
        int[][] kombinationen = {
                {0, 1, 2}, // obere Reihe
                {3, 4, 5}, // mittlere Reihe
                {6, 7, 8}, // untere Reihe
                {0, 3, 6}, // linke Spalte
                {1, 4, 7}, // mittlere Spalte
                {2, 5, 8}, // rechte Spalte
                {0, 4, 8}, // Diagonale
                {2, 4, 6}  // Diagonale
        };

        char zeichen = getZeichen();

        for (int[] kombination : kombinationen) {
            if (feld[kombination[0]] == zeichen &&
                feld[kombination[1]] == zeichen &&
                feld[kombination[2]] == zeichen) {
                return true;
            }
        }

        return false;
    }

    private boolean istUnentschieden() {
        for (char zeichen : feld) {
            if (zeichen != 'X' && zeichen != 'O') {
                return false;
            }
        }
        return true;
    }
}
