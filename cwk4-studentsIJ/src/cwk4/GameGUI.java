package cwk4;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * Provide a GUI interface for the game
 *
 * @author A.A.Marczyk
 * @version 20/10/23
 */

public class GameGUI {
    public static void main(String[] args) {
        GameGUI gui = new GameGUI();
    }

    private WIN gp = new SpaceWars("Horatio");
    private JFrame myFrame = new JFrame("Game GUI");

    private JTextArea listing = new JTextArea();
    private JLabel codeLabel = new JLabel();
    private JButton fightBtn = new JButton("Fight");
    private JPanel eastPanel = new JPanel();

    public GameGUI() {
        makeFrame();
        makeMenuBar(myFrame);
    }

    /**
     * Create the main frame's menu bar.
     */
    private void makeMenuBar(JFrame frame) {
        JMenuBar menubar = new JMenuBar();
        frame.setJMenuBar(menubar);

        // create the Forces menu
        JMenu forcesMenu = new JMenu("Forces");
        menubar.add(forcesMenu);

        JMenuItem listASFleetItem = new JMenuItem("List ASFleet");
        listASFleetItem.addActionListener(new ListASFleetHandler());
        forcesMenu.add(listASFleetItem);

        JMenuItem activateForceItem = new JMenuItem("Activate a Force");
        activateForceItem.addActionListener(new ActivateForceHandler());
        forcesMenu.add(activateForceItem);

        JMenuItem recallForceItem = new JMenuItem("Recall a Force");
        recallForceItem.addActionListener(new RecallForceHandler());
        forcesMenu.add(recallForceItem);

        // create the Battles menu
        JMenu battlesMenu = new JMenu("Battles");
        menubar.add(battlesMenu);

        JMenuItem listBattlesItem = new JMenuItem("List all battles");
        listBattlesItem.addActionListener(new ListBattlesHandler());
        battlesMenu.add(listBattlesItem);
    }

    /**
     * Create the Swing frame and its content.
     */
    private void makeFrame() {
        myFrame.setLayout(new BorderLayout());
        myFrame.add(listing, BorderLayout.CENTER);
        listing.setVisible(false);
        myFrame.add(eastPanel, BorderLayout.EAST);

        // set panel layout and add components
        eastPanel.setLayout(new GridLayout(4, 1));
        eastPanel.add(fightBtn);
        fightBtn.addActionListener(new FightBtnHandler());
        fightBtn.setVisible(true);

        // add View State and Clear buttons to eastPanel
        JButton viewStateBtn = new JButton("View State");
        viewStateBtn.addActionListener(new ViewStateBtnHandler());
        eastPanel.add(viewStateBtn);

        JButton clearBtn = new JButton("Clear");
        clearBtn.addActionListener(new ClearBtnHandler());
        eastPanel.add(clearBtn);

        // building is done - arrange the components and show
        myFrame.pack();
        myFrame.setVisible(true);
    }

    /**
     * Handler for List ASFleet menu item
     */
    private class ListASFleetHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            listing.setVisible(true);
            String xx = gp.getASFleet();
            listing.setText(xx);
        }
    }

    /**
     * Handler for Activate a Force menu item
     */
    private class ActivateForceHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String name = JOptionPane.showInputDialog("Enter force name: ");
            String code = JOptionPane.showInputDialog("Enter force code: ");
            int result = gp.activateForce(name);
            JOptionPane.showMessageDialog(myFrame, "Force activation result: " + result);
        }
    }

    /**
     * Handler for Recall a Force menu item
     */
    private class RecallForceHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String name = JOptionPane.showInputDialog("Enter force name: ");
            gp.recallForce(name);
            JOptionPane.showMessageDialog(myFrame, "Force recalled successfully");
        }
    }

    /**
     * Handler for List all battles menu item
     */
    private class ListBattlesHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            listing.setVisible(true);
            String xx = gp.getAllBattles();
            listing.setText(xx);
        }
    }

    /**
     * Handler for Fight button
     */
    private class FightBtnHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String result = gp.getAllBattles();
            JOptionPane.showMessageDialog(myFrame, result);
        }
    }

    /**
     * Handler for View State button
     */
    private class ViewStateBtnHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String xx = gp.getASFleet();
            codeLabel.setText(xx);
            codeLabel.setVisible(true);
            myFrame.pack();
        }
    }

    /**
     * Handler for Clear button
     */
    private class ClearBtnHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            listing.setText("");
            codeLabel.setText("");
            listing.setVisible(false);
            codeLabel.setVisible(false);
            myFrame.pack();
        }
    }
}
