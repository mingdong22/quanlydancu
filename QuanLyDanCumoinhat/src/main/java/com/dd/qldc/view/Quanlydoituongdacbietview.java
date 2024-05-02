/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.dd.qldc.view;

import com.dd.qldc.entity.Doituongdacbiet;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author ASUS_PC
 */
public class Quanlydoituongdacbietview extends javax.swing.JFrame {

    private SimpleDateFormat fDate=new SimpleDateFormat("dd/MM/yyyy");
    private String filename=null;
    private byte[] specialPerson_image=null;
    private byte[] image=null;
    private String [] columnNames=new String[]{
        "STT", "Họ và tên", "Năm sinh", "Quê quán", "Ngày mở hồ sơ", "Loại đối tượng", "Ảnh"
    };
    private String [] columnNames2=new String[]{
       "<none>","Số lượng"
    };
    private Object data = new Object [][] {};
    //Chart chart=new Chart();
    /**
     * Creates new form Quanlydoituongdacbietview
     */
    public Quanlydoituongdacbietview() {
        initComponents();
        btnthem.setEnabled(true);
        btnsua.setEnabled(false);
        btnxoa.setEnabled(false);
        btntimkiem.setEnabled(true);
        lblImage.setIcon(new ImageIcon("default-image.png"));
        tableSpecialPerson.setDefaultRenderer(Object.class,new MyRenderer());
        tableStatistic.setDefaultRenderer(Object.class, new MyRenderer2());
        
    }
    private static Image getCircleImage(Image image) {
        int width = image.getWidth(null);
        int height = image.getHeight(null);
        BufferedImage circleImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = circleImage.createGraphics();
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Ellipse2D.Double circle = new Ellipse2D.Double(0, 0, width, height);
        graphics.setClip(circle);
        graphics.drawImage(image, 0, 0, null);
        graphics.setColor(Color.WHITE);
        graphics.setStroke(new BasicStroke(2));
        graphics.draw(circle);
        return circleImage;
    }
     private ImageIcon ImageIconSize(JLabel label, String filename)
    {
        Image image = new ImageIcon(filename).getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon imageIcon=new ImageIcon(image);
        //jLabel14.setIcon(new ImageIcon(getCircleImage(imageIcon.getImage())));
        return imageIcon;
    }
    private String abbreviation(String name) {
        return name;
    }
    public class MyRenderer extends DefaultTableCellRenderer{
         public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
            TableColumnModel columnModel=table.getColumnModel();
            columnModel.getColumn(0).setPreferredWidth(10);
            columnModel.getColumn(1).setPreferredWidth(150);
            columnModel.getColumn(2).setPreferredWidth(60);
            columnModel.getColumn(3).setPreferredWidth(200);
            columnModel.getColumn(4).setPreferredWidth(50);
            columnModel.getColumn(5).setPreferredWidth(100);
            //columnModel.getColumn(0).setPreferredWidth(5);
            JTableHeader header = table.getTableHeader();
            header.setBackground(new Color(0, 0, 139));
            header.setForeground(Color.WHITE);
            header.setFont(new java.awt.Font("Times New Roman", 0, 18));
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
            if (!isSelected) {
                if (row % 2 == 0) {
                    c.setBackground(new Color(191, 239, 255));
                } else {
                    c.setBackground(new Color(135, 206, 250));
                }
            } else {
                c.setBackground(new Color(193, 255, 193));
            }

