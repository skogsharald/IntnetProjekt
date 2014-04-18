package kth.intnet.projekt.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ludde on 2014-03-06.
 */
public class UserList {
    private User[] users;
    private ArrayList<User> uArrayList;

    public UserList(){
        uArrayList = new ArrayList<User>();
    }

    public void convertToArrayList() {
        for(User u: users){
            uArrayList.add(u);
        }
    }

    public void addUser(User u){
        uArrayList.add(u);
    }
    public User[] getUserList() {
        return users;
    }

    public void setUserList(User[] userList) {
        this.users = users;
    }

    public ArrayList<User> getUserArrayList(){
        return uArrayList;
    }
}
