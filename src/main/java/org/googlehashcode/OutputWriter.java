package org.googlehashcode;

import org.googlehashcode.domain.Output;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by magiccrafter on 2/23/2017.
 */
public class OutputWriter {

    public static void writeToFile(String filename, Output output) throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter(filename));

        pw.write(output.serversNumber + "\n");

        output.cacheServers.entrySet().forEach(e -> {
            int serverId = e.getKey();
            List<Integer> videos = e.getValue();

            String serverInfoLine = serverId + "";
            for (int videoId : videos) {
                serverInfoLine += " " + videoId;
            }
            serverInfoLine += "\n";
            pw.write(serverInfoLine);
        });

        pw.close();
    }
}
