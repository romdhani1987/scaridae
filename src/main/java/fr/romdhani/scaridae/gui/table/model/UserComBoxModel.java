package fr.romdhani.scaridae.gui.table.model;

import fr.romdhani.scaridae.core.orm.UserAccount;

import javax.swing.*;
import java.util.ArrayList;

public class UserComBoxModel extends DefaultComboBoxModel {
    private ArrayList<UserAccount> users;

    public UserComBoxModel(){
        super();

        users = new ArrayList<UserAccount>();
    }

    public UserComBoxModel(UserAccount[] users){
        super();

        this.users = new ArrayList<UserAccount>();

        for(UserAccount string : users){
            this.users.add(string);
        }
    }

    protected ArrayList<UserAccount> getUsers(){
        return users;
    }

    public String getSelectedString(){
        return (String)getSelectedItem();
    }

    @Override
    public Object getElementAt(int index) {
        return users.get(index).getLogin();
    }

    @Override
    public int getSize() {
        return users.size();
    }

    @Override
    public int getIndexOf(Object element) {
        return users.indexOf(element);
    }

}
