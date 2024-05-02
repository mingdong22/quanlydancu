
package com.dd.qldc.controller;

import com.dd.qldc.dao.*;
import com.dd.qldc.controller.ResidentController;

import com.dd.qldc.view.ResidentView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MainController {
    private ResidentView residentView;
    
    //
   
    //
    
    //
     class ChooseResidentListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) 
        {
            residentView = new ResidentView();
            ResidentController residentController = new ResidentController(residentView);
            residentController.showManagerView();
           
        }
    }

     //cái này để sau
     /*
     
     class ChooseSpecialPersonListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) 
        {
            managerView = new ManagerView();
            SpecialPersonController managerController = new SpecialPersonController(managerView);
            managerController.showManagerView();
            mainView.setVisible(false);
        }
    }
     */
    
}
