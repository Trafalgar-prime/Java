package com.example.auth;

public class User {
    public String name;
    public int power;

    public void addPrivilege(int privilege){
        power |= privilege;
    }

    public void removePrivilege(int privilege){
        power &= ~privilege;
    }

    public boolean hasPrivilege(int privilege){
        return (power & privilege) == privilege;
    }

    public void printPrivileges(){
        IO.println("Privilegi di " + name + ":");
        if (hasPrivilege(Privilege.READ)) IO.println("-READ");
        if (hasPrivilege(Privilege.WRITE)) IO.println("-WRITE");
        if (hasPrivilege(Privilege.EXECUTE)) IO.println("-EXECUTE");
        if (hasPrivilege(Privilege.ADMIN)) IO.println("-ADMIN");
        if (hasPrivilege(Privilege.DELETE)) IO.println("-DELETE");
    }
}
