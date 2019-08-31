/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Final_Project;

/**
 *
 * @author Ripon
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class GUI_APP {

    class Vertex {

        public int label;
        public boolean wasVisited;

        public Vertex(int label) {
            this.label = label;
            wasVisited = false;
        }
    }

    static class DAFTAR {

        JFrame frame;
        JScrollPane scroll;
        JLabel pilihjumlahtitik, pilihanpo, pilihtitikasal, deskripsipo;
        JLabel judul, deskripsipopo;
        JLabel deskripsi11, deskripsi12, deskripsi13, deskripsi14, deskripsi15, deskripsi16, deskripsi17;
        JLabel titik1, titik2, titik3, titik4, titik5, titik6, titik7, titik8;
        JLabel destinasi1, destinasi2, destinasi3, destinasi4, destinasi5, destinasi6, destinasi7, destinasi8;
        JLabel jarak1, jarak2, jarak3, jarak4, jarak5, jarak6, jarak7, jarak8;
        JLabel km1, km2, km3, km4, km5, km6, km7, km8;
        JLabel angkasatu, angkadua, angkatiga, angkaempat, angkalima, angkaenam, angkatujuh, angkadelapan;
        JLabel gambar1, gambar2, gambar3, gambar4, gambar5, gambar6, gambar7;
        JLabel thedeskripsi, carikode;
        JLabel po1, po2, po3, po4, po5, po6, po7;
        JComboBox titikkota1, titikkota2, titikkota3, titikkota4, titikkota5, titikkota6, titikkota7, titikkota8;
        JComboBox destinasikota1, destinasikota2, destinasikota3, destinasikota4, destinasikota5, destinasikota6,
                destinasikota7, destinasikota8;
        JComboBox pilihtitik, pilihdestinasi;
        JComboBox semuapo;
        ButtonGroup jumlahtitik, titikasal;
        JTextArea daftarnya;
        JTextField row11, row12, row13, row21, row22, row23, row31, row32, row33, row41, row42, row43,
                row51, row52, row53, row61, row62, row63, row71, row72, row73, row81, row82, row83;
        JTextField pencariantitik, pencariandestinasi;
        JButton cari, carapenggunaan, reset;
        String[] daftarkota = {"Pilih Titik ...", "Surabaya", "Semarang", "Jogjakarta", "Jakarta", "Solo", "Bandung", "Malang", "Bekasi"};
        String[] daftarpo = {"Daftar PO ...", "PO Surya", "PO Subur", "PO Jaya", "PO Laju", "PO Rejeki", "PO Aman", "PO Buroq"};
        JRadioButton empat, lima, enam, tujuh, delapan;
        JRadioButton carititik0, carititik1, carititik2, carititik3, carititik4, carititik5, carititik6, carititik7;
        Font font;

        int vertices;
        int matrix[][];
        Vertex vertexList[];

        public DAFTAR(int vertex) {
            this.vertices = vertex;
            matrix = new int[vertex][vertex];
        }

        public void addEdge(int source, int destination, int weight) {
            matrix[source][destination] = weight;
            matrix[destination][source] = weight;
        }

        int getMinimumVertex(boolean[] mst, int[] key) {
            int minKey = Integer.MAX_VALUE;
            int vertex = -1;
            for (int i = 0; i < vertices; i++) {
                if (mst[i] == false && minKey > key[i]) {
                    minKey = key[i];
                    vertex = i;
                }
            }
            return vertex;
        }

        public void dijkstra_GetMinDistances(int sourceVertex) {
            boolean[] spt = new boolean[vertices];
            int[] distance = new int[vertices];
            int INFINITY = Integer.MAX_VALUE;
            for (int i = 0; i < vertices; i++) {
                distance[i] = INFINITY;
            }
            distance[sourceVertex] = 0;
            for (int i = 0; i < vertices; i++) {
                int vertex_U = getMinimumVertex(spt, distance);
                spt[vertex_U] = true;
                for (int vertex_V = 0; vertex_V < vertices; vertex_V++) {
                    if (matrix[vertex_U][vertex_V] > 0) {
                        if (spt[vertex_V] == false && matrix[vertex_U][vertex_V] != INFINITY) {
                            int newKey = matrix[vertex_U][vertex_V] + distance[vertex_U];
                            if (newKey < distance[vertex_V]) {
                                distance[vertex_V] = newKey;
                            }
                        }
                    }
                }
            }
            printDijkstra(sourceVertex, distance);
        }

        public void printDijkstra(int sourceVertex, int[] key) {
            int cost;
            frame = new JFrame("Penghitungan Biaya PO. Travel Berdasarkan Jarak Terdekat");
            frame.setSize(640, 680);
            frame.getContentPane().setBackground(Color.WHITE);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setLayout(null);
            frame.setResizable(false);
            judul = new JLabel("Deskripsi Biaya");
            judul.setBounds(30, 10, 300, 30);
            judul.setBackground(Color.BLACK);
            judul.setFont(new Font("Times New Roman", Font.BOLD, 23));
            carikode = new JLabel("Pencarian Kode Daerah");
            carikode.setBounds(240, 10, 250, 30);
            carikode.setBackground(Color.WHITE);
            carikode.setFont(new Font("Times New Roman", Font.BOLD, 23));
            String[] daftartitik = {"Pilih Titik ...", "Surabaya", "Semarang", "Jogjakarta", "Jakarta", "Solo", "Bandung", "Malang", "Bekasi"};
            String[] daftardestinasi = {"Pilih Destinasi ...", "Surabaya", "Semarang", "Jogjakarta", "Jakarta", "Solo", "Bandung", "Malang", "Bekasi"};
            pilihtitik = new JComboBox(daftartitik);
            pilihtitik.setBounds(240, 40, 230, 30);
            pilihtitik.setBackground(Color.WHITE);
            pilihtitik.setFont(new Font("Times New Roman", Font.BOLD, 15));
            pilihdestinasi = new JComboBox(daftardestinasi);
            pilihdestinasi.setBounds(240, 75, 230, 30);
            pilihdestinasi.setBackground(Color.WHITE);
            pilihdestinasi.setFont(new Font("Times New Roman", Font.BOLD, 15));
            pencariantitik = new JTextField("");
            pencariantitik.setBounds(475, 40, 130, 30);
            pencariantitik.setBackground(Color.WHITE);
            pencariantitik.setFont(new Font("Times New Roman", Font.BOLD, 15));
            pencariantitik.setEditable(false);
            pencariantitik.setHorizontalAlignment(SwingConstants.CENTER);
            pencariandestinasi = new JTextField("");
            pencariandestinasi.setBounds(475, 75, 130, 30);
            pencariandestinasi.setBackground(Color.WHITE);
            pencariandestinasi.setFont(new Font("Times New Roman", Font.BOLD, 15));
            pencariandestinasi.setEditable(false);
            pencariandestinasi.setHorizontalAlignment(SwingConstants.CENTER);
            po1 = new JLabel("PO Surya");
            po1.setBounds(240, 120, 250, 30);
            po1.setBackground(Color.WHITE);
            po1.setFont(new Font("Times New Roman", Font.BOLD, 23));
            po2 = new JLabel("PO Subur");
            po2.setBounds(240, 120, 250, 30);
            po2.setBackground(Color.WHITE);
            po2.setFont(new Font("Times New Roman", Font.BOLD, 23));
            po3 = new JLabel("PO Jaya");
            po3.setBounds(240, 120, 250, 30);
            po3.setBackground(Color.WHITE);
            po3.setFont(new Font("Times New Roman", Font.BOLD, 23));
            po4 = new JLabel("PO Laju");
            po4.setBounds(240, 120, 250, 30);
            po4.setBackground(Color.WHITE);
            po4.setFont(new Font("Times New Roman", Font.BOLD, 23));
            po5 = new JLabel("PO Rejeki");
            po5.setBounds(240, 120, 250, 30);
            po5.setBackground(Color.WHITE);
            po5.setFont(new Font("Times New Roman", Font.BOLD, 23));
            po6 = new JLabel("PO Aman");
            po6.setBounds(240, 120, 250, 30);
            po6.setBackground(Color.WHITE);
            po6.setFont(new Font("Times New Roman", Font.BOLD, 23));
            po7 = new JLabel("PO Buroq");
            po7.setBounds(240, 120, 250, 30);
            po7.setBackground(Color.WHITE);
            po7.setFont(new Font("Times New Roman", Font.BOLD, 23));
            daftarnya = new JTextArea();
            daftarnya.setBounds(30, 40, 170, 600);
            daftarnya.setBackground(Color.WHITE);
            daftarnya.setFont(new Font("Times New Roman", Font.BOLD, 14));
            gambar1 = new JLabel(new ImageIcon("C:\\Users\\Lenovo\\Documents\\NetBeansProjects\\MyGraph\\src\\Final_Project\\Image\\Bus\\Surya.jpg"));
            gambar1.setBounds(240, 170, 365, 310);
            gambar2 = new JLabel(new ImageIcon("C:\\Users\\Lenovo\\Documents\\NetBeansProjects\\MyGraph\\src\\Final_Project\\Image\\Bus\\Subur.jpg"));
            gambar2.setBounds(240, 170, 365, 310);
            gambar3 = new JLabel(new ImageIcon("C:\\Users\\Lenovo\\Documents\\NetBeansProjects\\MyGraph\\src\\Final_Project\\Image\\Bus\\Jaya.jpg"));
            gambar3.setBounds(240, 170, 365, 310);
            gambar4 = new JLabel(new ImageIcon("C:\\Users\\Lenovo\\Documents\\NetBeansProjects\\MyGraph\\src\\Final_Project\\Image\\Bus\\Laju.jpg"));
            gambar4.setBounds(240, 170, 365, 310);
            gambar5 = new JLabel(new ImageIcon("C:\\Users\\Lenovo\\Documents\\NetBeansProjects\\MyGraph\\src\\Final_Project\\Image\\Bus\\Rejeki.jpg"));
            gambar5.setBounds(240, 170, 365, 310);
            gambar6 = new JLabel(new ImageIcon("C:\\Users\\Lenovo\\Documents\\NetBeansProjects\\MyGraph\\src\\Final_Project\\Image\\Bus\\Aman.jpg"));
            gambar6.setBounds(240, 170, 365, 310);
            gambar7 = new JLabel(new ImageIcon("C:\\Users\\Lenovo\\Documents\\NetBeansProjects\\MyGraph\\src\\Final_Project\\Image\\Bus\\Buroq.jpg"));
            gambar7.setBounds(240, 170, 365, 310);
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (ClassNotFoundException | InstantiationException
                    | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            }
            StringBuilder sb = new StringBuilder(30);
            sb.append("<html>Asal PO<br>").
                    append("Biaya Pengantaran<br>").
                    append("Fasilitas<br>").
                    append("Penumpang Maksimum</html>");
            StringBuilder sb11 = new StringBuilder(30);
            sb11.append("<html>: Surabaya<br>").
                    append(": Rp 200/Km<br>").
                    append(": AC<br>").
                    append(": 50</html>");
            StringBuilder sb12 = new StringBuilder(30);
            sb12.append("<html>: Surabaya<br>").
                    append(": Rp 210/Km<br>").
                    append(": AC<br>").
                    append(": 55</html>");
            StringBuilder sb13 = new StringBuilder(30);
            sb13.append("<html>: Surabaya<br>").
                    append(": Rp 220/Km<br>").
                    append(": AC<br>").
                    append(": 60</html>");
            StringBuilder sb14 = new StringBuilder(30);
            sb14.append("<html>: Surabaya<br>").
                    append(": Rp 210/Km<br>").
                    append(": AC<br>").
                    append(": 55</html>");
            StringBuilder sb15 = new StringBuilder(30);
            sb15.append("<html>: Surabaya<br>").
                    append(": Rp 200/Km<br>").
                    append(": AC<br>").
                    append(": 50</html>");
            StringBuilder sb16 = new StringBuilder(30);
            sb16.append("<html>: Surabaya<br>").
                    append(": Rp 210/Km<br>").
                    append(": AC<br>").
                    append(": 55</html>");
            StringBuilder sb17 = new StringBuilder(30);
            sb17.append("<html>: Surabaya<br>").
                    append(": Rp 230/Km<br>").
                    append(": AC<br>").
                    append(": 65</html>");
            deskripsipopo = new JLabel(sb.toString());
            deskripsipopo.setBounds(240, 470, 200, 100);
            deskripsipopo.setBackground(Color.WHITE);
            deskripsipopo.setFont(new Font("Times New Roman", Font.BOLD, 15));
            deskripsi11 = new JLabel(sb11.toString());
            deskripsi11.setBounds(430, 470, 130, 100);
            deskripsi11.setBackground(Color.WHITE);
            deskripsi11.setFont(new Font("Times New Roman", Font.BOLD, 15));
            deskripsi12 = new JLabel(sb12.toString());
            deskripsi12.setBounds(430, 470, 130, 100);
            deskripsi12.setBackground(Color.WHITE);
            deskripsi12.setFont(new Font("Times New Roman", Font.BOLD, 15));
            deskripsi13 = new JLabel(sb13.toString());
            deskripsi13.setBounds(430, 470, 130, 100);
            deskripsi13.setBackground(Color.WHITE);
            deskripsi13.setFont(new Font("Times New Roman", Font.BOLD, 15));
            deskripsi14 = new JLabel(sb14.toString());
            deskripsi14.setBounds(430, 470, 130, 100);
            deskripsi14.setBackground(Color.WHITE);
            deskripsi14.setFont(new Font("Times New Roman", Font.BOLD, 15));
            deskripsi15 = new JLabel(sb15.toString());
            deskripsi15.setBounds(430, 470, 130, 100);
            deskripsi15.setBackground(Color.WHITE);
            deskripsi15.setFont(new Font("Times New Roman", Font.BOLD, 15));
            deskripsi16 = new JLabel(sb16.toString());
            deskripsi16.setBounds(430, 470, 130, 100);
            deskripsi16.setBackground(Color.WHITE);
            deskripsi16.setFont(new Font("Times New Roman", Font.BOLD, 15));
            deskripsi17 = new JLabel(sb17.toString());
            deskripsi17.setBounds(430, 470, 130, 100);
            deskripsi17.setBackground(Color.WHITE);
            deskripsi17.setFont(new Font("Times New Roman", Font.BOLD, 15));
            String[] daftarpo = {"Pilih PO ...", "PO Surya", "PO Subur", "PO Jaya", "PO Laju", "PO Rejeki", "PO Aman", "PO Buroq"};
            String n = (String) JOptionPane.showInputDialog(null, "Pilih PO untuk hasil kalkulasi",
                    "Pilih PO", JOptionPane.QUESTION_MESSAGE, null, daftarpo, daftarpo[0]);
            if (n == "PO Surya") {
                frame.validate();
                frame.repaint();
                frame.add(po1);
                frame.add(gambar1);
                frame.add(deskripsi11);
            } else if (n == "PO Subur") {
                frame.validate();
                frame.repaint();
                frame.add(po2);
                frame.add(gambar2);
                frame.add(deskripsi12);
            } else if (n == "PO Jaya") {
                frame.validate();
                frame.repaint();
                frame.add(po3);
                frame.add(gambar3);
                frame.add(deskripsi13);
            } else if (n == "PO Laju") {
                frame.validate();
                frame.repaint();
                frame.add(po4);
                frame.add(gambar4);
                frame.add(deskripsi14);
            } else if (n == "PO Rejeki") {
                frame.validate();
                frame.repaint();
                frame.add(po5);
                frame.add(gambar5);
                frame.add(deskripsi15);
            } else if (n == "PO Aman") {
                frame.validate();
                frame.repaint();
                frame.add(po6);
                frame.add(gambar6);
                frame.add(deskripsi16);
            } else if (n == "PO Buroq") {
                frame.validate();
                frame.repaint();
                frame.add(po7);
                frame.add(gambar7);
                frame.add(deskripsi17);
            }
            if (n == "PO Surya") {
                cost = 200;
                for (int i = 0; i < vertices; i++) {
                    if (sourceVertex == +i) {
                    } else if (sourceVertex != +i) {
                        daftarnya.append(
                                "Asal Daerah\t: " + sourceVertex
                                + " \nTujuan \t: " + +i
                                + " \nJarak \t: " + key[i] + " Km"
                                + " \nJumlah Biaya\t: Rp " + cost * key[i]
                                + "\n---------------------------------------\n"
                        );
                        frame.add(daftarnya);
                    }
                }
            } else if (n == "PO Subur") {
                cost = 210;
                for (int i = 0; i < vertices; i++) {
                    if (sourceVertex == +i) {
                    } else if (sourceVertex != +i) {
                        daftarnya.append(
                                "Asal Daerah\t: " + sourceVertex
                                + " \nTujuan \t: " + +i
                                + " \nJarak \t: " + key[i] + " Km"
                                + " \nJumlah Biaya\t: Rp " + cost * key[i]
                                + "\n---------------------------------------\n"
                        );
                        frame.add(daftarnya);
                    }
                }
            } else if (n == "PO Jaya") {
                cost = 220;
                for (int i = 0; i < vertices; i++) {
                    if (sourceVertex == +i) {
                    } else if (sourceVertex != +i) {
                        daftarnya.append(
                                "Asal Daerah\t: " + sourceVertex
                                + " \nTujuan \t: " + +i
                                + " \nJarak \t: " + key[i] + " Km"
                                + " \nJumlah Biaya\t: Rp " + cost * key[i]
                                + "\n---------------------------------------\n"
                        );
                        frame.add(daftarnya);
                    }
                }
            } else if (n == "PO Laju") {
                cost = 210;
                for (int i = 0; i < vertices; i++) {
                    if (sourceVertex == +i) {
                    } else if (sourceVertex != +i) {
                        daftarnya.append(
                                "Asal Daerah\t: " + sourceVertex
                                + " \nTujuan \t: " + +i
                                + " \nJarak \t: " + key[i] + " Km"
                                + " \nJumlah Biaya\t: Rp " + cost * key[i]
                                + "\n---------------------------------------\n"
                        );
                        frame.add(daftarnya);
                    }
                }
            } else if (n == "PO Rejeki") {
                cost = 200;
                for (int i = 0; i < vertices; i++) {
                    if (sourceVertex == +i) {
                    } else if (sourceVertex != +i) {
                        daftarnya.append(
                                "Asal Daerah\t: " + sourceVertex
                                + " \nTujuan \t: " + +i
                                + " \nJarak \t: " + key[i] + " Km"
                                + " \nJumlah Biaya\t: Rp " + cost * key[i]
                                + "\n---------------------------------------\n"
                        );
                        frame.add(daftarnya);
                    }
                }
            } else if (n == "PO Aman") {
                cost = 210;
                for (int i = 0; i < vertices; i++) {
                    if (sourceVertex == +i) {
                    } else if (sourceVertex != +i) {
                        daftarnya.append(
                                "Asal Daerah\t: " + sourceVertex
                                + " \nTujuan \t: " + +i
                                + " \nJarak \t: " + key[i] + " Km"
                                + " \nJumlah Biaya\t: Rp " + cost * key[i]
                                + "\n---------------------------------------\n"
                        );
                        frame.add(daftarnya);
                    }
                }
            } else if (n == "PO Buroq") {
                cost = 230;
                for (int i = 0; i < vertices; i++) {
                    if (sourceVertex == +i) {
                    } else if (sourceVertex != +i) {
                        daftarnya.append(
                                "Asal Daerah\t: " + sourceVertex
                                + " \nTujuan \t: " + +i
                                + " \nJarak \t: " + key[i] + " Km"
                                + " \nJumlah Biaya\t: Rp " + cost * key[i]
                                + "\n---------------------------------------\n"
                        );
                        frame.add(daftarnya);
                    }
                }
            }
            daftarnya.setEditable(false);
            frame.add(pencariantitik);
            frame.add(pencariandestinasi);
            frame.add(pilihtitik);
            frame.add(pilihdestinasi);
            frame.add(deskripsipopo);
            frame.add(carikode);
            frame.add(judul);
            frame.setVisible(true);
            pilihtitik.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    JComboBox comboBox = (JComboBox) event.getSource();
                    Object selected = comboBox.getSelectedItem();
                    if (selected.toString().equals("Surabaya")) {
                        pencariantitik.setText("0");
                    } else if (selected.toString().equals("Semarang")) {
                        pencariantitik.setText("1");
                    } else if (selected.toString().equals("Jogjakarta")) {
                        pencariantitik.setText("2");
                    } else if (selected.toString().equals("Jakarta")) {
                        pencariantitik.setText("3");
                    } else if (selected.toString().equals("Solo")) {
                        pencariantitik.setText("4");
                    } else if (selected.toString().equals("Bandung")) {
                        pencariantitik.setText("5");
                    } else if (selected.toString().equals("Malang")) {
                        pencariantitik.setText("6");
                    } else if (selected.toString().equals("Bekasi")) {
                        pencariantitik.setText("7");
                    }
                }
            });
            pilihdestinasi.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    JComboBox comboBox = (JComboBox) event.getSource();
                    Object selected = comboBox.getSelectedItem();
                    if (selected.toString().equals("Surabaya")) {
                        pencariandestinasi.setText("0");
                    } else if (selected.toString().equals("Semarang")) {
                        pencariandestinasi.setText("1");
                    } else if (selected.toString().equals("Jogjakarta")) {
                        pencariandestinasi.setText("2");
                    } else if (selected.toString().equals("Jakarta")) {
                        pencariandestinasi.setText("3");
                    } else if (selected.toString().equals("Solo")) {
                        pencariandestinasi.setText("4");
                    } else if (selected.toString().equals("Bandung")) {
                        pencariandestinasi.setText("5");
                    } else if (selected.toString().equals("Malang")) {
                        pencariandestinasi.setText("6");
                    } else if (selected.toString().equals("Bekasi")) {
                        pencariandestinasi.setText("7");
                    }
                }
            });
        }

        DAFTAR() {
            frame = new JFrame("Penghitungan Biaya Pengantaran PO Travel Berdasarkan Jarak Terdekat");
            frame.setSize(1000, 680);
            frame.getContentPane().setBackground(Color.WHITE);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setLayout(null);
            frame.setResizable(false);
            judul = new JLabel("Penghitungan Biaya PO Travel Berdasarkan Jarak Terdekat");
            judul.setBounds(180, 10, 630, 30);
            judul.setBackground(Color.WHITE);
            judul.setFont(new Font("Times New Roman", Font.BOLD, 24));
            pilihanpo = new JLabel("Lihat Deskripsi PO >>");
            pilihanpo.setBounds(615, 50, 200, 30);
            pilihanpo.setBackground(Color.WHITE);
            pilihanpo.setFont(new Font("Times New Roman", Font.BOLD, 15));
            semuapo = new JComboBox(daftarpo);
            semuapo.setBounds(615, 80, 360, 30);
            semuapo.setBackground(Color.WHITE);
            semuapo.setFont(new Font("Times New Roman", Font.BOLD, 15));
            deskripsipo = new JLabel("Rincian PO >>");
            deskripsipo.setBounds(615, 290, 360, 200);
            deskripsipo.setBackground(Color.WHITE);
            deskripsipo.setFont(new Font("Times New Roman", Font.BOLD, 15));
            pilihtitikasal = new JLabel("Titik Asal >>");
            pilihtitikasal.setBounds(20, 50, 130, 30);
            pilihtitikasal.setBackground(Color.WHITE);
            pilihtitikasal.setFont(new Font("Times New Roman", Font.BOLD, 15));
            titikasal = new javax.swing.ButtonGroup();
            carititik0 = new JRadioButton("0");
            carititik0.setBounds(120, 50, 50, 30);
            carititik0.setBackground(Color.WHITE);
            carititik0.setFont(new Font("Times New Roman", Font.BOLD, 15));
            titikasal.add(carititik0);
            carititik1 = new JRadioButton("1");
            carititik1.setBounds(182, 50, 50, 30);
            carititik1.setBackground(Color.WHITE);
            carititik1.setFont(new Font("Times New Roman", Font.BOLD, 15));
            titikasal.add(carititik1);
            carititik2 = new JRadioButton("2");
            carititik2.setBounds(245, 50, 50, 30);
            carititik2.setBackground(Color.WHITE);
            carititik2.setFont(new Font("Times New Roman", Font.BOLD, 15));
            titikasal.add(carititik2);
            carititik3 = new JRadioButton("3");
            carititik3.setBounds(307, 50, 50, 30);
            carititik3.setBackground(Color.WHITE);
            carititik3.setFont(new Font("Times New Roman", Font.BOLD, 15));
            titikasal.add(carititik3);
            carititik4 = new JRadioButton("4");
            carititik4.setBounds(370, 50, 50, 30);
            carititik4.setBackground(Color.WHITE);
            carititik4.setFont(new Font("Times New Roman", Font.BOLD, 15));
            carititik5 = new JRadioButton("5");
            carititik5.setBounds(432, 50, 50, 30);
            carititik5.setBackground(Color.WHITE);
            carititik5.setFont(new Font("Times New Roman", Font.BOLD, 15));
            titikasal.add(carititik5);
            carititik6 = new JRadioButton("6");
            carititik6.setBounds(495, 50, 50, 30);
            carititik6.setBackground(Color.WHITE);
            carititik6.setFont(new Font("Times New Roman", Font.BOLD, 15));
            titikasal.add(carititik6);
            carititik7 = new JRadioButton("7");
            carititik7.setBounds(557, 50, 50, 30);
            carititik7.setBackground(Color.WHITE);
            carititik7.setFont(new Font("Times New Roman", Font.BOLD, 15));
            titikasal.add(carititik7);
            pilihjumlahtitik = new JLabel("Jumlah Titik >");
            pilihjumlahtitik.setBounds(20, 80, 130, 30);
            pilihjumlahtitik.setBackground(Color.WHITE);
            pilihjumlahtitik.setFont(new Font("Times New Roman", Font.BOLD, 15));
            jumlahtitik = new javax.swing.ButtonGroup();
            empat = new JRadioButton("4");
            empat.setBounds(120, 80, 50, 30);
            empat.setBackground(Color.WHITE);
            empat.setFont(new Font("Times New Roman", Font.BOLD, 15));
            jumlahtitik.add(empat);
            lima = new JRadioButton("5");
            lima.setBounds(230, 80, 50, 30);
            lima.setBackground(Color.WHITE);
            lima.setFont(new Font("Times New Roman", Font.BOLD, 15));
            jumlahtitik.add(lima);
            enam = new JRadioButton("6");
            enam.setBounds(340, 80, 50, 30);
            enam.setBackground(Color.WHITE);
            enam.setFont(new Font("Times New Roman", Font.BOLD, 15));
            jumlahtitik.add(enam);
            tujuh = new JRadioButton("7");
            tujuh.setBounds(450, 80, 50, 30);
            tujuh.setBackground(Color.WHITE);
            tujuh.setFont(new Font("Times New Roman", Font.BOLD, 15));
            jumlahtitik.add(tujuh);
            delapan = new JRadioButton("8");
            delapan.setBounds(557, 80, 50, 30);
            delapan.setBackground(Color.WHITE);
            delapan.setFont(new Font("Times New Roman", Font.BOLD, 15));
            jumlahtitik.add(delapan);
            angkasatu = new JLabel("1.");
            angkasatu.setBounds(20, 125, 130, 30);
            angkasatu.setBackground(Color.WHITE);
            angkasatu.setFont(new Font("Times New Roman", Font.BOLD, 15));
            titik1 = new JLabel("Dari");
            titik1.setBounds(40, 125, 130, 30);
            titik1.setBackground(Color.WHITE);
            titik1.setFont(new Font("Times New Roman", Font.BOLD, 15));
            row11 = new JTextField("");
            row11.setBounds(90, 125, 40, 30);
            row11.setBackground(Color.WHITE);
            row11.setFont(new Font("Times New Roman", Font.BOLD, 15));
            row11.setEditable(false);
            row11.setHorizontalAlignment(SwingConstants.CENTER);
            titikkota1 = new JComboBox(daftarkota);
            titikkota1.setBounds(135, 125, 150, 30);
            titikkota1.setBackground(Color.WHITE);
            titikkota1.setFont(new Font("Times New Roman", Font.BOLD, 15));
            destinasi1 = new JLabel("Ke");
            destinasi1.setBounds(40, 160, 130, 30);
            destinasi1.setBackground(Color.WHITE);
            destinasi1.setFont(new Font("Times New Roman", Font.BOLD, 15));
            row12 = new JTextField("");
            row12.setBounds(90, 160, 40, 30);
            row12.setBackground(Color.WHITE);
            row12.setFont(new Font("Times New Roman", Font.BOLD, 15));
            row12.setEditable(false);
            row12.setHorizontalAlignment(SwingConstants.CENTER);
            destinasikota1 = new JComboBox(daftarkota);
            destinasikota1.setBounds(135, 160, 150, 30);
            destinasikota1.setBackground(Color.WHITE);
            destinasikota1.setFont(new Font("Times New Roman", Font.BOLD, 15));
            jarak1 = new JLabel("Jarak");
            jarak1.setBounds(40, 195, 130, 30);
            jarak1.setBackground(Color.WHITE);
            jarak1.setFont(new Font("Times New Roman", Font.BOLD, 15));
            row13 = new JTextField("");
            row13.setBounds(90, 195, 40, 30);
            row13.setBackground(Color.WHITE);
            row13.setFont(new Font("Times New Roman", Font.BOLD, 15));
            row13.setHorizontalAlignment(SwingConstants.CENTER);
            km1 = new JLabel("Km");
            km1.setBounds(135, 195, 130, 30);
            km1.setBackground(Color.WHITE);
            km1.setFont(new Font("Times New Roman", Font.BOLD, 15));
            angkadua = new JLabel("2.");
            angkadua.setBounds(20, 240, 130, 30);
            angkadua.setBackground(Color.WHITE);
            angkadua.setFont(new Font("Times New Roman", Font.BOLD, 15));
            titik2 = new JLabel("Dari");
            titik2.setBounds(40, 240, 130, 30);
            titik2.setBackground(Color.WHITE);
            titik2.setFont(new Font("Times New Roman", Font.BOLD, 15));
            row21 = new JTextField("");
            row21.setBounds(90, 240, 40, 30);
            row21.setBackground(Color.WHITE);
            row21.setFont(new Font("Times New Roman", Font.BOLD, 15));
            row21.setEditable(false);
            row21.setHorizontalAlignment(SwingConstants.CENTER);
            titikkota2 = new JComboBox(daftarkota);
            titikkota2.setBounds(135, 240, 150, 30);
            titikkota2.setBackground(Color.WHITE);
            titikkota2.setFont(new Font("Times New Roman", Font.BOLD, 15));
            destinasi2 = new JLabel("Ke");
            destinasi2.setBounds(40, 275, 30, 30);
            destinasi2.setBackground(Color.WHITE);
            destinasi2.setFont(new Font("Times New Roman", Font.BOLD, 15));
            row22 = new JTextField("");
            row22.setBounds(90, 275, 40, 30);
            row22.setBackground(Color.WHITE);
            row22.setFont(new Font("Times New Roman", Font.BOLD, 15));
            row22.setEditable(false);
            row22.setHorizontalAlignment(SwingConstants.CENTER);
            destinasikota2 = new JComboBox(daftarkota);
            destinasikota2.setBounds(135, 275, 150, 30);
            destinasikota2.setBackground(Color.WHITE);
            destinasikota2.setFont(new Font("Times New Roman", Font.BOLD, 15));
            jarak2 = new JLabel("Jarak");
            jarak2.setBounds(40, 310, 130, 30);
            jarak2.setBackground(Color.WHITE);
            jarak2.setFont(new Font("Times New Roman", Font.BOLD, 15));
            row23 = new JTextField("");
            row23.setBounds(90, 310, 40, 30);
            row23.setBackground(Color.WHITE);
            row23.setFont(new Font("Times New Roman", Font.BOLD, 15));
            row23.setHorizontalAlignment(SwingConstants.CENTER);
            km2 = new JLabel("Km");
            km2.setBounds(135, 310, 130, 30);
            km2.setBackground(Color.WHITE);
            km2.setFont(new Font("Times New Roman", Font.BOLD, 15));
            angkatiga = new JLabel("3.");
            angkatiga.setBounds(20, 355, 130, 30);
            angkatiga.setBackground(Color.WHITE);
            angkatiga.setFont(new Font("Times New Roman", Font.BOLD, 15));
            titik3 = new JLabel("Dari");
            titik3.setBounds(40, 355, 130, 30);
            titik3.setBackground(Color.WHITE);
            titik3.setFont(new Font("Times New Roman", Font.BOLD, 15));
            row31 = new JTextField("");
            row31.setBounds(90, 355, 40, 30);
            row31.setBackground(Color.WHITE);
            row31.setFont(new Font("Times New Roman", Font.BOLD, 15));
            row31.setEditable(false);
            row31.setHorizontalAlignment(SwingConstants.CENTER);
            titikkota3 = new JComboBox(daftarkota);
            titikkota3.setBounds(135, 355, 150, 30);
            titikkota3.setBackground(Color.WHITE);
            titikkota3.setFont(new Font("Times New Roman", Font.BOLD, 15));
            destinasi3 = new JLabel("Ke");
            destinasi3.setBounds(40, 390, 30, 30);
            destinasi3.setBackground(Color.WHITE);
            destinasi3.setFont(new Font("Times New Roman", Font.BOLD, 15));
            row32 = new JTextField("");
            row32.setBounds(90, 390, 40, 30);
            row32.setBackground(Color.WHITE);
            row32.setFont(new Font("Times New Roman", Font.BOLD, 15));
            row32.setEditable(false);
            row32.setHorizontalAlignment(SwingConstants.CENTER);
            destinasikota3 = new JComboBox(daftarkota);
            destinasikota3.setBounds(135, 390, 150, 30);
            destinasikota3.setBackground(Color.WHITE);
            destinasikota3.setFont(new Font("Times New Roman", Font.BOLD, 15));
            jarak3 = new JLabel("Jarak");
            jarak3.setBounds(40, 425, 130, 30);
            jarak3.setBackground(Color.WHITE);
            jarak3.setFont(new Font("Times New Roman", Font.BOLD, 15));
            row33 = new JTextField("");
            row33.setBounds(90, 425, 40, 30);
            row33.setBackground(Color.WHITE);
            row33.setFont(new Font("Times New Roman", Font.BOLD, 15));
            row33.setHorizontalAlignment(SwingConstants.CENTER);
            km3 = new JLabel("Km");
            km3.setBounds(135, 425, 130, 30);
            km3.setBackground(Color.WHITE);
            km3.setFont(new Font("Times New Roman", Font.BOLD, 15));
            angkaempat = new JLabel("4.");
            angkaempat.setBounds(20, 470, 130, 30);
            angkaempat.setBackground(Color.WHITE);
            angkaempat.setFont(new Font("Times New Roman", Font.BOLD, 15));
            titik4 = new JLabel("Dari");
            titik4.setBounds(40, 470, 130, 30);
            titik4.setBackground(Color.WHITE);
            titik4.setFont(new Font("Times New Roman", Font.BOLD, 15));
            row41 = new JTextField("");
            row41.setBounds(90, 470, 40, 30);
            row41.setBackground(Color.WHITE);
            row41.setFont(new Font("Times New Roman", Font.BOLD, 15));
            row41.setEditable(false);
            row41.setHorizontalAlignment(SwingConstants.CENTER);
            titikkota4 = new JComboBox(daftarkota);
            titikkota4.setBounds(135, 470, 150, 30);
            titikkota4.setBackground(Color.WHITE);
            titikkota4.setFont(new Font("Times New Roman", Font.BOLD, 15));
            destinasi4 = new JLabel("Ke");
            destinasi4.setBounds(40, 505, 30, 30);
            destinasi4.setBackground(Color.WHITE);
            destinasi4.setFont(new Font("Times New Roman", Font.BOLD, 15));
            row42 = new JTextField("");
            row42.setBounds(90, 505, 40, 30);
            row42.setBackground(Color.WHITE);
            row42.setFont(new Font("Times New Roman", Font.BOLD, 15));
            row42.setEditable(false);
            row42.setHorizontalAlignment(SwingConstants.CENTER);
            destinasikota4 = new JComboBox(daftarkota);
            destinasikota4.setBounds(135, 505, 150, 30);
            destinasikota4.setBackground(Color.WHITE);
            destinasikota4.setFont(new Font("Times New Roman", Font.BOLD, 15));
            jarak4 = new JLabel("Jarak");
            jarak4.setBounds(40, 540, 130, 30);
            jarak4.setBackground(Color.WHITE);
            jarak4.setFont(new Font("Times New Roman", Font.BOLD, 15));
            row43 = new JTextField("");
            row43.setBounds(90, 540, 40, 30);
            row43.setBackground(Color.WHITE);
            row43.setFont(new Font("Times New Roman", Font.BOLD, 15));
            row43.setHorizontalAlignment(SwingConstants.CENTER);
            km4 = new JLabel("Km");
            km4.setBounds(135, 540, 130, 30);
            km4.setBackground(Color.WHITE);
            km4.setFont(new Font("Times New Roman", Font.BOLD, 15));
            angkalima = new JLabel("5.");
            angkalima.setBounds(320, 125, 130, 30);
            angkalima.setBackground(Color.WHITE);
            angkalima.setFont(new Font("Times New Roman", Font.BOLD, 15));
            titik5 = new JLabel("Dari");
            titik5.setBounds(340, 125, 130, 30);
            titik5.setBackground(Color.WHITE);
            titik5.setFont(new Font("Times New Roman", Font.BOLD, 15));
            row51 = new JTextField("");
            row51.setBounds(390, 125, 40, 30);
            row51.setBackground(Color.WHITE);
            row51.setFont(new Font("Times New Roman", Font.BOLD, 15));
            row51.setEditable(false);
            row51.setHorizontalAlignment(SwingConstants.CENTER);
            titikkota5 = new JComboBox(daftarkota);
            titikkota5.setBounds(435, 125, 150, 30);
            titikkota5.setBackground(Color.WHITE);
            titikkota5.setFont(new Font("Times New Roman", Font.BOLD, 15));
            destinasi5 = new JLabel("Ke");
            destinasi5.setBounds(340, 160, 130, 30);
            destinasi5.setBackground(Color.WHITE);
            destinasi5.setFont(new Font("Times New Roman", Font.BOLD, 15));
            row52 = new JTextField("");
            row52.setBounds(390, 160, 40, 30);
            row52.setBackground(Color.WHITE);
            row52.setFont(new Font("Times New Roman", Font.BOLD, 15));
            row52.setEditable(false);
            row52.setHorizontalAlignment(SwingConstants.CENTER);
            destinasikota5 = new JComboBox(daftarkota);
            destinasikota5.setBounds(435, 160, 150, 30);
            destinasikota5.setBackground(Color.WHITE);
            destinasikota5.setFont(new Font("Times New Roman", Font.BOLD, 15));
            jarak5 = new JLabel("Jarak");
            jarak5.setBounds(340, 195, 130, 30);
            jarak5.setBackground(Color.WHITE);
            jarak5.setFont(new Font("Times New Roman", Font.BOLD, 15));
            row53 = new JTextField("");
            row53.setBounds(390, 195, 40, 30);
            row53.setBackground(Color.WHITE);
            row53.setFont(new Font("Times New Roman", Font.BOLD, 15));
            row53.setHorizontalAlignment(SwingConstants.CENTER);
            km5 = new JLabel("Km");
            km5.setBounds(435, 195, 130, 30);
            km5.setBackground(Color.WHITE);
            km5.setFont(new Font("Times New Roman", Font.BOLD, 15));
            angkaenam = new JLabel("6.");
            angkaenam.setBounds(320, 240, 130, 30);
            angkaenam.setBackground(Color.WHITE);
            angkaenam.setFont(new Font("Times New Roman", Font.BOLD, 15));
            titik6 = new JLabel("Dari");
            titik6.setBounds(340, 240, 130, 30);
            titik6.setBackground(Color.WHITE);
            titik6.setFont(new Font("Times New Roman", Font.BOLD, 15));
            row61 = new JTextField("");
            row61.setBounds(390, 240, 40, 30);
            row61.setBackground(Color.WHITE);
            row61.setFont(new Font("Times New Roman", Font.BOLD, 15));
            row61.setEditable(false);
            row61.setHorizontalAlignment(SwingConstants.CENTER);
            titikkota6 = new JComboBox(daftarkota);
            titikkota6.setBounds(435, 240, 150, 30);
            titikkota6.setBackground(Color.WHITE);
            titikkota6.setFont(new Font("Times New Roman", Font.BOLD, 15));
            destinasi6 = new JLabel("Ke");
            destinasi6.setBounds(340, 275, 30, 30);
            destinasi6.setBackground(Color.WHITE);
            destinasi6.setFont(new Font("Times New Roman", Font.BOLD, 15));
            row62 = new JTextField("");
            row62.setBounds(390, 275, 40, 30);
            row62.setBackground(Color.WHITE);
            row62.setFont(new Font("Times New Roman", Font.BOLD, 15));
            row62.setEditable(false);
            row62.setHorizontalAlignment(SwingConstants.CENTER);
            destinasikota6 = new JComboBox(daftarkota);
            destinasikota6.setBounds(435, 275, 150, 30);
            destinasikota6.setBackground(Color.WHITE);
            destinasikota6.setFont(new Font("Times New Roman", Font.BOLD, 15));
            jarak6 = new JLabel("Jarak");
            jarak6.setBounds(340, 310, 130, 30);
            jarak6.setBackground(Color.WHITE);
            jarak6.setFont(new Font("Times New Roman", Font.BOLD, 15));
            row63 = new JTextField("");
            row63.setBounds(390, 310, 40, 30);
            row63.setBackground(Color.WHITE);
            row63.setFont(new Font("Times New Roman", Font.BOLD, 15));
            row63.setHorizontalAlignment(SwingConstants.CENTER);
            km6 = new JLabel("Km");
            km6.setBounds(435, 310, 130, 30);
            km6.setBackground(Color.WHITE);
            km6.setFont(new Font("Times New Roman", Font.BOLD, 15));
            angkatujuh = new JLabel("7.");
            angkatujuh.setBounds(320, 355, 130, 30);
            angkatujuh.setBackground(Color.WHITE);
            angkatujuh.setFont(new Font("Times New Roman", Font.BOLD, 15));
            titik7 = new JLabel("Dari");
            titik7.setBounds(340, 355, 130, 30);
            titik7.setBackground(Color.WHITE);
            titik7.setFont(new Font("Times New Roman", Font.BOLD, 15));
            row71 = new JTextField("");
            row71.setBounds(390, 355, 40, 30);
            row71.setBackground(Color.WHITE);
            row71.setFont(new Font("Times New Roman", Font.BOLD, 15));
            row71.setEditable(false);
            row71.setHorizontalAlignment(SwingConstants.CENTER);
            titikkota7 = new JComboBox(daftarkota);
            titikkota7.setBounds(435, 355, 150, 30);
            titikkota7.setBackground(Color.WHITE);
            titikkota7.setFont(new Font("Times New Roman", Font.BOLD, 15));
            destinasi7 = new JLabel("Ke");
            destinasi7.setBounds(340, 390, 30, 30);
            destinasi7.setBackground(Color.WHITE);
            destinasi7.setFont(new Font("Times New Roman", Font.BOLD, 15));
            row72 = new JTextField("");
            row72.setBounds(390, 390, 40, 30);
            row72.setBackground(Color.WHITE);
            row72.setFont(new Font("Times New Roman", Font.BOLD, 15));
            row72.setEditable(false);
            row72.setHorizontalAlignment(SwingConstants.CENTER);
            destinasikota7 = new JComboBox(daftarkota);
            destinasikota7.setBounds(435, 390, 150, 30);
            destinasikota7.setBackground(Color.WHITE);
            destinasikota7.setFont(new Font("Times New Roman", Font.BOLD, 15));
            jarak7 = new JLabel("Jarak");
            jarak7.setBounds(340, 425, 130, 30);
            jarak7.setBackground(Color.WHITE);
            jarak7.setFont(new Font("Times New Roman", Font.BOLD, 15));
            row73 = new JTextField("");
            row73.setBounds(390, 425, 40, 30);
            row73.setBackground(Color.WHITE);
            row73.setFont(new Font("Times New Roman", Font.BOLD, 15));
            row73.setHorizontalAlignment(SwingConstants.CENTER);
            km7 = new JLabel("Km");
            km7.setBounds(435, 425, 130, 30);
            km7.setBackground(Color.WHITE);
            km7.setFont(new Font("Times New Roman", Font.BOLD, 15));
            angkadelapan = new JLabel("8.");
            angkadelapan.setBounds(320, 470, 130, 30);
            angkadelapan.setBackground(Color.WHITE);
            angkadelapan.setFont(new Font("Times New Roman", Font.BOLD, 15));
            titik8 = new JLabel("Dari");
            titik8.setBounds(340, 470, 130, 30);
            titik8.setBackground(Color.WHITE);
            titik8.setFont(new Font("Times New Roman", Font.BOLD, 15));
            row81 = new JTextField("");
            row81.setBounds(390, 470, 40, 30);
            row81.setBackground(Color.WHITE);
            row81.setFont(new Font("Times New Roman", Font.BOLD, 15));
            row81.setEditable(false);
            row81.setHorizontalAlignment(SwingConstants.CENTER);
            titikkota8 = new JComboBox(daftarkota);
            titikkota8.setBounds(435, 470, 150, 30);
            titikkota8.setBackground(Color.WHITE);
            titikkota8.setFont(new Font("Times New Roman", Font.BOLD, 15));
            destinasi8 = new JLabel("Ke");
            destinasi8.setBounds(340, 505, 30, 30);
            destinasi8.setBackground(Color.WHITE);
            destinasi8.setFont(new Font("Times New Roman", Font.BOLD, 15));
            row82 = new JTextField("");
            row82.setBounds(390, 505, 40, 30);
            row82.setBackground(Color.WHITE);
            row82.setFont(new Font("Times New Roman", Font.BOLD, 15));
            row82.setEditable(false);
            row82.setHorizontalAlignment(SwingConstants.CENTER);
            destinasikota8 = new JComboBox(daftarkota);
            destinasikota8.setBounds(435, 505, 150, 30);
            destinasikota8.setBackground(Color.WHITE);
            destinasikota8.setFont(new Font("Times New Roman", Font.BOLD, 15));
            jarak8 = new JLabel("Jarak");
            jarak8.setBounds(340, 540, 130, 30);
            jarak8.setBackground(Color.WHITE);
            jarak8.setFont(new Font("Times New Roman", Font.BOLD, 15));
            row83 = new JTextField("");
            row83.setBounds(390, 540, 40, 30);
            row83.setBackground(Color.WHITE);
            row83.setFont(new Font("Times New Roman", Font.BOLD, 15));
            row83.setHorizontalAlignment(SwingConstants.CENTER);
            km8 = new JLabel("Km");
            km8.setBounds(435, 540, 130, 30);
            km8.setBackground(Color.WHITE);
            km8.setFont(new Font("Times New Roman", Font.BOLD, 15));
            cari = new JButton("Cari");
            cari.setBounds(20, 580, 280, 40);
            cari.setBackground(Color.WHITE);
            cari.setFont(new Font("Times New Roman", Font.BOLD, 15));
            carapenggunaan = new JButton("Cara Penggunaan");
            carapenggunaan.setBounds(310, 580, 280, 40);
            carapenggunaan.setBackground(Color.WHITE);
            carapenggunaan.setFont(new Font("Times New Roman", Font.BOLD, 15));
            gambar1 = new JLabel(new ImageIcon("C:\\Users\\Lenovo\\Documents\\NetBeansProjects\\MyGraph\\src\\Final_Project\\Image\\Bus\\Surya.jpg"));
            gambar1.setBounds(615, 90, 365, 310);
            gambar2 = new JLabel(new ImageIcon("C:\\Users\\Lenovo\\Documents\\NetBeansProjects\\MyGraph\\src\\Final_Project\\Image\\Bus\\Subur.jpg"));
            gambar2.setBounds(615, 90, 365, 310);
            gambar3 = new JLabel(new ImageIcon("C:\\Users\\Lenovo\\Documents\\NetBeansProjects\\MyGraph\\src\\Final_Project\\Image\\Bus\\Jaya.jpg"));
            gambar3.setBounds(615, 90, 365, 310);
            gambar4 = new JLabel(new ImageIcon("C:\\Users\\Lenovo\\Documents\\NetBeansProjects\\MyGraph\\src\\Final_Project\\Image\\Bus\\Laju.jpg"));
            gambar4.setBounds(615, 90, 365, 310);
            gambar5 = new JLabel(new ImageIcon("C:\\Users\\Lenovo\\Documents\\NetBeansProjects\\MyGraph\\src\\Final_Project\\Image\\Bus\\Rejeki.jpg"));
            gambar5.setBounds(615, 90, 365, 310);
            gambar6 = new JLabel(new ImageIcon("C:\\Users\\Lenovo\\Documents\\NetBeansProjects\\MyGraph\\src\\Final_Project\\Image\\Bus\\Aman.jpg"));
            gambar6.setBounds(615, 90, 365, 310);
            gambar7 = new JLabel(new ImageIcon("C:\\Users\\Lenovo\\Documents\\NetBeansProjects\\MyGraph\\src\\Final_Project\\Image\\Bus\\Buroq.jpg"));
            gambar7.setBounds(615, 90, 365, 310);
            reset = new JButton("Reset");
            reset.setBounds(615, 500, 360, 40);
            reset.setBackground(Color.WHITE);
            reset.setFont(new Font("Times New Roman", Font.BOLD, 15));
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (ClassNotFoundException | InstantiationException
                    | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            }
            StringBuilder sb = new StringBuilder(30);
            sb.append("<html>Asal PO<br>").
                    append("Biaya Pengantaran<br>").
                    append("Fasilitas<br>").
                    append("Penumpang Maksimum</html>");
            StringBuilder sb11 = new StringBuilder(30);
            sb11.append("<html>: Surabaya<br>").
                    append(": Rp 200/Km<br>").
                    append(": AC<br>").
                    append(": 50</html>");
            StringBuilder sb12 = new StringBuilder(30);
            sb12.append("<html>: Surabaya<br>").
                    append(": Rp 210/Km<br>").
                    append(": AC<br>").
                    append(": 55</html>");
            StringBuilder sb13 = new StringBuilder(30);
            sb13.append("<html>: Surabaya<br>").
                    append(": Rp 220/Km<br>").
                    append(": AC<br>").
                    append(": 60</html>");
            StringBuilder sb14 = new StringBuilder(30);
            sb14.append("<html>: Surabaya<br>").
                    append(": Rp 210/Km<br>").
                    append(": AC<br>").
                    append(": 55</html>");
            StringBuilder sb15 = new StringBuilder(30);
            sb15.append("<html>: Surabaya<br>").
                    append(": Rp 200/Km<br>").
                    append(": AC<br>").
                    append(": 50</html>");
            StringBuilder sb16 = new StringBuilder(30);
            sb16.append("<html>: Surabaya<br>").
                    append(": Rp 210/Km<br>").
                    append(": AC<br>").
                    append(": 55</html>");
            StringBuilder sb17 = new StringBuilder(30);
            sb17.append("<html>: Surabaya<br>").
                    append(": Rp 230/Km<br>").
                    append(": AC<br>").
                    append(": 65</html>");
            deskripsipopo = new JLabel(sb.toString());
            deskripsipopo.setBounds(615, 390, 200, 100);
            deskripsipopo.setBackground(Color.WHITE);
            deskripsipopo.setFont(new Font("Times New Roman", Font.BOLD, 15));
            deskripsi11 = new JLabel(sb11.toString());
            deskripsi11.setBounds(800, 390, 200, 100);
            deskripsi11.setBackground(Color.WHITE);
            deskripsi11.setFont(new Font("Times New Roman", Font.BOLD, 15));
            deskripsi12 = new JLabel(sb12.toString());
            deskripsi12.setBounds(800, 390, 200, 100);
            deskripsi12.setBackground(Color.WHITE);
            deskripsi12.setFont(new Font("Times New Roman", Font.BOLD, 15));
            deskripsi13 = new JLabel(sb13.toString());
            deskripsi13.setBounds(800, 390, 200, 100);
            deskripsi13.setBackground(Color.WHITE);
            deskripsi13.setFont(new Font("Times New Roman", Font.BOLD, 15));
            deskripsi14 = new JLabel(sb14.toString());
            deskripsi14.setBounds(800, 390, 200, 100);
            deskripsi14.setBackground(Color.WHITE);
            deskripsi14.setFont(new Font("Times New Roman", Font.BOLD, 15));
            deskripsi15 = new JLabel(sb15.toString());
            deskripsi15.setBounds(800, 390, 200, 100);
            deskripsi15.setBackground(Color.WHITE);
            deskripsi15.setFont(new Font("Times New Roman", Font.BOLD, 15));
            deskripsi16 = new JLabel(sb16.toString());
            deskripsi16.setBounds(800, 390, 200, 100);
            deskripsi16.setBackground(Color.WHITE);
            deskripsi16.setFont(new Font("Times New Roman", Font.BOLD, 15));
            deskripsi17 = new JLabel(sb17.toString());
            deskripsi17.setBounds(800, 390, 200, 100);
            deskripsi17.setBackground(Color.WHITE);
            deskripsi17.setFont(new Font("Times New Roman", Font.BOLD, 15));
            frame.add(judul);
            frame.add(reset);
            frame.add(pilihanpo);
            frame.add(semuapo);
            frame.add(pilihtitikasal);
            frame.add(deskripsipo);
            frame.add(carititik0);
            frame.add(carititik1);
            frame.add(carititik2);
            frame.add(carititik3);
            frame.add(carititik4);
            frame.add(carititik5);
            frame.add(carititik6);
            frame.add(carititik7);
            frame.add(pilihjumlahtitik);
            frame.add(empat);
            frame.add(lima);
            frame.add(enam);
            frame.add(tujuh);
            frame.add(delapan);
            frame.add(angkasatu);
            frame.add(angkadua);
            frame.add(angkatiga);
            frame.add(angkaempat);
            frame.add(angkalima);
            frame.add(angkaenam);
            frame.add(angkatujuh);
            frame.add(angkadelapan);
            frame.add(km1);
            frame.add(km2);
            frame.add(km3);
            frame.add(km4);
            frame.add(km5);
            frame.add(km6);
            frame.add(km7);
            frame.add(km8);
            frame.add(titik1);
            frame.add(titik2);
            frame.add(titik3);
            frame.add(titik4);
            frame.add(titik5);
            frame.add(titik6);
            frame.add(titik7);
            frame.add(titik8);
            frame.add(destinasi1);
            frame.add(destinasi2);
            frame.add(destinasi3);
            frame.add(destinasi4);
            frame.add(destinasi5);
            frame.add(destinasi6);
            frame.add(destinasi7);
            frame.add(destinasi8);
            frame.add(jarak1);
            frame.add(jarak2);
            frame.add(jarak3);
            frame.add(jarak4);
            frame.add(jarak5);
            frame.add(jarak6);
            frame.add(jarak7);
            frame.add(jarak8);
            frame.add(titikkota1);
            frame.add(titikkota2);
            frame.add(titikkota3);
            frame.add(titikkota4);
            frame.add(titikkota5);
            frame.add(titikkota6);
            frame.add(titikkota7);
            frame.add(titikkota8);
            frame.add(destinasikota1);
            frame.add(destinasikota2);
            frame.add(destinasikota3);
            frame.add(destinasikota4);
            frame.add(destinasikota5);
            frame.add(destinasikota6);
            frame.add(destinasikota7);
            frame.add(destinasikota8);
            frame.add(row11);
            frame.add(row12);
            frame.add(row13);
            frame.add(row21);
            frame.add(row22);
            frame.add(row23);
            frame.add(row31);
            frame.add(row32);
            frame.add(row33);
            frame.add(row41);
            frame.add(row42);
            frame.add(row43);
            frame.add(row51);
            frame.add(row52);
            frame.add(row53);
            frame.add(row61);
            frame.add(row62);
            frame.add(row63);
            frame.add(row71);
            frame.add(row72);
            frame.add(row73);
            frame.add(row81);
            frame.add(row82);
            frame.add(row83);
            frame.add(cari);
            frame.add(carapenggunaan);
            frame.add(deskripsipopo);
            frame.setVisible(true);
            reset.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    tmbkActionPerformed(evt);
                }

                private void tmbkActionPerformed(ActionEvent evt) {
                    DAFTAR daftar = new DAFTAR();
                    frame.setVisible(false);
                }
            });
            semuapo.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    JComboBox comboBox = (JComboBox) event.getSource();
                    Object selected = comboBox.getSelectedItem();
                    if (selected.toString().equals("PO Surya")) {
                        frame.remove(gambar2);
                        frame.remove(gambar3);
                        frame.remove(gambar4);
                        frame.remove(gambar5);
                        frame.remove(gambar6);
                        frame.remove(gambar7);
                        frame.remove(deskripsi12);
                        frame.remove(deskripsi13);
                        frame.remove(deskripsi14);
                        frame.remove(deskripsi15);
                        frame.remove(deskripsi16);
                        frame.remove(deskripsi17);
                        frame.validate();
                        frame.repaint();
                        frame.add(gambar1);
                        frame.add(deskripsi11);
                    } else if (selected.toString().equals("PO Subur")) {
                        frame.remove(gambar1);
                        frame.remove(gambar3);
                        frame.remove(gambar4);
                        frame.remove(gambar5);
                        frame.remove(gambar6);
                        frame.remove(gambar7);
                        frame.remove(deskripsi11);
                        frame.remove(deskripsi13);
                        frame.remove(deskripsi14);
                        frame.remove(deskripsi15);
                        frame.remove(deskripsi16);
                        frame.remove(deskripsi17);
                        frame.validate();
                        frame.repaint();
                        frame.add(gambar2);
                        frame.add(deskripsi12);
                    } else if (selected.toString().equals("PO Jaya")) {
                        frame.remove(gambar1);
                        frame.remove(gambar2);
                        frame.remove(gambar4);
                        frame.remove(gambar5);
                        frame.remove(gambar6);
                        frame.remove(gambar7);
                        frame.remove(deskripsi11);
                        frame.remove(deskripsi12);
                        frame.remove(deskripsi14);
                        frame.remove(deskripsi15);
                        frame.remove(deskripsi16);
                        frame.remove(deskripsi17);
                        frame.validate();
                        frame.repaint();
                        frame.add(gambar3);
                        frame.add(deskripsi13);
                    } else if (selected.toString().equals("PO Laju")) {
                        frame.remove(gambar1);
                        frame.remove(gambar2);
                        frame.remove(gambar3);
                        frame.remove(gambar5);
                        frame.remove(gambar6);
                        frame.remove(gambar7);
                        frame.remove(deskripsi11);
                        frame.remove(deskripsi12);
                        frame.remove(deskripsi13);
                        frame.remove(deskripsi15);
                        frame.remove(deskripsi16);
                        frame.remove(deskripsi17);
                        frame.validate();
                        frame.repaint();
                        frame.add(gambar4);
                        frame.add(deskripsi14);
                    } else if (selected.toString().equals("PO Rejeki")) {
                        frame.remove(gambar1);
                        frame.remove(gambar2);
                        frame.remove(gambar3);
                        frame.remove(gambar4);
                        frame.remove(gambar6);
                        frame.remove(gambar7);
                        frame.remove(deskripsi11);
                        frame.remove(deskripsi12);
                        frame.remove(deskripsi13);
                        frame.remove(deskripsi14);
                        frame.remove(deskripsi16);
                        frame.remove(deskripsi17);
                        frame.validate();
                        frame.repaint();
                        frame.add(gambar5);
                        frame.add(deskripsi15);
                    } else if (selected.toString().equals("PO Aman")) {
                        frame.remove(gambar1);
                        frame.remove(gambar2);
                        frame.remove(gambar3);
                        frame.remove(gambar4);
                        frame.remove(gambar5);
                        frame.remove(gambar7);
                        frame.remove(deskripsi11);
                        frame.remove(deskripsi12);
                        frame.remove(deskripsi13);
                        frame.remove(deskripsi14);
                        frame.remove(deskripsi15);
                        frame.remove(deskripsi17);
                        frame.validate();
                        frame.repaint();
                        frame.add(gambar6);
                        frame.add(deskripsi16);
                    } else if (selected.toString().equals("PO Buroq")) {
                        frame.remove(gambar1);
                        frame.remove(gambar2);
                        frame.remove(gambar3);
                        frame.remove(gambar4);
                        frame.remove(gambar5);
                        frame.remove(gambar6);
                        frame.remove(deskripsi11);
                        frame.remove(deskripsi12);
                        frame.remove(deskripsi13);
                        frame.remove(deskripsi14);
                        frame.remove(deskripsi15);
                        frame.remove(deskripsi16);
                        frame.validate();
                        frame.repaint();
                        frame.add(gambar7);
                        frame.add(deskripsi17);
                    }
                }
            });
            cari.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    tmbkActionPerformed(evt);
                }

                private void tmbkActionPerformed(ActionEvent evt) {

                    empat.setActionCommand("4");
                    lima.setActionCommand("5");
                    enam.setActionCommand("6");
                    tujuh.setActionCommand("7");
                    delapan.setActionCommand("8");
                    String thevertices = jumlahtitik.getSelection().getActionCommand();
                    int fromvertices = Integer.parseInt(thevertices);
                    int vertices = fromvertices;

                    if (vertices == 4) {
                        String fromrow11 = row11.getText();
                        int axis11 = Integer.parseInt(fromrow11);
                        String fromrow12 = row12.getText();
                        int axis12 = Integer.parseInt(fromrow12);
                        String fromrow13 = row13.getText();
                        int axis13 = Integer.parseInt(fromrow13);

                        String fromrow21 = row21.getText();
                        int axis21 = Integer.parseInt(fromrow21);
                        String fromrow22 = row22.getText();
                        int axis22 = Integer.parseInt(fromrow22);
                        String fromrow23 = row23.getText();
                        int axis23 = Integer.parseInt(fromrow23);

                        String fromrow31 = row31.getText();
                        int axis31 = Integer.parseInt(fromrow31);
                        String fromrow32 = row32.getText();
                        int axis32 = Integer.parseInt(fromrow32);
                        String fromrow33 = row33.getText();
                        int axis33 = Integer.parseInt(fromrow33);

                        String fromrow41 = row41.getText();
                        int axis41 = Integer.parseInt(fromrow41);
                        String fromrow42 = row42.getText();
                        int axis42 = Integer.parseInt(fromrow42);
                        String fromrow43 = row43.getText();
                        int axis43 = Integer.parseInt(fromrow43);

                        DAFTAR graph = new DAFTAR(vertices);
                        carititik0.setActionCommand("0");
                        carititik1.setActionCommand("1");
                        carititik2.setActionCommand("2");
                        carititik3.setActionCommand("3");
                        carititik4.setActionCommand("4");
                        carititik5.setActionCommand("5");
                        carititik6.setActionCommand("6");
                        carititik7.setActionCommand("7");
                        String thesourcevertex = titikasal.getSelection().getActionCommand();
                        int fromsource = Integer.parseInt(thesourcevertex);
                        int sourceVertex = fromsource;
                        graph.addEdge(axis11, axis12, axis13);
                        graph.addEdge(axis21, axis22, axis23);
                        graph.addEdge(axis31, axis32, axis33);
                        graph.addEdge(axis41, axis42, axis43);
                        graph.dijkstra_GetMinDistances(sourceVertex);
                    } else if (vertices == 5) {
                        String fromrow11 = row11.getText();
                        int axis11 = Integer.parseInt(fromrow11);
                        String fromrow12 = row12.getText();
                        int axis12 = Integer.parseInt(fromrow12);
                        String fromrow13 = row13.getText();
                        int axis13 = Integer.parseInt(fromrow13);

                        String fromrow21 = row21.getText();
                        int axis21 = Integer.parseInt(fromrow21);
                        String fromrow22 = row22.getText();
                        int axis22 = Integer.parseInt(fromrow22);
                        String fromrow23 = row23.getText();
                        int axis23 = Integer.parseInt(fromrow23);

                        String fromrow31 = row31.getText();
                        int axis31 = Integer.parseInt(fromrow31);
                        String fromrow32 = row32.getText();
                        int axis32 = Integer.parseInt(fromrow32);
                        String fromrow33 = row33.getText();
                        int axis33 = Integer.parseInt(fromrow33);

                        String fromrow41 = row41.getText();
                        int axis41 = Integer.parseInt(fromrow41);
                        String fromrow42 = row42.getText();
                        int axis42 = Integer.parseInt(fromrow42);
                        String fromrow43 = row43.getText();
                        int axis43 = Integer.parseInt(fromrow43);

                        String fromrow51 = row51.getText();
                        int axis51 = Integer.parseInt(fromrow51);
                        String fromrow52 = row52.getText();
                        int axis52 = Integer.parseInt(fromrow52);
                        String fromrow53 = row53.getText();
                        int axis53 = Integer.parseInt(fromrow53);

                        DAFTAR graph = new DAFTAR(vertices);
                        carititik0.setActionCommand("0");
                        carititik1.setActionCommand("1");
                        carititik2.setActionCommand("2");
                        carititik3.setActionCommand("3");
                        carititik4.setActionCommand("4");
                        carititik5.setActionCommand("5");
                        carititik6.setActionCommand("6");
                        carititik7.setActionCommand("7");
                        String thesourcevertex = titikasal.getSelection().getActionCommand();
                        int fromsource = Integer.parseInt(thesourcevertex);
                        int sourceVertex = fromsource;
                        graph.addEdge(axis11, axis12, axis13);
                        graph.addEdge(axis21, axis22, axis23);
                        graph.addEdge(axis31, axis32, axis33);
                        graph.addEdge(axis41, axis42, axis43);
                        graph.addEdge(axis51, axis52, axis53);
                        graph.dijkstra_GetMinDistances(sourceVertex);
                    } else if (vertices == 4 || vertices == 5) {
                        String fromrow11 = row11.getText();
                        int axis11 = Integer.parseInt(fromrow11);
                        String fromrow12 = row12.getText();
                        int axis12 = Integer.parseInt(fromrow12);
                        String fromrow13 = row13.getText();
                        int axis13 = Integer.parseInt(fromrow13);

                        String fromrow21 = row21.getText();
                        int axis21 = Integer.parseInt(fromrow21);
                        String fromrow22 = row22.getText();
                        int axis22 = Integer.parseInt(fromrow22);
                        String fromrow23 = row23.getText();
                        int axis23 = Integer.parseInt(fromrow23);

                        String fromrow31 = row31.getText();
                        int axis31 = Integer.parseInt(fromrow31);
                        String fromrow32 = row32.getText();
                        int axis32 = Integer.parseInt(fromrow32);
                        String fromrow33 = row33.getText();
                        int axis33 = Integer.parseInt(fromrow33);

                        String fromrow41 = row41.getText();
                        int axis41 = Integer.parseInt(fromrow41);
                        String fromrow42 = row42.getText();
                        int axis42 = Integer.parseInt(fromrow42);
                        String fromrow43 = row43.getText();
                        int axis43 = Integer.parseInt(fromrow43);

                        String fromrow51 = row51.getText();
                        int axis51 = Integer.parseInt(fromrow51);
                        String fromrow52 = row52.getText();
                        int axis52 = Integer.parseInt(fromrow52);
                        String fromrow53 = row53.getText();
                        int axis53 = Integer.parseInt(fromrow53);

                        DAFTAR graph = new DAFTAR(vertices);
                        carititik0.setActionCommand("0");
                        carititik1.setActionCommand("1");
                        carititik2.setActionCommand("2");
                        carititik3.setActionCommand("3");
                        carititik4.setActionCommand("4");
                        carititik5.setActionCommand("5");
                        carititik6.setActionCommand("6");
                        carititik7.setActionCommand("7");
                        String thesourcevertex = titikasal.getSelection().getActionCommand();
                        int fromsource = Integer.parseInt(thesourcevertex);
                        int sourceVertex = fromsource;
                        graph.addEdge(axis11, axis12, axis13);
                        graph.addEdge(axis21, axis22, axis23);
                        graph.addEdge(axis31, axis32, axis33);
                        graph.addEdge(axis41, axis42, axis43);
                        graph.addEdge(axis51, axis52, axis53);
                        graph.dijkstra_GetMinDistances(sourceVertex);
                    } else if (vertices == 4 || vertices == 5 || vertices == 6) {
                        String fromrow11 = row11.getText();
                        int axis11 = Integer.parseInt(fromrow11);
                        String fromrow12 = row12.getText();
                        int axis12 = Integer.parseInt(fromrow12);
                        String fromrow13 = row13.getText();
                        int axis13 = Integer.parseInt(fromrow13);

                        String fromrow21 = row21.getText();
                        int axis21 = Integer.parseInt(fromrow21);
                        String fromrow22 = row22.getText();
                        int axis22 = Integer.parseInt(fromrow22);
                        String fromrow23 = row23.getText();
                        int axis23 = Integer.parseInt(fromrow23);

                        String fromrow31 = row31.getText();
                        int axis31 = Integer.parseInt(fromrow31);
                        String fromrow32 = row32.getText();
                        int axis32 = Integer.parseInt(fromrow32);
                        String fromrow33 = row33.getText();
                        int axis33 = Integer.parseInt(fromrow33);

                        String fromrow41 = row41.getText();
                        int axis41 = Integer.parseInt(fromrow41);
                        String fromrow42 = row42.getText();
                        int axis42 = Integer.parseInt(fromrow42);
                        String fromrow43 = row43.getText();
                        int axis43 = Integer.parseInt(fromrow43);

                        String fromrow51 = row51.getText();
                        int axis51 = Integer.parseInt(fromrow51);
                        String fromrow52 = row52.getText();
                        int axis52 = Integer.parseInt(fromrow52);
                        String fromrow53 = row53.getText();
                        int axis53 = Integer.parseInt(fromrow53);

                        String fromrow61 = row61.getText();
                        int axis61 = Integer.parseInt(fromrow61);
                        String fromrow62 = row62.getText();
                        int axis62 = Integer.parseInt(fromrow62);
                        String fromrow63 = row63.getText();
                        int axis63 = Integer.parseInt(fromrow63);

                        DAFTAR graph = new DAFTAR(vertices);
                        carititik0.setActionCommand("0");
                        carititik1.setActionCommand("1");
                        carititik2.setActionCommand("2");
                        carititik3.setActionCommand("3");
                        carititik4.setActionCommand("4");
                        carititik5.setActionCommand("5");
                        carititik6.setActionCommand("6");
                        carititik7.setActionCommand("7");
                        String thesourcevertex = titikasal.getSelection().getActionCommand();
                        int fromsource = Integer.parseInt(thesourcevertex);
                        int sourceVertex = fromsource;
                        graph.addEdge(axis11, axis12, axis13);
                        graph.addEdge(axis21, axis22, axis23);
                        graph.addEdge(axis31, axis32, axis33);
                        graph.addEdge(axis41, axis42, axis43);
                        graph.addEdge(axis51, axis52, axis53);
                        graph.addEdge(axis61, axis62, axis63);
                        graph.dijkstra_GetMinDistances(sourceVertex);
                    } else if (vertices == 5 || vertices == 6 || vertices == 7) {
                        String fromrow11 = row11.getText();
                        int axis11 = Integer.parseInt(fromrow11);
                        String fromrow12 = row12.getText();
                        int axis12 = Integer.parseInt(fromrow12);
                        String fromrow13 = row13.getText();
                        int axis13 = Integer.parseInt(fromrow13);

                        String fromrow21 = row21.getText();
                        int axis21 = Integer.parseInt(fromrow21);
                        String fromrow22 = row22.getText();
                        int axis22 = Integer.parseInt(fromrow22);
                        String fromrow23 = row23.getText();
                        int axis23 = Integer.parseInt(fromrow23);

                        String fromrow31 = row31.getText();
                        int axis31 = Integer.parseInt(fromrow31);
                        String fromrow32 = row32.getText();
                        int axis32 = Integer.parseInt(fromrow32);
                        String fromrow33 = row33.getText();
                        int axis33 = Integer.parseInt(fromrow33);

                        String fromrow41 = row41.getText();
                        int axis41 = Integer.parseInt(fromrow41);
                        String fromrow42 = row42.getText();
                        int axis42 = Integer.parseInt(fromrow42);
                        String fromrow43 = row43.getText();
                        int axis43 = Integer.parseInt(fromrow43);

                        String fromrow51 = row51.getText();
                        int axis51 = Integer.parseInt(fromrow51);
                        String fromrow52 = row52.getText();
                        int axis52 = Integer.parseInt(fromrow52);
                        String fromrow53 = row53.getText();
                        int axis53 = Integer.parseInt(fromrow53);

                        String fromrow61 = row61.getText();
                        int axis61 = Integer.parseInt(fromrow61);
                        String fromrow62 = row62.getText();
                        int axis62 = Integer.parseInt(fromrow62);
                        String fromrow63 = row63.getText();
                        int axis63 = Integer.parseInt(fromrow63);

                        String fromrow71 = row71.getText();
                        int axis71 = Integer.parseInt(fromrow71);
                        String fromrow72 = row72.getText();
                        int axis72 = Integer.parseInt(fromrow72);
                        String fromrow73 = row73.getText();
                        int axis73 = Integer.parseInt(fromrow73);

                        DAFTAR graph = new DAFTAR(vertices);
                        carititik0.setActionCommand("0");
                        carititik1.setActionCommand("1");
                        carititik2.setActionCommand("2");
                        carititik3.setActionCommand("3");
                        carititik4.setActionCommand("4");
                        carititik5.setActionCommand("5");
                        carititik6.setActionCommand("6");
                        carititik7.setActionCommand("7");
                        String thesourcevertex = titikasal.getSelection().getActionCommand();
                        int fromsource = Integer.parseInt(thesourcevertex);
                        int sourceVertex = fromsource;
                        graph.addEdge(axis11, axis12, axis13);
                        graph.addEdge(axis21, axis22, axis23);
                        graph.addEdge(axis31, axis32, axis33);
                        graph.addEdge(axis41, axis42, axis43);
                        graph.addEdge(axis51, axis52, axis53);
                        graph.addEdge(axis61, axis62, axis63);
                        graph.addEdge(axis71, axis72, axis73);
                        graph.dijkstra_GetMinDistances(sourceVertex);
                    } else if (vertices == 5 || vertices == 6 || vertices == 7 || vertices == 8) {
                        String fromrow11 = row11.getText();
                        int axis11 = Integer.parseInt(fromrow11);
                        String fromrow12 = row12.getText();
                        int axis12 = Integer.parseInt(fromrow12);
                        String fromrow13 = row13.getText();
                        int axis13 = Integer.parseInt(fromrow13);

                        String fromrow21 = row21.getText();
                        int axis21 = Integer.parseInt(fromrow21);
                        String fromrow22 = row22.getText();
                        int axis22 = Integer.parseInt(fromrow22);
                        String fromrow23 = row23.getText();
                        int axis23 = Integer.parseInt(fromrow23);

                        String fromrow31 = row31.getText();
                        int axis31 = Integer.parseInt(fromrow31);
                        String fromrow32 = row32.getText();
                        int axis32 = Integer.parseInt(fromrow32);
                        String fromrow33 = row33.getText();
                        int axis33 = Integer.parseInt(fromrow33);

                        String fromrow41 = row41.getText();
                        int axis41 = Integer.parseInt(fromrow41);
                        String fromrow42 = row42.getText();
                        int axis42 = Integer.parseInt(fromrow42);
                        String fromrow43 = row43.getText();
                        int axis43 = Integer.parseInt(fromrow43);

                        String fromrow51 = row51.getText();
                        int axis51 = Integer.parseInt(fromrow51);
                        String fromrow52 = row52.getText();
                        int axis52 = Integer.parseInt(fromrow52);
                        String fromrow53 = row53.getText();
                        int axis53 = Integer.parseInt(fromrow53);

                        String fromrow61 = row61.getText();
                        int axis61 = Integer.parseInt(fromrow61);
                        String fromrow62 = row62.getText();
                        int axis62 = Integer.parseInt(fromrow62);
                        String fromrow63 = row63.getText();
                        int axis63 = Integer.parseInt(fromrow63);

                        String fromrow71 = row71.getText();
                        int axis71 = Integer.parseInt(fromrow71);
                        String fromrow72 = row72.getText();
                        int axis72 = Integer.parseInt(fromrow72);
                        String fromrow73 = row73.getText();
                        int axis73 = Integer.parseInt(fromrow73);

                        String fromrow81 = row81.getText();
                        int axis81 = Integer.parseInt(fromrow81);
                        String fromrow82 = row82.getText();
                        int axis82 = Integer.parseInt(fromrow82);
                        String fromrow83 = row83.getText();
                        int axis83 = Integer.parseInt(fromrow83);

                        DAFTAR graph = new DAFTAR(vertices);
                        carititik0.setActionCommand("0");
                        carititik1.setActionCommand("1");
                        carititik2.setActionCommand("2");
                        carititik3.setActionCommand("3");
                        carititik4.setActionCommand("4");
                        carititik5.setActionCommand("5");
                        carititik6.setActionCommand("6");
                        carititik7.setActionCommand("7");
                        String thesourcevertex = titikasal.getSelection().getActionCommand();
                        int fromsource = Integer.parseInt(thesourcevertex);
                        int sourceVertex = fromsource;
                        graph.addEdge(axis11, axis12, axis13);
                        graph.addEdge(axis21, axis22, axis23);
                        graph.addEdge(axis31, axis32, axis33);
                        graph.addEdge(axis41, axis42, axis43);
                        graph.addEdge(axis51, axis52, axis53);
                        graph.addEdge(axis61, axis62, axis63);
                        graph.addEdge(axis71, axis72, axis73);
                        graph.addEdge(axis81, axis82, axis83);
                        graph.dijkstra_GetMinDistances(sourceVertex);
                    }
                    row13.setEditable(false);
                    row23.setEditable(false);
                    row33.setEditable(false);
                    row43.setEditable(false);
                    row53.setEditable(false);
                    row63.setEditable(false);
                    row73.setEditable(false);
                    row83.setEditable(false);
                }
            });
            titikkota1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    JComboBox comboBox = (JComboBox) event.getSource();
                    Object selected = comboBox.getSelectedItem();
                    if (selected.toString().equals("Surabaya")) {
                        row11.setText("0");
                    } else if (selected.toString().equals("Semarang")) {
                        row11.setText("1");
                    } else if (selected.toString().equals("Jogjakarta")) {
                        row11.setText("2");
                    } else if (selected.toString().equals("Jakarta")) {
                        row11.setText("3");
                    } else if (selected.toString().equals("Solo")) {
                        row11.setText("4");
                    } else if (selected.toString().equals("Bandung")) {
                        row11.setText("5");
                    } else if (selected.toString().equals("Malang")) {
                        row11.setText("6");
                    } else if (selected.toString().equals("Bekasi")) {
                        row11.setText("7");
                    }
                }
            });
            titikkota2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    JComboBox comboBox = (JComboBox) event.getSource();
                    Object selected = comboBox.getSelectedItem();
                    if (selected.toString().equals("Surabaya")) {
                        row21.setText("0");
                    } else if (selected.toString().equals("Semarang")) {
                        row21.setText("1");
                    } else if (selected.toString().equals("Jogjakarta")) {
                        row21.setText("2");
                    } else if (selected.toString().equals("Jakarta")) {
                        row21.setText("3");
                    } else if (selected.toString().equals("Solo")) {
                        row21.setText("4");
                    } else if (selected.toString().equals("Bandung")) {
                        row21.setText("5");
                    } else if (selected.toString().equals("Malang")) {
                        row21.setText("6");
                    } else if (selected.toString().equals("Bekasi")) {
                        row21.setText("7");
                    }
                }
            });
            titikkota3.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    JComboBox comboBox = (JComboBox) event.getSource();
                    Object selected = comboBox.getSelectedItem();
                    if (selected.toString().equals("Surabaya")) {
                        row31.setText("0");
                    } else if (selected.toString().equals("Semarang")) {
                        row31.setText("1");
                    } else if (selected.toString().equals("Jogjakarta")) {
                        row31.setText("2");
                    } else if (selected.toString().equals("Jakarta")) {
                        row31.setText("3");
                    } else if (selected.toString().equals("Solo")) {
                        row31.setText("4");
                    } else if (selected.toString().equals("Bandung")) {
                        row31.setText("5");
                    } else if (selected.toString().equals("Malang")) {
                        row31.setText("6");
                    } else if (selected.toString().equals("Bekasi")) {
                        row31.setText("7");
                    }
                }
            });
            titikkota4.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    JComboBox comboBox = (JComboBox) event.getSource();
                    Object selected = comboBox.getSelectedItem();
                    if (selected.toString().equals("Surabaya")) {
                        row41.setText("0");
                    } else if (selected.toString().equals("Semarang")) {
                        row41.setText("1");
                    } else if (selected.toString().equals("Jogjakarta")) {
                        row41.setText("2");
                    } else if (selected.toString().equals("Jakarta")) {
                        row41.setText("3");
                    } else if (selected.toString().equals("Solo")) {
                        row41.setText("4");
                    } else if (selected.toString().equals("Bandung")) {
                        row41.setText("5");
                    } else if (selected.toString().equals("Malang")) {
                        row41.setText("6");
                    } else if (selected.toString().equals("Bekasi")) {
                        row41.setText("7");
                    }
                }
            });
            titikkota5.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    JComboBox comboBox = (JComboBox) event.getSource();
                    Object selected = comboBox.getSelectedItem();
                    if (selected.toString().equals("Surabaya")) {
                        row51.setText("0");
                    } else if (selected.toString().equals("Semarang")) {
                        row51.setText("1");
                    } else if (selected.toString().equals("Jogjakarta")) {
                        row51.setText("2");
                    } else if (selected.toString().equals("Jakarta")) {
                        row51.setText("3");
                    } else if (selected.toString().equals("Solo")) {
                        row51.setText("4");
                    } else if (selected.toString().equals("Bandung")) {
                        row51.setText("5");
                    } else if (selected.toString().equals("Malang")) {
                        row51.setText("6");
                    } else if (selected.toString().equals("Bekasi")) {
                        row51.setText("7");
                    }
                }
            });
            titikkota6.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    JComboBox comboBox = (JComboBox) event.getSource();
                    Object selected = comboBox.getSelectedItem();
                    if (selected.toString().equals("Surabaya")) {
                        row61.setText("0");
                    } else if (selected.toString().equals("Semarang")) {
                        row61.setText("1");
                    } else if (selected.toString().equals("Jogjakarta")) {
                        row61.setText("2");
                    } else if (selected.toString().equals("Jakarta")) {
                        row61.setText("3");
                    } else if (selected.toString().equals("Solo")) {
                        row61.setText("4");
                    } else if (selected.toString().equals("Bandung")) {
                        row61.setText("5");
                    } else if (selected.toString().equals("Malang")) {
                        row61.setText("6");
                    } else if (selected.toString().equals("Bekasi")) {
                        row61.setText("7");
                    }
                }
            });
            titikkota7.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    JComboBox comboBox = (JComboBox) event.getSource();
                    Object selected = comboBox.getSelectedItem();
                    if (selected.toString().equals("Surabaya")) {
                        row71.setText("0");
                    } else if (selected.toString().equals("Semarang")) {
                        row71.setText("1");
                    } else if (selected.toString().equals("Jogjakarta")) {
                        row71.setText("2");
                    } else if (selected.toString().equals("Jakarta")) {
                        row71.setText("3");
                    } else if (selected.toString().equals("Solo")) {
                        row71.setText("4");
                    } else if (selected.toString().equals("Bandung")) {
                        row71.setText("5");
                    } else if (selected.toString().equals("Malang")) {
                        row71.setText("6");
                    } else if (selected.toString().equals("Bekasi")) {
                        row71.setText("7");
                    }
                }
            });
            titikkota8.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    JComboBox comboBox = (JComboBox) event.getSource();
                    Object selected = comboBox.getSelectedItem();
                    if (selected.toString().equals("Surabaya")) {
                        row81.setText("0");
                    } else if (selected.toString().equals("Semarang")) {
                        row81.setText("1");
                    } else if (selected.toString().equals("Jogjakarta")) {
                        row81.setText("2");
                    } else if (selected.toString().equals("Jakarta")) {
                        row81.setText("3");
                    } else if (selected.toString().equals("Solo")) {
                        row81.setText("4");
                    } else if (selected.toString().equals("Bandung")) {
                        row81.setText("5");
                    } else if (selected.toString().equals("Malang")) {
                        row81.setText("6");
                    } else if (selected.toString().equals("Bekasi")) {
                        row81.setText("7");
                    }
                }
            });
            destinasikota1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    JComboBox comboBox = (JComboBox) event.getSource();
                    Object selected = comboBox.getSelectedItem();
                    if (selected.toString().equals("Surabaya")) {
                        row12.setText("0");
                    } else if (selected.toString().equals("Semarang")) {
                        row12.setText("1");
                    } else if (selected.toString().equals("Jogjakarta")) {
                        row12.setText("2");
                    } else if (selected.toString().equals("Jakarta")) {
                        row12.setText("3");
                    } else if (selected.toString().equals("Solo")) {
                        row12.setText("4");
                    } else if (selected.toString().equals("Bandung")) {
                        row12.setText("5");
                    } else if (selected.toString().equals("Malang")) {
                        row12.setText("6");
                    } else if (selected.toString().equals("Bekasi")) {
                        row12.setText("7");
                    }
                }
            });
            destinasikota2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    JComboBox comboBox = (JComboBox) event.getSource();
                    Object selected = comboBox.getSelectedItem();
                    if (selected.toString().equals("Surabaya")) {
                        row22.setText("0");
                    } else if (selected.toString().equals("Semarang")) {
                        row22.setText("1");
                    } else if (selected.toString().equals("Jogjakarta")) {
                        row22.setText("2");
                    } else if (selected.toString().equals("Jakarta")) {
                        row22.setText("3");
                    } else if (selected.toString().equals("Solo")) {
                        row22.setText("4");
                    } else if (selected.toString().equals("Bandung")) {
                        row22.setText("5");
                    } else if (selected.toString().equals("Malang")) {
                        row22.setText("6");
                    } else if (selected.toString().equals("Bekasi")) {
                        row22.setText("7");
                    }
                }
            });
            destinasikota3.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    JComboBox comboBox = (JComboBox) event.getSource();
                    Object selected = comboBox.getSelectedItem();
                    if (selected.toString().equals("Surabaya")) {
                        row32.setText("0");
                    } else if (selected.toString().equals("Semarang")) {
                        row32.setText("1");
                    } else if (selected.toString().equals("Jogjakarta")) {
                        row32.setText("2");
                    } else if (selected.toString().equals("Jakarta")) {
                        row32.setText("3");
                    } else if (selected.toString().equals("Solo")) {
                        row32.setText("4");
                    } else if (selected.toString().equals("Bandung")) {
                        row32.setText("5");
                    } else if (selected.toString().equals("Malang")) {
                        row32.setText("6");
                    } else if (selected.toString().equals("Bekasi")) {
                        row32.setText("7");
                    }
                }
            });
            destinasikota4.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    JComboBox comboBox = (JComboBox) event.getSource();
                    Object selected = comboBox.getSelectedItem();
                    if (selected.toString().equals("Surabaya")) {
                        row42.setText("0");
                    } else if (selected.toString().equals("Semarang")) {
                        row42.setText("1");
                    } else if (selected.toString().equals("Jogjakarta")) {
                        row42.setText("2");
                    } else if (selected.toString().equals("Jakarta")) {
                        row42.setText("3");
                    } else if (selected.toString().equals("Solo")) {
                        row42.setText("4");
                    } else if (selected.toString().equals("Bandung")) {
                        row42.setText("5");
                    } else if (selected.toString().equals("Malang")) {
                        row42.setText("6");
                    } else if (selected.toString().equals("Bekasi")) {
                        row42.setText("7");
                    }
                }
            });
            destinasikota5.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    JComboBox comboBox = (JComboBox) event.getSource();
                    Object selected = comboBox.getSelectedItem();
                    if (selected.toString().equals("Surabaya")) {
                        row52.setText("0");
                    } else if (selected.toString().equals("Semarang")) {
                        row52.setText("1");
                    } else if (selected.toString().equals("Jogjakarta")) {
                        row52.setText("2");
                    } else if (selected.toString().equals("Jakarta")) {
                        row52.setText("3");
                    } else if (selected.toString().equals("Solo")) {
                        row52.setText("4");
                    } else if (selected.toString().equals("Bandung")) {
                        row52.setText("5");
                    } else if (selected.toString().equals("Malang")) {
                        row52.setText("6");
                    } else if (selected.toString().equals("Bekasi")) {
                        row52.setText("7");
                    }
                }
            });
            destinasikota6.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    JComboBox comboBox = (JComboBox) event.getSource();
                    Object selected = comboBox.getSelectedItem();
                    if (selected.toString().equals("Surabaya")) {
                        row62.setText("0");
                    } else if (selected.toString().equals("Semarang")) {
                        row62.setText("1");
                    } else if (selected.toString().equals("Jogjakarta")) {
                        row62.setText("2");
                    } else if (selected.toString().equals("Jakarta")) {
                        row62.setText("3");
                    } else if (selected.toString().equals("Solo")) {
                        row62.setText("4");
                    } else if (selected.toString().equals("Bandung")) {
                        row62.setText("5");
                    } else if (selected.toString().equals("Malang")) {
                        row62.setText("6");
                    } else if (selected.toString().equals("Bekasi")) {
                        row62.setText("7");
                    }
                }
            });
            destinasikota7.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    JComboBox comboBox = (JComboBox) event.getSource();
                    Object selected = comboBox.getSelectedItem();
                    if (selected.toString().equals("Surabaya")) {
                        row72.setText("0");
                    } else if (selected.toString().equals("Semarang")) {
                        row72.setText("1");
                    } else if (selected.toString().equals("Jogjakarta")) {
                        row72.setText("2");
                    } else if (selected.toString().equals("Jakarta")) {
                        row72.setText("3");
                    } else if (selected.toString().equals("Solo")) {
                        row72.setText("4");
                    } else if (selected.toString().equals("Bandung")) {
                        row72.setText("5");
                    } else if (selected.toString().equals("Malang")) {
                        row72.setText("6");
                    } else if (selected.toString().equals("Bekasi")) {
                        row72.setText("7");
                    }
                }
            });
            destinasikota8.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    JComboBox comboBox = (JComboBox) event.getSource();
                    Object selected = comboBox.getSelectedItem();
                    if (selected.toString().equals("Surabaya")) {
                        row82.setText("0");
                    } else if (selected.toString().equals("Semarang")) {
                        row82.setText("1");
                    } else if (selected.toString().equals("Jogjakarta")) {
                        row82.setText("2");
                    } else if (selected.toString().equals("Jakarta")) {
                        row82.setText("3");
                    } else if (selected.toString().equals("Solo")) {
                        row82.setText("4");
                    } else if (selected.toString().equals("Bandung")) {
                        row82.setText("5");
                    } else if (selected.toString().equals("Malang")) {
                        row82.setText("6");
                    } else if (selected.toString().equals("Bekasi")) {
                        row82.setText("7");
                    }
                }
            });
            carapenggunaan.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    tmbkActionPerformed(evt);
                }

                private void tmbkActionPerformed(ActionEvent evt) {
                    CARA daftar = new CARA();
                }
            });
        }
    }

    static class CARA {

        JFrame frame;
        JButton lanjut;
        JLabel caranya, satu, dua, tiga, empat, lima, judul;

        CARA() {
            frame = new JFrame("Cara Penggunaan");
            frame.setSize(1000, 625);
            frame.getContentPane().setBackground(Color.WHITE);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setLayout(null);
            frame.setResizable(false);
            judul = new JLabel("CARA PENGGUNAAN");
            judul.setBounds(330, 25, 340, 60);
            judul.setBackground(Color.WHITE);
            judul.setFont(new Font("Times New Roman", Font.BOLD, 33));
            lanjut = new JButton("Keluar dari Cara Penggunaan");
            lanjut.setBounds(310, 500, 380, 40);
            lanjut.setBackground(Color.WHITE);
            lanjut.setFont(new Font("Times New Roman", Font.BOLD, 20));
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (ClassNotFoundException | InstantiationException
                    | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            }
            StringBuilder sb = new StringBuilder(30);
            sb.append("<html>Masukkan jumlah titik dan titik asal daerah yang akan dilakukan proses kalkulasi biaya pengantaran "
                    + "dengan memilih salah satu radio button (titik asal harus ada dalam list titik pecarian).<br><br>").
                    append("Masukkan nama daerah yang menjadi titik asal dan destinasi dengan memilih daerah "
                            + "di combo box di sebelah kotak isian (text field) sesuai dengan uturan dan jumlah masukan "
                            + "harus sama dengan jumlah titik asal dan jangan lupa masukkan jarak.<br><br>").
                    append("Klik tombol cari.<br><br>").
                    append("Pilih jenis PO untuk mendapatkan biaya pengantaran yang "
                            + "akan tampil pada JOptionPane.<br><br>").
                    append("Surabaya harus ada dikarenakan pusat semua PO berada di Surabaya<br><br></html>");
            caranya = new JLabel(sb.toString());
            caranya.setBounds(50, 90, 940, 400);
            caranya.setBackground(Color.WHITE);
            caranya.setFont(new Font("Times New Roman", Font.BOLD, 23));
            satu = new JLabel("1.");
            satu.setBounds(20, 100, 130, 30);
            satu.setBackground(Color.WHITE);
            satu.setFont(new Font("Times New Roman", Font.BOLD, 23));
            dua = new JLabel("2.");
            dua.setBounds(20, 208, 130, 30);
            dua.setBackground(Color.WHITE);
            dua.setFont(new Font("Times New Roman", Font.BOLD, 23));
            tiga = new JLabel("3.");
            tiga.setBounds(20, 316, 130, 30);
            tiga.setBackground(Color.WHITE);
            tiga.setFont(new Font("Times New Roman", Font.BOLD, 23));
            empat = new JLabel("4.");
            empat.setBounds(20, 370, 130, 30);
            empat.setBackground(Color.WHITE);
            empat.setFont(new Font("Times New Roman", Font.BOLD, 23));
            lima = new JLabel("5.");
            lima.setBounds(20, 423, 130, 30);
            lima.setBackground(Color.WHITE);
            lima.setFont(new Font("Times New Roman", Font.BOLD, 23));
            frame.add(judul);
            frame.add(lanjut);
            frame.add(caranya);
            frame.add(satu);
            frame.add(dua);
            frame.add(tiga);
            frame.add(empat);
            frame.add(lima);
            frame.setVisible(true);
            lanjut.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    tmbkActionPerformed(evt);
                }

                private void tmbkActionPerformed(ActionEvent evt) {
                    frame.dispose();
                }
            });
        }
    }

    static class COVER {

        JFrame frame;
        JButton hitung, carapenggunaan;
        JLabel judul, bg;

        COVER() {
            frame = new JFrame("Penghitungan Biaya Pengantaran PO Travel Berdasarkan Jarak Terdekat");
            frame.setSize(1000, 625);
            frame.getContentPane().setBackground(Color.WHITE);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setLayout(null);
            frame.setResizable(false);
            judul = new JLabel("Penghitungan Biaya PO Travel Berdasarkan Jarak Terdekat");
            judul.setBounds(180, 20, 630, 30);
            judul.setBackground(Color.WHITE);
            judul.setFont(new Font("Times New Roman", Font.BOLD, 24));
            hitung = new JButton("Penghitungan Biaya");
            hitung.setBounds(310, 490, 380, 40);
            hitung.setBackground(Color.WHITE);
            hitung.setFont(new Font("Times New Roman", Font.BOLD, 20));
            carapenggunaan = new JButton("Cara Penggunaan");
            carapenggunaan.setBounds(310, 540, 380, 40);
            carapenggunaan.setBackground(Color.WHITE);
            carapenggunaan.setFont(new Font("Times New Roman", Font.BOLD, 20));
            bg = new JLabel(new ImageIcon("C:\\Users\\Lenovo\\Documents\\NetBeansProjects\\MyGraph\\src\\Final_Project\\Image\\Bus\\BG.jpg"));
            bg.setBounds(0, 0, 1000, 625);
            frame.add(judul);
            frame.add(hitung);
            frame.add(carapenggunaan);
            frame.add(bg);
            frame.setVisible(true);
            hitung.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    tmbkActionPerformed(evt);
                }

                private void tmbkActionPerformed(ActionEvent evt) {
                    DAFTAR daftar = new DAFTAR();
                    frame.setVisible(false);
                }
            });
            carapenggunaan.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    tmbkActionPerformed(evt);
                }

                private void tmbkActionPerformed(ActionEvent evt) {
                    CARA daftar = new CARA();
                }
            });
        }
    }

    public static void main(String[] args) {
        COVER cara = new COVER();
    }
}
