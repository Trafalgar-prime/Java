import com.example.privilege.Privilege;
import com.example.privilege.User;

void main() {
    User utente = new User();
    utente.name = "Dave";
    utente.power = Privilege.READ | Privilege.WRITE;
    /*
    utente.addPrivilege(2);
    utente.addPrivilege(4);
    utente.addPrivilege(1);
    utente.addPrivilege(8);
    utente.addPrivilege(16);
    */
    IO.println("\n" + utente.hasPrivilege(2) + "\n");

    utente.printPrivilege();
}