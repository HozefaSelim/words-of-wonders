package finalprojesi;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;

import javax.swing.*;

public class main extends Canvas implements MouseListener {

    int x1top, y1top;
    int x2sol, x2sag, y2;
    int x3sol, x3sag, y3;
    int sx, sy;
    int ex, ey;
    JButton kontrol, bittirOyun;
    String kelime = "";
    boolean yanit;
    JMenuBar mb;
    JMenu yenioyun;
    JMenuItem yeni, bitir;
    baslama m;
    int x, y;
    JFrame f = new JFrame("Worlds Of Wonders");
    JPanel p = new JPanel();

    public main() throws FileNotFoundException {
        if (kelimeler.j == 0) { // bu sart her kontrol yapinca tekrar harfleri değiştirmez sağlanır 
            new kelimeler();
            kelimeler.j++;
        }
        olusturmak();
        kelimeKontrol();
        yenioyun();
        oyunBitir();
        f.add(kontrol);
        f.add(this);
        f.setSize(600, 700);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);

        addMouseListener(this);
        x1top = 275;
        y1top = 100;
        x2sol = 175;
        x2sag = 375;
        y2 = 200;
        x3sol = x2sol;
        x3sag = x2sag;
        y3 = 350;
        sx = 0;
        sy = 0;
        ex = 0;
        ey = 0;
    }

    @Override
    public void paint(Graphics g) {

        setBackground(new Color(235, 225, 216));
        Font font = new Font("Stencil", Font.BOLD, 30);
        g.setFont(font);
        g.setColor(new Color(165, 131, 97));
        g.drawString("Words Of Wonders", 150, 50);
        g.fillOval(x1top, y1top, 50, 50); // en ust
        g.fillOval(x2sol, y2, 50, 50); // 1. satir solda
        g.fillOval(x2sag, y2, 50, 50); // 1. satir sagda
        g.fillOval(x2sol, y3, 50, 50); // 2. satir solada
        g.fillOval(x2sag, y3, 50, 50); // 2. satir sagda

        Font font2 = new Font("Arial", 5, 30);
        g.setFont(font2);
        g.setColor(Color.white);
        g.drawString(Character.toString(kelimeler.kar[0]), x1top + 15, y1top + 35);
        g.drawString(Character.toString(kelimeler.kar[1]), x2sol + 15, y2 + 35);
        g.drawString(Character.toString(kelimeler.kar[2]), x2sag + 15, y2 + 35);
        g.drawString(Character.toString(kelimeler.kar[3]), x3sol + 15, y3 + 35);
        g.drawString(Character.toString(kelimeler.kar[4]), x3sag + 15, y3 + 35);

        g.setColor(Color.black);
        g.drawLine(sx, sy, ex, ey);

    }

    public void update(Graphics g) {
        g.drawLine(sx, sy, ex, ey);

    }

    public static void main(String[] args) throws FileNotFoundException {
        new main();

    }

    public void olusturmak() { // jframe elemanlari olusturmak metodu
        p.setBackground(Color.BLACK);
        f.add(p);
        mb = new JMenuBar();
        yenioyun = new JMenu("Yeni Oyun");
        yeni = new JMenuItem("Yeni");
        yenioyun.add(yeni);
        mb.add(yenioyun);
        JMenu Bitir = new JMenu("Bitir");
        bitir = new JMenuItem("Oyun Bitir");
        Bitir.add(bitir);
        mb.add(Bitir);
        f.setJMenuBar(mb);
        kontrol = new JButton("KONTROL");
        bittirOyun = new JButton("BITTIR OYUN");
        yanit = false;
        kontrol.setBounds(225, 500, 150, 100);
        kontrol.setFont(new Font("Arial", Font.BOLD, 20) {
        });
    }

    public void kelimeKontrol() { // kelimeler dogur olup olmadigini kontrol yapan metodu 
        kontrol.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                x = ex;
                y = ey;
                if (x > x1top && x < x1top + 50 && y > y1top && y < y1top + 50) {
                    kelime += kelimeler.kar[0];
                } else if (x > x2sol && x < x2sol + 50 && y > y2 && y < y2 + 50) {
                    kelime += kelimeler.kar[1];
                } else if (x > x2sag && x < x2sag + 50 && y > y2 && y < y2 + 50) {
                    kelime += kelimeler.kar[2];
                } else if (x > x3sol && x < x3sol + 50 && y > y3 && y < y3 + 50) {
                    kelime += kelimeler.kar[3];
                } else if (x > x3sag && x < x3sag + 50 && y > y3 && y < y3 + 50) {
                    kelime += kelimeler.kar[4];
                }
                for (int i = 0; i < kelimeler.str.length; i++) {
                    if (kelimeler.str[i].equals(kelime)) {
                        yanit = true;
                        if (baslama.dogrukelimeler.contains(kelime)) {
                            JOptionPane.showMessageDialog(null, kelime + " Önceden Bulundu");
                            f.dispose();
                            try {
                                new main();
                            } catch (FileNotFoundException ex) {
                            }
                        }
                    }
                }
                if (yanit == true && kelime.length() != 0 && (!(baslama.dogrukelimeler.contains(kelime)))) {
                    baslama.dogrukelimeler.add(kelime);
                    baslama.puan += kelime.length();
                    JOptionPane.showMessageDialog(null, kelime + "  Doğru Cevap");
                    f.dispose();
                    try {
                        new main();
                    } catch (FileNotFoundException ex) {
                    }

                }
                if (yanit == false) {
                    JOptionPane.showMessageDialog(null, "Yanlış Cevap");
                    f.dispose();
                    try {
                        new main();
                    } catch (FileNotFoundException ex) {
                    }
                }

            }
        });
    }

    public void yenioyun() { // kullanci istedigi zaman yeniden yeni oyun acabilir metodu 
        yeni.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == yeni) {
                    JOptionPane.showMessageDialog(null, baslama.kullanciAdi + "  puanınız : " + baslama.puan);
                    f.dispose();
                    m = new baslama();
                    m.show();
                }

            }

        });
    }

    public void oyunBitir() { // Kullanıcı başka kelimeler bulamıyorsa metodu 
        bitir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == bitir) {
                    JOptionPane.showMessageDialog(null, baslama.kullanciAdi + "  puanınız : " + baslama.puan);
                }
            }
        });
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if ((e.getX() >= x1top && e.getX() < x1top + 50 && e.getY() >= y1top && e.getY() < y1top + 50)
                || (e.getX() >= x2sol && e.getX() < x2sol + 50 && e.getY() >= y2 && e.getY() < y2 + 50)
                || (e.getX() >= x2sag && e.getX() < x2sag + 50 && e.getY() >= y2 && e.getY() < y2 + 50)
                || (e.getX() >= x3sol && e.getX() < x3sol + 50 && e.getY() >= y3 && e.getY() < y3 + 50)
                || (e.getX() >= x3sag && e.getX() < x3sag + 50 && e.getY() >= y3 && e.getY() < y3 + 50)) {

            sx = e.getX();
            sy = e.getY();

            if (sx > x1top && sx < x1top + 50 && sy > y1top && sy < y1top + 50) {
                kelime += kelimeler.kar[0];
            } else if (sx > x2sol && sx < x2sol + 50 && sy > y2 && sy < y2 + 50) {
                kelime += kelimeler.kar[1];
            } else if (sx > x2sag && sx < x2sag + 50 && sy > y2 && sy < y2 + 50) {
                kelime += kelimeler.kar[2];
            } else if (sx > x3sol && sx < x3sol + 50 && sy > y3 && sy < y3 + 50) {
                kelime += kelimeler.kar[3];
            } else if (sx > x3sag && sx < x3sag + 50 && sy > y3 && sy < y3 + 50) {
                kelime += kelimeler.kar[4];
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e
    ) {
        if ((e.getX() >= x1top && e.getX() < x1top + 50 && e.getY() >= y1top && e.getY() < y1top + 50)
                || (e.getX() >= x2sol && e.getX() < x2sol + 50 && e.getY() >= y2 && e.getY() < y2 + 50)
                || (e.getX() >= x2sag && e.getX() < x2sag + 50 && e.getY() >= y2 && e.getY() < y2 + 50)
                || (e.getX() >= x3sol && e.getX() < x3sol + 50 && e.getY() >= y3 && e.getY() < y3 + 50)
                || (e.getX() >= x3sag && e.getX() < x3sag + 50 && e.getY() >= y3 && e.getY() < y3 + 50)) {
            ex = e.getX();
            ey = e.getY();
            repaint();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e
    ) {
    }

    @Override
    public void mouseExited(MouseEvent e
    ) {
    }

}
