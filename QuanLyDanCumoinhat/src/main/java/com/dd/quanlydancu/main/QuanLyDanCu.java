/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.dd.quanlydancu.main;

import com.dd.qldc.controller.Logincontroller;
import com.dd.qldc.controller.ResidentController;
import com.dd.qldc.view.Loginview;
import com.dd.qldc.view.ResidentView;
import com.dd.qldc.view.Loginview;
import java.awt.EventQueue;

/**
 *
 * @author Hoàng Đăng
 */
public class QuanLyDanCu {

    public static void main(String[] args) {
           EventQueue.invokeLater(new Runnable() {  
        public void run() {
            Loginview login=new Loginview();
            Logincontroller cc=new Logincontroller(login);
        //ResidentView residentview=new ResidentView();
        //ResidentController controller=new ResidentController(residentview);
        //hiển thị màn hình
       cc.showLoginview();
        
        }
          });
    }
}