            return c;
        }
    }
    public class MyRenderer2 extends DefaultTableCellRenderer
    {
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
            TableColumnModel columnModel=table.getColumnModel();
            //columnModel.getColumn(0).setPreferredWidth(5);
            JTableHeader header = table.getTableHeader();
            header.setBackground(new Color(0, 0, 139));
            header.setForeground(Color.WHITE);
            header.setFont(new java.awt.Font("Times New Roman", 0, 18));
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
            if (!isSelected) {
                if (row % 2 == 0) {
                    c.setBackground(new Color(191, 239, 255));
                } else {
                    c.setBackground(new Color(135, 206, 250));
                }
            } else {
                c.setBackground(new Color(193, 255, 193));
            }

            return c;
        }
    }
        
    public static String capitalizeWords(String str) {
        str = str.toLowerCase();
        String[] words = str.split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            if (word.length() > 0) {
                if (word.equals("tt") || word.equals("tp") || word.equals("tx")) {
                    sb.append(word.toUpperCase());
                } else {
                    sb.append(Character.toUpperCase(word.charAt(0)));
                    sb.append(word.substring(1));
                }
                sb.append(" ");
            }
        }
        return sb.toString().trim();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        SearchDialog = new javax.swing.JDialog();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        fieldsearch = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        CheckBoxname = new javax.swing.JCheckBox();
        CheckBoxadress = new javax.swing.JCheckBox();
        CheckBoxyear = new javax.swing.JCheckBox();
        btnSearchDialog = new javax.swing.JButton();
        btnCancelSearchDialog = new javax.swing.JButton();
        StatisticView = new javax.swing.JFrame();
        jPanel8 = new javax.swing.JPanel();
        btnStatisticAge = new javax.swing.JButton();
        btnStatisticType = new javax.swing.JButton();
        btnStatisticUndo = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        lblTable = new javax.swing.JLabel();
        lblChart = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableStatistic = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        btnthem = new javax.swing.JButton();
        btnsua = new javax.swing.JButton();
        btnxoa = new javax.swing.JButton();
        btnlammoi = new javax.swing.JButton();
        btntimkiem = new javax.swing.JButton();
        btnhuy = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        fieldsum = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        fieldid = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        fieldname = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        BirthdayChooser = new com.toedter.calendar.JDateChooser();
        fieldquequan = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        ComboBoxType = new javax.swing.JComboBox<>();
        OpeningDateChooser = new com.toedter.calendar.JDateChooser();
        jPanel4 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        lblImage = new javax.swing.JLabel();
        btnthemanh = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        btnsxten = new javax.swing.JButton();
        btnsxnamsinh = new javax.swing.JButton();
        btnsxhoso = new javax.swing.JButton();
        btnthongke = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableSpecialPerson = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();

        SearchDialog.setSize(new java.awt.Dimension(400, 400));
        SearchDialog.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel13.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Tìm kiếm");
        SearchDialog.getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(138, 29, 109, 43));

        jLabel14.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jLabel14.setText("Nhập nội dung tìm kiếm:");
        SearchDialog.getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(41, 90, 184, 40));

        fieldsearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldsearchActionPerformed(evt);
            }
        });
        SearchDialog.getContentPane().add(fieldsearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(72, 136, 259, 34));

        jLabel15.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jLabel15.setText("Chọn tiêu chí tìm kiếm:");
        SearchDialog.getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(41, 197, 176, 32));

        CheckBoxname.setText("tên");
        SearchDialog.getContentPane().add(CheckBoxname, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 235, 85, -1));

        CheckBoxadress.setText("Quê quán");
        CheckBoxadress.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CheckBoxadressActionPerformed(evt);
            }
        });
        SearchDialog.getContentPane().add(CheckBoxadress, new org.netbeans.lib.awtextra.AbsoluteConstraints(141, 235, 85, -1));

        CheckBoxyear.setText("Năm sinh");
        SearchDialog.getContentPane().add(CheckBoxyear, new org.netbeans.lib.awtextra.AbsoluteConstraints(279, 235, 85, -1));

        btnSearchDialog.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnSearchDialog.setText("Tìm kiếm");
        btnSearchDialog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchDialogActionPerformed(evt);
            }
        });
        SearchDialog.getContentPane().add(btnSearchDialog, new org.netbeans.lib.awtextra.AbsoluteConstraints(61, 273, 111, 45));

        btnCancelSearchDialog.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnCancelSearchDialog.setText("Hủy");
        SearchDialog.getContentPane().add(btnCancelSearchDialog, new org.netbeans.lib.awtextra.AbsoluteConstraints(227, 273, 101, 45));

        StatisticView.setSize(new java.awt.Dimension(1500, 700));
        StatisticView.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel8.setBackground(new java.awt.Color(153, 153, 255));

        btnStatisticAge.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        btnStatisticAge.setText("Tuổi");
        btnStatisticAge.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStatisticAgeActionPerformed(evt);
            }
        });

        btnStatisticType.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        btnStatisticType.setText("Loại đối tượng");
        btnStatisticType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStatisticTypeActionPerformed(evt);
            }
        });

        btnStatisticUndo.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        btnStatisticUndo.setText("Quay lại");
        btnStatisticUndo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStatisticUndoActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Thống kê theo:");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnStatisticType, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnStatisticUndo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnStatisticAge, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel16)))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(95, 95, 95)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnStatisticAge, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(btnStatisticType, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnStatisticUndo, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62))
        );

        StatisticView.getContentPane().add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 640));

        jPanel9.setBackground(new java.awt.Color(153, 153, 255));

        jLabel17.setFont(new java.awt.Font("Segoe UI Black", 0, 36)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Thống kê");

        lblTable.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        lblTable.setForeground(new java.awt.Color(255, 255, 255));
        lblTable.setText("Thống kê số lượng theo mục");

        lblChart.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        lblChart.setForeground(new java.awt.Color(255, 255, 255));
        lblChart.setText("Biểu đồ");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(373, 373, 373))
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(lblTable, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 523, Short.MAX_VALUE)
                .addComponent(lblChart, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(163, 163, 163))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblTable, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    .addComponent(lblChart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(16, 16, 16))
        );

        StatisticView.getContentPane().add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(211, 0, 1260, -1));

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tableStatistic.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tableStatistic);

        jPanel10.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 170, -1, 454));

        StatisticView.getContentPane().add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1480, 650));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(204, 204, 255));

        btnthem.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        btnthem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/dd/qldc/images/add-1-icon.png"))); // NOI18N
        btnthem.setText("Thêm");
        btnthem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthemActionPerformed(evt);
            }
        });

        btnsua.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        btnsua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/dd/qldc/images/Text-Edit-icon.png"))); // NOI18N
        btnsua.setText("Sửa");
        btnsua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsuaActionPerformed(evt);
            }
        });

        btnxoa.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        btnxoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/dd/qldc/images/Close-icon.png"))); // NOI18N
        btnxoa.setText("Xóa");
        btnxoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxoaActionPerformed(evt);
            }
        });

        btnlammoi.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        btnlammoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/dd/qldc/images/reset-icon.png"))); // NOI18N
        btnlammoi.setText("Làm mới");
        btnlammoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnlammoiActionPerformed(evt);
            }
        });

        btntimkiem.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        btntimkiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/dd/qldc/images/find-icon.png"))); // NOI18N
        btntimkiem.setText("Tìm kiếm ");
        btntimkiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntimkiemActionPerformed(evt);
            }
        });

        btnhuy.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        btnhuy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/dd/qldc/images/cancel-icon.png"))); // NOI18N
        btnhuy.setText("Hủy tìm kiếm");
        btnhuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhuyActionPerformed(evt);
            }
        });

        btnBack.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        btnBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/dd/qldc/images/edit-undo-icon.png"))); // NOI18N
        btnBack.setText("Quay Lại");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnhuy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnxoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnthem, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnsua, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btntimkiem, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                    .addComponent(btnlammoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnBack, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(btnthem, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addComponent(btnsua, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addComponent(btnxoa, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addComponent(btnlammoi, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addComponent(btntimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addComponent(btnhuy, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(4, 169, 180, 530));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel6.setBackground(new java.awt.Color(51, 153, 255));

        jLabel11.setFont(new java.awt.Font("Segoe UI Black", 0, 54)); // NOI18N
        jLabel11.setText("QUẢN LÝ ĐỐI TƯỢNG ĐẶC BIỆT");

        ImageIcon imageicon=new ImageIcon("src/main/java/com/dd/qldc/images/anh.png");
        Image imageI=imageicon.getImage().getScaledInstance(200, 100, Image.SCALE_SMOOTH);
        imageicon=new ImageIcon(imageI);
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/dd/qldc/images/anh.png"))); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(243, Short.MAX_VALUE)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 889, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 26, Short.MAX_VALUE))
        );

        jPanel7.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 50, 1410, 130));

        ImageIcon imageIcon = new ImageIcon("src/main/java/com/dd/qldc/images/logo.png");
        Image image = imageIcon.getImage().getScaledInstance(100,100, Image.SCALE_SMOOTH);
        imageIcon=new ImageIcon(image);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/dd/qldc/images/logo.png"))); // NOI18N
        jLabel1.setToolTipText("");
        jPanel7.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 121, 120));

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        jLabel2.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jLabel2.setText("Tổng số đối tượng:");

        fieldsum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldsumActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jLabel3.setText("ID:");

        fieldid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldidActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jLabel4.setText("Họ và tên:");

        jLabel5.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jLabel5.setText("Ngày sinh:");

        jLabel6.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jLabel6.setText("Quê quán:");

        BirthdayChooser.setBackground(new java.awt.Color(255, 255, 255));
        BirthdayChooser.setDateFormatString("dd/MM/yyyy");

        fieldquequan.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fieldid, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(BirthdayChooser, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(fieldname, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE))
                            .addComponent(fieldquequan, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(fieldsum, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fieldsum, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fieldid, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(fieldname, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BirthdayChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                    .addComponent(fieldquequan, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel7.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 190, 410, 230));

        jPanel3.setBackground(new java.awt.Color(204, 204, 255));

        jLabel7.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jLabel7.setText("Ngày mở hồ sơ:");

        jLabel8.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jLabel8.setText("Loại đối tượng:");

        ComboBoxType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mại dâm", "Ma túy", "Trộm cắp", "Buôn bán chất cắm" }));

        OpeningDateChooser.setBackground(new java.awt.Color(255, 255, 255));
        OpeningDateChooser.setDateFormatString("dd/MM/yyyy");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ComboBoxType, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(OpeningDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(78, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(OpeningDateChooser, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE))
                .addGap(35, 35, 35)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ComboBoxType, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jPanel7.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 430, 410, 150));

        jPanel4.setBackground(new java.awt.Color(204, 204, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jLabel9.setText("Ảnh");
        jPanel4.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 40, 54, 33));
        jPanel4.add(lblImage, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 10, 110, 100));

        btnthemanh.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        btnthemanh.setText("Thêm ảnh");
        btnthemanh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthemanhActionPerformed(evt);
            }
        });
        jPanel4.add(btnthemanh, new org.netbeans.lib.awtextra.AbsoluteConstraints(244, 40, -1, 33));

        jPanel7.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 590, 410, 130));

        jPanel5.setBackground(new java.awt.Color(204, 204, 255));

        btnsxten.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        btnsxten.setText("Sắp xếp theo tên");
        btnsxten.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsxtenActionPerformed(evt);
            }
        });

        btnsxnamsinh.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        btnsxnamsinh.setText("Sắp xếp theo năm sinh");
        btnsxnamsinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsxnamsinhActionPerformed(evt);
            }
        });

        btnsxhoso.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        btnsxhoso.setText("Sắp xếp theo hồ sơ");
        btnsxhoso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsxhosoActionPerformed(evt);
            }
        });

        btnthongke.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        btnthongke.setText("Thống kê theo loại đối tượng");
        btnthongke.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthongkeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(btnsxten)
                .addGap(65, 65, 65)
                .addComponent(btnsxnamsinh)
                .addGap(65, 65, 65)
                .addComponent(btnsxhoso)
                .addGap(68, 68, 68)
                .addComponent(btnthongke, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(83, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnsxnamsinh, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnsxten, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnsxhoso, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnthongke, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jPanel7.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 190, 990, -1));

        tableSpecialPerson.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            }, columnNames
        ));
        tableSpecialPerson.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_LAST_COLUMN);

        tableSpecialPerson.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        tableSpecialPerson.setRowHeight(30);

        tableSpecialPerson.removeColumn(tableSpecialPerson.getColumnModel().getColumn(6));
        jScrollPane1.setViewportView(tableSpecialPerson);

        jPanel7.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 300, 994, 420));

        getContentPane().add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -20, 1610, 740));
        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fieldsumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldsumActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldsumActionPerformed

    private void btntimkiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntimkiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btntimkiemActionPerformed

    private void btnthemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnthemActionPerformed

    private void btnsuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsuaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnsuaActionPerformed

    private void btnxoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxoaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnxoaActionPerformed

    private void btnlammoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlammoiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnlammoiActionPerformed

    private void btnhuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhuyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnhuyActionPerformed

    private void btnsxtenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsxtenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnsxtenActionPerformed

    private void btnsxnamsinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsxnamsinhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnsxnamsinhActionPerformed

    private void btnsxhosoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsxhosoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnsxhosoActionPerformed

    private void btnthongkeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthongkeActionPerformed
        // TODO add your handling code here:
        SwingUtilities.invokeLater(() -> {
            TypeBarChart chart = new TypeBarChart("Biểu đồ loại đối tượng");
            chart.pack();
            //chart.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            chart.setLocationRelativeTo(null);
            chart.setVisible(true);
        });
    }//GEN-LAST:event_btnthongkeActionPerformed

    private void btnthemanhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthemanhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnthemanhActionPerformed

    private void fieldidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldidActionPerformed

    private void fieldsearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldsearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldsearchActionPerformed

    private void CheckBoxadressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CheckBoxadressActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CheckBoxadressActionPerformed

    private void btnSearchDialogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchDialogActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSearchDialogActionPerformed

    private void btnStatisticUndoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStatisticUndoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnStatisticUndoActionPerformed

    private void btnStatisticAgeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStatisticAgeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnStatisticAgeActionPerformed

    private void btnStatisticTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStatisticTypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnStatisticTypeActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Quanlydoituongdacbietview.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Quanlydoituongdacbietview.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Quanlydoituongdacbietview.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Quanlydoituongdacbietview.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Quanlydoituongdacbietview().setVisible(true);
            }
        });
    }
    public void showMessage(String message)
    {
        JOptionPane.showMessageDialog(this, message);
    }
     public class RoundedBorder implements Border {
        private int radius;
        RoundedBorder(int radius) {
            this.radius = radius;
        }
        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
        }
        public boolean isBorderOpaque() {
            return true;
        }
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.drawRoundRect(x, y, width-1, height-1, radius, radius);
        }
    }
    public void SpecialPersonImage()
    {
        String lastImagePath = "";
        JFileChooser chooser=new JFileChooser(lastImagePath);
        chooser.setDialogTitle("Chọn ảnh");
        // Giới hạn chọn tệp đến các tệp hình ảnh
        chooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
            public boolean accept(File f) {
                return f.getName().toLowerCase().endsWith(".jpg")
                    || f.getName().toLowerCase().endsWith(".png")
                    || f.getName().toLowerCase().endsWith(".gif")
                    || f.isDirectory();
            }
            public String getDescription() {
                return "Hình ảnh (*.jpg, *.png, *.gif)";
            }
        });
        chooser.showOpenDialog(null);
        File f=chooser.getSelectedFile();
        filename=f.getAbsolutePath();
        lastImagePath = f.getPath();
        ImageIcon imageIcon=new ImageIcon(new ImageIcon(filename).getImage().getScaledInstance(lblImage.getWidth(), lblImage.getHeight(), Image.SCALE_SMOOTH));
        lblImage.setIcon(imageIcon);
        try
        {
            File image=new File(filename);
            FileInputStream fis=new FileInputStream(image);
            ByteArrayOutputStream bos=new ByteArrayOutputStream();
            byte[] buf=new byte[1024];
            for (int readNum;(readNum=fis.read(buf))!=-1;)
            {
                bos.write(buf, 0, readNum);
            }
            specialPerson_image=bos.toByteArray();
        }
        catch (Exception e)
        {
            showMessage(e.toString());
        }
    }
    public void showListSpecialPersons(List<Doituongdacbiet> list)
    {
        int size=list.size();
        Object [][] doituongdacbiets=new Object[size][7];
        for(int i=0;i<size;i++)
        {
            doituongdacbiets[i][0]=list.get(i).getId();
            doituongdacbiets[i][1]=list.get(i).getName();
            doituongdacbiets[i][2]= fDate.format(list.get(i).getBirthday());
            doituongdacbiets[i][3]=list.get(i).getAddress();
            doituongdacbiets[i][4]=fDate.format(list.get(i).getOpeningDate());
            doituongdacbiets[i][5]=list.get(i).getType();
            doituongdacbiets[i][6]=list.get(i).getImage();
        }
        tableSpecialPerson.setModel(new DefaultTableModel(doituongdacbiets,columnNames));
        tableSpecialPerson.removeColumn(tableSpecialPerson.getColumnModel().getColumn(6));
        //tableSpecialPerson.getColumnModel().getColumn(0).setWidth(3);
    }
    public void showCountListSpecialPerson(List<Doituongdacbiet> list)
    {
        int size=list.size();
        fieldsum.setText(String.valueOf(size));
    }
    public void fillSpecialPersonFromSelectedRow(List<Doituongdacbiet> list) throws ParseException
    {
        // lấy chỉ số của hàng được chọn 
        int row = tableSpecialPerson.getSelectedRow();
        if (row >= 0) {
            int residentID = Integer.parseInt(tableSpecialPerson.getModel().getValueAt(row, 0).toString());
            Doituongdacbiet selected = findDoiTuongByID(list, residentID);

            if (selected != null) {
                fieldid.setText(String.valueOf(selected.getId()));
                fieldname.setText(selected.getName());
                //FieldName.setText(selectedResident.getName());
                BirthdayChooser.setDate(fDate.parse(tableSpecialPerson.getModel().getValueAt(row, 2).toString()));
                fieldquequan.setText(selected.getAddress());
                //FieldOpeningDate.setText("" + fDate.format(resident.getOpeningDate()));
                OpeningDateChooser.setDate(fDate.parse(tableSpecialPerson.getModel().getValueAt(row, 4).toString()));
                ComboBoxType.setSelectedItem(selected.getType());
                //FieldBirthPlace.setText(selectedResident.getBirthPlace());
                
                byte[] img=(byte[]) selected.getImage();
                specialPerson_image=img;
                ImageIcon imageIcon=new ImageIcon(new ImageIcon(img).getImage().getScaledInstance(lblImage.getWidth(), lblImage.getHeight(), Image.SCALE_SMOOTH));
                lblImage.setIcon(imageIcon);
                // enable Edit and Delete buttons
                btnsua.setEnabled(true);
                btnxoa.setEnabled(true);
                // disable Add button
                btnthem.setEnabled(false);
                btnlammoi.setEnabled(true);
            }
        }
    }
    
    private Doituongdacbiet findDoiTuongByID(List<Doituongdacbiet> residentsList, int residentID) {
        for (Doituongdacbiet resident : residentsList) {
            if (resident.getId() == residentID) {
                return resident;
            }
        }
        return null; // Trả về null nếu không tìm thấy đối tượng
    }
    
    public void clearSpecialPersonInfo()
    {
        fieldid.setText("");
        fieldname.setText("");
        BirthdayChooser.setDate(null);
        fieldquequan.setText("");
        OpeningDateChooser.setDate(null);
        lblImage.setIcon(new ImageIcon("default-image.png"));
        specialPerson_image=null;
        ComboBoxType.setSelectedItem("<none>");
        btnsua.setEnabled(false);
        btnxoa.setEnabled(false);
        btnthem.setEnabled(true);
    }
    public void searchNameSpecialPersonInfo()
    {
        SearchDialog.setVisible(true);
    }
    public void displayStatisticView()
    {
        StatisticView.setVisible(true);
        Quanlydoituongdacbietview.this.setVisible(false);
        StatisticView.addWindowListener(new WindowAdapter (){
            public void windowClosing(WindowEvent e){
                StatisticView.dispose();
                System.exit(0);
            }
        });
    }
    public void cancelDialogSearchSpecialPersonInfo(){
        SearchDialog.setVisible(false);
    }
    public void showSpecialPerson(Doituongdacbiet specialPerson)
    {
        fieldid.setText(""+specialPerson.getId());
        fieldname.setText(specialPerson.getName());
        BirthdayChooser.setDate(specialPerson.getBirthday());
        fieldquequan.setText(specialPerson.getAddress());
        OpeningDateChooser.setDate(specialPerson.getOpeningDate());
        ComboBoxType.setSelectedItem(""+specialPerson.getType());
        byte[] img=specialPerson.getImage();
        ImageIcon imageIcon=new ImageIcon(new ImageIcon(img).getImage().getScaledInstance(200, 300, Image.SCALE_SMOOTH));
        lblImage.setIcon(imageIcon);
        btnsua.setEnabled(true);
        btnxoa.setEnabled(true);
        btnthem.setEnabled(false);
        
    }
    public Doituongdacbiet getSpecialPersonInfo()
    {
        if(!validateName() || !validateyear() || !validateAddress() || !validateImage() || !validateOpeningDate() || !validateType())
        {
            return null;
        }
        try
        {
            Doituongdacbiet specialPerson=new Doituongdacbiet();
            if(fieldid.getText()!=null && !"".equals(fieldid.getText()))
            {
                specialPerson.setId(Integer.parseInt(fieldid.getText()));
            }
            specialPerson.setName(capitalizeWords(fieldname.getText().trim()));
            specialPerson.setBirthday(BirthdayChooser.getDate());
            specialPerson.setAddress(capitalizeWords(fieldquequan.getText().trim()));
            specialPerson.setOpeningDate(OpeningDateChooser.getDate());
            specialPerson.setType(ComboBoxType.getSelectedItem().toString().trim());
            specialPerson.setImage(specialPerson_image);
            return specialPerson;
        }
        catch(Exception e)
        {
            showMessage(e.getMessage());
        }
        return null;
    }
    private boolean validateName()
    {
        String name=fieldname.getText();
        if(name==null||"".equals(name.trim()))
        {
            fieldname.requestFocus();
            showMessage("Họ và tên không được để trống");
            return false;
        }
        return true;
    }
    private boolean validateType()
    {
        String type=ComboBoxType.getSelectedItem().toString().trim();
        if(type.equals("<none>"))
        {
            ComboBoxType.requestFocus();
            showMessage("Bạn chưa chọn loại đối tượng");
            return false;
        }
        return true;
    }
    private boolean validateImage()
    {
        byte[]k=specialPerson_image;
        if(k==null)
        {
            showMessage("Bạn chưa tải ảnh lên");
            return false;
        }
        return true;
    }
    private boolean validateAddress()
    {
        String adress=fieldquequan.getText();
        if(adress==null||"".equals(adress.trim()))
        {
            showMessage("Quê quán không được để trống");
            return false;
        }
        return true;
    }
    private boolean validateyear()
    {
         try {
            java.util.Date today=new java.util.Date();
            Date date=BirthdayChooser.getDate();
            if (date.compareTo(today)==1) {
                BirthdayChooser.requestFocus();
                showMessage("Ngày nhập không tồn tại hoặc lớn hơn ngày hôm nay");
                return false;
            }
        } catch (Exception e) {
            BirthdayChooser.requestFocus();
            showMessage("Ngày sinh không được trống");
            return false;
        }
        return true;
    }
    private boolean validateOpeningDate()
    {
        try
        {
            java.util.Date today=new java.util.Date();
            Date date=OpeningDateChooser.getDate();
            if(date.compareTo(today)==1)
            {
                OpeningDateChooser.requestFocus();
                showMessage("Ngày nhập không tồn tại hoặc lớn hơn ngày hôm nay");
                return false;
            }
            
        }
        catch(Exception e)
                {
                    OpeningDateChooser.requestFocus();
                    showMessage("Bạn đã nhập ngày sai định dạng");
                    return false;
                         
                }
        return true;
    }
    public int getChooserSelectSearch()
    {
        if(CheckBoxname.isSelected())return 1;
        else if(CheckBoxadress.isSelected()) return 2;
        else if(CheckBoxyear.isSelected()) return 3;
        return 0;
    }
    public String validateSearch()
    {
        String search=fieldsearch.getText();
        if(search==null||"".equals(search.trim()))
        {
            fieldsearch.requestFocus();
            showMessage("Nội dung tìm kiếm không hợp lệ!");
            return "";
        }
        btnhuy.setEnabled(true);
        SearchDialog.setVisible(false);
        btnthem.setEnabled(false);
        btnsua.setEnabled(true);
        btnxoa.setEnabled(true);
        btnlammoi.setEnabled(false);
        btntimkiem.setEnabled(false);
        return search;
    }
    public void cancelSearchSpecialPerson()
    {
        String id=fieldid.getText();
        btnhuy.setEnabled(false);
        btntimkiem.setEnabled(true);
        btnlammoi.setEnabled(true);
        if(id==null || "".equals(id.trim()))
        {
            btnthem.setEnabled(true);
            btnsua.setEnabled(false);
            btnxoa.setEnabled(false);
        }
        else
        {
            btnthem.setEnabled(false);
            btnsua.setEnabled(true);
            btnxoa.setEnabled(true);
        }
        
    }
    public void UndoViewSpecialPerson()
    {
        StatisticView.setVisible(false);
        Quanlydoituongdacbietview.this.setVisible(true);
    }
    public void showstatisticTypeSpecialPersons(List<Doituongdacbiet> list)
    {
        
    }
    public void showstatisticAgeSpecialPersons(List<Doituongdacbiet> list)
    {
        
    }
    private int calculateAge(Date birthdate, Date referenceDate)
    {
        if((birthdate==null) && (referenceDate==null))
        {
            Calendar birthCalendar=Calendar.getInstance();
            Calendar referenceCalendar=Calendar.getInstance();
            birthCalendar.setTime(birthdate);
            referenceCalendar.setTime(referenceDate);
            int birthYear=birthCalendar.get(Calendar.YEAR);
            int referenceyear=referenceCalendar.get(Calendar.YEAR);
            return referenceyear-birthYear;
            
        }
        else
        {
            return 0;
        }
    }
    private double ConvertToDouble(Object k)
    {
        return Double.valueOf(k.toString());
    }
    public void addAddSpecialPersonListener(ActionListener listener)
    {
        btnthem.addActionListener(listener);
    }
    public void addEditSpecialPersonListener(ActionListener listener)
    {
        btnsua.addActionListener(listener);
    }
    public void addDeleteSpecialPersonListener(ActionListener listener)
    {
        btnxoa.addActionListener(listener);
    }
    public void addClearSpecialPersonListener(ActionListener listener)
    {
        btnlammoi.addActionListener(listener);
    }
    public void addSearchSpecialPersonListener(ActionListener listener)
    {
        btntimkiem.addActionListener(listener);
    }
    public void addcancelSpecialPersonListener(ActionListener listener)
    {
        btnhuy.addActionListener(listener);
    }
    public void addSortByOpeningDateListener(ActionListener listener)
    {
        btnsxhoso.addActionListener(listener);
    }
    public void addSortByNameListener(ActionListener listener)
    {
        btnsxten.addActionListener(listener);
    }
    public void addSortByYearListener(ActionListener listener)
    {
        btnsxnamsinh.addActionListener(listener);
    }
    public void addSearchDialogListener(ActionListener listener)
    {
        btnSearchDialog.addActionListener(listener);
    }
    public void addlistSpecialPersonSelectionListener(ListSelectionListener listener)
    {
        tableSpecialPerson.getSelectionModel().addListSelectionListener(listener);
        
    }
    public void addSearchDialogSpecialPersonListener(ActionListener listener)
    {
        btnSearchDialog.addActionListener(listener);
    }
    public void addImageSpecialPersonListener(ActionListener listener)
    {
        btnthemanh.addActionListener(listener);
    }
    public void addCancelSearchDialogListener(ActionListener listener)
    {
        btnCancelSearchDialog.addActionListener(listener);
    }

