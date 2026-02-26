package com.example.auth;

public class Main {
    public static void main(String[] args) {
        User user = new User();
        user.name = "Dave";
        user.power = Privilege.READ | Privilege.WRITE;
        user.printPrivileges();

        IO.println("Aggiungo il privilegio DELETE");
        user.addPrivilege(Privilege.DELETE);
        user.printPrivileges();

        IO.println("Ha EXECUTE? " + user.hasPrivilege(Privilege.EXECUTE));

        IO.println("Rimuovo il privilegio WRITE");
        user.removePrivilege(Privilege.WRITE);
        user.printPrivileges();
    }
}
