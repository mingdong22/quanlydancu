/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dd.qldc.controller;

/**
 *
 * @author ASUS_PC
 */
import com.dd.qldc.dao.Checklogin;
import com.dd.qldc.entity.User;
import com.dd.qldc.view.Loginview;
import com.dd.qldc.view.Quanlydoituongdacbietview;
import com.dd.qldc.view.ResidentView;
import com.dd.qldc.entity.User;
import com.dd.qldc.view.Quanlydoituongdacbietview;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class Logincontroller {
    private Checklogin checklogin;
    private Loginview loginview;
    private Quanlydoituongdacbietview quanlydoituongdacbietview;
    private ResidentView residentview;

    public Logincontroller(Loginview view) {
        this.loginview = view;
        this.checklogin = new Checklogin();
        view.addLoginListener(new LoginListener());
        view.addEnter(new EnterListener()); // Thêm dòng này để kích hoạt sự kiện khi nhấn Enter
       
    }

    public void showLoginview() {
        loginview.setVisible(true);
    }

  class LoginListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        User user = loginview.getuser();
        if (user.getUsername().isEmpty() || user.getPassword().isEmpty()) {
            loginview.showMessage("Vui lòng nhập tên đăng nhập và mật khẩu.");
        } else {
            if (checklogin.checkUser(user)) {
                residentview = new ResidentView();
                ResidentController cc = new ResidentController(residentview);
                cc.showManagerView();
                loginview.setVisible(false);
            } else {
                loginview.showMessage("Tên đăng nhập hoặc mật khẩu không đúng.");
            }
        }
    }
}

    // Xử lý sự kiện khi nhấn Enter trên trường nhập mật khẩu
     
    class EnterListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            User user = loginview.getuser();
            if (checklogin.checkUser(user)) {
                residentview = new ResidentView();
                ResidentController cc = new ResidentController(residentview);
                cc.showManagerView();
                loginview.setVisible(false);
            } else {
                loginview.showMessage("Tên đăng nhập hoặc mật khẩu không đúng.");
            }
        }
    }
   
  
     
}