//    public void addStatisticListener(ActionListener listener)
//    {
//        btnthongke.addActionListener(listener);
//    }
    public void addStatisticAgeListener(ActionListener listener)
    {
        btnStatisticAge.addActionListener(listener);
    }
    public void addStaticTypeListener(ActionListener listener)
    {
        btnStatisticType.addActionListener(listener);
    }
     public void addStaticUndoListener(ActionListener listener)
    {
        btnStatisticUndo.addActionListener(listener);
    }
     //
     public void addbBackListener(ActionListener listener)
    {
        btnBack.addActionListener(listener);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser BirthdayChooser;
    private javax.swing.JCheckBox CheckBoxadress;
    private javax.swing.JCheckBox CheckBoxname;
    private javax.swing.JCheckBox CheckBoxyear;
    private javax.swing.JComboBox<String> ComboBoxType;
    private com.toedter.calendar.JDateChooser OpeningDateChooser;
    private javax.swing.JDialog SearchDialog;
    private javax.swing.JFrame StatisticView;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnCancelSearchDialog;
    private javax.swing.JButton btnSearchDialog;
    private javax.swing.JButton btnStatisticAge;
    private javax.swing.JButton btnStatisticType;
    private javax.swing.JButton btnStatisticUndo;
    private javax.swing.JButton btnhuy;
    private javax.swing.JButton btnlammoi;
    private javax.swing.JButton btnsua;
    private javax.swing.JButton btnsxhoso;
    private javax.swing.JButton btnsxnamsinh;
    private javax.swing.JButton btnsxten;
    private javax.swing.JButton btnthem;
    private javax.swing.JButton btnthemanh;
    private javax.swing.JButton btnthongke;
    private javax.swing.JButton btntimkiem;
    private javax.swing.JButton btnxoa;
    private javax.swing.JTextField fieldid;
    private javax.swing.JTextField fieldname;
    private javax.swing.JTextField fieldquequan;
    private javax.swing.JTextField fieldsearch;
    private javax.swing.JTextField fieldsum;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblChart;
    private javax.swing.JLabel lblImage;
    private javax.swing.JLabel lblTable;
    private javax.swing.JTable tableSpecialPerson;
    private javax.swing.JTable tableStatistic;
    // End of variables declaration//GEN-END:variables
}
