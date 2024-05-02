/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dd.qldc.dao;

import com.dd.qldc.entity.User;

/**
 *
 * @author ASUS_PC
 */
public class Checklogin {
    public boolean checkUser(User user)
    {
        if("admin".equals(user.getUsername())&&"admin".equals(user.getPassword()))
        {
            return true;
        }
        return false;
    }
}
