///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//package Logic;
//
//import java.net.Inet6Address;
//import java.net.InetAddress;
//
///**
// *
// * @author sumit
// */
//public class ipv6 {
//   public static void main(String[] args) throws Exception {
//    // args[0] == generate-loopback generates serial data for loopback if
//    // args[0] == generateAll generates serial data for interfaces with an
//    // IPV6 address binding
//
//    if (args.length != 0) {
//
//        if (args[0].equals("generate-loopback")) {
//
//            generateSerializedInet6AddressData(Inet6Address.getByAddress(
//                    InetAddress.getLoopbackAddress().getHostName(),
//                    LOOPBACKIPV6ADDRESS, LOOPBACK_SCOPE_ID), System.out,
//                    true);
//
//        } else {
//            generateAllInet6AddressSerializedData();
//        }
//    } else {
//        runTests();
//    }
//} 
//}
