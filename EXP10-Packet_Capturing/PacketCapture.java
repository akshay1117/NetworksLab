import java.io.BufferedReader;
import java.io.InputStreamReader;

public class PacketCapture {
    public static void main(String[] args) {
        System.out.println("Note: Native raw sockets (SOCK_RAW) are not supported directly in standard Java.");
        System.out.println("Typically, packet capturing in Java relies on third-party libraries like pcap4j (which wraps libpcap).");
        System.out.println("Alternatively, we can invoke system tools like 'tcpdump' directly from Java.\n");
        
        System.out.println("Executing: tcpdump -c 5 (Capturing 5 packets...)");
        System.out.println("Note: this might require sudo privileges to work correctly.\n");
        
        try {
            Process process = Runtime.getRuntime().exec(new String[]{"tcpdump", "-c", "5"});
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            
            while ((line = errorReader.readLine()) != null) {
                System.out.println("ERR: " + line);
            }
            
            process.waitFor();
            System.out.println("\nPacket capture complete.");
        } catch (Exception e) {
            System.out.println("Failed to capture packets. Make sure you have tcpdump installed and have sufficient permissions.");
            e.printStackTrace();
        }
    }
}
