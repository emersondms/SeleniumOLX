package system;

public class Connection {

    public static boolean isInternetConnected() {
        String host = "www.olx.com.br";
        String cmd;

        try {
            if (System.getProperty("os.name").startsWith("Windows"))
                cmd = "ping -n 1 " + host;
            else
                cmd = "ping -c 1 " + host;

            Process process = Runtime.getRuntime().exec(cmd);
            process.waitFor();

            if (process.exitValue() == 0)
                return true;
        } catch(Exception ex) {
            ex.printStackTrace();
        }

        return false;
    }
}
