package com.company;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Calendar {
    private JPanel PanelMain;
    private JComboBox monthBox;
    private JComboBox dayBox;
    private JComboBox yearBox;
    private JTextField taskField;
    private JButton btnComplete;
    private JButton btnEdit;
    private JButton btnRemove;
    private JButton btnAdd;
    private JList taskList;
    private JList compList;

    public Calendar() {
        DefaultListModel<tasks> model = new DefaultListModel<>();
        DefaultListModel<tasks> modelComp = new DefaultListModel<>();


        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                int i = 0;
                if(taskField.getText().equals("")){
                    String message = "Give a name to the task!";
                    JOptionPane.showMessageDialog(new JFrame(), message, "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
                else {
                    model.add(i, new tasks(taskField.getText(), monthBox.getSelectedItem().toString(), dayBox.getSelectedItem().toString(), yearBox.getSelectedItem().toString()));
                    i++;
                    taskList.setModel(model);
                    taskList.revalidate();
                    taskField.setText("");
                }
            }
        });
        btnComplete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                int selectedIndex = taskList.getSelectedIndex();
                if (selectedIndex != -1) {
                    int i = 0;
                    String selected = taskList.getSelectedValue().toString();
                    modelComp.add(i, new tasks(selected, monthBox.getSelectedItem().toString(), dayBox.getSelectedItem().toString(), yearBox.getSelectedItem().toString()));
                    model.remove(selectedIndex);

                }
                else{
                    String message = "Select task to complete";
                    JOptionPane.showMessageDialog(new JFrame(), message, "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
                compList.setModel(modelComp);
                taskList.setModel(model);
            }
        });
        btnRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                int selectedIndex = taskList.getSelectedIndex();
                if(selectedIndex != -1){
                    model.remove(selectedIndex);
                    taskList.clearSelection();
                }
                else{
                    String message = "Select task to remove!";
                    JOptionPane.showMessageDialog(new JFrame(), message, "Error",
                            JOptionPane.ERROR_MESSAGE);
                }

                taskList.setModel(model);
                taskList.revalidate();
                taskList.repaint();


            }
        });
        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int selectedIndex = taskList.getSelectedIndex();
                if(selectedIndex != -1){
                    model.set(selectedIndex,new tasks(taskField.getText(), monthBox.getSelectedItem().toString(), dayBox.getSelectedItem().toString(), yearBox.getSelectedItem().toString()));
                    taskField.setText("");
                }
                else{
                    String message = "Select task to edit!";
                    JOptionPane.showMessageDialog(new JFrame(), message, "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        taskList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent listSelectionEvent) {
                tasks t = (tasks) taskList.getSelectedValue();
                int selectedIndex = taskList.getSelectedIndex();
                if(selectedIndex != -1) {
                    monthBox.setSelectedItem(t.getTaskMonth());
                    dayBox.setSelectedItem(t.getTaskDay());
                    yearBox.setSelectedItem(t.getTaskYear());
                }


            }
        });
        compList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent listSelectionEvent) {
                tasks t = (tasks) compList.getSelectedValue();
                int selectedIndex = compList.getSelectedIndex();
                if(selectedIndex != -1) {
                    monthBox.setSelectedItem(t.getTaskMonth());
                    dayBox.setSelectedItem(t.getTaskDay());
                    yearBox.setSelectedItem(t.getTaskYear());
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Calendar");
        frame.setContentPane(new Calendar().PanelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(800, 600);
        frame.setVisible(true);
    }


    private class tasks{
        String taskName;
        String taskMonth;
        String taskDay;
        String taskYear;

        public tasks(String taskName, String taskMonth, String taskDay, String taskYear){
            this.taskName = taskName;
            this.taskMonth = taskMonth;
            this.taskDay = taskDay;
            this.taskYear = taskYear;
        }

        public String getTaskName() {
            return taskName;
        }

        public void setTaskName(String taskName) {
            this.taskName = taskName;
        }

        public String getTaskMonth() {
            return taskMonth;
        }

        public void setTaskMonth(String taskMonth) {
            this.taskMonth = taskMonth;
        }

        public String getTaskDay() {
            return taskDay;
        }

        public void setTaskDay(String taskDay) {
            this.taskDay = taskDay;
        }

        public String getTaskYear() {
            return taskYear;
        }

        public void setTaskYear(String taskYear) {
            this.taskYear = taskYear;
        }

        @Override
        public String toString() {
            return taskName;
        }

    }
}
