/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import java.net.Inet6Address;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sumit
 */
public class ipv6 {
    public static void main(String[] args) {
        try {
            byte[] add= Inet6Address.getLocalHost().getAddress();
            String ad=new String(add);
            System.out.println(ad);
        } catch (UnknownHostException ex) {
            Logger.getLogger(ipv6.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
