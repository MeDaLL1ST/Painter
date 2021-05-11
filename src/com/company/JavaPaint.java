package com.company;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class JavaPaint extends JPanel implements ActionListener, ItemListener {

    private int brushSize = 10;

    public JavaPaint() {

        super(new BorderLayout());
        createGUI();
    }

    public static void main(String[] args) {

        JFrame frame = new JFrame("Java Painter");
        frame.setBackground(Color.white);
        frame.add(new JavaPaint());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setPreferredSize(new Dimension(500, 550));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    private void createGUI() {
        PaintPanel paintPanel = new PaintPanel();
        this.add(paintPanel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
    }

    private class PaintPanel extends JPanel implements MouseMotionListener {

        Image bufferImage;
        Graphics backgroundGraphics;

        public PaintPanel() {
            this.setPreferredSize(new Dimension(500, 400));
            this.setSize(new Dimension(500, 400));
            this.addMouseMotionListener(this);
        }

        public void mouseMoved(MouseEvent e) {
        }

        public void mouseDragged(MouseEvent e) {

            int x = e.getX();
            int y = e.getY();

            if (SwingUtilities.isLeftMouseButton(e)) {

                brushSize = 10;
                backgroundGraphics.setColor(Color.green);
                backgroundGraphics.fillOval(x - brushSize / 2, y - brushSize / 2, brushSize, brushSize);
                repaint();

            } else {

                brushSize = 20;
                backgroundGraphics.setColor(Color.WHITE);
                backgroundGraphics.fillOval(x - brushSize, y - brushSize, brushSize, brushSize);
                repaint();

            }
        }

        private void createBufferedImage() {
            Dimension d = getSize();

            if (bufferImage == null) {
                bufferImage = createImage(d.width, d.height);
            }
        }

        public void update(Graphics g) {
            g.drawImage(bufferImage, 0, 0, this);
        }

        public void paint(Graphics g) {
            createBufferedImage();
            backgroundGraphics = bufferImage.getGraphics();
            update(g);
        }
    }
}
